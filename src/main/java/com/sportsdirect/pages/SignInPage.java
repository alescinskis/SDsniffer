package com.sportsdirect.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.sportsdirect.test.SportsdirectTest.driver;

public class SignInPage {

    public SignInPage clickForgotPassword(){
        driver.findElement(By.className("ForgotPasswordLinkButton")).click();
        return this;
    }

    public SignInPage sendRecoveryEmail(String email){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.findElement(By.id("dnn_ctr54473_PasswordReset_UserName")).sendKeys(email);
        driver.findElement(By.id("dnn_ctr54473_PasswordReset_cmdSendPassword")).click();
        return this;
    }

}
