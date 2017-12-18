package com.sportsdirect;

import org.openqa.selenium.remote.RemoteWebDriver;

import static com.sportsdirect.SportsdirectTest.driver;

public class FiretrapPage {

    public SearchResultPage clickMensFootwear(){
        driver.executeScript("document.getElementsByClassName('img-responsive')[7].click();");
        return new SearchResultPage(driver);
    }

}
