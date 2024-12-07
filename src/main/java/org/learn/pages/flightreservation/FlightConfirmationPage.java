package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FlightConfirmationPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    public FlightConfirmationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".card-body div:nth-child(1) p")
    private WebElement flightConfirmationText;

    @FindBy(css = ".card-body div:nth-child(3) p")
    private WebElement totalPrice;

    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.totalPrice));
        return this.totalPrice.isDisplayed();
    }

    public String getPrice(){
        String confirmationInfo = this.flightConfirmationText.getText();
        String totalPriceInfo = this.totalPrice.getText();
        log.info("Flight Confirmation : {}, Total Price: {}",confirmationInfo,totalPriceInfo);
        return totalPriceInfo;
    }

}
