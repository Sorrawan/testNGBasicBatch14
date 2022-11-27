package class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertions {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void launchTheWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(groups = "regression")
    public void invalidCredentials() {
        //username
        WebElement userName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        userName.sendKeys("admin");
//        password
        WebElement password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        password.sendKeys("abracadabra");
//        login
        WebElement logIn = driver.findElement(By.xpath("//*[@id='btnLogin']"));
        logIn.click();
//        invalid credentials
        WebElement errorMSG = driver.findElement(By.xpath("//span[text()='Invalid credentials']"));
        String ActualErrorMsg = errorMSG.getText();  // Get the "Invalid credentials" from the internet
        String ExpectedErrorMsg = "Invalid credentials"; //the one we use to compare if it's equal to "ActualErrorMsg" //pass


        //        if we want use soft Assertion we call it from the class SoftAssert by declaring an instance
        SoftAssert soft = new SoftAssert();
//        assertion
        soft.assertEquals(ActualErrorMsg, ExpectedErrorMsg);
//     /sout
        System.out.println("i am here after 1st assertion");
        //        check if the webelement error message isDisplayed
        boolean isdisplayed = errorMSG.isDisplayed();
//        assertion
        soft.assertTrue(isdisplayed);
//  assert all the assertions that have been made
        soft.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}