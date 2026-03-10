package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import Entity.*;

public class LogInFrame extends JFrame implements ActionListener {
	User user;
    File usersFile;
    Scanner usersScanner;
	JLabel header, unameLabel, passwordLabel, imgLabel;
	JButton loginButton, signUpButton, backButton;
	JTextField unameTextField;
	JPasswordField passwordField;
	ImageIcon img;
	JDialog dialog;
	JPanel panel;
	public LogInFrame() {
		this.setTitle("Log In");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setLayout(null);



		header = new JLabel(
				"<html><center> " +
						"<h1><span style='color:#17A2B8;'>ToDo</span> List</h1>" +
						"</center></html>"
		);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBounds(400, 30, 300, 100);
		header.setFont(new Font("Arial", Font.BOLD, 35));
		panel.add(header);

		unameLabel = new JLabel("Username");
		unameLabel.setBounds(375, 150, 100, 50);
		unameLabel.setVerticalAlignment(SwingConstants.CENTER);
		unameLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(unameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(375, 225, 100, 50);
		passwordLabel.setVerticalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(passwordLabel);

		unameTextField = new JTextField();
		unameTextField.setBounds(500, 150, 250, 50);
		unameTextField.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(unameTextField);

		passwordField = new JPasswordField();
		passwordField.setBounds(500, 225, 250, 50);
		passwordField.setFont(new Font("Arial", Font.BOLD, 18));
		passwordField.setEchoChar('*');
		panel.add(passwordField);

		loginButton = new JButton("LogIn");
		loginButton.setBounds(550, 310, 150, 40);
		loginButton.setFont(new Font("Arial", Font.BOLD, 18));
		loginButton.setBackground(new Color(0, 0, 180));
		loginButton.setForeground(Color.CYAN);
		loginButton.setBorderPainted(false);
		loginButton.setBackground(new Color(0x007BFF));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(this);
		panel.add(loginButton);

		signUpButton = new JButton("SignUp");
		signUpButton.setBounds(670, 420, 90, 40);
		signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
		signUpButton.setBackground(new Color(0x28A745));
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setFocusPainted(false);
		signUpButton.setBorderPainted(false);
		signUpButton.addActionListener(this);
		panel.add(signUpButton);

		backButton = new JButton("Back");
		backButton.setBounds(500, 420, 90, 40);
		backButton.setFont(new Font("Arial", Font.BOLD, 14));
		backButton.setBackground(new Color(0x6C757D));
		backButton.setForeground(Color.WHITE);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(this);
		panel.add(backButton);

		img = new ImageIcon("./Resources/Image/LoginBg.jpg");
		Image image = img.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		imgLabel = new JLabel(new ImageIcon(image));
		imgLabel.setBounds(0, 0, 800, 600);
		panel.add(imgLabel);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae) {
		boolean flag = false;
		if(ae.getSource() == loginButton) {
            try {
                usersFile = new File("./Resources/Text/Users.txt");
                usersFile.createNewFile();
                usersScanner = new Scanner(usersFile);

                while (usersScanner.hasNextLine()) {
					String uname = unameTextField.getText();
					String password = new String(passwordField.getPassword());
                    String[] line = usersScanner.nextLine().split(" ---> ");
					if (line[0].equals(uname) && line[1].equals(password) ) {
						flag = true;
						user = new User(line[2], line[0], line[3], line[4], line[1]);
					}
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

			if (flag == true) {
				this.setVisible(false);
				new MainFrame(user).setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password\nPlease try again");
			}
		}
		else if(ae.getSource() == signUpButton) {
			this.setVisible(false);
			new SignUpFrame().setVisible(true);
		}
		else if(ae.getSource() == backButton) {
			this.setVisible(false);
			WelcomeFrame welcomeFrame = new WelcomeFrame();
			welcomeFrame.setVisible(true);
		}
	}
}
