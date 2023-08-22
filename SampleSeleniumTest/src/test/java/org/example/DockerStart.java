package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Calendar;

public class DockerStart {

    public void startBatFile() throws IOException, InterruptedException {

        boolean flag=false;
        String dockerLogfile = "docker-log.txt";

        //This Will run the .bat file
        Runtime runtime= Runtime.getRuntime();
        runtime.exec("cmd /c start docker-up.bat");

        //This is to take current time and keep looping the log file for Required time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60);
        long stopTime = calendar.getTimeInMillis();
        // It takes some time to start creating the log file. To avoid file-not-found Exception adding some sleep
        Thread.sleep(3000);

        while (System.currentTimeMillis() < stopTime) {
            //This is to break the Time based loop if the KeyWOrd is found before given time frame
            if(flag){
                break;
            }

            //This will read the Log File
            BufferedReader reader = new BufferedReader(new FileReader(dockerLogfile));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLine.contains("Node has been added") &&
                        (currentLine.contains("chrome") || currentLine.contains("firefox")|| currentLine.contains("edge"))) {
                    System.out.println("Match Found --> Selenium Grid is UP and Running");
                    flag=true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }

        Assert.assertTrue(flag);

        //Scale No of nodes
        runtime.exec("cmd /c start docker-scale.bat");
        Thread.sleep(15000);
    }
}
