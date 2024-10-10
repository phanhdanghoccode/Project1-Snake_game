/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo1;

import java.awt.*;
import java.util.Random;

public abstract class Power {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected ObstacleType type;

    public Power(int x, int y, int width, int height, ObstacleType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public abstract void draw(Graphics g);

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

    public ObstacleType getType() {
        return type;
    }

    public enum ObstacleType {
        SPEED_BOOST,
        SCORE_BOOST,
        EXTRA_LIFE
    }

    public static class SpeedBoostPower extends Power {
        public SpeedBoostPower(int x, int y, int width, int height) {
            super(x, y, width, height, ObstacleType.SPEED_BOOST);
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.PINK);
        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + getWidth();
        int y2 = getY() + getHeight() / 2;
        int x3 = getX() + getWidth() / 2;
        int y3 = getY() + getHeight();
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }
}
    public static class ScoreBoostPower extends Power {
    public ScoreBoostPower(int x, int y, int width, int height) {
        super(x, y, width, height, ObstacleType.SCORE_BOOST);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x + (width - 50) / 2, y + (height - 50) / 2, 50, 50);
    }
}

 public static class ExtraLifePower extends Power {
    public ExtraLifePower(int x, int y, int width, int height) {
        super(x, y, width, height, ObstacleType.EXTRA_LIFE);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        int[] xPoints = {x + width / 2, x + width, x + width / 2, x, x + width / 2, x + width / 4, x + width / 2, x + width * 3 / 4};
        int[] yPoints = {y, y + height / 2, y + height, y + height / 2, y + height / 4, y + height / 8, y + height / 4, y + height / 8};
        g.fillPolygon(xPoints, yPoints, 8);
    }
}

    public static Power createRandomPower(int x, int y, int width, int height) {
        ObstacleType type = getRandomObstacleType();

        Power power = null;
        switch (type) {
            case SPEED_BOOST -> power = new SpeedBoostPower(x, y, width, height);
            case SCORE_BOOST -> power = new ScoreBoostPower(x, y, width, height);
            case EXTRA_LIFE -> power = new ExtraLifePower(x, y, width, height);
        }

        return power;
    }

    private static ObstacleType getRandomObstacleType() {
        ObstacleType[] types = ObstacleType.values();
        Random random = new Random();
        return types[random.nextInt(types.length)];
    }
}
    
