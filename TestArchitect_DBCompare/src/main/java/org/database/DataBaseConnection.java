package org.database;

import java.sql.*;

public class DataBaseConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/business"; //connection string
        String username = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= null;
        //Creating Connection with Connection String
        conn= DriverManager.getConnection(jdbcUrl,username,password);

        //Object oh Statement class, with help to run queries
        Statement st=conn.createStatement();
        ResultSet resSet= st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia'");

        while (resSet.next()){
            System.out.println(resSet.getString(1)+"  "+resSet.getString(2)+"  "+
                    resSet.getInt(3)+"  "+resSet.getString(4));
        }

    }
}
