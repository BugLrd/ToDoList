package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeFrame extends JFrame implements ActionListener, MouseListener {
    JLabel welcomeMsg, descriptionMsg, imgLabel;
    JButton button;
    ImageIcon img;
    JPanel panel;

    public WelcomeFrame() {
        this.setTitle("Welcome to ToDo-List");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(800, 600);

        welcomeMsg = new JLabel("<html><h1><span style='color:#007BFF;'>Welcome</span> to <span style='color:#17A2B8;'>ToDo</span> List</h1></html>");
        welcomeMsg.setBounds(250, 40, 500, 150);
        welcomeMsg.setFont(new Font("SansSerif", Font.BOLD, 30));
        panel.add(welcomeMsg);

        descriptionMsg = new JLabel("<html><center>" +
                "<ul style='text-align: left; display: inline-block;'>" +
				"<li>Stay organized and boost your productivity</li>" +
                "<li>Easily manage your tasks</li>" +
                "<li>Set deadlines and reminders</li>" +
                "<li>Organize tasks by priority</li>" +
                "<li>Track your daily progress</li>" +
                "</ul>" +
				"<br>" +
                "<p><i>Start planning your day, one task at a time!</i></p>" +
                "</center></html>"
        );
        descriptionMsg.setBounds(50, 150, 400, 200);
        descriptionMsg.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionMsg.setForeground(Color.DARK_GRAY);
        panel.add(descriptionMsg);

		button = new JButton("Start with ToDo List →"); 
		button.setBounds(100, 365, 250, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
		button.setBackground(new Color(0x17A2B8));
        button.setForeground(new Color(0xE0FFFF));
        button.addActionListener(this);
        button.addMouseListener(this);
		panel.add(button);

        img = new ImageIcon("./Resources/Image/welcome.jpg");
        Image image = img.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(0, 0, 800, 600);
        panel.add(imgLabel);

        this.add(panel);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == button) {
            this.setVisible(false);
            LogInFrame logInFrame = new LogInFrame();
            logInFrame.setVisible(true);
        }
    }

    public void mouseClicked(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == button) {
            button.setBackground(new Color(0x138496));
        }
    }
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == button) {
            button.setBackground(new Color(0x17A2B8));
        }
    }
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
}
