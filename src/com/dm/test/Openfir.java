package com.dm.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Openfir {

	@Test
	public void open() {
//		FirefoxDriver driver = new FirefoxDriver();
//		driver.get("http://www.baidu.com");
//		WebElement findElementById = driver.findElementById("kw");
//		findElementById.sendKeys("雅蠛蝶");
//		WebElement button = driver.findElementById("su");
//		button.click();
//
//		WebElement findElementById2 = driver.findElementById("3");
//		// System.out.println(findElementById2);
//		// WebElement findElementByXPath =
//		// driver.findElementByXPath("//div/div/div/div/div/h3/a[last()]");
//		// System.out.println(findElementByXPath.getText());
//		// findElementByXPath.click();
//		findElementById2.findElement(By.tagName("a")).click();
//		System.out.println(findElementById2.findElement(By.tagName("a"))
//				.getText());
	}

	@Test
	public void testOpen() throws Exception {

		WebDriver driver;
		String baseUrl;
		boolean acceptNextAlert = true;
		StringBuffer verificationErrors = new StringBuffer();

		driver = new HtmlUnitDriver();
		baseUrl = "https://www.baidu.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(baseUrl + "/index.php?tn=monline_5_dg");
		driver.findElement(By.id("kw")).click();
		driver.findElement(By.id("kw")).clear();
		driver.findElement(By.id("kw")).sendKeys("李德明");
		driver.findElement(By.id("su")).click();
		System.out.println(driver.findElement(By.linkText("李德明(电视剧《川军团血战到底》人物)_百度百科")).getText());
		driver.findElement(By.linkText("李德明(电视剧《川军团血战到底》人物)_百度百科")).click();
	}
}
