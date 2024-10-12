/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo1;

import java.awt.*;
import javax.swing.ImageIcon;


public class Power {
    private int x, y; // Vị trí của vật phẩm
    private int duration = 30; // Thời gian tồn tại của vật phẩm (20 giây)
    private int scoreImpact = 50; // Điểm số tăng thêm khi ăn vật phẩm
    private boolean isAvailable = false; // Trạng thái của vật phẩm (có sẵn hay không)
    private GamePanel gamePanel;
    private ImageIcon imageVat; // Hình ảnh thực phẩm là ảnh tĩnh
    private ImageIcon imageVat2;
    
    public Power(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        loadRandomImage();
    }

    public void appear() {
    isAvailable = true;
    // Request a repaint of the GamePanel
    gamePanel.repaint();
}


    public void disappear() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getScoreImpact() {
        return scoreImpact;
    }

    public void updateDuration() {
        duration--;
        if (duration <= 0) {
            disappear();
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    private static final String[] CHARACTER_IMAGES = {
        "/demo1/images/grapes.png",
        };
 private void loadRandomImage() {

        String imageName = CHARACTER_IMAGES[(int) (Math.random() * CHARACTER_IMAGES.length)];
        imageVat = new ImageIcon(getClass().getResource(imageName));
        imageVat2 = new ImageIcon(getClass().getResource(imageName));

    }
   public void draw(Graphics g) {
    if (isAvailable) {
        // Vẽ vật phẩm tại vị trí (x, y)
//        g.setColor(Color.RED);
//        g.fillOval(x, y, 40, 40);
imageVat.paintIcon(null, g, x, y);
    }
}
}    