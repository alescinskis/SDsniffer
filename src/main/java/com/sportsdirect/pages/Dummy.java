package com.sportsdirect.pages;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Dummy {
    protected static RemoteWebDriver driver = new ChromeDriver();

    public static void main(String... args) {

//        ChromeDriverManager.getInstance().setup(); //new latest chrome browser version at least 63
//        RemoteWebDriver driver = new ChromeDriver();
//        driver.get("https://www.google.com");
    }

}
