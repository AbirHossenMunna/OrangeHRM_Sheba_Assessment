package TestRunner;

import Base.Setup;
import Pages.ApplyLeavePage;
import Pages.Login;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApplyLeaveTestRunner extends Setup {
    Login loginPage;
    ApplyLeavePage applyLeavePage;
    Utils utils;
    String comment;
    String invalidFromDateRange;
    String invalidToDateRange;
    String invalidFromDateFormat;
    String invalidToDateFormat;
    String validFromDate;
    String validToDate;

    public void basicInfo() {
        comment = "I need to take leave due to illness";
        invalidFromDateRange = "2024-27-11";
        invalidToDateRange = "2024-01-10";
        invalidFromDateFormat = "05-10-2024";
        invalidToDateFormat = "10-10-2024";
        validFromDate = "2024-2-12";
        validToDate = "2024-5-12";
    }

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        utils = new Utils();
        loginPage = new Login(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
    }

    @Test(priority = 0, description = "Mandatory Fields is empty")
    public void mandatoryAllFieldShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        applyLeavePage.sideBarTime.get(3).click();
        applyLeavePage.topBar.get(0).click();
        Thread.sleep(3000);
        applyLeavePage.btnApply.get(3).click();
        String isGotErrorMsg = applyLeavePage.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Apply for Leave with Blank Date Fields")
    public void doLeaveApplyWithDateFieldAreBlank() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        applyLeavePage.dropdownLeaveType.click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            applyLeavePage.dropdownLeaveType.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        applyLeavePage.dropdownLeaveType.sendKeys(Keys.ENTER);
        applyLeavePage.btnApply.get(3).click();
        String isGotErrorMsg = applyLeavePage.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Verifies that the leave application cannot be submitted when the date fields " +
                "(\"From Date\" or \"To Date\") are left blank, ensuring proper input validation for the required fields.");
    }

    @Test(priority = 2, description = "Apply for Leave with Invalid Date Range")
    public void doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        basicInfo();
        applyLeavePage.doLeaveApplyWithValidData(invalidFromDateRange, invalidToDateRange, comment);
        String isGotErrorMsg = applyLeavePage.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("To date should be after from date"));
        Allure.description("Verifies that the system enforces date range validation by preventing submission when " +
                "the 'To date should be after from date'.");
    }

    @Test(priority = 3, description = "Apply for Leave with Invalid Date Format")
    public void doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        basicInfo();
        applyLeavePage.doLeaveApplyWithValidData(invalidFromDateFormat, invalidToDateFormat, comment);
        String isGotErrorMsg = applyLeavePage.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in yyyy-dd-mm format"));
        Allure.description("Validates that the system enforces proper date format validation by preventing submission " +
                "when an invalid date format is entered in the leave application form.");
//        applyLeavePage.clearCredential();
    }

    @Test(priority = 4, description = "Apply for Leave with Valid Data")
    public void doLeaveApplyWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        basicInfo();
        applyLeavePage.doLeaveApplyWithValidData(validFromDate, validToDate, comment);
        Thread.sleep(3000);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList");
        Assert.assertTrue(applyLeavePage.tableValidation.isDisplayed());
        Allure.description("Verifies that the system successfully processes a leave application when all required fields," +
                " including valid date range and leave type, are correctly filled.");
    }
}
