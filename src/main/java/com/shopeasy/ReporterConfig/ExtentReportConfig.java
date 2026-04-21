package com.shopeasy.ReporterConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {

    public static ExtentReports getReportObject()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/testReport.html");

        reporter.config().setDocumentTitle("ShopEasy Automation Report");
        reporter.config().setReportName("ShopEasy Automation Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Akki");

        return extent;
    }
}
