package Utilities;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";

    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
           // LogsUtils.info("Text is: " + driver.findElement(locator).getText());
            return driver.findElement(locator).getText();
        }catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "";
        }


    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("SSS-ss-mm-hh-dd-MM-yyyy").format(new Date());
    }




    public static int generateRandomNumber(int upperBound) { //0 >> upper-1  > 5
        return new Random().nextInt(upperBound) + 1;
    }

    //Set >> unique >> 1,2,3,4,3 > condition
    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) //5 >> 50
    {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfProductsNeeded) { //11111 > 1 2 10 5 7
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static Set<Integer> selectedRandomUniqueNumber(int numberProductsNeeded, List<WebElement> webElementList) {
        Set<Integer> genertedNumbers = new HashSet<>();
        // int count = webElementList.size();
        while (genertedNumbers.size() < numberProductsNeeded) {
            int randomNumber = generateRandomNumber(webElementList.size());
            genertedNumbers.add(randomNumber);
        }
        return genertedNumbers;
    }

    public static boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return true;
    }


    // handle cookie and inject cookie
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }


}
