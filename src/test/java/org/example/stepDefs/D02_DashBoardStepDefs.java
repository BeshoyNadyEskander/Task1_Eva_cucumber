package org.example.stepDefs;

import Pages.P02_DashboardPage;
import Utilities.LogsUtils;
import io.cucumber.java.en.And;
import Pages.P01_LoginPage;
import Pages.P02_DashboardPage;
import Utilities.LogsUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static DriverFactory.DriverFactory.getDriver;

public class D02_DashBoardStepDefs {

    @And("admin click on Admin button")
    public void clickOnAdminButton()
    {
        new P02_DashboardPage(getDriver()).clickOnAdminButton();
        LogsUtils.info("Admin button is clicked");
    }
}
