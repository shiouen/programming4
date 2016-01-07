package be.kdg.chat.prog4.tdd.frontend.integration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ITloginUser {
    @Before
    public void setup() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("username"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("normaluser");
        element.submit();
    }

    @Test
    public void testHomeScreenNormalUser() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("normaluser");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: normaluser"));
        element = driver.findElement(By.name("favorites"));
        String tagname = element.getTagName();
        assertEquals("table", tagname);
        element = driver.findElement(By.name("favorite"));
        tagname = element.getTagName();
        assertEquals("input", tagname);
        element = driver.findElement(By.name("Add favorite"));
        tagname = element.getTagName();
        assertEquals("input", tagname);
        String type = element.getAttribute("type");
        assertEquals("submit", type);
    }

    @Test
    public void testLoginWrongUser() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("kris");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong password");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> !(d.findElement(By.id("error")).getText().equals("")));
        element = driver.findElement(By.id("error"));
        String error = element.getText();
        assertEquals("Wrong username or password.", error);
    }
}
