package com.selenuim.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Test2 {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void stUp() throws Exception {
        String phantomjsExeutableFilePath = "/var/jenkins_home/workspace/toto6/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";
        System.setProperty("phantomjs.binary.path", phantomjsExeutableFilePath);
        // Initiate PhantomJSDriver.
        driver = new PhantomJSDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sign In'])[2]/following::div[2]")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::input[4]")).click();
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery1")).sendKeys("house");
        driver.findElement(By.id("FullTextQuery1")).sendKeys(Keys.ENTER);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}