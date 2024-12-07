package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {
    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="firstName")
    private WebElement firstNameInput;

    @FindBy(id="lastName")
    private WebElement lastNameInput;

    @FindBy(id="email")
    private WebElement emailInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name="city")
    private WebElement cityInput;

    @FindBy(name="zip")
    private WebElement zipInput;

    @FindBy(id="register-btn")
    private WebElement registerButton;

    public void goTo(String url){
        this.driver.get(url);
    }

    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
        return this.firstNameInput.isDisplayed();
    }

    public void fillUserDetails(String firstName,String lastName){
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
    }

    public void fillUserCredential(String email,String password){
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }

    public void fillUserAddress(String street,String city,String zip)
    {
        this.streetInput.sendKeys(street);
        this.cityInput.sendKeys(city);
        this.zipInput.sendKeys(zip);
        this.registerButton.click();
    }

}
