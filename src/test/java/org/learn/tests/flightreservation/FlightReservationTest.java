package org.learn.tests.flightreservation;

import org.learn.pages.flightreservation.*;
import org.learn.tests.AbstractTest;
import org.learn.tests.flightreservation.model.FlightReservationTestData;
import org.learn.util.ConfigReader;
import org.learn.util.Constants;
import org.learn.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setUpParams(String testDataPath){
        testData = JsonUtil.getTestData(testDataPath,FlightReservationTestData.class);
    }
    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(ConfigReader.getKey(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.fillUserDetails(testData.firstName(),testData.lastName());
        registrationPage.fillUserCredential(testData.email(),testData.password());
        registrationPage.fillUserAddress(testData.street(),testData.city(),testData.street());
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.flightSearchButtonClick();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightsSearchPage()
    {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(testData.noOfPassengers());
    }

    @Test(dependsOnMethods = "flightsSearchPage")
    public void selectFlightsPage()
    {
        SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
        Assert.assertTrue(selectFlightsPage.isAt());
        selectFlightsPage.selectFlights();
    }

    @Test(dependsOnMethods = "selectFlightsPage")
    public void flightConfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.totalPrice());
    }

}
