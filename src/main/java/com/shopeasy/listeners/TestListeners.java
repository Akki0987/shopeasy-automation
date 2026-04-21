package com.shopeasy.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.shopeasy.ReporterConfig.ExtentReportConfig;

import java.io.IOException;

import static com.shopeasy.utils.Utilities.getScreenshot;

public class TestListeners implements ITestListener {

    ExtentReports extent = ExtentReportConfig.getReportObject();
    ExtentTest test;
    WebDriver driver;

        @Override
        public void onTestStart(ITestResult result) {
            test = extent.createTest(result.getMethod().getMethodName());
            System.out.println("Test started: " + result.getName());
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            test.log(Status.PASS, result.getName() + "Test passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {

            test.fail(Status.FAIL+"Test : " + result.getMethod().getMethodName()+ " passed"+ result.getThrowable());

            String path = null;

            try {
                driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
//		Take Screenshot
            try {
                path = getScreenshot(result.getMethod().getMethodName(), driver);
            } catch (IOException e) {
                e.printStackTrace();
            }
           test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            test.skip(Status.SKIP+"Test : " + result.getMethod().getMethodName()+ " skipped"+ result.getThrowable());
            System.out.println("Test skipped: " + result.getName());
        }

        @Override
        public void onFinish(ITestContext context) {
            extent.flush();
            System.out.println("Test Suite finished: " + context.getName());
        }

    }

