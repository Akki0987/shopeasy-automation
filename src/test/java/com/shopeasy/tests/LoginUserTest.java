package com.shopeasy.tests;

import com.shopeasy.baseTest.Base;
import com.shopeasy.pages.Login_Page;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTest extends Base {

    @Test
    public void loginWithValidCredentials() {

        String email = "akkii@gmail.com";
        String password = "Akki@123";
        String name = "Abhishek Kholiya";

//      Verify that home page is visible successfully
        Assert.assertEquals(homePage.getHomeBtnColor(), "rgba(255, 165, 0, 1)", "Homepage not loaded");
        Assert.assertTrue(homePage.isSliderVisible(), "Homepage not loaded properly");

//      Click on 'Signup / Login' button
        Login_Page loginPage = homePage.clickLoginSignupBtn();

//      Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getRegistrationFormHeader(), "Login to your account", "Login form not loaded");

//      Enter correct email address and password
        loginPage.enterLoginEmailAndPassword(email, password);

//      Click 'login' button
        loginPage.clickLogin();

//      Verify that 'Logged in as username' is visible
        Assert.assertEquals(homePage.getLoggedUser(), "Logged in as "+ name, "User didnt logged in");
    }
}