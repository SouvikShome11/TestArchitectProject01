package org.util;

import org.testng.annotations.Test;

import java.io.IOException;

public class Utility {

    public static void closeAllCmdWindows() throws IOException {
        Runtime runtime=Runtime.getRuntime();

        runtime.exec("taskkill /F /IM cmd.exe");
    }
}
