package demo1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginManager {
    private HashMap<String, Player> players;

    public LoginManager() {
        players = new HashMap<>();
        readPlayerInfoFromFile(); // Đọc thông tin người chơi khi khởi tạo
    }

    // Đăng nhập người chơi
    public boolean loginPlayer(String name, String password) {
        Player player = players.get(name);
        return player != null && player.getPassword().equals(password);
    }

    // Đăng ký người chơi mới
    public boolean registerPlayer(String name, String password) {
        if (!players.containsKey(name)) {
            Player player = new Player(name, password);
            players.put(name, player);
            writePlayerInfoToFile(); // Cập nhật file khi đăng ký người chơi mới
            return true; // Đăng ký thành công
        }
        return false; // Người chơi đã tồn tại
    }

    // Cập nhật điểm của người chơi
    public void updatePlayerScore(String name, int score) {
        Player player = players.get(name);
        if (player != null) {
            player.updateScore(score);
            writePlayerInfoToFile(); // Cập nhật điểm số vào file txt sau khi cập nhật
        }
    }

    // Ghi thông tin người chơi vào file
    public void writePlayerInfoToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player_info.txt"))) {
            for (Player player : players.values()) {
                writer.write(player.getName() + "," + player.getPassword() + "," + player.getInfo() + "," + player.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi viết thông tin người chơi vào file: " + e.getMessage());
        }
    }

    // Đọc thông tin người chơi từ file
    public void readPlayerInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("player_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Player player = new Player(parts[0], parts[1]);
                    player.setInfo(parts[2]);
                    player.updateScore(Integer.parseInt(parts[3]));
                    players.put(player.getName(), player);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc thông tin người chơi từ file: " + e.getMessage());
        }
    }

    // Lấy dữ liệu người chơi và điểm số để hiển thị trong bảng xếp hạng
    public Map<String, Integer> getPlayerData() {
        Map<String, Integer> playerData = new HashMap<>();
        for (Player player : players.values()) {
            playerData.put(player.getName(), player.getScore());
        }
        return playerData;
    }
}
