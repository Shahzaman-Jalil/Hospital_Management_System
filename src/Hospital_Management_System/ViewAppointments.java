package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAppointments extends JFrame {
    JTextField patientIdField;
    JTextArea outputArea;

    public ViewAppointments() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 540, 490);
        add(panel);

        JLabel For = new JLabel("View Appointment Schedule");
        For.setBounds(140, 10, 286, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel label = new JLabel("Patient ID:");
        label.setBounds(20, 70, 100, 25);
        label.setFont(new Font("Tahoma",Font.BOLD,15));
        label.setForeground(Color.white);
        panel.add(label);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(130, 70, 200, 25);
        panel.add(patientIdField);

        JButton button = new JButton("View Appointments");
        button.setBounds(350, 70, 150, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 110, 480, 300);
        panel.add(scrollPane);

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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAppointments();
            }
        });

//        setUndecorated(true);
        setSize(550, 500);
        setLayout(null);
        setLocation(500, 250);
        setVisible(true);
    }

    private void viewAppointments() {
        String patientIdText = patientIdField.getText();

        if (patientIdText.isEmpty()) {
            outputArea.setText("Please enter a Patient ID.");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            coon c = new coon();
            String q = "SELECT * FROM appointments WHERE patient_id = " + patientId;
            ResultSet resultSet = c.statement.executeQuery(q);

            StringBuilder appointments = new StringBuilder();
            appointments.append("Appointments for Patient ID: ").append(patientId).append("\n\n");

            while (resultSet.next()) {
                String doctorId = resultSet.getString("doctor_id");
                String appointmentDate = resultSet.getString("appointment_date");
                String appointmentTime = resultSet.getString("appointment_time");
                String purpose = resultSet.getString("purpose");

                appointments.append("Doctor ID: ").append(doctorId).append("\n")
                        .append("Date: ").append(appointmentDate).append("\n")
                        .append("Time: ").append(appointmentTime).append("\n")
                        .append("Purpose: ").append(purpose).append("\n\n");
            }

            if (appointments.length() == 0) {
                outputArea.setText("No appointments found for Patient ID: " + patientId);
            } else {
                outputArea.setText(appointments.toString());
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Patient ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error fetching appointments.");
        }
    }

    public static void main(String[] args) {
        new ViewAppointments();
   }
}
