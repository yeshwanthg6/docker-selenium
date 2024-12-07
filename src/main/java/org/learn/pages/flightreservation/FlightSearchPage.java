package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersDropdown));
        return this.passengersDropdown.isDisplayed();
    }

    @FindBy(id = "passengers")
    private WebElement passengersDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public void selectPassengers(String noOfPassengers){
        Select select= new Select(this.passengersDropdown);
        select.selectByValue(noOfPassengers);
        this.searchFlightsButton.click();
    }
}
