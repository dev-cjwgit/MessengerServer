package Connector;

import Connector.Server.WorldTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerCommand extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton TimerStopButton;

    public ServerCommand() {
        TimerStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WorldTimer.stopSchedule(textField1.getText());
                    JOptionPane.showMessageDialog(null, "타이머 중지에 성공하였습니다.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "타이머 중지에 실패하였습니다.", "중지실패", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    public static void main() {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("ServerCommand");
            frame.setContentPane(new ServerCommand().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 700);
            frame.setVisible(true);
        });
    }
}
