package demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton playButton;
    private LoginManager loginManager;

    public MainMenu(LoginManager loginManager) {
        this.loginManager = loginManager;
        setTitle("Màn hình chính - Trò chơi Snake");
        setSize(1100, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Đặt layout cho JFrame là BorderLayout
        setLayout(new BorderLayout());

        // Đặt ảnh nền
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/demo1/images/Background (2).jpg")));
        background.setLayout(new BorderLayout()); // Đặt layout cho JLabel

        // Tạo nút "Play"
        playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 24)); // Thiết lập phông chữ cho nút
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở màn đăng nhập
                new Main(loginManager);
                dispose(); // Đóng màn hình chính
            }
        });

        // Tạo một JPanel cho nút "Play" và đặt layout là FlowLayout.CENTER để nút nằm chính giữa
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Đặt nền trong suốt cho JPanel để không che ảnh nền
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Đảm bảo nút nằm chính giữa

        // Thêm khoảng cách từ nút đến đáy màn hình
        buttonPanel.add(Box.createVerticalGlue()); // Tạo khoảng cách từ trên xuống
        buttonPanel.add(playButton); // Thêm nút vào panel

        // Thêm khoảng trống 2cm (khoảng 56 pixel) từ dưới lên cho nút
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0)); // Cách biên dưới 2cm (56 pixel)

        // Thêm buttonPanel vào phía dưới cùng của background
        background.add(buttonPanel, BorderLayout.SOUTH);

        // Đặt JLabel (background) làm content pane của JFrame
        setContentPane(background);

        setVisible(true);
    }
}
