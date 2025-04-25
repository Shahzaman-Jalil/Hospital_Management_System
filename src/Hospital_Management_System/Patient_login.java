package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Patient_login extends JFrame {
    Patient_login() {

        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 160, 1520, 670);
        add(panel);

        BackgroundPanel panel1 = new BackgroundPanel("icon/file.png");
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1520, 150);
        add(panel1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/31.png"));
        Image image = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1150, 0, 200, 200);
        panel1.add(label);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/patient2.png"));
        Image image1 = i11.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(image1);
        JLabel label1 = new JLabel(i22);
        label1.setBounds(130, 0, 200, 200);
        panel1.add(label1);

        JButton btn1 = new JButton("View Medical Records");
        btn1.setBounds(380,15,200,30);
        btn1.setBackground(new Color(75,75,255));
        btn1.setForeground(Color.white);
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewMedicalRecords();
            }
        });

        JButton btn2 = new JButton("Book Appointments");
        btn2.setBounds(380,78,200,30);
        btn2.setBackground(new Color(75,75,255));
        btn2.setForeground(Color.white);
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookAppointment();
            }
        });

        JButton btn3 = new JButton("View Appointment Schedule");
        btn3.setBounds(660,15,200,30);
        btn3.setBackground(new Color(75,75,255));
        btn3.setForeground(Color.white);
        panel1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAppointments();
            }
        });

        JButton btn4 = new JButton("Update Personal Information");
        btn4.setBounds(660,78,200,30);
        btn4.setBackground(new Color(75,75,255));
        btn4.setForeground(Color.white);
        panel1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePersonalInfo();
            }
        });

        JButton btn5 = new JButton("Logout");
        btn5.setBounds(940,45,200,30);
        btn5.setBackground(new Color(75,75,255));
        btn5.setForeground(Color.white);
        panel1.add(btn5);
        btn5.addActionListener(new ActionListener() {
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
        new Patient_login();
    }
}
