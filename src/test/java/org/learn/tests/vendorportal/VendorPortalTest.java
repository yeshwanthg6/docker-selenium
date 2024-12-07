package org.learn.tests.vendorportal;

import org.learn.pages.vendorportal.DashboardPage;
import org.learn.pages.vendorportal.LoginPage;
import org.learn.tests.AbstractTest;
import org.learn.tests.vendorportal.model.VendorPortalTestData;
import org.learn.util.ConfigReader;
import org.learn.util.Constants;
import org.learn.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class  VendorPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setUpPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void userLoginTest(){
        this.loginPage.goTo(ConfigReader.getKey(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        this.loginPage.fillUserCredential(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "userLoginTest")
    public void dashboardTest(){
        Assert.assertTrue(this.dashboardPage.isAt());
        Assert.assertEquals(this.dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(this.dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(this.dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(this.dashboardPage.getAvailableInventory(),testData.availableInventory());
        this.dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(this.dashboardPage.getSearchResultCount(),testData.searchResultsCount());
        this.dashboardPage.userLogsOut();
        Assert.assertTrue(this.loginPage.isAt());
    }

}
