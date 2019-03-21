package com.selenuim.demo.klov;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class baseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public static ExtentReports extentReports;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setup () {

        String phantomjsExeutableFilePath = "C:\\Users\\eessa\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
        System.setProperty("phantomjs.binary.path", phantomjsExeutableFilePath);
        driver = new PhantomJSDriver();
        driver.manage().window().maximize();


        driver.navigate().to("https://www.rainworx.com/AWDemo31");
        KlovReporter klovReporter = new KlovReporter();

        klovReporter.initMongoDbConnection("localhost", 27017);

        klovReporter.setProjectName("ATTIJARINET e2e Tests");

        klovReporter.setReportName("E2E Test report v1.0");

        System.out.println("OKOKOKOKOKOKOKK");

        klovReporter.setKlovUrl("http://localhost");

        extentReports = new ExtentReports();
        extentReports.attachReporter(klovReporter);

    }

    @AfterClass
    public void teardown () {
        driver.quit();
        extentReports.flush();
    }

}
