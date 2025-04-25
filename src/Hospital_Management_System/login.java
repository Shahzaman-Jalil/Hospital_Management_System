package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {
    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;
    JComboBox<String> userRoleComboBox;

    login() {
        setBackgroundPanel();

        JLabel heading = new JLabel("Hospital Management System");
        heading.setBounds(40, 10, 700, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(new Color(0x2554C7));
        add(heading);

        JLabel nameLabel = new JLabel("User Name:");
        nameLabel.setBounds(40, 80, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(nameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 130, 100, 30);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(passwordLabel);

        textField = new JTextField();
        textField.setBounds(150, 80, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 130, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(jPasswordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(40, 180, 100, 30);
        roleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(roleLabel);

        String[] roles = {"Doctor", "Patient", "Nurse", "Admin"};
        userRoleComboBox = new JComboBox<>(roles);
        userRoleComboBox.setBounds(150, 180, 150, 30);
        userRoleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(userRoleComboBox);

        Image imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login_icon.png")).getImage();
        Image i1 = imageIcon.getScaledInstance(370, 310, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320, 20, 400, 300);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(40, 230, 260, 40);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(40, 280, 260, 40);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        setSize(770, 400);
        setLocation(400, 270);
        setLayout(null);
        setVisible(true);
    }

    private void setBackgroundPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icon/bg.jpg")).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                coon c = new coon();
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());
                String role = (String) userRoleComboBox.getSelectedItem();
                    switch (role) {
                        case "Doctor":
                            coon c2 = new coon();
                            String user1 = textField.getText();
                            String Pass= jPasswordField.getText();
                            String q1 = "SELECT * FROM doctorlogin WHERE ID = '"+user1+"' AND PW = '"+Pass+"'";



                            ResultSet resultSet1 = c.statement.executeQuery(q1);
                            if (resultSet1.next()){
                                new Doctor_login();
                                setVisible(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Invalid");
                            }
                            break;
                        case "Patient":
                            coon c3 = new coon();
                            String user2 = textField.getText();
                            String Pass2= jPasswordField.getText();
                            String q2 = "SELECT * FROM patientlogin WHERE ID = '" + user2 + "' AND PW= '" + Pass2 + "'";

                            ResultSet resultSet2 = c.statement.executeQuery(q2);
                            if (resultSet2.next()){
                                new Patient_login();
                                setVisible(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Invalid");
                            }

                            break;
                        case "Nurse":
                            coon c4 = new coon();
                            String user3 = textField.getText();
                            String Pass3= jPasswordField.getText();
                            String q3 ="SELECT * FROM Nurselogin WHERE ID='"+user3+"' AND PW='"+Pass3+"'";

                            ResultSet resultSet3 = c.statement.executeQuery(q3);
                            if (resultSet3.next()){
                                new nurse_login();
                                setVisible(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Invalid");
                            }
                            break;
                        case "Admin":
                            coon c5 = new coon();
                            String user4 = textField.getText();
                            String Pass4= jPasswordField.getText();
                            String q4 = "SELECT * FROM login WHERE ID = '" + user4 + "' AND PW = '" + Pass4 + "'";

                            ResultSet resultSet4 = c.statement.executeQuery(q4);
                            if (resultSet4.next()){
                                new Reception();
                                setVisible(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Invalid");
                            }
                            break;
                    }
                    setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            System.exit(10);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
