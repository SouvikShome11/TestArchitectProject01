package org.example;

import java.io.File;

public class FileHandler {

    public static void deleteFile(String fileName) {

        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println(fileName + " file deleted successfully");
            }
        }
    }
}
