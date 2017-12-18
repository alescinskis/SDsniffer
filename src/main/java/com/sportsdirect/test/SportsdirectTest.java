package com.sportsdirect.test;

import com.sportsdirect.pages.Homepage;
import com.sportsdirect.pages.SearchResultPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

public class SportsdirectTest {

    public static RemoteWebDriver driver;
    private static Homepage homepage;
    private SoftAssert softAssert;
    private String defaultURL = "https://www.sportsdirect.com";

    @BeforeClass
    public static void setup(){
        ChromeDriverManager.getInstance().setup(); //new latest chrome browser version at least 63
    }

    @AfterClass
    public static void cleanUp(){
        driver.quit();
    }

    @Before
    public void beforeMethod(){
        softAssert = new SoftAssert();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(defaultURL);
        homepage = new Homepage();
    }

    @After
    public void afterMethod(){
        driver.quit();
        softAssert.assertAll();
    }

    @Test
    public void findSkechersShoesWithinRange(){
        homepage.openBrandPage("sketchers");
        softAssert.assertTrue(driver.getCurrentUrl().contains("/skechers/all-skechers#dcp=1&dppp=100&OrderBy=rank&Filter=AFLOR%5EMens"));
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        BigDecimal minValue = BigDecimal.valueOf(30);
        BigDecimal maxValue = BigDecimal.valueOf(60);

        String filteredItem = searchResultPage
                .setSearchPrice(minValue.toString(), maxValue.toString())
                .getRandomFilteredPrice();

        BigDecimal filteredItemPrice = new BigDecimal(filteredItem);
        boolean withinRange = isWithinRange(filteredItemPrice, minValue, maxValue);
        softAssert.assertTrue(withinRange, String.format("price <%s> is not within specified range", filteredItemPrice));
    }

    @Test
    public void findFiretrapShoesWithinRange(){
        homepage.openBrandPage("firetrap");
        String mensFootwearURL = "/firetrap/mens-firetrap-footwear";
        softAssert.assertTrue(driver.getCurrentUrl().contains(mensFootwearURL), String.format("Wrong URL, doesn't contain <%s>, actual URL is <%s>", mensFootwearURL, driver.getCurrentUrl()));
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        BigDecimal minValue = BigDecimal.valueOf(30);
        BigDecimal maxValue = BigDecimal.valueOf(60);

        String filteredItem = searchResultPage
                .expandPrice()
                .setSearchPrice(minValue.toString(), maxValue.toString())
                .getRandomFilteredPrice();

        BigDecimal filteredItemPrice = new BigDecimal(filteredItem);
        boolean withinRange = isWithinRange(filteredItemPrice, minValue, maxValue);
        softAssert.assertTrue(withinRange, String.format("price <%s> is not within specified range", filteredItemPrice));
    }
    private boolean isWithinRange(BigDecimal valueToCheck, BigDecimal minValue, BigDecimal maxValue){
        return valueToCheck.compareTo(minValue) >= 0 && valueToCheck.compareTo(maxValue) <= 0;
    }
}
