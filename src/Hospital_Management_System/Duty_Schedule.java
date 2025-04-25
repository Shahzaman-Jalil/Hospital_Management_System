package Hospital_Management_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Duty_Schedule extends JFrame {
    public Duty_Schedule() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 940, 490);
        add(panel);

        JLabel titleLabel = new JLabel("Duty Schedule", SwingConstants.CENTER);
        titleLabel.setBounds(0, 8, 980, 70);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(titleLabel);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 80, 910, 300); // Adjusted scroll pane size to fit within the panel
        panel.add(scrollPane);

        try {
            coon c = new coon();
            String q = "SELECT * FROM attendance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton button = new JButton("BACK");
        button.setBounds(410, 400, 120, 30);
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
        setSize(950, 500);
        setLayout(null);
        setLocation(150, 130);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Duty_Schedule();
    }
}
