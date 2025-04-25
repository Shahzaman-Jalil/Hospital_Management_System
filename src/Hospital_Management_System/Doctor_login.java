package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Doctor_login extends JFrame {
    Doctor_login() {

        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 160, 1520, 670);
        add(panel);

        BackgroundPanel panel1 = new BackgroundPanel("icon/file.png");
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1520, 150);
        add(panel1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image image = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1130, 0, 200, 200);
        panel1.add(label);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.r1.png"));
        Image image1 = i11.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(image1);
        JLabel label1 = new JLabel(i22);
        label1.setBounds(130, 0, 200, 200);
        panel1.add(label1);

        JButton btn1 = new JButton("View Patient Details");
        btn1.setBounds(470,15,200,30);
        btn1.setBackground(new Color(75,75,255));
        btn1.setForeground(Color.white);
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new All_Patients_info();
            }
        });

        JButton btn2 = new JButton("Update Patient Diagnosis");
        btn2.setBounds(470,58,200,30);
        btn2.setBackground(new Color(75,75,255));
        btn2.setForeground(Color.white);
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePatientDiagnosis();
            }
        });

        JButton btn3 = new JButton("Prescribe Medication");
        btn3.setBounds(470,100,200,30);
        btn3.setBackground(new Color(75,75,255));
        btn3.setForeground(Color.white);
        panel1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrescribeMedication();
            }
        });

        JButton btn4 = new JButton("Schedule Appointments");
        btn4.setBounds(750,15,200,30);
        btn4.setBackground(new Color(75,75,255));
        btn4.setForeground(Color.white);
        panel1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScheduleAppointment();
            }
        });

        JButton btn5 = new JButton("View Appointment Schedule");
        btn5.setBounds(750,58,200,30);
        btn5.setBackground(new Color(75,75,255));
        btn5.setForeground(Color.white);
        panel1.add(btn5);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ViewAppointmentSchedule();
            }
        });

        JButton btn6 = new JButton("Logout");
        btn6.setBounds(750,101,200,30);
        btn6.setBackground(new Color(75,75,255));
        btn6.setForeground(Color.white);
        panel1.add(btn6);
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(1950, 1090);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Doctor_login();
    }
}
