package com.sportsdirect.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sportsdirect.test.SportsdirectTest.driver;
import static org.openqa.selenium.Keys.ENTER;

public class SearchResultPage {
    SearchResultPage() {
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

    public List<BigDecimal> getFilteredPrices(){
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        Function<WebElement, BigDecimal> convert = element ->
                new BigDecimal(element.getText().trim().replace(",", ".").replaceAll("[€ ]", ""));

        List<WebElement> pricesElements = driver.findElements(By.cssSelector("span.CurrencySizeLarge.curprice.productHasRef"));

        return pricesElements
                .stream()
                .map(convert)
                .collect(Collectors.toList());
    }

}
