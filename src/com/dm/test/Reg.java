package com.dm.test;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Reg {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
	  File file = new File("D:/adt-bundle-windows-x86-20130917/eclipse/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
	  driver = new ChromeDriver();
    baseUrl = "http://192.168.1.200";
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  }

  @Test
  public void testFirsrReg() throws Exception {
	  String username = "ldm0425002";
    driver.get(baseUrl + "/upload/");
    driver.findElement(By.linkText("立即注册")).click();
    driver.findElement(By.id("J_reg_username")).clear();
    driver.findElement(By.id("J_reg_username")).sendKeys(username);
    driver.findElement(By.id("J_reg_password")).clear();
    driver.findElement(By.id("J_reg_password")).sendKeys("111111");
    driver.findElement(By.id("J_reg_repassword")).clear();
    driver.findElement(By.id("J_reg_repassword")).sendKeys("111111");
    driver.findElement(By.id("J_reg_email")).clear();
    driver.findElement(By.id("J_reg_email")).sendKeys(username+"@qq.com");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    
    String success = driver.findElement(By.xpath("//div[@class='reg_activation']/h1")).getText();
    if(success.contains(username)){
    	System.out.println(success);
    	System.out.println("测试通过");
    }
    
//    WebElement tips = driver.findElement(By.xpath("//dd[@id='J_reg_tip_username']/span"));
//    if(tips==null){
//    	
//    	
//    	System.out.println();
//    }
    //获取Cookie；
//    Set<Cookie> cookies = driver.manage().getCookies();
//    for(Cookie c : cookies){
//    	System.out.println(c);
//    }

//    driver.findElement(By.xpath("//ul[@id='J_forum_list']/li")).click();
   // String text1 = driver.findElement(By.xpath("//dd[@id='J_reg_tip_username']/span")).getText();
//    String findElement = driver.findElement(By.className("tips_icon_error"));
   // System.out.println(text1);
//    System.out.println(findElement);
  }

  @After
  public void tearDown() throws Exception {
	  String verificationErrorString = verificationErrors.toString();
    driver.quit();
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
