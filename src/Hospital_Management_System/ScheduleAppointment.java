package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleAppointment extends JFrame {
    JTextField patientIdField;
    JTextField doctorIdField;
    JTextField appointmentDateField;
    JTextField appointmentTimeField;
    JTextArea purposeArea;
    JTextArea outputArea;

    public ScheduleAppointment() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 540);
        add(panel);

        JLabel labelName = new JLabel("SCHEDULE APPOINTMENT");
        labelName.setBounds(280, 10, 350, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.white);
        panel.add(labelName);

        JLabel label = new JLabel("Patient ID:");
        label.setBounds(200, 75, 100, 25);
        label.setFont(new Font("Tahoma",Font.BOLD,15));
        label.setForeground(Color.white);
        panel.add(label);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(400, 75, 200, 25);
        panel.add(patientIdField);

        JLabel label1 = new JLabel("Doctor ID:");
        label1.setBounds(200, 115, 100, 25);
        label1.setFont(new Font("Tahoma",Font.BOLD,15));
        label1.setForeground(Color.white);
        panel.add(label1);

        doctorIdField = new JTextField(20);
        doctorIdField.setBounds(400, 115, 200, 25);
        panel.add(doctorIdField);

        JLabel label2 = new JLabel("Date (YYYY-MM-DD):");
        label2.setBounds(200, 155, 250, 25);
        label2.setFont(new Font("Tahoma",Font.BOLD,15));
        label2.setForeground(Color.white);
        panel.add(label2);

        appointmentDateField = new JTextField(20);
        appointmentDateField.setBounds(400, 155, 150, 25);
        panel.add(appointmentDateField);

        JLabel label3 = new JLabel("Time (HH:MM):");
        label3.setBounds(200, 195, 150, 25);
        label3.setFont(new Font("Tahoma",Font.BOLD,15));
        label3.setForeground(Color.white);
        panel.add(label3);

        appointmentTimeField = new JTextField(20);
        appointmentTimeField.setBounds(400, 195, 200, 25);
        panel.add(appointmentTimeField);

        JLabel label4 = new JLabel("Purpose:");
        label4.setBounds(200, 235, 100, 25);
        label4.setFont(new Font("Tahoma",Font.BOLD,15));
        label4.setForeground(Color.white);
        panel.add(label4);

        purposeArea = new JTextArea();
        purposeArea.setLineWrap(true);
        purposeArea.setWrapStyleWord(true);
        JScrollPane purposeScrollPane = new JScrollPane(purposeArea);
        purposeScrollPane.setBounds(400, 235, 200, 50);
        panel.add(purposeScrollPane);

        JButton button = new JButton("Schedule");
        button.setBounds(350, 295, 150, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBounds(200, 342, 480, 120);
        panel.add(outputScrollPane);

        JButton button1 = new JButton("BACK");
        button1.setBounds(370,480,120,25);
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
                scheduleAppointment();
            }
        });

        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(230, 140);
        setVisible(true);
    }

    private void scheduleAppointment() {
        String patientIdText = patientIdField.getText();
        String doctorIdText = doctorIdField.getText();
        String appointmentDateText = appointmentDateField.getText();
        String appointmentTimeText = appointmentTimeField.getText();
        String purposeText = purposeArea.getText();

        if (patientIdText.isEmpty() || doctorIdText.isEmpty() || appointmentDateText.isEmpty() || appointmentTimeText.isEmpty() || purposeText.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            int doctorId = Integer.parseInt(doctorIdText);
            coon c = new coon();
            String q = "SELECT * FROM patient_info WHERE Number = " + patientId;
            ResultSet patientResultSet = c.statement.executeQuery(q);
            if (patientResultSet.next()) {
                // Check if the doctor exists
                String q2 = "SELECT * FROM doctors WHERE doctor_id = " + doctorId;
                ResultSet doctorResultSet = c.statement.executeQuery(q2);
                if (doctorResultSet.next()) {
                    // Insert appointment
                    String q1 = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, purpose) VALUES (" +
                            patientId + ", " + doctorId + ", '" + appointmentDateText + "', '" + appointmentTimeText + "', '" + purposeText + "')";
                    int rowsAffected = c.statement.executeUpdate(q1);
                    if (rowsAffected > 0) {
                        outputArea.setText("Appointment scheduled successfully for Patient ID: " + patientId);
                    } else {
                        outputArea.setText("Failed to schedule appointment.");
                    }
                } else {
                    outputArea.setText("No doctor found with ID: " + doctorId);
                }
            } else {
                outputArea.setText("No patient found with ID: " + patientId);
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Patient ID or Doctor ID. Please enter numeric values.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error scheduling appointment.");
        }
    }

    public static void main(String[] args) {
        new ScheduleAppointment();
    }
}
