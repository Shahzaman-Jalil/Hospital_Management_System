package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdatePersonalInfo extends JFrame {
    JTextField patientNumberField;
    JTextField nameField;
    JTextField genderField;
    JTextField diseaseField;
    JTextArea outputArea;

    public UpdatePersonalInfo() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 890, 560);
        add(panel);

        JLabel For = new JLabel("Update Patient Info");
        For.setBounds(320, 10, 286, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel label = new JLabel("Patient Number:");
        label.setBounds(200, 50, 150, 25);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);

        patientNumberField = new JTextField(20);
        patientNumberField.setBounds(400, 50, 200, 25);
        panel.add(patientNumberField);

        JLabel label1 = new JLabel("Name:");
        label1.setBounds(200, 100, 100, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 15));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        nameField = new JTextField(20);
        nameField.setBounds(400, 100, 200, 25);
        panel.add(nameField);

        JLabel label2 = new JLabel("Gender:");
        label2.setBounds(200, 150, 100, 25);
        label2.setFont(new Font("Tahoma", Font.BOLD, 15));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        genderField = new JTextField(20);
        genderField.setBounds(400, 150, 200, 25);
        panel.add(genderField);

        JLabel label3 = new JLabel("Disease:");
        label3.setBounds(200, 200, 100, 25);
        label3.setFont(new Font("Tahoma", Font.BOLD, 15));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        diseaseField = new JTextField(20);
        diseaseField.setBounds(400, 200, 200, 25);
        panel.add(diseaseField);

        JButton button = new JButton("Update");
        button.setBounds(350, 250, 150, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBounds(200, 290, 480, 150);
        panel.add(outputScrollPane);

        JButton button1 = new JButton("BACK");
        button1.setBounds(370, 490, 120, 25);
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
                updatePersonalInfo();
            }
        });



        setSize(900, 570);
        setLayout(null);
        setLocation(300, 250);
        setVisible(true);
    }

    private void updatePersonalInfo() {
        String number = patientNumberField.getText();
        String name = nameField.getText();
        String gender = genderField.getText();
        String disease = diseaseField.getText();

        if (number.isEmpty() || name.isEmpty() || gender.isEmpty() || disease.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        try {
            coon c = new coon();
            String query = "SELECT * FROM patient_info WHERE Number = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, number);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String updateQuery = "UPDATE patient_info SET Name = ?, Gender = ?, Patient_Disease = ? WHERE Number = ?";
                PreparedStatement updateStmt = c.connection.prepareStatement(updateQuery);
                updateStmt.setString(1, name);
                updateStmt.setString(2, gender);
                updateStmt.setString(3, disease);
                updateStmt.setString(4, number);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    outputArea.setText("Personal information updated successfully for Patient Number: " + number);
                } else {
                    outputArea.setText("Failed to update personal information.");
                }
            } else {
                outputArea.setText("No patient found with Number: " + number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error updating personal information.");
        }
    }

    public static void main(String[] args) {
        new UpdatePersonalInfo();
    }
}
