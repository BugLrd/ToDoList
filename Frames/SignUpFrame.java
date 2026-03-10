package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import Entity.*;

public class SignUpFrame extends JFrame implements ActionListener, FocusListener {
    UserManager userManager;
    File usersFile;
    Scanner userFileScanner;
    JLabel imgLabel, header, haveAnAccount;
    JButton createAccount, loginButton;
    JTextField unameTextField,nameTextField, emailTextField;
    JPasswordField passwordTextField, confirmPasswordTextField;
    ImageIcon img;
    String[] gender;
    JComboBox<String> genderComboBox;
    JPanel panel;
    public SignUpFrame() {
        this.setTitle("Sign Up");
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userManager = new UserManager();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 800);

        header = new JLabel("Create new User");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(300, 50, 200, 50);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(header);

        nameTextField = new JTextField("Name");
        nameTextField.setBounds(300, 110, 250, 40);
        nameTextField.setForeground(Color.GRAY);
        nameTextField.addFocusListener(this);
        panel.add(nameTextField);


        unameTextField = new JTextField("Username");
        unameTextField.setBounds(300, 160, 250, 40);
        unameTextField.setForeground(Color.GRAY);
        unameTextField.addFocusListener(this);
        panel.add(unameTextField);

        gender = new String[]{ "male", "female", "others" };
        genderComboBox = new JComboBox<>();
        genderComboBox.addItem("Select gender");
        for (String s : gender) {
            genderComboBox.addItem(s);
        }
        genderComboBox.setBounds(300, 210, 250, 40);
        genderComboBox.addFocusListener(this);
        panel.add(genderComboBox);

        emailTextField = new JTextField("Email");
        emailTextField.setBounds(300, 260, 250, 40);
        emailTextField.setForeground(Color.GRAY);
        emailTextField.addFocusListener(this);
        panel.add(emailTextField);

        passwordTextField = new JPasswordField("Password");
        passwordTextField.setBounds(300, 310, 250, 40);
        passwordTextField.setEchoChar((char) 0);
        passwordTextField.setForeground(Color.GRAY);
        passwordTextField.addFocusListener(this);
        panel.add(passwordTextField);

        confirmPasswordTextField = new JPasswordField("Confirm Password");
        confirmPasswordTextField.setBounds(300, 360, 250, 40);
        confirmPasswordTextField.setEchoChar((char) 0);
        confirmPasswordTextField.setForeground(Color.GRAY);
        confirmPasswordTextField.addFocusListener(this);
        panel.add(confirmPasswordTextField);

        createAccount = new JButton("Create Account");
        createAccount.setBounds(350, 420, 150, 40);
        createAccount.setBackground(new Color(0x28A745));
        createAccount.setForeground(Color.white);
        createAccount.addActionListener(this);
        panel.add(createAccount);

        haveAnAccount = new JLabel("Already have an account..");
        haveAnAccount.setBounds(330, 480, 200, 40);
        haveAnAccount.setForeground(Color.GRAY);
        panel.add(haveAnAccount);

        loginButton = new JButton("<html><u>Login</u></html>");
        loginButton.setBounds(463, 489, 50, 20);
        loginButton.setForeground(Color.BLUE);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        img = new ImageIcon("./Resources/Image/SignUpBg.jpg");
        Image image = img.getImage().getScaledInstance(600, 800, Image.SCALE_SMOOTH);
        imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(0, 0, 600, 800);
        panel.add(imgLabel);

        this.add(panel);
        SwingUtilities.invokeLater(() -> {
            header.requestFocusInWindow();
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createAccount) {
            if (!checkName()) {JOptionPane.showMessageDialog(null, "Full Name must be bigger than 6 characters");}
            else if (!checkUname()) {JOptionPane.showMessageDialog(null, "Username must be bigger than 6 characters\nand can only contain alphanumeric characters");}
            else if (!checkGender()) {JOptionPane.showMessageDialog(null, "You must select a gender");}
            else if (!checkEmail()) {JOptionPane.showMessageDialog(null, "Please enter a valid email");}
            else if (!checkPassword()) {JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters\nand At least one number and special character");}
            else if (!checkConfirmPassword()) {JOptionPane.showMessageDialog(null, "Password does not match with Confirm Password");}
            else if (!doesUnameExist()) {JOptionPane.showMessageDialog(null, "Username already exists");}
            else {
                String name = nameTextField.getText();
                String username = unameTextField.getText();
                String gender = genderComboBox.getSelectedItem().toString();
                String email = emailTextField.getText();
                String password = new String(passwordTextField.getPassword());
                User user = new User(name, username, gender, email, password);
                userManager.addUser(user);
                JOptionPane.showMessageDialog(null, "User created successfully");
                this.setVisible(false);
                new LogInFrame().setVisible(true);
            }
        }
        else if (ae.getSource() == loginButton) {
            this.setVisible(false);
            new LogInFrame().setVisible(true);
        }


    }

    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == nameTextField) {
            if (nameTextField.getText().equals("Name")) {
                nameTextField.setText("");
            }
        }
        else if (fe.getSource() == unameTextField) {
            if (unameTextField.getText().equals("Username")) {
                unameTextField.setText("");
            }
        }
        else if (fe.getSource() == emailTextField) {
            if (emailTextField.getText().equals("Email")) {
                emailTextField.setText("");
            }
        }
        else if (fe.getSource() == passwordTextField) {
            char[] password = passwordTextField.getPassword();
            String pass = new String(password);
            if (pass.equals("Password")) {
                passwordTextField.setText("");
                passwordTextField.setEchoChar('*');
            }

        }
        else if (fe.getSource() == confirmPasswordTextField) {
            char[] password = confirmPasswordTextField.getPassword();
            String pass = new String(password);
            if (pass.equals("Confirm Password")) {
                confirmPasswordTextField.setText("");
                confirmPasswordTextField.setEchoChar('*');
            }
        }
        else if (fe.getSource() == genderComboBox) {
            if (genderComboBox.getItemAt(0).equals("Select gender")) {
                genderComboBox.removeItemAt(0);
            }
        }
    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == nameTextField) {
            if (nameTextField.getText().isEmpty()) {
                nameTextField.setText("Name");
            }
        }
        else if (fe.getSource() == unameTextField) {
            if (unameTextField.getText().isEmpty()) {
                unameTextField.setText("Username");
            }
        }
        else if (fe.getSource() == emailTextField) {
            if (emailTextField.getText().isEmpty()) {
                emailTextField.setText("Email");
            }
        }
        else if (fe.getSource() == passwordTextField) {
            char[] password = passwordTextField.getPassword();
            String pass = new String(password);
            if (pass.isEmpty()) {
                passwordTextField.setText("Password");
                passwordTextField.setEchoChar((char) 0);
            }
        }
        else if (fe.getSource() == confirmPasswordTextField) {
            char[] password = confirmPasswordTextField.getPassword();
            String pass = new String(password);
            if (pass.isEmpty()) {
                confirmPasswordTextField.setText("Confirm Password");
                confirmPasswordTextField.setEchoChar((char) 0);
            }
        }
    }

    public boolean checkName() {
        String name = nameTextField.getText();
        return name.contains(" ") && name.length() >= 6;
    }

    public boolean checkUname() {
        String uname = unameTextField.getText();
        boolean flag = true;
        if (uname.equals("Username") || uname.length() < 6) {
            flag = false;
        }
        else {
            for (char c : uname.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    flag = false;
                }
            }
        }

        return flag;
    }

    public boolean doesUnameExist() {
        String uname = unameTextField.getText();
        try {
            usersFile = new File("./Resources/Text/Users.txt");
            usersFile.createNewFile();
            userFileScanner = new Scanner(usersFile);

            while (userFileScanner.hasNextLine()) {
                String userName = userFileScanner.nextLine().split(" ---> ")[0];
                if (userName.equals(uname)) {
                    return false;
                }
            }
            userFileScanner.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return true;
    }

    public boolean checkGender() {
        String gender = genderComboBox.getSelectedItem().toString();
        return !gender.equals("Select gender");
    }

    public boolean checkEmail() {
        String email = emailTextField.getText();
        boolean flag = true;
        if (email.equals("Email") || email.indexOf("@") < 5) {
            flag = false;
        }
        else {
            if (!email.substring(email.indexOf('@')).equals("@gmail.com")) {
                flag = false;
            }
        }

        return flag;
    }

    public boolean checkPassword() {
        char[] pass = passwordTextField.getPassword();
        String password = new String(pass);

        boolean containsDigit = false;
        boolean containsSign = false;
        boolean flag = true;
        if (password.equals("Password") || password.length() < 8) {
            flag = false;
        }
        else {
            for (char c : pass) {
                if (Character.isDigit(c)) {
                    containsDigit = true;
                }
                else if (!Character.isLetterOrDigit(c)) {
                    containsSign = true;
                }
            }

            if (!containsDigit || !containsSign) {
                flag = false;
            }
        }

        return flag;
    }

    public boolean checkConfirmPassword() {
        char[] pass = passwordTextField.getPassword();
        char[] conPass = confirmPasswordTextField.getPassword();

        return Arrays.equals(pass, conPass);
    }
}
