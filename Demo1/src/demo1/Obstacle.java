package demo1;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private String colour;
    private boolean isCollision;
    private ObstacleType type;
    private int scoreImpact;

    public Obstacle(int x, int y, int width, int height, String colour, ObstacleType type, int scoreImpact) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.isCollision = false;
        this.type = type;
        this.scoreImpact = scoreImpact;
    }

    public static Obstacle createObstacle(int width, int height, Obstacle[] obstacles) {
    Random random = new Random();
    int x = random.nextInt(width - 50); // Tạo vị trí x ngẫu nhiên
    int y = random.nextInt(height - 50); // Tạo vị trí y ngẫu nhiên
    int w = random.nextInt(30) + 20; // Tạo kích thước chiều rộng ngẫu nhiên từ 20 đến 50
    int h = random.nextInt(30) + 20; // Tạo kích thước chiều cao ngẫu nhiên từ 20 đến 50
    String colour = getRandomColour();
    ObstacleType type = getRandomType();
    int scoreImpact = random.nextInt(10) - 5;

    // Kiểm tra xem chướng ngại vật có chồng lên nhau hay không
    for (Obstacle obstacle : obstacles) {
        if (obstacle != null && x < obstacle.getX() + obstacle.getWidth() && x + w > obstacle.getX() &&
                y < obstacle.getY() + obstacle.getHeight() && y + h > obstacle.getY()) {
            return createObstacle(width, height, obstacles); // Tạo lại chướng ngại vật nếu chồng lên nhau
        }
    }

    if (x < 0) x = 0;
    if (y < 0) y = 0;

    return new Obstacle(x, y, w, h, colour, type, scoreImpact);
}
    private static String getRandomColour() {
        String[] colours = { "green", "blue", "yellow", "purple"};
        Random random = new Random();
        return colours[random.nextInt(colours.length)];
    }

    private static ObstacleType getRandomType() {
        ObstacleType[] types = ObstacleType.values();
        Random random = new Random();
        return types[random.nextInt(types.length)];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColour() {
        return colour;
    }

    public boolean isCollision() {
        return isCollision;
    }

    public ObstacleType getType() {
        return type;
    }

    public int getScoreImpact() {
        return scoreImpact;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public void setType(ObstacleType type) {
        this.type = type;
    }

    public void setScoreImpact(int scoreImpact) {
        this.scoreImpact = scoreImpact;
    }

    public boolean checkCollision(int x, int y) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            return true;
        }
        return false;
    }

    public void increaseScore() {
        this.scoreImpact += 10;
    }

    public void decreaseScore() {
        this.scoreImpact -= 10;
    }

    private Color getColor() {
        switch (getColour()) {
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.BLUE;
            case "yellow":
                return Color.YELLOW;
            case "purple":
                return new Color(128, 0, 128); // màu tím
            default:
                return Color.BLACK;
        }
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public enum ObstacleType {
        WALL, FOOD, POWER_UP, BONUS;
    }
}