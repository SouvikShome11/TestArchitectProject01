package org.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JavaToJsonInOneFile {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/business"; //connection string
        String username = "root";
        String password = "root";

        ArrayList<CustomerDetails> customerDetailsArrayList = new ArrayList<CustomerDetails>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        //Creating Connection with Connection String
        connection = DriverManager.getConnection(jdbcUrl, username, password);

        //Object oh Statement class, with help to run queries
        Statement st = connection.createStatement();
        ResultSet resSet = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia'");


        while (resSet.next()) {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setCourseName(resSet.getString(1));
            customerDetails.setPurchaseDate(resSet.getString(2));
            customerDetails.setAmount(resSet.getInt(3));
            customerDetails.setLocation(resSet.getString(4));

            customerDetailsArrayList.add(customerDetails);
        }

        /*
         * 1. Use google gson to convert JAVA object to string
         * 2. Usage of Json-simple to create a single json file
         *
         */
        JSONArray jsonArray = new JSONArray();
        for (CustomerDetails customerDetails : customerDetailsArrayList) {
            Gson gson = new Gson(); // Google gson Converts Object to String
            String jsonString = gson.toJson(customerDetails);
            System.out.println("Gson String -----------> "+ jsonString);
            jsonArray.add(jsonString); //adding the string to JSON array
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        System.out.println(jsonObject.toJSONString());

        // Using  common-test API to remove escape characters from json converted string
        String unescapedStrings = StringEscapeUtils.unescapeJava(jsonObject.toJSONString());
        System.out.println(unescapedStrings);

        String removeQuotation = unescapedStrings.replace("\"{", "{");
        removeQuotation = removeQuotation.replace("}\"", "}");
        System.out.println(removeQuotation);

        /*
         * The try-with-resources statement is a feature introduced in Java 7. It is used for automatically managing resources
         *  (in this case, the file) that need to be closed after they are no longer needed. When the try block is exited, either normally
         *  or due to an exception, the resources (in this case, the FileWriter) are automatically closed, which helps prevent resource leaks
         *  and ensures proper cleanup.
         * */
        try (FileWriter file = new FileWriter("..\\TestArchitect_DBCompare\\JsonFiles\\singleJson.json")) {
            file.write(removeQuotation);
        }


        connection.close();

    }
}
