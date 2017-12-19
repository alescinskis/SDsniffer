package com.sportsdirect.pages;

import static com.sportsdirect.test.SportsdirectTest.driver;

public class SkechersPage {

    public SearchResultPage clickAllMens(){
        driver.executeScript("document.getElementsByClassName('img-responsive')[16].click();");
        return new SearchResultPage();
    }

}
