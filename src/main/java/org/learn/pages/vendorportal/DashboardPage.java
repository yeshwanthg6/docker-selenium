package org.learn.pages.vendorportal;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningText;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningText;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginText;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryText;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchTextField;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultText;

    @FindBy(css = ".img-profile")
    private WebElement profilePictureButton;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement logoutButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningText));
        return this.monthlyEarningText.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningText.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningText.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginText.getText();
    }

    public String getAvailableInventory(){
        return this.availableInventoryText.getText();
    }

    public void searchOrderHistory(String keyword){
        this.searchTextField.sendKeys(keyword);
    }

    public int getSearchResultCount(){
        String search = this.searchResultText.getText();
        String[] arr = search.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Search count found to be {}",count);
        return count;
    }

    public void userLogsOut(){
        this.profilePictureButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
    }
}
