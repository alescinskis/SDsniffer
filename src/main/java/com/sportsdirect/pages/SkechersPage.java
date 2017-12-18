package com.sportsdirect.pages;

import static com.sportsdirect.test.SportsdirectTest.driver;

class SkechersPage {



    SearchResultPage clickAllMens(){
        driver.executeScript("document.getElementsByClassName('img-responsive')[16].click();");
        return new SearchResultPage(driver);
    }

}
