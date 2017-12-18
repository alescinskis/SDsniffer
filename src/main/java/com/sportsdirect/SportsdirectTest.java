package com.sportsdirect;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SportsdirectTest {
    protected static RemoteWebDriver driver;

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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sportsdirect.com");

    }

    @After
    public void afterMethod(){
        driver.quit();

    }

    @Test
    public void findSkechersShoes(){
        Homepage homepage = new Homepage();
        homepage.openBrandPage("sketchers");
    }

    @Test
    public void findFiretrapShoes(){
        Homepage homepage = new Homepage();
        homepage.openBrandPage("firetrap");
    }

}
