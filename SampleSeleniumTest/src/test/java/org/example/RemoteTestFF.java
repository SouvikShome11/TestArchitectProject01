package org.example;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteTestFF {

    DesiredCapabilities dc;
    WebDriver driver;

    @BeforeClass
    public void browserSetup() throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setBrowserName(Browser.FIREFOX.browserName());
        //dc.setBrowserName("firefox");
        dc.setPlatform(Platform.LINUX); // Used this for docker as container os is LINUX
        //dc.setPlatform(Platform.WINDOWS);

        //driver=new ChromeDriver();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
    }
    @AfterClass
    public void browserClose() {
        driver.quit();
    }

    @Test(description = "Open Google")
    public void openGoogleInFirefox() {
        driver.get("https://www.gmail.com/");
        System.out.println(driver.getTitle()+" Opened in FF");
        Assert.assertTrue(true, driver.getTitle());
    }

}
