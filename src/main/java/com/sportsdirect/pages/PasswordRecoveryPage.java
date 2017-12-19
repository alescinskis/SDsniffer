package com.sportsdirect.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sportsdirect.test.SportsdirectTest.driver;

public class PasswordRecoveryPage {
    public PasswordRecoveryPage() {

    }

    public PasswordRecoveryPage openPasswordRecoveryPage(String recoveryURL) {
        driver.get(recoveryURL);
        return new PasswordRecoveryPage();
    }

    public void enterNewPassword(String newPassword) {

        By passwordField = By.id("dnn_ctr53347402_PasswordReset_txtNewPassword");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));

        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(newPassword);
        driver.findElement(passwordField).sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(newPassword);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

}
