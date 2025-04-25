package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewMedicalRecords extends JFrame {
    private JTextField patientIdField;
    private JTextArea outputArea;

    public ViewMedicalRecords() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 540, 540);
        add(panel);

        JLabel For = new JLabel("View Medical Records");
        For.setBounds(170, 10, 312, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel label = new JLabel("Patient ID:");
        label.setBounds(20, 50, 100, 25);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setForeground(Color.white);
        panel.add(label);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(130, 50, 200, 25);
        panel.add(patientIdField);

        JButton button = new JButton("View Records");
        button.setBounds(350, 50, 120, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 89, 480, 350);
        panel.add(scrollPane);

        JButton button1 = new JButton("BACK");
        button1.setBounds(200, 450, 120, 25);
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
                viewRecords();
            }
        });

        setSize(550, 550);
        setLayout(null);
        setLocation(500, 250);
        setVisible(true);
    }

    private void viewRecords() {
        String patientIdText = patientIdField.getText();

        if (patientIdText.isEmpty()) {
            outputArea.setText("Please enter a Patient ID.");
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            coon c = new coon();
            String q = "SELECT * FROM prescriptions WHERE patient_id = " + patientId;
            ResultSet resultSet = c.statement.executeQuery(q);

            StringBuilder records = new StringBuilder();
            records.append("Medical record for Patient ID: ").append(patientId).append("\n\n");

            boolean hasRecords = false;
            while (resultSet.next()) {
                hasRecords = true;
                String dateOfRecord = resultSet.getString("prescription_date");
                String treatment = resultSet.getString("instructions");
                String prescription = resultSet.getString("medication_name");
                String dosage = resultSet.getString("dosage");

                records.append("Date: ").append(dateOfRecord).append("\n")
                        .append("Treatment: ").append(treatment).append("\n")
                        .append("Prescription: ").append(prescription).append("\n")
                        .append("Dosage: ").append(dosage).append("\n\n");
            }

            if (!hasRecords) {
                outputArea.setText("No medical records found for Patient ID: " + patientId);
            } else {
                outputArea.setText(records.toString());
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid Patient ID. Please enter a numeric value.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error fetching medical records.");
        }
    }

    public static void main(String[] args) {
        new ViewMedicalRecords();
    }
}
