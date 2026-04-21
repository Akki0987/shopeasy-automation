package com.shopeasy.tests;

import com.shopeasy.baseTest.Base;
import com.shopeasy.pages.AccountCreated_Page;
import com.shopeasy.pages.AccountDeleted_Page;
import com.shopeasy.pages.Login_Page;
import com.shopeasy.pages.SignUp_Page;
import com.shopeasy.pojo.UserDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends Base {

    @Test(dataProvider = "getDataFromJsonUsingPOJO", dataProviderClass = com.shopeasy.resources.DataProviders.UserData.class)
    public void registerUser(UserDetails User)
    {
//      3. Verify that home page is visible successfully
        Assert.assertEquals(homePage.getHomeBtnColor(), "rgba(255, 165, 0, 1)", "Homepage not loaded");
        Assert.assertTrue(homePage.isSliderVisible(), "Homepage not loaded properly");

//      4. Click on 'Signup / Login' button
        Login_Page loginPage = homePage.clickLoginSignupBtn();

//      5. Verify 'New User Signup!' is visible
        Assert.assertEquals(loginPage.getRegistrationFormHeader(), "New User Signup!", "Signup/Login page not loaded");

//      6. Enter name and email address
        loginPage.enterName(User.getName());
        loginPage.enterEmail(User.getEmail());

//      7. Click 'Signup' button
        SignUp_Page signUpPage = loginPage.clickSignUpBtn();

//      8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(signUpPage.isFormDisplayed(), "Account Information Not visible");

//      9. Fill details: Title, Name, Email, Password, Date of birth
//      Validate name email already filled
        Assert.assertEquals(signUpPage.getNameValue(), User.getName(), "Name not filled");
        Assert.assertEquals(signUpPage.getEmailValue(), User.getEmail(), "Email not filled");

//      Select Title
        signUpPage.selectMaleTitle();

//      Set password
        signUpPage.enterPassword(User.getPassword());

//      Set DOB
        signUpPage.setDOB(User.getDobDay(),User.getDobMonth(), User.getDobYear());

//      Select checkbox 'Sign up for our newsletter!' and 'Receive Offers from our partners!'
        signUpPage.selectNewsletter();
        signUpPage.selectReceiveOffers();

//      12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        signUpPage.enterUserDetails(User.getFirstName(), User.getLastName(),User.getCompany(), User.getAddress1(), User.getAddress2(), User.getCountry(), User.getState(), User.getCity(), User.getZipcode(), User.getMobileNumber());

//      13. Click 'Create Account button'
        AccountCreated_Page accountCreatedPage =  signUpPage.clickCreateAccount();

//      14. Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible("account_created"), "Account not created");

//      15. Click 'Continue' button
        accountCreatedPage.clickContinue();

//      16. Verify that 'Logged in as username' is visible
        Assert.assertEquals(homePage.getLoggedUser(), "Logged in as "+ User.getName(), "User didnt logged in");

//      17. Click 'Delete Account' button
        AccountDeleted_Page accountDeletedPage = homePage.clickDeleteAccount();

//      18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertTrue(accountDeletedPage.getAccountDeletedStatus(), "Account not deleted");
        accountDeletedPage.clickContinue();
    }
}