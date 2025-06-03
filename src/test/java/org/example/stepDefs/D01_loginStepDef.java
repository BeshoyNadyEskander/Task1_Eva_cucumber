package org.example.stepDefs;

import Pages.P01_LoginPage;
import Pages.P02_DashboardPage;
import Utilities.LogsUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;


public class D01_loginStepDef {

    @Given("admin navigate Login Page")
    public void navigate_login_page() throws IOException {

        setupDriver(getPropertyValue("environment", "Browser"));
        LogsUtils.info("driver is opened on: " + getPropertyValue("environment", "Browser") );
        getDriver().get(getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("Page is redirected to the URL");

    }

    @When("admin enter valid username")
    public void enter_valid_username() {
        new P01_LoginPage(getDriver()).enterUsername(getJsonData("validLogin", "username"));
        LogsUtils.info("valid Username is entered" +" "+getJsonData("validLogin", "username"));
    }

    @And("admin enter valid password")
    public void enter_valid_password() {
        new P01_LoginPage(getDriver()).enterPassword(getJsonData("validLogin", "password"));
        LogsUtils.info("valid Password is entered" +" "+getJsonData("validLogin", "password"));
    }

    @And("admin click on login button")
    public void clickOnLoginButton()
    {
        new P01_LoginPage(getDriver()).clickOnLoginButton();
        LogsUtils.info("Login button is clicked");
    }

    @Then("verify admin login successfully")
    public void verify_user_login_successfully()throws IOException
    {

//        new P02_DashboardPage(getDriver()).assertOnUrlOfDashboardPage(getPropertyValue("environment", "DASHBOARD_URL"))
//                        .assertOnUsernameIsDisplayed();
//        LogsUtils.info("User is logged in successfully");

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(new P02_DashboardPage(getDriver()).assertOnUrlOfDashboardPage(getPropertyValue("environment", "DASHBOARD_URL")));
        soft.assertAll();
    }


}
