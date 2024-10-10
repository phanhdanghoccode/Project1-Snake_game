package demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener {
    private Snake snake;
    private Food food;
    private final LoginManager loginManager;
    private final String playerName;
    private int score;
    private int speed = 200; // Tốc độ di chuyển của rắn
    private Timer timer;
    private Obstacle[] obstacles; // Mảng chướng ngại vật
    private boolean isGameOver = false; // Trạng thái game
    private Power power; 

    public GamePanel(LoginManager loginManager, String playerName) {
        this.loginManager = loginManager;
        this.playerName = playerName;
        snake = new Snake();
        food = new Food(power); // Khởi tạo Food với một đối tượng Power
        score = 0;
        setPreferredSize(new Dimension(900, 900)); // Thiết lập kích thước GamePanel
        obstacles = new Obstacle[0]; // Khởi tạo mảng chướng ngại vật ban đầu

        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        // Khởi tạo timer để di chuyển rắn
        timer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    snake.move();
                    checkCollision();
                    repaint(); // Vẽ lại GamePanel
                }
            }
        });
        timer.start(); // Bắt đầu timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g); // Vẽ nền
        snake.draw(g); // Vẽ rắn
        food.draw(g); // Vẽ thực phẩm
        for (Obstacle obstacle : obstacles) {
            if (obstacle != null) {
                obstacle.draw(g); // Vẽ chướng ngại vật
            }
        }
        // Hiển thị điểm số
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);
        // Vẽ khung của game
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    // Vẽ nền
    private void drawBackground(Graphics g) {
        int tileSize = 50; // Kích thước mỗi ô
        Color lightGreen = new Color(202, 255, 112); // Màu xanh lá nhạt
        Color darkGreen = new Color(162, 205, 90); // Màu xanh lá đậm

        for (int row = 0; row < getHeight() / tileSize; row++) {
            for (int col = 0; col < getWidth() / tileSize; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(lightGreen); // Màu xanh lá nhạt
                } else {
                    g.setColor(darkGreen); // Màu xanh lá đậm
                }
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }

    // Kiểm tra va chạm
    private void checkCollision() {
        // Kiểm tra va chạm giữa rắn và thực phẩm
        if (snake.checkCollision(food)) {
            snake.eatFood(food); // Nối thêm phần thân với ảnh từ thực phẩm
            food.reset(); // Tạo thực phẩm mới
            score++; // Tăng điểm số
            loginManager.updatePlayerScore(playerName, score); // Cập nhật điểm
            loginManager.writePlayerInfoToFile(); // Ghi thông tin người chơi vào file

            // Cập nhật điểm số trên GameFrame
            GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
            gameFrame.updateScore(score);

            // Tăng tốc độ của rắn
            speed -= 5;
            if (speed < 10) speed = 10; // Đảm bảo tốc độ không quá nhanh
            timer.setDelay(speed);

            // Tăng số lượng chướng ngại vật sau mỗi lần ăn thực phẩm
            if (score % 5 == 0) { // Tăng chướng ngại sau mỗi 5 điểm
                Obstacle[] newObstacles = new Obstacle[obstacles.length + 1];
                System.arraycopy(obstacles, 0, newObstacles, 0, obstacles.length);
                newObstacles[obstacles.length] = Obstacle.createObstacle(800, 600, obstacles); // Tạo chướng ngại mới
                obstacles = newObstacles;
            }
            repaint(); // Vẽ lại GamePanel
        }

        // Kiểm tra va chạm với biên của trò chơi
        if (snake.getXPoints()[0] < 0 || snake.getXPoints()[0] >= getWidth() || 
            snake.getYPoints()[0] < 0 || snake.getYPoints()[0] >= getHeight()) {
            endGame();
            return;
        }

        // Kiểm tra va chạm với các chướng ngại vật
        for (Obstacle obstacle : obstacles) {
            if (snake.checkCollision(obstacle)) {
                if (obstacle.getType() == Obstacle.ObstacleType.WALL) {
                    endGame();
                    return;
                } else if (obstacle.getType() == Obstacle.ObstacleType.POWER_UP) {
                    score += obstacle.getScoreImpact();
                    loginManager.updatePlayerScore(playerName, score);
                    loginManager.writePlayerInfoToFile();

                    // Cập nhật điểm số trên GameFrame
                    GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
                    gameFrame.updateScore(score);
                }
            }
        }
    }

    // Kết thúc trò chơi
    private void endGame() {
        isGameOver = true;
        timer.stop(); // Dừng timer
        int option = JOptionPane.showOptionDialog(this, "Game over! Điểm số của bạn là: " + score,
                "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{"Xem bảng xếp hạng", "Thoát"}, null);

        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        if (option == 0) { // Nếu chọn "Xem bảng xếp hạng"
            gameFrame.showRankingButton(); // Hiện nút bảng xếp hạng
        } else { // Nếu chọn "Thoát"
            System.exit(0); // Thoát game
        }
    }

    // Xử lý sự kiện bàn phím
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isGameOver) { // Chỉ cho phép điều khiển khi game chưa kết thúc
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP) {
                snake.setDirection(Snake.UP);
            } else if (keyCode == KeyEvent.VK_DOWN) {
                snake.setDirection(Snake.DOWN);
            } else if (keyCode == KeyEvent.VK_LEFT) {
                snake.setDirection(Snake.LEFT);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                snake.setDirection(Snake.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
