package com.sportsdirect;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.sportsdirect.SportsdirectTest.driver;

public class Homepage {

    private By brands = By.xpath("id(\"topMenu\")/ul[1]/li[8]/a[1]");

    BrandsPage openBrandPage(String brandName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(brands, 0));

        if(!driver.findElement(By.cssSelector("div.spanCurrencyLanguageSelector")).getText().contains("EUR")){
            driver.findElement(By.cssSelector(".spanCurrencyLanguageSelector")).click();
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("[value='EUR'][name='lCurrenciesSwitcher']")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(brands, 0));
        }
        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(brands)).build().perform();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("li.level1.b_" + brandName), 0));
        action.moveToElement(driver.findElement(By.cssSelector("li.level1.b_" + brandName))).click().build().perform();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return new BrandsPage(driver, brandName);
    }
}
