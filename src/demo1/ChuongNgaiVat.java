package demo1;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ChuongNgaiVat {

    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    private ArrayList<ImageIcon> obstacleImages; // Danh sách hình ảnh của từng chướng ngại
    private Food food;
    private Power power;

    // Danh sách các hình ảnh chướng ngại
    private static final String[] CHARACTER_IMAGES = {
        "/demo1/images/wall1.png",
        "/demo1/images/wall2.png",
        "/demo1/images/wall3.png",
        "/demo1/images/tree1.png"
    };

    public ChuongNgaiVat() {
        x = new ArrayList<>();
        y = new ArrayList<>();
        obstacleImages = new ArrayList<>(); // Lưu ảnh tương ứng với từng chướng ngại
        ThemVatCan(); // Khởi tạo chướng ngại đầu tiên
    }

    // Thêm chướng ngại vật mới
    public void ThemVatCan() {
        int random_x = (int) (Math.random() * 12) * 50; // Random vị trí theo grid 50x50
        int random_y = (int) (Math.random() * 12) * 50;

        // Đảm bảo vị trí chướng ngại không trùng với vị trí của thức ăn
        if (random_x != new Food(power).x && random_y != new Food(power).y) {
            x.add(random_x);
            y.add(random_y);
            loadRandomImage(); // Tải ngẫu nhiên hình ảnh cho chướng ngại
        }
    }

    // Tải ngẫu nhiên một hình ảnh từ danh sách cho chướng ngại
    private void loadRandomImage() {
        String imageName = CHARACTER_IMAGES[(int) (Math.random() * CHARACTER_IMAGES.length)];
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        obstacleImages.add(image); // Thêm hình ảnh vào danh sách
    }

    // Vẽ tất cả chướng ngại vật
    public void draw(Graphics g) {
        for (int i = 0; i < x.size(); i++) {
            obstacleImages.get(i).paintIcon(null, g, x.get(i), y.get(i)); // Vẽ chướng ngại tại tọa độ tương ứng
        }
    }

    // Kiểm tra va chạm giữa rắn và chướng ngại vật
    public boolean checkVatCan(int x_ran, int y_ran) {
        for (int i = 0; i < x.size(); i++) {
            if (x_ran == x.get(i) && y_ran == y.get(i)) {
                return true; // Có va chạm với chướng ngại
            }
        }
        return false;
    }

    // Lấy danh sách tọa độ X của chướng ngại
    public ArrayList<Integer> getX() {
        return x;
    }

    // Lấy danh sách tọa độ Y của chướng ngại
    public ArrayList<Integer> getY() {
        return y;
    }
}
