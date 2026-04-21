package com.shopeasy.pages;

import com.shopeasy.pojo.UserDetails;
import com.shopeasy.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUp_Page extends Utilities {

    WebDriver driver;

    public SignUp_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div.login-form h2:first-child")
    WebElement registrationFormHeader;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(css = "input#id_gender1")
    WebElement maleGender;

    @FindBy(css = "input#id_gender2")
    WebElement femaleGender;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "days")
    WebElement dayInput;

    @FindBy(id = "months")
    WebElement monthInput;

    @FindBy(id = "newsletter")
    WebElement newsLetter;

    @FindBy(id = "optin")
    WebElement offers;

    @FindBy(id = "years")
    WebElement yearInput;

    @FindBy(id = "first_name")
    WebElement firstNameInput;

    @FindBy(id = "last_name")
    WebElement lastNameInput;

    @FindBy(id = "company")
    WebElement companyInput;

    @FindBy(id = "address1")
    WebElement address1Input;

    @FindBy(id = "address2")
    WebElement address2Input;

    @FindBy(id = "country")
    WebElement countryInput;

    @FindBy(id = "state")
    WebElement stateInput;

    @FindBy(id = "city")
    WebElement cityInput;

    @FindBy(id = "zipcode")
    WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    WebElement mobNoInput;

    @FindBy(css = "button[data-qa='create-account']")
    WebElement createAccountBtn;

    public Boolean isFormDisplayed()
    {
        return registrationFormHeader.isDisplayed();
    }

    public String getNameValue()
    {
        return nameField.getAttribute("value");
    }

    public String getEmailValue()
    {
        return emailField.getAttribute("value");
    }

    public void selectMaleTitle()
    {
        maleGender.click();
    }

    public void selectFemaleTitle()
    {
        femaleGender.click();
    }

    public void enterPassword(String password)
    {
        passwordField.sendKeys(password);
    }

     public void setDOB(String day, String month, String year)
     {
         new Select(waitForVisibility(dayInput)).selectByValue(day);
         new Select(waitForVisibility(monthInput)).selectByVisibleText(month);
         new Select(waitForVisibility(yearInput)).selectByValue(year);
     }

     public void selectNewsletter()
     {
         scrollToElementAndClick(waitForElementClickable(newsLetter));
     }

    public void selectReceiveOffers()
    {
        scrollToElementAndClick(waitForElementClickable(offers));
    }

    public void enterUserDetails(String firstName, String lastName, String company, String address1, String address2,  String country, String state, String city, String zipcode, String mobileNumber)
    {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        companyInput.sendKeys(company);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
        new Select(countryInput).selectByValue(country);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipcodeInput.sendKeys(zipcode);
        mobNoInput.sendKeys(mobileNumber);
    }

    public AccountCreated_Page clickCreateAccount()
    {
        scrollToElementAndClick(createAccountBtn);
        return new AccountCreated_Page(driver);
    }
}
