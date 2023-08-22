package org.example;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.util.Utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteTestChrome {
    DesiredCapabilities dc;
    WebDriver driver;

    @BeforeTest
    public void setupDocker() throws InterruptedException, IOException {
        FileHandler.deleteFile("docker-log.txt");
        DockerStart dockerStart=new DockerStart();
        dockerStart.startBatFile();
    }
    @AfterTest
    public void killDocker() throws InterruptedException, IOException{
        DockerStop dockerStop=new DockerStop();
        dockerStop.stopBatFile();
        /*Thread.sleep(3000);
        Utility.closeAllCmdWindows();*/
    }

    @BeforeClass
    public void browserSetup() throws MalformedURLException {
        dc = new DesiredCapabilities();
        //dc.setBrowserName(Browser.CHROME.browserName());
        dc.setBrowserName("chrome");
        dc.setPlatform(Platform.LINUX); // Used this for docker
        //dc.setPlatform(Platform.WINDOWS);

        //driver=new ChromeDriver();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
    }
    @AfterClass
    public void browserClose() {
        driver.quit();
    }





    @Test(description = "Open Google")
    public void TestCase1() {
        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());
        Assert.assertTrue(true, driver.getTitle());
    }





}
