package org.TestUtils;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
public class ExtentReporting {
    static ExtentReports extentReports ;
    public static ExtentReports getExtent(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\report.html");
        reporter.config().setReportName("E-Commerce Mock Testing");
        reporter.config().setDocumentTitle("Results");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester","Mustafa M.");

        return extentReports;

    }
}
