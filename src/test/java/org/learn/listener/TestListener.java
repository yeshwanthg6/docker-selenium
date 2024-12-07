package org.learn.listener;

import org.learn.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;


public class TestListener implements ITestListener {
    public static final Logger log = LoggerFactory.getLogger(TestListener.class);
    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getTestContext().getAttributeNames().toString());
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s'/>";
        String imgFormat = String.format(htmlImageFormat,screenshot);
        Reporter.log(imgFormat);
    }
}
