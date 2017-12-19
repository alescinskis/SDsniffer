package com.sportsdirect.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.sportsdirect.test.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SearchResultPage {
    public SearchResultPage() {
    }

    public SearchResultPage expandPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".FilterCollapseImage.glyphicon"), 3));
        driver.findElements(By.cssSelector(".FilterCollapseImage.glyphicon")).get(4).click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        return this;
    }

    public SearchResultPage setSearchPrice(String min, String max){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("PriceFilterTextEntryMax"), 0));
        driver.findElement(By.id("PriceFilterTextEntryMin")).sendKeys(min);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(max);
        driver.findElement(By.id("PriceFilterTextEntryMax")).sendKeys(ENTER);
        return this;
    }

    public String getRandomFilteredPrice(){
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        int listSize = driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice")).size();
        Random random = new Random();
        int randomItem = random.nextInt(listSize - 1);
        return driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice.productHasRef"))
                .get(randomItem)
                .getText()
                .trim()
                .replaceAll("[€ ]", "")
                .replace(",", ".");
    }

    public List<String> getFilteredPrices(){
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        List<String> prices = new ArrayList<>();
        List<WebElement> pricesElements = driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice.productHasRef"));
        prices.addAll(pricesElements.stream().map(WebElement::getText).collect(Collectors.toList()));

        for (int i = 0; i < prices.size(); i++) {
            prices.get(i).trim().replace(",", ".").replaceAll("[€ ]", "");
        }

        prices.size();
        return prices;
    }

}
