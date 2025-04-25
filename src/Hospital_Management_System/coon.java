package Hospital_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class coon {
    Connection connection;
    public Statement statement;

    public coon(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system","root","1234");

            statement= connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
