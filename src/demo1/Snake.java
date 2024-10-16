package demo1;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Snake {
    private int x; // Tọa độ X của đầu rắn
    private int y; // Tọa độ Y của đầu rắn
    private int direction; // Hướng di chuyển
    private int length; // Độ dài của rắn
    private ArrayList<Point> bodyPositions; // Lưu tọa độ của thân rắn
    private ArrayList<ImageIcon> bodyImages; // Lưu các ảnh tĩnh của thân rắn
    private static final int TILE_SIZE = 50; // Kích thước mỗi ô (nên đồng bộ với kích thước hình ảnh)

    // Các hằng số đại diện cho hướng di chuyển
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake() {
        x = 100; // Vị trí khởi đầu
        y = 100; // Vị trí khởi đầu
        direction = RIGHT; // Hướng di chuyển ban đầu
        length = 1; // Bắt đầu với độ dài là 1
        bodyPositions = new ArrayList<>(); // Khởi tạo danh sách tọa độ cho thân rắn
        bodyImages = new ArrayList<>(); // Khởi tạo danh sách hình ảnh cho thân rắn
        bodyPositions.add(new Point(x, y)); // Thêm vị trí ban đầu cho đầu rắn

        // Thêm ảnh mặc định cho rắn
        bodyImages.add(loadDefaultImage()); // Thay đổi phương thức tải ảnh
    }

    // Tạo ảnh mặc định cho rắn (trường hợp không có ảnh cụ thể)
    private ImageIcon loadDefaultImage() {
        // Đường dẫn đến hình ảnh tĩnh
        URL location = getClass().getResource("/demo1/images/c3.jpg"); // Đảm bảo đường dẫn chính xác
        if (location != null) {
            return new ImageIcon(location); // Trả về ảnh tĩnh
        } else {
            System.err.println("Hình ảnh không tồn tại! Đường dẫn: /demo1/images/c3.jpg");
            return null; // Nếu không tìm thấy, trả về null
        }
    }

    // Di chuyển rắn
    public void move() {
        // Lưu tọa độ của phần thân sau
        for (int i = length - 1; i > 0; i--) {
            bodyPositions.set(i, bodyPositions.get(i - 1));
        }

        // Cập nhật tọa độ đầu rắn
        switch (direction) {
            case UP -> y -= TILE_SIZE;
            case DOWN -> y += TILE_SIZE;
            case LEFT -> x -= TILE_SIZE;
            case RIGHT -> x += TILE_SIZE;
        }

        // Cập nhật tọa độ đầu
        bodyPositions.set(0, new Point(x, y));

        // Cập nhật hình ảnh đầu rắn
        if (bodyImages.size() > 0) {
            bodyImages.set(0, loadDefaultImage()); // Đặt lại hình ảnh đầu
        }
    }

    // Vẽ rắn bằng cách sử dụng hình ảnh tĩnh
    public void draw(Graphics g) {
        for (int i = 0; i < length; i++) {
            if (i < bodyImages.size()) {
                Point segment = bodyPositions.get(i);
                int drawX = segment.x; // Tọa độ X của từng khối
                int drawY = segment.y; // Tọa độ Y của từng khối
                bodyImages.get(i).paintIcon(null, g, drawX, drawY); // Vẽ từng phần thân rắn
            }
        }
    }
public void eatPower(Power power) {
        // Tăng điểm số khi ăn vật phẩm
        // Ví dụ: tăng điểm số bằng cách tăng số lượng phần thân của rắn
        // ...
    }
    // Khi rắn ăn food, tăng độ dài và thêm phần thân mới
    public void eatFood(Food food) {
        length++; // Tăng độ dài của rắn
        Point tailPosition = bodyPositions.get(length - 2); // Lấy vị trí của phần thân cuối
        bodyPositions.add(new Point(tailPosition)); // Thêm một đoạn thân mới tại vị trí đó
        bodyImages.add(food.getImageIcon()); // Giả sử food.getImageIcon() trả về ảnh tĩnh
    }

    // Kiểm tra va chạm giữa rắn và vật cản
//    public boolean checkCollision(Obstacle obstacle) {
//        return checkCollision(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
//    }
    public boolean checkCollision(Power power) {
        // Kiểm tra va chạm giữa rắn và vật phẩm
        // Ví dụ: kiểm tra xem đầu rắn có trùng với vị trí của vật phẩm không
        return getXPoints()[0] == power.getX() && getYPoints()[0] == power.getY();
    }
    public boolean DauCanThan() {

        for (int i = 3; i < length; i++) {
            if (i < bodyImages.size()) {
               
                Point segment = bodyPositions.get(i);
                int dx = segment.x; // Tọa độ X của từng khối
                int dy = segment.y; // Tọa độ Y của từng khối
               if(dx==x&& dy==y){
                   return true;
               }
            }
        }
        return false;
    }
//    public boolean checkVatcan(int x1, int y1){
//        return (x==x1&&y==y1);
//    }
    // Kiểm tra va chạm với một tọa độ cụ thể
//    public boolean checkCollision(int x, int y, int width, int height) {
//        return (this.x >= x && this.x < x + width && this.y >= y && this.y < y + height);
//    }

    // Kiểm tra va chạm giữa rắn và food
    public boolean checkCollision(Food food) {
        return x < food.getX() + 20 && x + TILE_SIZE > food.getX() &&
               y < food.getY() + 20 && y + TILE_SIZE > food.getY();
    }

    // Đặt lại trạng thái của rắn khi bắt đầu lại trò chơi
    public void reset() {
        x = 100;
        y = 100;
        length = 1; // Đặt lại độ dài là 1
        bodyPositions.clear(); // Xóa toàn bộ tọa độ cũ
        bodyImages.clear(); // Xóa toàn bộ hình ảnh cũ
        bodyPositions.add(new Point(x, y)); // Thêm tọa độ ban đầu
        bodyImages.add(loadDefaultImage()); // Thêm ảnh mặc định
    }

    // Lấy hướng ngược lại để tránh việc quay ngược rắn
    private int getOppositeDirection(int direction) {
        return switch (direction) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> direction;
        };
    }

    // Đặt hướng di chuyển của rắn
    public void setDirection(int direction) {
        if (direction != getOppositeDirection(this.direction)) {
            this.direction = direction;
        }
    }

    // Trả về độ dài của rắn
    public int getLength() {
        return length;
    }

    // Trả về tọa độ X của rắn
    public int getX() {
        return x;
    }

    // Trả về tọa độ Y của rắn
    public int getY() {
        return y;
    }

    // Trả về chiều rộng của rắn
    public int getWidth() {
        return TILE_SIZE; // Đối với rắn, chiều rộng bằng kích thước ô
    }

    // Trả về chiều cao của rắn
    public int getHeight() {
        return TILE_SIZE; // Đối với rắn, chiều cao bằng kích thước ô
    }

    // Trả về mảng các điểm X của rắn
    public int[] getXPoints() {
        int[] xPoints = new int[length]; // Tạo mảng chứa tọa độ X của các phần thân rắn
        for (int i = 0; i < length; i++) {
            xPoints[i] = bodyPositions.get(i).x; // Tính toán tọa độ X cho mỗi khối
        }
        return xPoints;
    }

    // Trả về mảng các điểm Y của rắn
    public int[] getYPoints() {
        int[] yPoints = new int[length]; // Tạo mảng chứa tọa độ Y của các phần thân rắn
        for (int i = 0; i < length; i++) {
            yPoints[i] = bodyPositions.get(i).y; // Lấy tọa độ Y cho mỗi khối
        }
        return yPoints;
    }

   

   

   
}