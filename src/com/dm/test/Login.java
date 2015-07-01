package com.dm.test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Login {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
//    driver = new HtmlUnitDriver();
    baseUrl = "http://192.168.1.200";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl + "/upload/");
    driver.findElement(By.id("J_sidebar_login")).click();
    driver.findElement(By.id("J_u_login_username")).clear();
    driver.findElement(By.id("J_u_login_username")).sendKeys("李德明");
    driver.findElement(By.id("J_u_login_password")).clear();
    driver.findElement(By.id("J_u_login_password")).sendKeys("111111");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    
    driver.findElement(By.cssSelector("span.header_post")).click();
    driver.findElement(By.xpath("//ul[@id='J_forum_list']/li")).click();
    driver.findElement(By.xpath("//ul[@id='J_forum_ul']/li")).click();
    driver.findElement(By.id("J_head_forum_sub")).click();
    driver.findElement(By.id("J_atc_title")).clear();
    driver.findElement(By.id("J_atc_title")).sendKeys("20150421发帖");
//    driver.findElement(By.cssSelector("body.editor_content")).sendKeys(" Love you ~");
    driver.findElement(By.xpath("//body[@spellcheck='false']"));;
//    System.out.println(text);
    
    
  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
