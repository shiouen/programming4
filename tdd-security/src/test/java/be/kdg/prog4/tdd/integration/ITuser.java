package be.kdg.prog4.tdd.integration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ITuser {
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
    public void testAddFavorite() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("normaluser");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: normaluser"));
        element = driver.findElement(By.name("favorite"));
        element.sendKeys("pasta");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: normaluser"));
        element = driver.findElement(By.name("favorites"));
        List<WebElement> rows = element.findElements(By.tagName("td"));
        assertEquals(1, rows.size());
        assertEquals("pasta", rows.get(0).getText());

        element = driver.findElement(By.name("favorite"));
        element.sendKeys("frieten");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: normaluser"));
        element = driver.findElement(By.name("favorites"));
        rows = element.findElements(By.tagName("td"));
        assertEquals(2, rows.size());
        String favorite1 = rows.get(0).getText();
        String favorite2 = rows.get(1).getText();
        assertEquals("pasta", favorite1);
        assertEquals("frieten", favorite2);
    }

    @Test
    public void normalUserShouldNotAddUsers() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("normaluser");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: normaluser"));
        driver.get("http://127.0.0.1:9966/tdd/add-user");
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> !(d.findElement(By.tagName("title")).getText().equals("")));
        element = driver.findElement(By.tagName("title"));
        String error = element.getText();
        assertEquals("Apache Tomcat/7.0.47 - Error report", error);
    }
}
