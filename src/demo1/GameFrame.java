package demo1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final LoginManager loginManager;
    private String playerName;
    private int score;
    private JLabel scoreLabel;
    private GamePanel gamePanel;
    private JButton rankingButton;

    public GameFrame(LoginManager loginManager, String playerName) {
        this.loginManager = loginManager;
        this.playerName = playerName;
        score = 0;
        scoreLabel = new JLabel("Score: 0");
        gamePanel = new GamePanel(loginManager, playerName);

        setSize(900, 900);
        setTitle("Màn chơi Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamePanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        
        rankingButton = new JButton("Xem bảng xếp hạng");
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankingFrame(loginManager); // Mở bảng xếp hạng
            }
        });
        rankingButton.setVisible(false);
        add(rankingButton, BorderLayout.NORTH);

        pack();
        setVisible(true);
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + score);
    }

    public void showRankingButton() {
        rankingButton.setVisible(true); // Hiện nút bảng xếp hạng
        revalidate(); // Cập nhật giao diện
        repaint(); // Vẽ lại giao diện
    }

    // Phương thức hiển thị hộp thoại restart/exit
    public void showRestartExitDialog() {
        int option = JOptionPane.showOptionDialog(this, "Bạn muốn làm gì tiếp theo?",
                "Chọn hành động",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"Restart", "Exit"}, null);
        
        if (option == JOptionPane.YES_OPTION) {
            // Nếu chọn Restart, khởi tạo lại Main để đăng nhập lại
            dispose(); // Đóng GameFrame
            new Main(loginManager); // Tạo một phiên bản mới của Main để đăng nhập
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0); // Thoát chương trình
        }
    }
}
