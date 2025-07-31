package Listners_Utilities;

//import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listners implements ITestListener, ISuiteListener {
	
	public ExtentReports report;
	public ExtentSparkReporter spark;	
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Execution started - Configure the Reports", true);
		
		Date d= new Date();
		String time=d.toString().replace(":", "_").replace(" ", "_");		//Configure the report
		
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReports/report_"+time+".html");
		spark.config().setDocumentTitle("Vtiger CRM Prjt");
		spark.config().setReportName("Test Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Chrome-13.8");
		
		test = report.createTest("Test");
		test.log(Status.INFO, "Configuring the report");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Suite Execution ended - Backup the Reports", true);
		test.log(Status.INFO,  "Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution started", true);
		test.log(Status.INFO, " " + testname + "Test Execution started");
//		test = report.createTest(" " + testname + "Report");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution Success", true);
		test.log(Status.PASS, "" + testname + " Test Execution Sucess ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution Failed", true);
		Date d= new Date();
		String date=d.toString().replace(":", "_").replace(" ", "_");
		
		test.log(Status.FAIL, ""+testname + "Test Execution Failed");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sDriver;
		
		String ss = ts.getScreenshotAs(OutputType.BASE64);		
		test.addScreenCaptureFromBase64String(ss," " +testname + date + " ");
		
		//test.log(Status.FAIL, result.getThrowable());
		//File src = ts.getScreenshotAs(OutputType.FILE);
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		//String currentDate=sdf.format(d);
		//String date=currentDate.replace(":", "_");
//		File dest = new File("./Screenshot/" + testname+date+ ".png");
//		try {
//			FileUtils.copyFile(src, dest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution Skipped", true);
		test.log(Status.SKIP, ""+ testname + "Test Execution Skipped");
	}
}
