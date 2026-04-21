package com.shopeasy.pages;

import com.shopeasy.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Home_Page extends Utilities {

    WebDriver driver;

    public Home_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    By homeBtn = By.cssSelector("ul a[href='/']");
    By slider = By.id("slider");

    @FindBy(css="a[href='/login']")
            WebElement signupLoginBtn;

    @FindBy(css = "ul.nav.navbar-nav li:nth-of-type(10) a")
    WebElement loggedBtn;

    @FindBy(css = "a[href='/delete_account']")
    WebElement deleteAccountBtn;


    public void goToLandingPage() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/shopeasy/resources/config.properties");
        prop.load(fis);
        driver.get(prop.getProperty("url"));
    }
    public String getHomeBtnColor()
    {
        return waitForElementVisibilityBy(homeBtn).getCssValue("color");
    }

    public boolean isSliderVisible()
    {
        return waitForElementVisibilityBy(slider).isDisplayed();
    }

    public Login_Page clickLoginSignupBtn()
    {
        waitForElementClickable(signupLoginBtn).click();
        return new Login_Page(driver);
    }

    public String getLoggedUser()
    {
        return waitForVisibility(loggedBtn).getText();
    }

    public AccountDeleted_Page clickDeleteAccount()
    {
        deleteAccountBtn.click();
        return new AccountDeleted_Page(driver);
    }
}
