package com.selenuim.demo.klov;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestListener extends baseTest implements ITestListener {

    ExtentTest test;
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        test = extentReports.createTest(getTestMethodName(iTestResult) + " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult)  {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            test.fail("test failed");
            test.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
            String imagepath = CaptureScreenShot(iTestResult.getName());
            test.addScreenCaptureFromPath(imagepath);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
            test.fail("test failed");
        }
    }

    public String CaptureScreenShot(String screenshotname) throws Exception{
        DateFormat DF = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
        Date D = new Date();
        String time = DF.format(D);
        TakesScreenshot ts = (TakesScreenshot)driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "./" + screenshotname+" "+time+".png";
        File destination = new File(dest);
        FileUtils.copyFile(Source, destination);
        return dest;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        test.skip("Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        test.skip("Test Failed");
    }
}