package be.kdg.chat.prog4.tdd.integratie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ITroot {
    @Before
    public void setUp() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:8080/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("username"));
        element.sendKeys("testuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("testuser");
        element.submit();
    }

    @Test
    public void testAddUser() throws InterruptedException {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:8080/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("testuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("testuser");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: testuser"));
    }

    @Test
    public void testRemoveUser() throws InterruptedException {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:8080/tdd/add-user");
        WebElement element = driver.findElement(By.id("testuser"));
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        driver.get("http://127.0.0.1:8080/tdd");
        element = driver.findElement(By.name("username"));
        element.sendKeys("testuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("testuser");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> !(d.findElement(By.id("error")).getText().equals("")));
        element = driver.findElement(By.id("error"));
        String error = element.getText();
        Assert.assertEquals("Wrong username or password.", error);
    }
}
