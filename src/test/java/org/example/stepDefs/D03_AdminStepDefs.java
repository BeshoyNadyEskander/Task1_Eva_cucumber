package org.example.stepDefs;

import Pages.P03_AdminPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;
import static Utilities.DataUtils.*;
import static Utilities.Utility.getTimeStamp;

public class D03_AdminStepDefs {


  //  private static final String STOREDUSERNAME = set("environment", "addedUserName" , USERNAME);


    @And("Get the number of records found")
    public void get_the_number_of_records_found() {
        new P03_AdminPage(getDriver()).getRecordedNumber();
    }

    @And("admin could click on add button")
    public void admin_could_click_on_add_button() {
        new P03_AdminPage(getDriver()).clickOnAddButton();
    }

    @And("Fill the required data")
    public void fill_all_required_data()
    {
        new P03_AdminPage(getDriver())
                .selectAdminRole()
                .chooseAdminRole()
                .clickOnStatusButton()
                .chooseEnableStatus()
                .enterPassword(getJsonData("fillNewUserData" , "password"))
                .enterConfirmPassword(getJsonData("fillNewUserData" , "confirmPassword"))
                .enterEmployeeName(getJsonData("fillNewUserData", "employeeName"))
                .enterUserName(getJsonData("fillNewUserData", "userName")+"_"+getTimeStamp())
                .clickOnSaveButton()
                .getRecordedNumberAfter();
    }

    @Then("Verify that the number of records increased by one")
    public void verify_that_the_number_of_records_increased_by_one()
    {
        Assert.assertTrue(new P03_AdminPage(getDriver()).VerifyOnRecordedIncreased());
     }


    @When("search on new added username")
    public void addNewUserName() throws IOException
    {
        new P03_AdminPage(getDriver()).enterUsernameForSearch(getPropertyValue("environment", "username"))
                .clickOnSearch()
                .clickOndeleteButton()
                .clickOnYesOfDelete()
                .refreshPage();
    }
    @Then("Verify that the number of records decreased by one")
    public void assertOnRecordedIsDecreasedAfterDeleted()
    {
        Assert.assertTrue(new P03_AdminPage(getDriver()).verifyOnRecordedDecreased());
    }



}
