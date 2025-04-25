package Hospital_Management_System;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nurse_login extends JFrame{
    public nurse_login() {

        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 160, 1520, 670);
        add(panel);

        BackgroundPanel panel1 = new BackgroundPanel("icon/file.png");
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1520, 150);
        add(panel1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/nursebg.png"));
        Image image = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1150, 0, 200, 200);
        panel1.add(label);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/nurse2bg.png"));
        Image image1 = i11.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(image1);
        JLabel label1 = new JLabel(i22);
        label1.setBounds(130, 0, 200, 200);
        panel1.add(label1);

        JButton btn1 = new JButton("View Patient List");
        btn1.setBounds(420,15,200,30);
        btn1.setBackground(new Color(75,75,255));
        btn1.setForeground(Color.white);
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new All_Patient_info();
            }
        });

        JButton btn2 = new JButton("Update Patient Vitals");
        btn2.setBounds(420,78,200,30);
        btn2.setBackground(new Color(75,75,255));
        btn2.setForeground(Color.white);
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_Vitals();
            }
        });

        JButton btn3 = new JButton("Check Duty Schedule");
        btn3.setBounds(700,15,200,30);
        btn3.setBackground(new Color(75,75,255));
        btn3.setForeground(Color.white);
        panel1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Duty_Schedule();
            }
        });

        JButton btn4 = new JButton("Mark Attendance");
        btn4.setBounds(700,78,200,30);
        btn4.setBackground(new Color(75,75,255));
        btn4.setForeground(Color.white);
        panel1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Mark_Attendance();
            }
        });


        JButton btn5 = new JButton("Logout");
        btn5.setBounds(950,45,200,30);
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
        new nurse_login();
    }
}
