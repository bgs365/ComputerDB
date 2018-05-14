package com.excilys.cdb.integration;

import java.util.ResourceBundle;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDashboard {
  private static WebDriver selenium;
  private static String baseUrl;
  
  @BeforeClass
  public static void setUp()  {
      baseUrl = "http://localhost:8085/ComputerDB/";
      System.setProperty("webdriver.chrome.driver", ResourceBundle.getBundle("driver").getString("chromeDriver"));
      selenium = new ChromeDriver();
  }

  @Test
  public void testPage() throws Exception {
      selenium.navigate().to(baseUrl+"dashboard");
      selenium.navigate().to(baseUrl+"addComputer");
      selenium.navigate().to(baseUrl+"editComputer");
  }

  @After
  public void tearDown() throws Exception {
      selenium.close();;
  }
}
