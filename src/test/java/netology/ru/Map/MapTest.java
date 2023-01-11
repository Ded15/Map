package netology.ru.Map;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUppAll(){ WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get ("http://localhost:9999/");
    }

    @AfterEach
    void  tearDown(){
        driver.quit();
        driver=null;
    }

    @Test
    void positiveStringValidation() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванова Нина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79524064315");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals(expected,actual);
    }
}
