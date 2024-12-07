package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="go-to-flights-search")
    private WebElement flightSearchButton;

    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.flightSearchButton));
        return this.flightSearchButton.isDisplayed();
    }

    public void flightSearchButtonClick(){
        this.flightSearchButton.click();
    }
}
