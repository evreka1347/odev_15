import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AddRecordTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddRecord(){
        driver.get("https://demoqa.com/webtables");

        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        driver.findElement(By.id("firstName")).sendKeys("Esma");
        driver.findElement(By.id("lastName")).sendKeys("Coban");
        driver.findElement(By.id("userEmail")).sendKeys("esmacoban@gmail.com");
        driver.findElement(By.id("age")).sendKeys("31");
        driver.findElement(By.id("salary")).sendKeys("47500");
        driver.findElement(By.id("department")).sendKeys("Yazılım Departmanı");

        driver.findElement(By.id("submit")).click();

        WebElement fourthRowSalary = driver.findElement(By.cssSelector("div.rt-tr-group:nth-child(4) div.rt-td:nth-child(5)"));
        String initialSalary = fourthRowSalary.getText();

        WebElement editButton = driver.findElement(By.cssSelector("div.rt-tr-group:nth-child(4) span[title='Edit']"));
        editButton.click();
        WebElement salaryField = driver.findElement(By.id("salary"));
        salaryField.clear();
        salaryField.sendKeys("50000");
        driver.findElement(By.id("submit")).click();

        WebElement editedSalary = driver.findElement(By.cssSelector("div.rt-tr-group:nth-child(4) div.rt-td:nth-child(5)"));
        String updatedSalary = editedSalary.getText();
        Assert.assertEquals(updatedSalary, "50000");
        Assert.assertNotEquals(initialSalary, updatedSalary);
    }
    @AfterClass
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}

