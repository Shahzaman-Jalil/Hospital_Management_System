package Hospital_Management_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_info extends JFrame {
    Employee_info() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 890, 500);
        add(panel);

        // Title Label
        JLabel titleLabel = new JLabel("Employee Info");
        titleLabel.setBounds(350, 20, 200, 30);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        // Table and Scroll Pane
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 870, 350);
        panel.add(scrollPane);

        // Fetch and display data in the table
        try {
            coon c = new coon();
            String q = "select * from EMP_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back Button
        JButton button = new JButton("Back");
        button.setBounds(385, 450, 120, 30); // Adjust button position to be centered horizontally
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame settings
        setUndecorated(true);
        setSize(900, 540); // Adjust frame size
        setLocation(300, 140);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
