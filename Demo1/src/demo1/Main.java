package demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    private JFrame frame;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private LoginManager loginManager;

    public Main(LoginManager loginManager) {
        this.loginManager = loginManager;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Trò chơi Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());
        

        txtName = new JTextField(20);
        txtPassword = new JPasswordField(20);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnRegister = new JButton("Đăng ký");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        frame.add(new JLabel("Tên người chơi:"));
        frame.add(txtName);
        frame.add(new JLabel("Mật khẩu:"));
        frame.add(txtPassword);
        frame.add(btnLogin);
        frame.add(btnRegister);

        frame.setVisible(true);
    }

    private void login() {
        String name = txtName.getText();
        String password = new String(txtPassword.getPassword());
        if (loginManager.loginPlayer(name, password)) {
            JOptionPane.showMessageDialog(frame, "Đăng nhập thành công!");
            // Mở màn chơi Snake
            new GameFrame(loginManager, name);
            frame.dispose(); // Đóng màn đăng nhập
        } else {
            JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại!");
        }
    }

    private void register() {
        String name = txtName.getText();
        String password = new String(txtPassword.getPassword());
        loginManager.registerPlayer(name, password);
        loginManager.writePlayerInfoToFile();
        JOptionPane.showMessageDialog(frame, "Đăng ký thành công!");
    }

    public static void main(String[] args) {
        LoginManager loginManager = new LoginManager();
        loginManager.readPlayerInfoFromFile();
        new MainMenu(loginManager); // Khởi động màn hình chính
    }
}
