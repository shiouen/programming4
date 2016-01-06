package be.kdg.chat.prog4.tdd.integratie;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ITloginRoot {
    @Test
    public void testHomeScreenRoot() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:8080/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("username"));
        String tagName = element.getTagName();
        assertEquals("input", tagName);
        element = driver.findElement(By.name("password"));
        tagName = element.getTagName();
        assertEquals("input", tagName);
        element = driver.findElement(By.name("Add user"));
        tagName = element.getTagName();
        assertEquals("input", tagName);
        String type = element.getAttribute("type");
        assertEquals("submit", type);
    }
}
