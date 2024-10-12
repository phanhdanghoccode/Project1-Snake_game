package demo1;

public class Player {
    private String name;
    private String password;
    private int score;
    private String info;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0;
        this.info = "";
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}