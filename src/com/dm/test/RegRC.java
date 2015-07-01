package com.dm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class RegRC {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://192.168.1.200/");
		selenium.start();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRegRC() throws Exception {
		
		String username = "ldm0425003";
		
		selenium.open("/upload/");
		selenium.click("link=Á¢¼´×¢²á");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=J_reg_username", username);
		selenium.type("id=J_reg_password", "111111");
		selenium.type("id=J_reg_repassword", "111111");
		selenium.type("id=J_reg_email", username+"@qq.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.waitForPageToLoad("30000");
		
//		String text = selenium.getText("xpath=(//dd[@id='J_reg_tip_username']/span)");
//		System.out.println(text+"ll");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
