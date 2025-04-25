package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mark_Attendance extends JFrame {
    private JTextArea outputArea;
    private JTextField nurseIdField;

    public Mark_Attendance() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 690, 540);
        add(panel);

        JLabel titleLabel = new JLabel("Attendance Marksheet", SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 690, 40);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel nurseIdLabel = new JLabel("Nurse ID:");
        nurseIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        nurseIdLabel.setForeground(Color.white);
        nurseIdLabel.setBounds(20, 60, 100, 25);
        panel.add(nurseIdLabel);

        nurseIdField = new JTextField(20);
        nurseIdField.setBounds(130, 60, 200, 25);
        panel.add(nurseIdField);

        JButton button = new JButton("Mark Attendance");
        button.setBounds(350, 60, 140, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(130, 100, 440, 300); // Adjusted size and position
        panel.add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        JButton button1 = new JButton("BACK");
        button1.setBounds(300, 410, 120, 30); // Adjusted position
        button1.setBackground(Color.black);
        button1.setForeground(Color.white);
        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(700, 480); // Adjusted size
        setLayout(null);
        setLocation(300, 150);
        setVisible(true);
    }

    private void markAttendance() {
        String nurseIdText = nurseIdField.getText();
        if (nurseIdText.isEmpty()) {
            outputArea.setText("Please enter a Nurse ID.");
            return;
        }

        try {
            coon c = new coon();
            int nurseId = Integer.parseInt(nurseIdText);
            String date = java.time.LocalDate.now().toString();

            // Check if attendance already marked for today
            String q = "SELECT * FROM attendance WHERE nurse_id = " + nurseId + " AND date = '" + date + "'";
            ResultSet resultSet = c.statement.executeQuery(q);

            if (resultSet.next()) {
                outputArea.setText("Attendance already marked for today.");
            } else {
                // Insert attendance only if not already present
                String q1 = "INSERT INTO attendance (nurse_id, date, status) VALUES (" + nurseId + ", '" + date + "', 'Present')";
                int rowsAffected = c.statement.executeUpdate(q1);
                if (rowsAffected > 0) {
                    outputArea.setText("Attendance marked successfully for " + date);
                } else {
                    outputArea.setText("Failed to mark attendance.");
                }
            }

        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Nurse ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error marking attendance: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Mark_Attendance();
    }
}
