package Hospital_Management_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Ambulance extends JFrame {
    Ambulance() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 890, 500);
        add(panel);

        JLabel For = new JLabel("Ambulance Service");
        For.setBounds(320, 20, 286, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 870, 350);
        panel.add(scrollPane);

        try {
            coon c = new coon();
            String q = "select * from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton button = new JButton("BACK");
        button.setBounds(390, 450, 120, 30); // Adjusted to fit at the bottom center of the window
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(900, 540);
        setLayout(null);
        setLocation(230, 170);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ambulance();
    }
}
