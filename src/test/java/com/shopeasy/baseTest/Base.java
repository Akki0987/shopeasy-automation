package com.shopeasy.baseTest;

import com.shopeasy.pages.Home_Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    protected Home_Page homePage;
    WebDriver driver;
    WebDriverWait wait;

    public WebDriver setup() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/shopeasy/resources/config.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akki\\Desktop\\Project AG\\shopeasy-automation\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("brave"))
        {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akki\\Desktop\\Project AG\\shopeasy-automation\\drivers\\chromedriver.exe");
            driver = new ChromeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\Akki\\Desktop\\Project AG\\shopeasy-automation\\drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
         else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

         wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         return driver;
    }

    @BeforeMethod()
    public Home_Page launchApplication() throws IOException {
        driver = setup();
        homePage = new Home_Page(driver);
        homePage.goToLandingPage();
        return homePage;
    }

    @AfterMethod()
    public void closeUp()
    {
        driver.quit();
    }
}
