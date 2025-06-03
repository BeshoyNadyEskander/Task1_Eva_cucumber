package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_DashboardPage {

    private final WebDriver driver;

    public P02_DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By usernameLocator = By.xpath("//li[@class=\"oxd-userdropdown\"] //p");
    private final By adminLocator = By.cssSelector("div[class=\"oxd-sidepanel-body\"] li:nth-of-type(1)");

    public Boolean assertOnUrlOfDashboardPage(String expectedUrl)
    {

        LogsUtils.info("Actual URL of Dashboard page: " +driver.getCurrentUrl() );
            return driver.getCurrentUrl().equals(expectedUrl);

    }
    public P02_DashboardPage assertOnUsernameIsDisplayed()
    {
        try {
            Utility.findWebElement(driver ,usernameLocator).isDisplayed();
        }catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return this;
    }

    public P03_AdminPage clickOnAdminButton()
    {
        Utility.clickingOnElement(driver, adminLocator);

        return new P03_AdminPage(driver);
    }
}
