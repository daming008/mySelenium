package com.phpwind.register;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Register {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  
	  File file = new File("D:/adt-bundle-windows-x86-20130917/eclipse/chromedriver.exe"); //chromedriver.exe所在路径
	  System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
	  driver = new ChromeDriver();
	  
    //driver = new FirefoxDriver();
    baseUrl = "http://192.168.1.200/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
	public void read(){
		Workbook readwb = null;
		try {
			InputStream instream = new FileInputStream("e:/excelFile/注册数据.xls");
			readwb = Workbook.getWorkbook(instream);
			
			//获取第一张sheeet表
			Sheet readSheet = readwb.getSheet(0);
			//获取总列数
			int rsColumns = readSheet.getColumns();
			//获取总行数
			int rsRows = readSheet.getRows();
			
			for(int i=1;i<rsRows;i++){
//				for(int j=0;j<rsColumns;j++){
//					Cell cell = readSheet.getCell(j, i);
//					System.out.print(cell.getContents()+" :");
//				}
				String username = readSheet.getCell(0, i).getContents();
				String password = readSheet.getCell(1, i).getContents();
				String repassword = readSheet.getCell(2, i).getContents();
				String email = readSheet.getCell(3, i).getContents();
				System.out.println(username+"::"+email);
				System.out.println("~~~~~~~~~~~~");
				testRegister(username, password, repassword, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			readwb.close();
		}
	}

  public void testRegister(String username,String password,String repassword,String email) throws Exception {
    driver.get(baseUrl + "/upload2/index.php?m=u&c=register");
    driver.findElement(By.id("J_reg_username")).clear();
    driver.findElement(By.id("J_reg_username")).sendKeys(username);
    driver.findElement(By.id("J_reg_password")).clear();
    driver.findElement(By.id("J_reg_password")).sendKeys(password);
    driver.findElement(By.id("J_reg_repassword")).clear();
    driver.findElement(By.id("J_reg_repassword")).sendKeys(repassword);
    driver.findElement(By.id("J_reg_email")).clear();
    driver.findElement(By.id("J_reg_email")).sendKeys(email);
    driver.findElement(By.id("J_reg_code")).clear();
    //获取验证码
    String codeValue = driver.manage().getCookieNamed("ldmcode2").getValue();
    driver.findElement(By.id("J_reg_code")).sendKeys(codeValue);
    System.out.println(codeValue);
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("J_head_user_a")).click();
    driver.findElement(By.linkText("退出")).click();
    Thread.sleep(1000);
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
