package com.shopeasy.utils;

import com.shopeasy.pojo.UserDetails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Utilities {

    WebDriver driver;
    WebDriverWait wait;
    public Utilities(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

//    Waits
    public WebElement  waitForElementVisibilityBy(By locator)
    {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibility(WebElement e)
    {
        return wait.until(ExpectedConditions.visibilityOf(e));
    }

    public WebElement waitForElementClickable(WebElement e)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public void waitTillURLContains(String text)
    {
        wait.until(ExpectedConditions.urlContains(text));
    }

//    Scroll
    public void scrollToElement(WebElement e)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true)", e);
    }

    public void scrollToElementAndClick(WebElement e)
    {
        scrollToElement(e);
        e.click();
    }


//    Read JsonData and Convert to HashMap

    public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {

//        Read json data to string
        File filePath = new File(System.getProperty("user.dir") + "/src/test/java/com/shopeasy/resources/testData/users.json");
        String jsonStringContent = FileUtils.readFileToString(filePath, StandardCharsets.UTF_8);

//         Convert json string to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data=  mapper.readValue(jsonStringContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    public List<UserDetails> getJsonDataToPojoObject(String path) throws IOException {

//        Read json data to string
        File filePath = new File(path);
        String jsonStringContent = FileUtils.readFileToString(filePath, StandardCharsets.UTF_8);

//         Convert json string to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List <UserDetails> data =  mapper.readValue(jsonStringContent, new TypeReference<List<UserDetails>>() {
        });

        return data;
    }

    public static String getScreenshot(String testcasename, WebDriver driver) throws IOException {
        String savePath = System.getProperty("user.dir")+"/screenshots"+ testcasename + ".png";

        TakesScreenshot ss = (TakesScreenshot)driver;
        File src = ss.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(src, new File(savePath));
        return savePath;
    }

}
