package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Login {
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    public WebElement btnLogin;
    @FindBy(tagName = "span")
    public WebElement inLineErrorMsg;
    @FindBy(xpath = "//header/div[1]/div[1]/span[1]/h6[1]")
    public WebElement lblDashboard;
    @FindBy(tagName = "p")
    public WebElement errorMsg;
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement imgProfile;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    public WebElement linkLogout;
    @FindBy(tagName = "p")
    public List<WebElement> linkForgetPassword;
    @FindBy(css = "[type=submit]")
    public WebElement btnResetPassword;
    @FindBy(name = "username")
    public WebElement txtResetName;
    @FindBy(tagName = "h6")
    public WebElement lblResetPasswordSuccessfullyMsg;

    public Login (WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void doLoginWithValidCredential(String username, String password) throws InterruptedException {
        txtUserName.sendKeys(username);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public void clearCredential() {
        txtUserName.sendKeys(Keys.CONTROL, "a");
        txtUserName.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL, "a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
