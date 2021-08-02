package Connector;

import Connector.Server.WorldTimer;
import DataBase.DAO;
import UserException.ResultHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static UserException.ResultHandler.*;

public class ServerCommand extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton TimerStopButton;
    private JTextField emailText;
    private JTextField pwText;
    private JTextField nameText;
    private JButton AddAccountButton;
    private JTextField yearText;
    private JTextField monthText;
    private JTextField dayText;
    private JTextField phoneText;

    public ServerCommand() {
        TimerStopButton.addActionListener(e -> {
            try {
                WorldTimer.stopSchedule(textField1.getText());
                JOptionPane.showMessageDialog(null, "타이머 중지에 성공하였습니다.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "타이머 중지에 실패하였습니다.", "중지실패", JOptionPane.ERROR_MESSAGE);

            }
        });
        AddAccountButton.addActionListener(e -> {
            var res = DAO.insertAccount(emailText.getText(), pwText.getText(), nameText.getText(), Integer.parseInt(yearText.getText()), Integer.parseInt(monthText.getText()), Integer.parseInt(dayText.getText()), phoneText.getText(), 0);
            switch (res) {
                case Success -> JOptionPane.showMessageDialog(null, "회원가입을 성공하였습니다.");
                case Fail, DuplicateKey_Err -> JOptionPane.showMessageDialog(null, "회원가입을 실패하였습니다.", "가입실패", JOptionPane.ERROR_MESSAGE);
                default -> JOptionPane.showMessageDialog(null, "회원가입 도중 알 수 없는 에러가 발생하였습니다.", "예외발생", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

    public static void main() {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("ServerCommander by cjw.git@gmail.com");
            frame.setContentPane(new ServerCommand().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 700);
            frame.setVisible(true);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
