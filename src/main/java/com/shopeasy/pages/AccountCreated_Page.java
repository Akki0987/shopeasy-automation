package com.shopeasy.pages;

import com.shopeasy.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountCreated_Page extends Utilities {

    WebDriver driver;

    public AccountCreated_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div h2[data-qa='account-created']")
    WebElement header;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueBtn;

    public Boolean isAccountCreatedHeaderVisible(String text)
    {
        waitTillURLContains(text);
        return header.isDisplayed();
    }

    public void clickContinue()
    {
        continueBtn.click();
    }
}
