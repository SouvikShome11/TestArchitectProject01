package org.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JavaToJson {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/business"; //connection string
        String username = "root";
        String password = "root";

        ArrayList<CustomerDetails> customerDetailsArrayList= new ArrayList<CustomerDetails>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= null;
        //Creating Connection with Connection String
        connection= DriverManager.getConnection(jdbcUrl,username,password);

        //Object of Statement class, with help to run queries
        Statement st=connection.createStatement();
        ResultSet resSet= st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia'");


        while (resSet.next()){
            CustomerDetails customerDetails= new CustomerDetails();
                customerDetails.setCourseName(resSet.getString(1));
                customerDetails.setPurchaseDate(resSet.getString(2));
                customerDetails.setAmount(resSet.getInt(3));
                customerDetails.setLocation(resSet.getString(4));

                customerDetailsArrayList.add(customerDetails);
        }

        /*
        * Code for writing object of POJO class to json file
        * Usage of JACKSON API
        * JACKSON API will create separate .json file for each object
        * */
        /*ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(new File("..\\TestArchitect_DBCompare\\JsonFiles\\customerInfo1.json"), customerDetails);*/

        for(int i=0; i<customerDetailsArrayList.size(); i++){
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(new File("..\\TestArchitect_DBCompare\\JsonFiles\\customerInfo"+(i+1)+".json"), customerDetailsArrayList.get(i));
        }

        connection.close();
    }
}
