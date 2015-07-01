package com.dm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.thoughtworks.selenium.SeleneseTestCase;

@SuppressWarnings("deprecation")
public class Baidu extends SeleneseTestCase {
 private static String PROPERTIES_FILE = "setting.properties";
 private static Properties props = null;


 private void initProperties() { 
  String path = Baidu.class.getResource("/").getPath();
  path = path + PROPERTIES_FILE;
  System.out.print(path);
  props = new Properties();
  try {
   props.load(new FileInputStream(new File(path)));
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 private String getConfigParam(String key) {  //��ȡ�ļ��󣬸����Ӧ���������������ֵ
  return props.getProperty(key);
 }

 @SuppressWarnings("deprecation")
public void setUp() throws Exception {
  if (props == null) {
   initProperties();
  }

  String baseUrl = getConfigParam("baseURL");
  System.out.print(baseUrl);
  setUp(baseUrl, "*chrome");
 }

 public void tearDown() throws Exception {
  //selenium.stop();
 }

 @SuppressWarnings("deprecation")
public void testTabs() throws Exception {
  if (props == null) {
   initProperties();
  }
    selenium.open("/");
  selenium.type("kw", getConfigParam("search_text"));
  selenium.captureEntirePageScreenshot("C:\\login.png","");
  selenium.click("su");
  selenium.waitForPageToLoad("30000");
  selenium.click("kw");
  selenium.type("kw", "2");
  selenium.click("su");
  selenium.waitForPageToLoad("30000");
  selenium.click("kw");
  selenium.type("kw", "3");
  selenium.captureEntirePageScreenshot("C:\\selenium.png","");
  selenium.click("su");
  selenium.waitForPageToLoad("30000");


 }

 
}