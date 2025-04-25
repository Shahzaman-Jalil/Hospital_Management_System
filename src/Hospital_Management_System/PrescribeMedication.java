package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PrescribeMedication extends JFrame {
    JTextField patientIdField;
    JTextField medicationNameField;
    JTextField dosageField;
    JTextArea instructionsArea;
    JTextArea outputArea;

    public PrescribeMedication() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 540);
        add(panel);

        JLabel labelName = new JLabel("PRESCRIBE MEDICATION");
        labelName.setBounds(300, 10, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.white);
        panel.add(labelName);

        JLabel label1 = new JLabel("Patient ID:");
        label1.setBounds(200, 70, 100, 25);
        label1.setFont(new Font("Tahoma",Font.BOLD,15));
        label1.setForeground(Color.white);
        panel.add(label1);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(310, 70, 220, 25);
        panel.add(patientIdField);

        JLabel label2 = new JLabel("Medication:");
        label2.setBounds(200, 110, 100, 25);
        label2.setFont(new Font("Tahoma",Font.BOLD,15));
        label2.setForeground(Color.white);
        panel.add(label2);

        medicationNameField = new JTextField(20);
        medicationNameField.setBounds(310, 110, 220, 25);
        panel.add(medicationNameField);

        JLabel label3 = new JLabel("Dosage:");
        label3.setBounds(200, 150, 100, 25);
        label3.setFont(new Font("Tahoma",Font.BOLD,15));
        label3.setForeground(Color.white);
        panel.add(label3);

        dosageField = new JTextField(20);
        dosageField.setBounds(310, 150, 220, 25);
        panel.add(dosageField);

        JLabel label4 = new JLabel("Instructions:");
        label4.setBounds(200, 190, 100, 25);
        label4.setFont(new Font("Tahoma",Font.BOLD,15));
        label4.setForeground(Color.white);
        panel.add(label4);

        instructionsArea = new JTextArea();
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea);
        instructionsScrollPane.setBounds(310, 190, 220, 50);
        panel.add(instructionsScrollPane);

        JButton button = new JButton("Prescribe");
        button.setBounds(340, 260, 120, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBounds(180, 300, 480, 150);
        panel.add(outputScrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prescribeMedication();
            }
        });

        JButton button1 = new JButton("BACK");
        button1.setBounds(350,480,120,25);
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
        setLocation(230, 140);
        setVisible(true);
    }

    private void prescribeMedication() {
        String s1 = patientIdField.getText();
        String s2 = medicationNameField.getText();
        String s3 = dosageField.getText();
        String s4 = instructionsArea.getText();
        String date = java.time.LocalDate.now().toString();

        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        try {
            int patientId = Integer.parseInt(s1);
            coon c = new coon();
            String q = "SELECT * FROM patient_info WHERE Number = " + patientId;
            ResultSet resultSet = c.statement.executeQuery(q);
            if (resultSet.next()) {
                // Insert prescription
                String q1 = "INSERT INTO prescriptions (patient_id, medication_name, dosage, instructions, prescription_date) VALUES (" +
                        patientId + ", '" + s2 + "', '" + s3 + "', '" + s4 + "', '" + date + "')";
                int rowsAffected = c.statement.executeUpdate(q1);
                if (rowsAffected > 0) {
                    outputArea.setText("Medication prescribed successfully for Patient ID: " + patientId);
                } else {
                    outputArea.setText("Failed to prescribe medication.");
                }
            } else {
                outputArea.setText("No patient found with ID: " + patientId);
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Patient ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error prescribing medication.");
        }
    }

    public static void main(String[] args) {
        new PrescribeMedication();
    }
}
