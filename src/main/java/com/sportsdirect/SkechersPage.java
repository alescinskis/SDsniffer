package com.sportsdirect;

import static com.sportsdirect.SportsdirectTest.driver;

public class SkechersPage {



    public SearchResultPage clickAllMens(){
        driver.executeScript("document.getElementsByClassName('img-responsive')[16].click();");
        return new SearchResultPage(driver);
    }

}
