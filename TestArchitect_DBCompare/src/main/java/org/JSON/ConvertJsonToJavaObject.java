package org.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConvertJsonToJavaObject {

    /**
     * To Implement This Method I am creating a fake API using designer.mocky.io
     */
    public static void main(String[] args) throws JsonProcessingException, IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        EmpPOJO empPOJO= objectMapper.readValue(new File("..\\TestArchitect_DBCompare\\src\\main\\java\\org\\JSON\\Sample.json"), EmpPOJO.class);

        System.out.println(empPOJO.getAge());

        AddressPOJO addressPOJO= empPOJO.getAddress();

        System.out.println(addressPOJO.getCity());

    }
}
