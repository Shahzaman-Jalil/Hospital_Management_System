package Hospital_Management_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class All_Patients_info extends JFrame {

    All_Patients_info() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 880, 490);
        add(panel);

        JLabel For = new JLabel("Patient Info");
        For.setBounds(370, 20, 286, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 860, 350); // Adjusted scroll pane size to fit within the panel
        panel.add(scrollPane);

        try {
            coon c = new coon();
            String q = "select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton button = new JButton("Back");
        button.setBounds((panel.getWidth() - 120) / 2, 440, 120, 30); // Center the Back button horizontally at the bottom
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setUndecorated(true);
        setSize(900, 540); // Adjusted frame size to fit the panel and components
        setLocation(300, 140);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new All_Patients_info();
    }
}
