package com.sportsdirect.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.sportsdirect.test.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SignInPage {

    public SignInPage clickForgotPassword(){
        driver.findElement(By.className("ForgotPasswordLinkButton")).click();
        return this;
    }

    public SignInPage sendRecoveryEmail(String email){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".dnnFormItem")).click();
        driver.switchTo().activeElement().sendKeys(email);
        driver.switchTo().activeElement().sendKeys(ENTER);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return this;
    }

}
