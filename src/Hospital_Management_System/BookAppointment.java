package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookAppointment extends JFrame {
    JTextField patientIdField;
    JTextField doctorIdField;
    JTextField appointmentDateField;
    JTextField appointmentTimeField;
    JTextField purposeField;
    JTextArea outputArea;

    public BookAppointment() {
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setBounds(5, 5, 890, 590);
        panel.setLayout(null);
        add(panel);

        JLabel labelName = new JLabel("Book Appointment");
        labelName.setBounds(300, 10, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.white);
        panel.add(labelName);

        JLabel label = new JLabel("Patient ID:");
        label.setBounds(40, 80, 100, 25);
        label.setFont(new Font("Tahoma",Font.BOLD,15));
        label.setForeground(Color.white);
        panel.add(label);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(150, 80, 200, 25);
        panel.add(patientIdField);

        JLabel label1 = new JLabel("Doctor ID:");
        label1.setBounds(480, 80, 100, 25);
        label1.setFont(new Font("Tahoma",Font.BOLD,15));
        label1.setForeground(Color.white);
        panel.add(label1);

        doctorIdField = new JTextField(20);
        doctorIdField.setBounds(590, 80, 200, 25);
        panel.add(doctorIdField);

        JLabel label2 = new JLabel("Date (YYYY-MM-DD):");
        label2.setBounds(40, 140, 180, 25);
        label2.setFont(new Font("Tahoma",Font.BOLD,15));
        label2.setForeground(Color.white);
        panel.add(label2);

        appointmentDateField = new JTextField(20);
        appointmentDateField.setBounds(220, 140, 160, 25);
        panel.add(appointmentDateField);

        JLabel label3 = new JLabel("Time (HH:MM:SS):");
        label3.setBounds(480, 140, 150, 25);
        label3.setFont(new Font("Tahoma",Font.BOLD,15));
        label3.setForeground(Color.white);
        panel.add(label3);

        appointmentTimeField = new JTextField(20);
        appointmentTimeField.setBounds(650, 140, 160, 25);
        panel.add(appointmentTimeField);

        JLabel label4 = new JLabel("Purpose:");
        label4.setBounds(40, 200, 100, 25);
        label4.setFont(new Font("Tahoma",Font.BOLD,15));
        label4.setForeground(Color.white);
        panel.add(label4);

        purposeField = new JTextField(20);
        purposeField.setBounds(130, 200, 200, 25);
        panel.add(purposeField);

        JButton button = new JButton("Book Appointment");
        button.setBounds(380, 270, 180, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(200, 330, 500, 150);
        panel.add(scrollPane);

        JButton button1 = new JButton("BACK");
        button1.setBounds(400,520,120,25);
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
                bookAppointment();
            }
        });

        setSize(900, 600);
        setLayout(null);
        setLocation(300, 230);
        setVisible(true);
    }

    private void bookAppointment() {
        String s = patientIdField.getText();
        String s1 = doctorIdField.getText();
        String s2 = appointmentDateField.getText();
        String s3 = appointmentTimeField.getText();
        String s4 = purposeField.getText();

        if (s.isEmpty() || s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        try {
            int patientId = Integer.parseInt(s);
            int doctorId = Integer.parseInt(s1);
            coon c = new coon();

            String q = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, purpose) VALUES ("
                    + patientId + ", " + doctorId + ", '" + s2 + "', '" + s3 + "', '" + s4 + "')";
            int rowsAffected = c.statement.executeUpdate(q);
            if (rowsAffected > 0) {
                outputArea.setText("Appointment booked successfully.");
            } else {
                outputArea.setText("Failed to book appointment.");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid ID. Please enter numeric values for Patient ID and Doctor ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error booking appointment.");
        }
    }

    public static void main(String[] args) {
        new BookAppointment();
    }
}
