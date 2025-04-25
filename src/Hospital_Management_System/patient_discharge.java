package Hospital_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {
    patient_discharge(){
        BackgroundPanel panel = new BackgroundPanel("icon/file.png");
        panel.setLayout(null);
        panel.setBounds(5, 5, 790, 390);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{
            coon c = new coon();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.white);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30,180,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JLabel INTime = new JLabel();
        INTime.setBounds(200,180,250,20);
        INTime.setFont(new Font("Tahoma",Font.BOLD,14));
        INTime.setForeground(Color.white);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        Date date = new Date();

        JLabel OUTTime = new JLabel(""+date);
        OUTTime .setBounds(200,230,250,20);
        OUTTime .setFont(new Font("Tahoma",Font.BOLD,14));
        OUTTime .setForeground(Color.white);
        panel.add(OUTTime );

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coon c = new coon();
                try {
                    // First delete prescriptions for the selected patient
                    c.statement.executeUpdate("DELETE FROM prescriptions WHERE patient_id = '" + choice.getSelectedItem() + "'");

                    // Now delete the patient record
                    c.statement.executeUpdate("DELETE FROM patient_info WHERE Number = '" + choice.getSelectedItem() + "'");

                    // Update the room availability
                    c.statement.executeUpdate("UPDATE Room SET Available = 'Available' WHERE Room_Number = '" + RNo.getText() + "'");

                    JOptionPane.showMessageDialog(null, "Patient discharged successfully!");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton Check = new JButton("Check");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coon c = new coon();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from patient_info where Number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(300,500,120,30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
