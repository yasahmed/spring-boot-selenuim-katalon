package com.selenuim.demo;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
      //  System.setProperty("webdriver.gecko.driver", "yyyy");
       // System.setProperty("webdriver.firefox.bin", "kkk");
         System.setProperty("webdriver.firefox.bin", "/tmp/firefox-portable/firefox-portable");
         System.setProperty("webdriver.gecko.driver", "/var/jenkins_home/workspace/toto2/drivers/geckodriver");


        FirefoxOptions firefoxOptions =  new FirefoxOptions();
      //  firefoxOptions.addArguments("--headless");
        firefoxOptions.setCapability("marionette", false);
        driver = new FirefoxDriver(firefoxOptions);
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @org.testng.annotations.Test
    public void testUntitledTestCase() throws Exception {

     /*   driver.get("https://www.rainworx.com/AWDemo31/Account/LogOn?returnUrl=%2FAWDemo31");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Passwordl'])[1]/following::input[4]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Categories'])[1]/following::a[2]")).click();
  */
    }

/*    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
    */
}
