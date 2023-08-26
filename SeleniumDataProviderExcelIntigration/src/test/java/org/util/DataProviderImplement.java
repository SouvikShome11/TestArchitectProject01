package org.util;

import org.testng.Assert;
import  org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Channels;

public class DataProviderImplement {


    @Test(dataProvider =  "driveTest")
    public void testOne(String a, String b, String c){
        System.out.println(a+b+c);
    }


    @DataProvider(name="driveTest")
    public Object[][] getData() throws FileNotFoundException, IOException {

        //Object[][] data= {{"Hello", "World", 1}, {"Hello", "World", 2}, {"Hello", "World", 3}};
        //return data;
        ReadExcel readExcel=new ReadExcel();
        return readExcel.readAndReturn2DArray("./InputFiles/InputFIle.xlsx");
    }
}
