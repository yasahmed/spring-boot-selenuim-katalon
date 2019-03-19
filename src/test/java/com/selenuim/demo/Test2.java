package com.selenuim.demo;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.nio.charset.StandardCharsets;

public class Test2 {
    WebDriver driver;
    ExtentTest test;
    ExtentReports report;

    @BeforeClass(alwaysRun = true)
    public void stUp() throws Exception {

        report = new ExtentReports("/var/jenkins_home/workspace/toto7/" + "/test-output/reports/" + "ExtentReportResults.html");
        //report = new ExtentReports(System.getProperty("user.dir") + "/test-output/reports/" + "ExtentReportResults.html");

        test = report.startTest("ExtentDemo");

        //String phantomjsExeutableFilePath = "C:\\Users\\eessa\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
        String phantomjsExeutableFilePath = "/var/jenkins_home/workspace/toto6/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";
        System.setProperty("phantomjs.binary.path", phantomjsExeutableFilePath);
        driver = new PhantomJSDriver();
        driver.manage().window().maximize();

        test.setDescription("description du teste !");

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+System.getProperty("user.dir"));

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

    @AfterMethod
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                test.log(LogStatus.FAIL, test.addBase64ScreenShot(ImgStreamToBase64(scrFile)) + generateMessage(result));
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        } else {
            test.log(LogStatus.PASS, generateMessage(result));
        }
    }

    private String ImgStreamToBase64(File file) throws IOException {
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        String str = new String(encoded, StandardCharsets.US_ASCII);
        return "data:image/png;base64," + str;
    }

    private String generateMessage(ITestResult result) {
        boolean isFailed = ITestResult.FAILURE == result.getStatus();
        String color = isFailed ? "red" : "green";
        String messageStatus = isFailed ? " test failed !!" : "test successfully passed :)";
        String successMsg = "<br/>" +
                "<div style='color:" + color + ";'> " +
                "<b> - Class name: " + result.getClass().getName() + "</b>" +
                "<br/>" +
                "<b> - Method name : " + result.getName() + "</b> : " + messageStatus +
                "</div>";
        if (isFailed) {
            try {
                JSONObject detailsJson = new JSONObject(result.getThrowable().getMessage());
                successMsg += "<b style='color:red'> - Cause : " + detailsJson.get("errorMessage").toString() + "</b>";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return successMsg;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        report.endTest(test);
        report.flush();

        driver.quit();
    }
}