package com.sportsdirect.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.sportsdirect.test.SportsdirectTest.driver;

public class Homepage {

    public static String defaultURL = "https://lv.sportsdirect.com";
    private By brands = By.xpath("id('topMenu')/ul[1]/li[8]/a[1]");

    public Homepage openHomepage() {
        driver.get(defaultURL);
        return this;
    }

    public FiretrapPage openFireTrapPage() {
        popupCloser();

        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(brands)).build().perform();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("li.level1.b_firetrap"), 0));
        action.moveToElement(driver.findElement(By.cssSelector("li.level1.b_firetrap"))).click().build().perform();
        return new FiretrapPage();
    }

    public SkechersPage openSkechersPage() {
        popupCloser();

        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(brands)).build().perform();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("li.level1.b_sketchers"), 0));
        action.moveToElement(driver.findElement(By.cssSelector("li.level1.b_sketchers"))).click().build().perform();
        return new SkechersPage();
    }

    public SignInPage clickOnSignIn() {
        popupCloser();
        driver.findElement(By.id("dnn_dnnLOGIN_loginLink")).click();
        return new SignInPage();
    }

    public Homepage changeCurrencyTo(String currencyCode) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(brands, 0));

        By currencyCodeButton = By.cssSelector("[value='" + currencyCode + "'][name='lCurrenciesSwitcher']");
        if (!driver.findElement(By.cssSelector("div.spanCurrencyLanguageSelector")).getText().contains(currencyCode)) {
            driver.findElement(By.cssSelector(".spanCurrencyLanguageSelector")).click();
            wait.until(ExpectedConditions.elementToBeClickable(currencyCodeButton));
            driver.findElement(currencyCodeButton).click();
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(brands, 0));
        }
        return this;
    }

    private void popupCloser() {
        try {
            By popupCloseButton = By.cssSelector(".advertPopup .close");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(popupCloseButton));
            driver.findElement(popupCloseButton).click();
        } catch (NoSuchElementException ex) {
            System.out.println("pop-up didn't appear");
        }
    }
}
