package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Update_Vitals extends JFrame {
    private JTextField patientIdField, temperatureField, bloodPressureField, heartRateField;
    private JTextArea outputArea;

    public Update_Vitals() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 840, 540);
        add(panel);

        JLabel titleLabel = new JLabel("Update Patient Vitals", SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 840, 40);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel label = new JLabel("Patient ID:");
        label.setBounds(250, 60, 100, 25);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(label);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(350, 60, 200, 25);
        panel.add(patientIdField);

        JLabel label1 = new JLabel("Temperature:");
        label1.setBounds(250, 100, 100, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(label1);

        temperatureField = new JTextField(20);
        temperatureField.setBounds(350, 100, 200, 25);
        panel.add(temperatureField);

        JLabel label2 = new JLabel("Blood Pressure:");
        label2.setBounds(250, 140, 100, 25);
        label2.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(label2);

        bloodPressureField = new JTextField(20);
        bloodPressureField.setBounds(350, 140, 200, 25);
        panel.add(bloodPressureField);

        JLabel label3 = new JLabel("Heart Rate:");
        label3.setBounds(250, 180, 100, 25);
        label3.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(label3);

        heartRateField = new JTextField(20);
        heartRateField.setBounds(350, 180, 200, 25);
        panel.add(heartRateField);

        JButton button = new JButton("Update Vitals");
        button.setBounds(380, 230, 140, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(200, 270, 480, 200);
        scrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateVitals();
            }
        });

        JButton button1 = new JButton("BACK");
        button1.setBounds(380, 480, 120, 25);
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
        setLocation(250, 150);
        setVisible(true);
    }

    private void updateVitals() {
        String patientIdText = patientIdField.getText();
        String temperatureText = temperatureField.getText();
        String bloodPressureText = bloodPressureField.getText();
        String heartRateText = heartRateField.getText();

        if (patientIdText.isEmpty() || temperatureText.isEmpty() || bloodPressureText.isEmpty() || heartRateText.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            float temperature = Float.parseFloat(temperatureText);
            int heartRate = Integer.parseInt(heartRateText);
            String date = java.time.LocalDate.now().toString();

            coon c = new coon();
            String q = "INSERT INTO patient_vitals (patient_id, date, temperature, blood_pressure, heart_rate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.connection.prepareStatement(q);
            pstmt.setInt(1, patientId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setFloat(3, temperature);
            pstmt.setString(4, bloodPressureText);
            pstmt.setInt(5, heartRate);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                outputArea.setText("Vitals updated successfully for patient ID: " + patientId);
            } else {
                outputArea.setText("Failed to update vitals.");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input. Please enter numeric values for Patient ID, Temperature, and Heart Rate.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error updating vitals.");
        }
    }

    public static void main(String[] args) {
        new Update_Vitals();
    }
}
