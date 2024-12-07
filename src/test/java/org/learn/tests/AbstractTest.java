package org.learn.tests;
import org.learn.listener.TestListener;
import org.learn.util.ConfigReader;
import org.learn.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public class AbstractTest {

    protected WebDriver driver;

    public static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void initConfig(){
        ConfigReader.initializeProperties();
    }

    @BeforeTest
    public void setUpDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(ConfigReader.getKey(Constants.GRID_ENABLED)) ? getRemoteWebDriver():getLocalWebdriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);

    }

    private WebDriver getRemoteWebDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();;
        if(Constants.FIREFOX.equalsIgnoreCase(ConfigReader.getKey(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }
        String urlFormat = ConfigReader.getKey(Constants.GRID_URL_FORMAT);
        String host = ConfigReader.getKey(Constants.GRID_HOST_NAME);
        String url = String.format(urlFormat,host);
        log.info("url : {}",url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalWebdriver(){
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
