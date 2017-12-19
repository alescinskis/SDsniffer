package com.sportsdirect.test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.mailosaur.MailboxApi;
import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import com.sportsdirect.pages.Homepage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sportsdirect.pages.Homepage.defaultURL;

public class SportsdirectTest {

    public static RemoteWebDriver driver;
    private static Homepage homepage;
    private SoftAssert softAssert;

    @BeforeClass
    public static void setup(){
        //latest chrome browser version required, at least 63
        ChromeDriverManager.getInstance().setup();
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
        homepage = new Homepage();
    }

    @After
    public void afterMethod(){
        driver.quit();
        softAssert.assertAll();
    }

    @Test
    public void findSkechersShoesWithinRange(){

        BigDecimal minValue = BigDecimal.valueOf(30);
        BigDecimal maxValue = BigDecimal.valueOf(60);

        List<BigDecimal> filteredItems = homepage
                .openHomepage()
                .changeCurrencyTo("EUR")
                .openSkechersPage()
                .clickAllMens()
                .setSearchPrice(minValue.toString(), maxValue.toString())
                .getFilteredPrices();

        boolean withinRange = isItemsWithinPriceRange(filteredItems, minValue, maxValue);
        String allMensURL = "/skechers/all-skechers#dcp=1&dppp=100&OrderBy=rank&Filter=AFLOR%5EMens";

        softAssert.assertTrue(driver.getCurrentUrl().contains(allMensURL),
                String.format("Wrong URL, doesn't contain <%s>, actual URL is <%s>", allMensURL, driver.getCurrentUrl()));
        softAssert.assertTrue(withinRange, String.format("prices <%s> are not within specified range", filteredItems));
    }

    @Test
    public void findFiretrapShoesWithinRange(){

        BigDecimal minValue = BigDecimal.valueOf(30);
        BigDecimal maxValue = BigDecimal.valueOf(60);

        List<BigDecimal> filteredItems = homepage
                .openHomepage()
                .changeCurrencyTo("EUR")
                .openFireTrapPage()
                .clickMensFootwear()
                .expandPrice()
                .setSearchPrice(minValue.toString(), maxValue.toString())
                .getFilteredPrices();

        boolean withinRange = isItemsWithinPriceRange(filteredItems, minValue, maxValue);
        String mensFootwearURL = "/firetrap/mens-firetrap-footwear";

        softAssert.assertTrue(driver.getCurrentUrl().contains(mensFootwearURL),
                String.format("Wrong URL, doesn't contain <%s>, actual URL is <%s>", mensFootwearURL, driver.getCurrentUrl()));
        softAssert.assertTrue(withinRange, String.format("prices <%s> are not within specified range", filteredItems));

    }

    @Test
    public void passwordRecovery() throws MailosaurException, IOException {
        //email expires on 01.01.2018
        //captcha appears after 3rd password recovery without password change
        String recoveryEmail = "testy-stephen.qmy88euo@mailosaur.io";
        homepage
                .openHomepage()
                .clickOnSignIn()
                .clickForgotPassword()
                .sendRecoveryEmail(recoveryEmail);

        //to give some time for email to arrive
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

        MailboxApi mailbox = new MailboxApi("qmy88euo", "NA4wH6RPMx4lgbM");
        Email[] emails = mailbox.getEmailsByRecipient(recoveryEmail);

        Email latestEmail = emails[0];

        softAssert.assertTrue(latestEmail.subject.contains("Sports Direct Latvia - Forgotten Password"), "Incorrect mail subject");
        softAssert.assertTrue(latestEmail.senderhost.contains("extmail.sportski.com"), "Incorrect sender host");
        softAssert.assertEquals(latestEmail.html.links.length, 1, "there should be only one URL");
        softAssert.assertTrue(latestEmail.html.body.contains(defaultURL + "/Login/PasswordReset?token="), "recovery URL is missing");
        softAssert.assertTrue(latestEmail.html.links[0].href.contains(defaultURL + "/Login/PasswordReset?token="), "recovery URL is not clickable");

        //to keep test alive for further checks
        String recoveryURL = latestEmail.html.links[0].href;

        driver.get(recoveryURL);

        driver.findElement(By.id("dnn_ctr53347402_PasswordReset_txtNewPassword")).click();
        driver.findElement(By.id("dnn_ctr53347402_PasswordReset_txtNewPassword")).sendKeys("Iddqd1");
        driver.findElement(By.id("dnn_ctr53347402_PasswordReset_txtNewPassword")).sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys("Iddqd1");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

    }

    private boolean isItemsWithinPriceRange(List<BigDecimal> valuesToCheck, BigDecimal minValue, BigDecimal maxValue) {
        return valuesToCheck.stream().noneMatch(a->
                a.compareTo(minValue) < 0 ||
                        a.compareTo(maxValue) > 0);
    }
}
