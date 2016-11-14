package com.cooltrickshome;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebPageScreenShotTaker {

	static int screenshotNum=0;
	static WebDriver driver=null;
	
	public static void main(String[] args) throws IOException {

		initDriver();
		WebPageScreenShotTaker ws=new WebPageScreenShotTaker();
		ws.capture("https://www.google.com");
		ws.capture("https://facebook.com");
		destroy();
	}
	
	public static void initDriver()
	{
		System.setProperty("webdriver.chrome.driver",
				"./driver/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().setPosition(new Point(-2000, 0));
	}
	
	public void capture(String site) throws IOException
	{
		screenshotNum++;
		driver.get(site);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("site"+screenshotNum+".png"));
		System.out.println("Took Screenshot for "+site+" and saved as "+"site"+screenshotNum+".png");
	}
	
	public static void destroy()
	{
		driver.quit();
	}
}
