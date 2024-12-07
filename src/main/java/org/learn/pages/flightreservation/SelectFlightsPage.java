package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightsPage extends AbstractPage {

    public SelectFlightsPage(WebDriver driver){
        super(driver);
    }

    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();
    }

    @FindBy(name="departure-flight")
    private List<WebElement> flightDepartureRadios;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightRadios;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsButton;

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, this.flightDepartureRadios.size());
        this.flightDepartureRadios.get(random).click();
        this.arrivalFlightRadios.get(random).click();
        this.confirmFlightsButton.click();
    }
}
