package com.BriteERP.tests;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    //test 1
    //before driver has new value
    //test
    //after method running driver.quit
    //test 2
    //before driver has new value

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void setUpTest(){
        report = new ExtentReports();

        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(filePath);
        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("Vytrack automate test reports");

        report.setSystemInfo("Environment", "QA3");
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("Testing Engineer", "Admiral Kunkka");

    }

    @AfterTest
    public void tearDownTest(){
        report.flush();
    }


    @BeforeMethod
    public void setUpMethod(){
        //initializes the webdriver object in test base class using the Driver utility
        driver = Driver.get();
        //setting implicit wait --> when elements not found, it will keep trying to find it for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //actions class enable advanced interactions like double click, drag drop
        actions = new Actions(driver);
        //set up the explicit wait object.
        wait = new WebDriverWait(driver, 10);
        driver.get(ConfigurationReader.get("url"));
    }
    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        //if the test failed
        if(result.getStatus() == ITestResult.FAILURE){
            extentLogger.fail(result.getName());
            // take screen shot
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            //capture the exception
            extentLogger.fail(result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentLogger.skip("Test case skipper: "+ result.getName());
        }

        Thread.sleep(4000);
        Driver.closeDriver();
    }
}