package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdatePatientDiagnosis extends JFrame {
    JTextField patientIdField;
    JTextArea diagnosisField;
    JTextArea outputArea;

    public UpdatePatientDiagnosis() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 540);
        add(panel);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(300, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.white);
        panel.add(labelName);

        JLabel label1 = new JLabel("Patient ID:");
        label1.setBounds(200, 80, 100, 25);
        label1.setFont(new Font("Tahoma",Font.BOLD,15));
        label1.setForeground(Color.white);
        panel.add(label1);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(300, 80, 220, 25);
        panel.add(patientIdField);

        JLabel label2 = new JLabel("Diagnosis:");
        label2.setBounds(200, 120, 100, 25);
        label2.setForeground(Color.white);
        label2.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(label2);

        diagnosisField = new JTextArea();
        diagnosisField.setBounds(300, 120, 220, 50);
        diagnosisField.setLineWrap(true);
        diagnosisField.setWrapStyleWord(true);
        JScrollPane diagnosisScrollPane = new JScrollPane(diagnosisField);
        diagnosisScrollPane.setBounds(300, 120, 220, 50);
        panel.add(diagnosisScrollPane);

        JButton button = new JButton("Update Diagnosis");
        button.setBounds(340, 200, 150, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(180, 250, 480, 200);
        panel.add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePatientDiagnosis();
            }
        });

        JButton button1 = new JButton("BACK");
        button1.setBounds(350,470,120,25);
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
        setSize(850, 550);
        setLayout(null);
        setLocation(220, 140);
        setVisible(true);
    }

    private void updatePatientDiagnosis() {
        String patientIdText = patientIdField.getText();
        String diagnosisText = diagnosisField.getText();
        String date = java.time.LocalDate.now().toString();

        if (patientIdText.isEmpty() || diagnosisText.isEmpty()) {
            outputArea.setText("Please enter both Patient ID and Diagnosis.");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            coon c = new coon();
            String q = "SELECT * FROM patient_info WHERE Number = " + patientId;
            ResultSet resultSet = c.statement.executeQuery(q);
            if (resultSet.next()) {
                // Update diagnosis
                String q1 = "INSERT INTO patient_diagnosis (patient_id, diagnosis, diagnosis_date) VALUES (" + patientId + ", '" + diagnosisText + "', '" + date + "')";
                int rowsAffected = c.statement.executeUpdate(q1);
                if (rowsAffected > 0) {
                    outputArea.setText("Diagnosis updated successfully for Patient ID: " + patientId);
                } else {
                    outputArea.setText("Failed to update diagnosis.");
                }
            } else {
                outputArea.setText("No patient found with ID: " + patientId);
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Patient ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error updating diagnosis.");
        }
    }

    public static void main(String[] args) {
        new UpdatePatientDiagnosis();
    }
}
