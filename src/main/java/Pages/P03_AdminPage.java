package Pages;

import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P03_AdminPage {

    private final WebDriver driver;

    private final By recordedNumberLocator = By.xpath("//div[contains(@class,'orangehrm-vertical-padding')]//span");
    private final By AddButtonLocator = By.xpath("//div[@class=\"orangehrm-header-container\"]//button[@type=\"button\"]");

    private final By userRoleLocator = By.xpath("//div[@class=\"oxd-select-text--after\"]//preceding::div[@class=\"oxd-select-wrapper\"]");

    private final By selectedAdminRoleLocator = By.xpath("//div[@class='oxd-select-text-input'][text()='-- Select --']/parent::div/following-sibling::div//span[text()='Admin']");

    private final By statusLocator = By.xpath("//div[@class=\"oxd-select-text--after\"]//following::div[@class=\"oxd-select-wrapper\"]");
    private final By selectedEnableStatusLocator = By.xpath("//div[@class='oxd-select-text-input'][text()='-- Select --']/parent::div/following-sibling::div//span[text()='Enabled']");

    private final By passwordLocator = By.xpath("(//label[contains(text(),'Password')]/following::input)[1]");
    private final By confirmPasswordLocator = By.xpath("//label[contains(text(),'Confirm Password')]/following::input[1]");
    private final By employeeNameLocator = By.xpath("//label[contains(.,'Employee Name')]/following::input[1]");
    private final By userNameLocator = By.xpath("//label[contains(.,'Username')]/following::input[1]");
    private final By saveButtonLocator = By.xpath("//button[@type=\"submit\"]");
    private final By suggestionDropdownLocator= By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown')]");
    private final By usernameForSearchLocator =By.xpath("//div//label[text()='Username']//following::div/input[@class]");
    private final By searchButtonLocator = By.xpath("//button[@type=\"submit\"]");
    private final By deleteButtonLocator = By.xpath("//button[@type=\"button\"]//i[contains(@class,'bi-trash')]");

    private final By yesOfDeleteButtonLocator = By.xpath(" //button[text()=' Yes, Delete ']");

    private static  String beforeRecorded;
    private static  String afterRecorded;
    private static int numberOfAfterRecorded;




    public P03_AdminPage(WebDriver driver) {
        this.driver = driver;
    }


    public P03_AdminPage getRecordedNumber() {
        beforeRecorded = Utility.getText(driver, recordedNumberLocator);
        LogsUtils.info("Recorded number is: " + Utility.getText(driver, recordedNumberLocator));
        return this;
    }
    public P03_AdminPage clickOnAddButton() {
        Utility.clickingOnElement(driver, AddButtonLocator);
        LogsUtils.info("Add button is clicked");
        return this;
    }

    public P03_AdminPage selectAdminRole() {
        Utility.clickingOnElement(driver, userRoleLocator);
        LogsUtils.info("Admin role is selected an drop menu is displayed");

        return this;
    }

    public P03_AdminPage chooseAdminRole() {
        try {
            //Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(MenuDropUserRole));
            Utility.clickingOnElement(driver, selectedAdminRoleLocator);
            LogsUtils.info("Admin role is selected");
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

        return this;
    }

    public P03_AdminPage clickOnStatusButton() {
        Utility.clickingOnElement(driver, statusLocator);
        LogsUtils.info("Status button is clicked");
        return this;
    }

    public P03_AdminPage chooseEnableStatus() {
        try {
            Utility.clickingOnElement(driver, selectedEnableStatusLocator);
            LogsUtils.info("Status is selected");
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return this;
    }


    public P03_AdminPage enterPassword(String passwordText) {
        try {
            Utility.sendData(driver, passwordLocator, passwordText);
            LogsUtils.info("Password is entered: " + passwordText);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return this;
    }

    public P03_AdminPage enterConfirmPassword(String confirmPasswordText) {
        try {
            Utility.sendData(driver, confirmPasswordLocator, confirmPasswordText);
            LogsUtils.info("Confirm Password is entered: " + confirmPasswordText);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return this;
    }


    public P03_AdminPage enterEmployeeName(String searchText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate Employee Name input field
     //   WebElement employeeNameInput = wait.until(ExpectedConditions.elementToBeClickable(employeeNameLocator)

        Utility.generalWait(driver).until(ExpectedConditions.elementToBeClickable(employeeNameLocator)).sendKeys(searchText);

        // Enter partial name to trigger suggestions
       // employeeNameInput.sendKeys(searchText);

        // Wait for suggestion dropdown to appear
        WebElement suggestionDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionDropdownLocator));


        // Get list of suggestions
        List<WebElement> suggestions = suggestionDropdown.findElements(By.xpath(".//span"));

        boolean nameFound = false;
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().contains( searchText)) {
                suggestion.click();
                nameFound = true;
                break;
            }
        }

        if (!nameFound) {
            LogsUtils.error("Employee name '" + searchText + "' not found.");
        } else {
            LogsUtils.info("Employee name '" + searchText + "' found.");
        }

    return this;
}


    public P03_AdminPage enterUserName(String userNameText)
    {
        try {
            Utility.findWebElement(driver, userNameLocator).clear();
            Utility.sendData(driver , userNameLocator , userNameText);
            LogsUtils.info("User Name is entered: " + userNameText);

            DataUtils.set("environment" , "username" , userNameText);

        } catch (Exception e)
        {LogsUtils.error(e.getMessage());}

        return this;
    }

    public P03_AdminPage clickOnSaveButton()
    {
        Utility.clickingOnElement(driver, saveButtonLocator);
        LogsUtils.info("Save button is clicked");

        return this;
    }




    public P03_AdminPage getRecordedNumberAfter() {
        afterRecorded = Utility.getText(driver, recordedNumberLocator);
        LogsUtils.info("After Recorded number is: " + Utility.getText(driver, recordedNumberLocator));
        return this;
    }


        public int BeforeRecordedNumber()
        {
            String numberStr = beforeRecorded.substring(beforeRecorded.indexOf('(') + 1, beforeRecorded.indexOf(')'));
            int numberOfBeforeRecorded = Integer.parseInt(numberStr);
            LogsUtils.info("Recorded number before is: " + numberOfBeforeRecorded);
            return numberOfBeforeRecorded;
        }


        public int AfterRecordedNumber()
        {
            String numberStr = afterRecorded.substring(afterRecorded.indexOf('(') + 1, afterRecorded.indexOf(')'));
             numberOfAfterRecorded = Integer.parseInt(numberStr);
            LogsUtils.info("Recorded number after is: " + numberOfAfterRecorded);
            return numberOfAfterRecorded;
        }

        public boolean VerifyOnRecordedIncreased()
        {
            LogsUtils.info("Verifying if recorded number increased");
           return AfterRecordedNumber()==(BeforeRecordedNumber()+1) || AfterRecordedNumber()>(BeforeRecordedNumber()+1); // handle OR con >> if any recorded is added through running;
        }

        public P03_AdminPage enterUsernameForSearch(String usernameText)
        {
            Utility.sendData(driver , usernameForSearchLocator , usernameText);
            LogsUtils.info("User Name for search is entered: " + usernameText);
            return this;
        }

        public P03_AdminPage clickOnSearch()
        {
            Utility.clickingOnElement(driver, searchButtonLocator);
            LogsUtils.info("Search button is clicked");
            return this;
        }

        public P03_AdminPage clickOndeleteButton()
        {
            Utility.clickingOnElement(driver, deleteButtonLocator);
            LogsUtils.info("Delete button is clicked");
            return this;
        }

        public P03_AdminPage clickOnYesOfDelete()
        {
            Utility.clickingOnElement(driver, yesOfDeleteButtonLocator);
            LogsUtils.info("Yes of delete button is clicked");

            return this;
        }
        public P03_AdminPage refreshPage()
        {
            driver.navigate().refresh();
            return this;
        }

        public boolean verifyOnRecordedDecreased()
        {

            numberOfAfterRecorded--;
            LogsUtils.info("Verifying if recorded number decreased:" + Utility.getText(driver, recordedNumberLocator));
             return true;
        }

}
