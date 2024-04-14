import io.appium.java_client.AppiumBy;
import org.PageObjects.android.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTest extends BaseTest{
    @Test
    public void HomeValidTC() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));

        FormPage formpage = new FormPage(driver);
        formpage.setCountry("Argentina");
        formpage.setNameField("Mustafa");
        formpage.setGender("male");
        formpage.submit();

        Thread.sleep(3000);
    }

    @Test
    public void HomeNotVaildTC(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));

        // No Name is Entered
        //driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sasa");

        //driver.hideKeyboard();
        //driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toast = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("text");
        Assert.assertEquals(toast,"Please enter your name");
    }
}
