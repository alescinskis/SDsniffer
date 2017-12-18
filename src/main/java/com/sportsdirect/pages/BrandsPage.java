package com.sportsdirect.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

class BrandsPage {


    BrandsPage(RemoteWebDriver driver, String brandName) {
        if (brandName.equals("firetrap")) {
            FiretrapPage firetrapPage = new FiretrapPage();
            firetrapPage.clickMensFootwear();
        } else if (brandName.equals("sketchers")) {
            SkechersPage skechersPage = new SkechersPage();
            skechersPage.clickAllMens();

        } else if (brandName.equals("other")) {
            driver.get("https://www.sportsdirect.com");

        }
    }
}
