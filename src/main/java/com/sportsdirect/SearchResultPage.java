package com.sportsdirect;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.sportsdirect.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SearchResultPage {
    public SearchResultPage(RemoteWebDriver driver) {

    }

    protected SearchResultPage expandPrice(){
        driver.findElements(By.cssSelector("h3.productFilterTitle")).get(4).click();
        return this;
    }

    protected SearchResultPage setSearchPrice(String min, String max){
        driver.findElement(By.id("PriceFilterTextEntryMin")).sendKeys(min);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(max);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(ENTER);
        return this;
    }

}
