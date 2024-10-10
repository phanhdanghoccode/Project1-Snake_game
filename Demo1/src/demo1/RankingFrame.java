package demo1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class RankingFrame extends JFrame {

    private LoginManager loginManager;
    private JTable rankingTable;
    private DefaultTableModel tableModel;

    public RankingFrame(LoginManager loginManager) {
        this.loginManager = loginManager;

        setTitle("Bảng xếp hạng");
        setSize(400, 300);
        setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ này mà không đóng toàn bộ ứng dụng

        // Khởi tạo bảng và model
        String[] columnNames = {"Người chơi", "Điểm số"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rankingTable = new JTable(tableModel);
        rankingTable.setEnabled(false); // Không cho phép chỉnh sửa bảng

        // Tạo bảng cuộn để bao quanh bảng
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        add(scrollPane, BorderLayout.CENTER);

        loadRankingData(); // Tải dữ liệu xếp hạng từ LoginManager

        // Tạo panel cho nút
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Thoát");

        // Thêm hành động cho nút Restart
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng RankingFrame
                dispose();
                // Tạo lại một GameFrame mới để chơi lại
                new GameFrame(loginManager, "Tên người chơi"); // Thay "Tên người chơi" bằng biến tên người chơi thực tế
            }
        });

        // Thêm hành động cho nút Exit
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Thoát toàn bộ ứng dụng
            }
        });

        // Thêm nút vào panel và panel vào frame
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH); // Đặt panel ở phần dưới

        setVisible(true);
    }

    private void loadRankingData() {
        // Lấy dữ liệu người chơi từ LoginManager
        Map<String, Integer> playerData = loginManager.getPlayerData();

        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Duyệt qua danh sách người chơi và thêm vào bảng
        for (Map.Entry<String, Integer> entry : playerData.entrySet()) {
            String playerName = entry.getKey();
            Integer score = entry.getValue();
            tableModel.addRow(new Object[]{playerName, score});
        }
    }
}
