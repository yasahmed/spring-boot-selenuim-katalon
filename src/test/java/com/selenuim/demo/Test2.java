package com.selenuim.demo;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Test2 {
    WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;


    @BeforeClass(alwaysRun = true)
    public void stUp() throws Exception {

        report = new ExtentReports(System.getProperty("user.dir") + "\\test-output\\reports\\ExtentReportResults.html");
        test = report.startTest("ExtentDemo");

        //String phantomjsExeutableFilePath = "C:\\Users\\eessa\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
         String phantomjsExeutableFilePath = "/var/jenkins_home/workspace/toto6/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";
        System.setProperty("phantomjs.binary.path", phantomjsExeutableFilePath);
        // Initiate PhantomJSDriver.
        driver = new PhantomJSDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void login_OK() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::input[4]")).click();
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=FullTextQuery1 | ]]
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery1")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }


    @Test
    public void searchHouse_OK() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31/Browse");
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery1")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }

    @Test
    public void searchHouse_KO() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31/Browse");
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery111")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }


    public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir") + "\\test-output\\screenShots\\" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }


    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) {
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + "Test Failed");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        } else {
            test.log(LogStatus.PASS, "Navigated to the specified URL");

        }
        // driver.quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        report.endTest(test);
        report.flush();

        driver.quit();
    }
}