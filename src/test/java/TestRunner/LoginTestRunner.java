package TestRunner;

import Base.Setup;
import Pages.Login;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    Utils utils;
    Login login;

    @Test(priority = 0)
    public void doLoginWithBothBlank() throws IOException, ParseException, InterruptedException {
        utils = new Utils();
        login = new Login(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        Thread.sleep(3000);
        utils.getUserCreds(0);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String isGotText = login.inLineErrorMsg.getText();
        Assert.assertTrue(isGotText.contains("Required"));
    }

    @Test(priority = 1, description = "User tries to login with blank username but blank password")
    public void doLoginWithBlankUserNameAndValidPassword() throws IOException, ParseException, InterruptedException {
        login = new Login(driver);
        utils = new Utils();
        utils.getUserCreds(1);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String ValidationMessage = login.inLineErrorMsg.getText();
        Assert.assertTrue(ValidationMessage.contains("Required"));
        Allure.description("User tries to login with invalid username and valid password" +
                "User will not be allowed to login and 'Required' will be prompted");
        Thread.sleep(3000);
        login.clearCredential();
    }

    @Test(priority = 2, description = "User tries to login with correct username but blank password")
    public void doLoginWithValidUserNameAndBlankPassword() throws IOException, ParseException, InterruptedException {
        login = new Login(driver);
        utils = new Utils();
        utils.getUserCreds(2);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String ValidationMessage = login.inLineErrorMsg.getText();
        Assert.assertTrue(ValidationMessage.contains("Required"));
        Allure.description("User tries to login with valid username and blank password" +
                "User will not be allowed to login and 'Required' will be prompted");
        Thread.sleep(3000);
        login.clearCredential();
    }
    @Test(priority = 3, description = "User tries to login with incorrect username but correct password")
    public void doLoginWithInvalidUserNameAndValidPassword() throws IOException, ParseException, InterruptedException {
        login = new Login(driver);
        utils = new Utils();
        utils.getUserCreds(3);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String isGotText = login.errorMsg.getText();
        Assert.assertTrue(isGotText.contains("Invalid credentials"));
        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Invalid credentials' will be prompted");
        Thread.sleep(3000);
        login.clearCredential();
    }
    @Test(priority = 4, description = "User tries to login with correct username but incorrect password")
    public void doLoginWithValidUserNameAndInvalidPassword() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        login = new Login(driver);
        utils = new Utils();
        utils.getUserCreds(4);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String isGotText = login.errorMsg.getText();
        Assert.assertTrue(isGotText.contains("Invalid credentials"));
        Allure.description("User tries to login with valid username and Incorrect password" +
                "User will not be allowed to login and 'Invalid credentials' will be prompted");
        login.clearCredential();
    }

    @Test(priority = 6, description = "User gives valid credentials and login is successful")
    public void doLoginWithValidCredential() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        login = new Login(driver);
        utils = new Utils();
        utils.getUserCreds(5);
        login.doLoginWithValidCredential(utils.getUsername(), utils.getPassword());
        String isGotText = login.lblDashboard.getText();
        Assert.assertTrue(isGotText.contains("Dashboard"));
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urlExpected));
        Allure.description("After giving valid credentials of the user, user will be able to successfully login " +
                "and after login logout button will be displayed");
    }

    @Test(priority = 7, description = "Logout button will be clicked,Login button should be displayed")
    public void signOutPerformed() throws InterruptedException {
        login = new Login(driver);
        login.imgProfile.click();
        Thread.sleep(1500);
        login.linkLogout.click();
        boolean isGotText = login.btnLogin.isDisplayed();
        Assert.assertTrue(isGotText);
        Allure.description("If the user signs out,Login button should be displayed");
    }

    @Test(priority = 8, description = "Reset password by giving registered username")
    public void resetPassword() throws InterruptedException {
        Thread.sleep(3000);
        login = new Login(driver);
        login.linkForgetPassword.get(2).click();
        login.txtResetName.sendKeys("abir@gmail.com");
        Thread.sleep(1000);
        login.btnResetPassword.click();
        String isGotButton = login.lblResetPasswordSuccessfullyMsg.getText();
        Assert.assertTrue(isGotButton.contains("Reset Password link sent successfully"));
        Allure.description("The password is reset password by giving registered username and after retrieving password user " +
                "will click back to successfully login again.");

    }
}
