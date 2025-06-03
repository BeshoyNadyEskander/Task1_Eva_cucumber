package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    private final WebDriver driver;

    private final By usernameLocator = By.cssSelector("input[name=\"username\"]");
    private final By passwordLocator = By.cssSelector("input[name=\"password\"]");
    private final By loginButtonLocator = By.xpath("//button[@type=\"submit\"]");

   public P01_LoginPage(WebDriver driver) {
       this.driver = driver;
   }

public P01_LoginPage enterUsername(String usernameText) {
       Utility.sendData(driver, usernameLocator, usernameText);
       return this;
}
public P01_LoginPage enterPassword(String passwordText) {
       Utility.sendData(driver, passwordLocator, passwordText);
       return this;
}

public P02_DashboardPage clickOnLoginButton()
{
    Utility.clickingOnElement(driver, loginButtonLocator);
    return new P02_DashboardPage(driver);
}



}
