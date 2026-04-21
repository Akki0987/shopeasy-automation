package com.shopeasy.pages;

import com.shopeasy.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page extends Utilities {

    WebDriver driver;

    public Login_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div.signup-form h2")
    WebElement signUpHeader;

    @FindBy(css="div.signup-form input[name='name']")
    WebElement nameField;

    @FindBy(css="div.signup-form input[name='email']")
    WebElement emailField;

    @FindBy(css="div.signup-form button[data-qa='signup-button']")
    WebElement signUpBtn;

    @FindBy(css = "div.login-form h2")
    WebElement loginHeader;

    @FindBy(css = "form[action='/login'] input[name='email']")
    WebElement loginEmailField;

    @FindBy(css = "form[action='/login'] input[name='password']")
    WebElement loginPasswordField;

    @FindBy(css = "form[action='/login'] button[data-qa='login-button']")
    WebElement loginBtn;


    public String getRegistrationFormHeader()
    {
        waitTillURLContains("login");
        return signUpHeader.getText();
    }

    public void enterName(String name)
    {
        nameField.sendKeys(name);
    }

    public void enterEmail(String email)
    {
        emailField.sendKeys(email);
    }


    public SignUp_Page clickSignUpBtn()
    {
        signUpBtn.click();
        return new SignUp_Page(driver);
    }

    public String getLoginFormHeader()
    {
        waitTillURLContains("login");
        return loginHeader.getText();
    }

    public void enterLoginEmailAndPassword(String email, String password)
    {
        loginEmailField.sendKeys(email);
        loginPasswordField.sendKeys(password);
    }

    public void clickLogin()
    {
        loginBtn.click();
    }
}
