import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ClickButtonTest1 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testClickButton() {
        driver.get("https://demoqa.com/elements");

        WebElement buttonsOption = driver.findElement(By.xpath("//li[@id='item-4']"));
        buttonsOption.click();

        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='mt-4']//button[contains(@class, 'btn btn-primary')]"));
        for (WebElement button : buttons) {
            if (button.getText().equals("Click Me")) {
                button.click();
                break;
            }
        }

        WebElement message = driver.findElement(By.xpath("//p[@id='dynamicClickMessage']"));
        Assert.assertTrue(message.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
