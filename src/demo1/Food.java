package demo1;

import javax.swing.*;
import java.awt.*;

public class Food {
    public int x; // Tọa độ X của food
    public int y; // Tọa độ Y của food
    private Power power; // Sức mạnh liên kết với food
    private ImageIcon imageIcon; // Hình ảnh thực phẩm là ảnh tĩnh
    

    // Danh sách các nhân vật
    private static final String[] CHARACTER_IMAGES = {
        "/demo1/images/c1.jpg",
        "/demo1/images/c2.jpg",
        "/demo1/images/c4.jpg",
        "/demo1/images/c5.jpg",
        "/demo1/images/c6.jpg",
        "/demo1/images/c7.jpg",
        "/demo1/images/c8.jpg"
    };

    public Food(Power power) {
        this.power = power;
        reset(); // Khởi tạo thực phẩm với vị trí và hình ảnh ngẫu nhiên
    }

    public Power getPower() {
        return power;
    }

    // Đặt lại vị trí của food và chọn ngẫu nhiên một ảnh nhân vật
    public void reset() {
        x = (int) (Math.random() * 12) * 50; // Đặt lại vị trí của food theo grid (800/50 = 16 ô chiều ngang)
        y = (int) (Math.random() * 12) * 50; // Đặt lại vị trí của food theo grid (600/50 = 12 ô chiều dọc)
      
        
        
        loadRandomImage(); // Tải hình ảnh nhân vật ngẫu nhiên
    }

    // Tải ngẫu nhiên một ảnh nhân vật từ danh sách
    private void loadRandomImage() {
        try {
            String imageName = CHARACTER_IMAGES[(int) (Math.random() * CHARACTER_IMAGES.length)];
            imageIcon = new ImageIcon(getClass().getResource(imageName));
        } catch (Exception e) {
            System.err.println("Lỗi khi tải ảnh nhân vật: " + e.getMessage());
            imageIcon = null; // Nếu gặp lỗi, không có ảnh
        }
    }

    // Trả về hình ảnh dưới dạng ImageIcon để thêm vào thân rắn
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    // Vẽ thực phẩm
    public void draw(Graphics g) {
        if (imageIcon != null) {
            imageIcon.paintIcon(null, g, x, y); // Vẽ ảnh nhân vật tại vị trí food
        }
    }

    // Trả về tọa độ X của food
    public int getX() {
        return x;
    }

    // Trả về tọa độ Y của food
    public int getY() {
        return y;
    }
}
