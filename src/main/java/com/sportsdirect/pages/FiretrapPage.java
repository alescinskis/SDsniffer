package com.sportsdirect.pages;

import static com.sportsdirect.test.SportsdirectTest.driver;

class FiretrapPage {

    SearchResultPage clickMensFootwear(){
        driver.executeScript("document.getElementsByClassName('img-responsive')[7].click();");
        return new SearchResultPage(driver);
    }

}
