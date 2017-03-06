package es.gorka.edu.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class InsertNewPost {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@Value("http://localhost:${local.server.port}${server.context-path:}")
	String documentRootUrl;

  @Before
  public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUsuarioIntroduceUnPostNuevo() throws Exception {
		driver.get(documentRootUrl + "/board.html");
    driver.findElement(By.xpath("//input[@name='userName']")).clear();
    driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("123");
    driver.findElement(By.xpath("//textarea[@name='text']")).clear();
    driver.findElement(By.xpath("//textarea[@name='text']")).sendKeys("abc");
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    assertEquals("123", driver.findElement(By.xpath("//div[last()-1]/span")).getText());
    assertEquals("abc", driver.findElement(By.xpath("//div[last()-1]/pre")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
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
