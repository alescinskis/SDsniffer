package com.sportsdirect.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sportsdirect.test.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SignInPage {

    public SignInPage clickForgotPassword() {
        driver.findElement(By.className("ForgotPasswordLinkButton")).click();
        return this;
    }

    public void sendRecoveryEmail(String email) {
        By emailField = By.cssSelector(".dnnFormItem");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).click();
        driver.switchTo().activeElement().sendKeys(email);
        driver.switchTo().activeElement().sendKeys(ENTER);
    }

}
