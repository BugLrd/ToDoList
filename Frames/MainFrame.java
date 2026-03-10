package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import Entity.*;

public class MainFrame extends JFrame implements ActionListener, WindowListener {
    User user;
    JLabel bannerLabel;
    JButton deleteButton;
    JPanel MainPanel,topPanel,taskPanel,bottomPanel;
    JButton addButton;
    ImageIcon deleteIcon;
    Image image;
    File file;
    FileWriter fw;
    Scanner sc;





    public MainFrame(User user) {
        super("My To Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(this);

        this.user = user;

        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(800, 40));
        MainPanel.add(topPanel, BorderLayout.NORTH);

        bannerLabel = new JLabel("To Do List");
        bannerLabel.setBounds(350, 15, 800, 50);
        bannerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        bannerLabel.setForeground(new Color(51, 51, 51));
        topPanel.setBackground(new Color(220, 220, 255)); // light blue

        topPanel.add(bannerLabel);

        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(800, 40));
        bottomPanel.setBackground(new Color(220, 220, 255));
        MainPanel.add(bottomPanel, BorderLayout.SOUTH);

        addButton = new JButton("Add Task");
        addButton.setPreferredSize(new Dimension(800, 40));
        addButton.addActionListener(this);
        addButton.setBackground(new Color(100, 149, 237)); // cornflower blue
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        bottomPanel.add(addButton);

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setAlignmentY(TOP_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        MainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(MainPanel);
        checkForPastTasks();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addTasks(false, "");
        }
    }

    public void windowOpened(WindowEvent we) {}

    public void windowClosing(WindowEvent we) {
        saveToFiles();
    }
    public void windowClosed(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowActivated(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}

    public void addTasks(boolean checked, String text) {
        JTextField textField;
        JCheckBox checkBox;
        JPanel taskRow = new JPanel();
        taskRow.setOpaque(true);
        taskRow.setBackground(new Color(0x96C9B7));
        taskRow.setLayout(new BoxLayout(taskRow, BoxLayout.X_AXIS));
        taskRow.setMaximumSize(new Dimension(800, 40));
        taskRow.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        textField = new JTextField();
        textField.setText(text);
        textField.setEnabled(!checked);
        textField.setDisabledTextColor(Color.GRAY);
        textField.setBackground(new Color(240, 240, 240));
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setMaximumSize(new Dimension(800, 30));
        textField.setPreferredSize(new Dimension(630, 30));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(Color.BLUE);
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        checkBox = new JCheckBox();
        checkBox.setSelected(checked);
        checkBox.setBackground(new Color(150, 201, 183));
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                boolean checked = checkBox.isSelected();
                textField.setEnabled(!checked);
                if (checked) {
                    textField.setDisabledTextColor(Color.GRAY);
                    textField.setBackground(new Color(240, 240, 240));
                } else {
                    textField.setBackground(Color.WHITE);
                }
            }
        });
        taskRow.add(checkBox);

        taskRow.add(Box.createRigidArea(new Dimension(10, 0)));
        taskRow.add(textField);

        deleteIcon = new ImageIcon("./Resources/Image/trash.png");
        image = deleteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        deleteButton = new JButton(new ImageIcon(image));
        deleteButton.setBounds(600, 0, 50, 40);
        deleteButton.setOpaque(true);
        deleteButton.setBackground(new Color(0x34543));
        deleteButton.setPreferredSize(new Dimension(50, 40));
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                taskPanel.remove(taskRow);
                taskPanel.revalidate();
                taskPanel.repaint();
                saveToFiles();
            }
        });
        taskRow.add(Box.createRigidArea(new Dimension(10, 0)));
        taskRow.add(deleteButton);

        taskPanel.add(taskRow);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private void saveToFiles() {
        try {
            file = new File("./Resources/Text/" + user.getUsername() + ".txt");
            file.createNewFile();
            fw = new FileWriter(file);
            for (Component comp : taskPanel.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel taskRow = (JPanel) comp;
                    for (Component subComp : taskRow.getComponents()) {
                        if (subComp instanceof JCheckBox) {
                            boolean checked = ((JCheckBox) subComp).isSelected();
                            if (checked) {
                                fw.write("[checked]" + "\t");
                            }
                            else {
                                fw.write("[unchecked]" + "\t");
                            }
                        }
                        if (subComp instanceof JTextField) {
                            String text = ((JTextField) subComp).getText();
                            if (text.equals("")) {
                                fw.write("[EMPTY]" + "\n");
                            }
                            else {
                                fw.write(text + "\n");
                            }
                        }
                    }
                }
            }
            fw.flush();
            fw.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void checkForPastTasks() {
        try {
            file = new File("./Resources/Text/" + user.getUsername() + ".txt");
            file.createNewFile();
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\t", 2);
                boolean checked = parts[0].equals("[checked]");
                String text = parts[1].equals("[EMPTY]") ? "" : parts[1];
                addTasks(checked, text);
            }
            sc.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}


