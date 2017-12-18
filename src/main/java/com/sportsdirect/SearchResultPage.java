package com.sportsdirect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static com.sportsdirect.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SearchResultPage {
    public SearchResultPage(RemoteWebDriver driver) {

    }

    protected SearchResultPage expandPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("h3.productFilterTitle"), 0));
        driver.findElements(By.cssSelector("h3.productFilterTitle")).get(4).click();
        return this;
    }

    protected SearchResultPage setSearchPrice(String min, String max){
        driver.findElement(By.id("PriceFilterTextEntryMin")).sendKeys(min);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(max);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(ENTER);
        return this;
    }

    protected String getRandomFilteredPrice(){
        int listSize = driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice.productHasRef")).size();
        Random random = new Random();
        int randomItem = random.nextInt(listSize - 1);
        return driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice.productHasRef")).get(randomItem).getText().trim().replaceAll("[€ ]", "").replace(",", ".");
    }

}
