package com.university.checking;

import java.sql.*;
public class TestConnection2Oracle {



        public static void main(String[] args) throws Exception {

            String url = "jdbc:oracle:thin:@//192.168.155.161:1521/dboqas";
            String user = "M_AXMEDOV";
            String password = "hIBAlgRalEtr_2025";
            Class.forName("oracle.jdbc.OracleDriver");
            try(Connection c = DriverManager.getConnection(url,user,password)){
                System.out.println("Connection Established");
                try(Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT 1 FROM DUAL")){

                    if(rs.next()) System.out.println(rs.getInt(1));
                }




            }
    }
}
