package com.SalesForce.Utilities;

import java.text.SimpleDateFormat;



import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.SalesForce.base.BaseTest;

public class ListenerUtility extends BaseTest implements ITestListener {


	protected Logger Listenerlog=LogManager.getLogger();
	private static ExtentReportsUtility extentReport= ExtentReportsUtility.getInstance();
	
	public ListenerUtility() {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTestStart(ITestResult result) {
		Listenerlog.info(result.getMethod().getMethodName()+".......test execution started........");
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
			
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Listenerlog.info(result.getMethod().getMethodName()+".......test execution completed with success........");
		extentReport.logTestpassed("Test execution complited with success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Listenerlog.error(result.getMethod().getMethodName()+"test execution completed with  failure");
		extentReport.logTestFailed(result.getMethod().getMethodName()+"test is Failed");
		String filename= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String path = System.getProperty("user.dir") + "/screenshots/" + filename + ".png";
		Listenerlog.error(".......test execution completed with failure........");
		
		takescreenshot(path);
		extentReport.logTestWithScreenshot(System.getProperty("user.dir")+"/.png");
		extentReport.logTestFailedWithException(result.getThrowable());
		
		
		}

		
	

	@Override
	public void onStart(ITestContext context) {
		Listenerlog.info(context.getName()+" ...... has Started......");
		extentReport.startExtentReport();
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		Listenerlog.info(context.getName()+" ...... has ended......");
		extentReport.endReprt();
		
		
	}
	
	}



