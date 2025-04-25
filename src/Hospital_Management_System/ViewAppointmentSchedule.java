package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAppointmentSchedule extends JFrame {
    JTextField doctorIdField;
    JTextArea outputArea;

    public ViewAppointmentSchedule() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 540, 490);
        add(panel);

        JLabel For = new JLabel("View Appointment Schedule");
        For.setBounds(135, 10, 286, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel label = new JLabel("Doctor ID:");
        label.setBounds(20, 50, 100, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD,15));
        panel.add(label);

        doctorIdField = new JTextField(20);
        doctorIdField.setBounds(130, 50, 200, 25);
        panel.add(doctorIdField);

        JButton button = new JButton("View Schedule");
        button.setBounds(350, 50, 120, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 90, 480, 300);
        panel.add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSchedule();
            }
        });

        JButton button1 = new JButton("BACK");
        button1.setBounds(200,420,120,25);
        button1.setBackground(Color.black);
        button1.setForeground(Color.white);
        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(550, 500);
        setLayout(null);
        setLocation(400, 160);
        setVisible(true);
    }

    private void viewSchedule() {
        String doctorIdText = doctorIdField.getText();

        if (doctorIdText.isEmpty()) {
            outputArea.setText("Please enter a Doctor ID.");
            return;
        }

        try {
            int doctorId = Integer.parseInt(doctorIdText);
            coon c = new coon();
            String q = "SELECT * FROM appointments WHERE doctor_id = " + doctorId;
            ResultSet resultSet = c.statement.executeQuery(q);

            StringBuilder schedule = new StringBuilder();
            schedule.append("Appointment Schedule for Doctor ID: ").append(doctorId).append("\n\n");

            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                String appointmentDate = resultSet.getString("appointment_date");
                String appointmentTime = resultSet.getString("appointment_time");
                String purpose = resultSet.getString("purpose");

                schedule.append("Patient ID: ").append(patientId).append("\n")
                        .append("Date: ").append(appointmentDate).append("\n")
                        .append("Time: ").append(appointmentTime).append("\n")
                        .append("Purpose: ").append(purpose).append("\n\n");
            }

            if (schedule.length() == 0) {
                outputArea.setText("No appointments found for Doctor ID: " + doctorId);
            } else {
                outputArea.setText(schedule.toString());
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Doctor ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error fetching appointment schedule.");
        }
    }

    public static void main(String[] args) {
        new ViewAppointmentSchedule();
   }
}
