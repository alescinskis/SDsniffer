package com.sportsdirect;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SportsdirectTest {

    protected static RemoteWebDriver driver;
    protected static Homepage homepage;
    private SoftAssert softAssert;

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
        driver.get("https://www.sportsdirect.com");
        homepage = new Homepage();
    }

    @After
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void findSkechersShoes(){
        homepage.openBrandPage("sketchers");
        softAssert.assertTrue(driver.getCurrentUrl().contains("/skechers/all-skechers#dcp=1&dppp=100&OrderBy=rank&Filter=AFLOR%5EMens"));
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.setSearchPrice("30", "60");
    }

    @Test
    public void findFiretrapShoes(){
        homepage.openBrandPage("firetrap");
        softAssert.assertTrue(driver.getCurrentUrl().contains("/firetrap/mens-firetrap-footwear"));
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        List<String> filteredItems = searchResultPage
                .expandPrice()
                .setSearchPrice("30", "60")
                .getRandomFilteredPrice();
    }

}
