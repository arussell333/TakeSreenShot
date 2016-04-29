package com.appium;


import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;
import java.util.concurrent.TimeUnit;



public class   TakeScreenShotAndroid {
	
	private AndroidDriver driver;
	Dimension size; 
	String destDir; 
	DateFormat dateFormat;
	
	public void takeScreenShot() { 
		
		// Set folder name to store screenshots. 
		destDir = "screenshots"; 
		// Capture screenshot. 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		// Set date format to set It as screenshot file name. 
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); 
		// Create folder under project with name "screenshots" provided to destDir. 
		new File(destDir).mkdirs(); 
		// Set file name using current date time. 
		String destFile = dateFormat.format(new Date()) + ".png"; 
		try { 
			// Copy paste file at destination folder location 
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e) { e.printStackTrace(); 
			}
		} 
		
	
	
	
    @Before
    public void testCaseSetupEM()throws  Exception
    {

		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("automationName", "appium");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("plafformVersion", "5.1.1");
		capabilities.setCapability("app", ("/Users/adamrussell/Downloads/My_BMW_Remote.apk"));
		capabilities.setCapability("appPackage", "com.bmw.remote");
		//capabilities.setCapability("noReset", "true");
		capabilities.setCapability("appActivity", "base.ui.commonwidgets.StartupActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
		
		
    }
    
    

    
    @Test
    
    public void testCaseLogin() throws  Exception
    
    {
    	
    	driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.bmw.remote:id/disclaimer_btn_ok\").text(\"Accept\")").click();
    	
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.bmw.remote:id/username\")").sendKeys("arussell333@gmail.com");
        takeScreenShot();
     

    }
  
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
    
    
    
    
}