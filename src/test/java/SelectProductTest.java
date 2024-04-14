import io.appium.java_client.AppiumBy;
import org.PageObjects.android.CartPage;
import org.PageObjects.android.FormPage;
import org.PageObjects.android.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectProductTest extends BaseTest{
        @Test
        public void J6TC() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));


        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sasa");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();


        ProductPage productPage = new ProductPage(driver);
        productPage.addProduct("Nike SFB Jungle");
        productPage.addProduct("Air Jordan 9 Retro");


        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);



        }

        @Test
        public void CheckChosenProductsTC() throws InterruptedException {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));

                FormPage formPage = new FormPage(driver);
                formPage.setCountry("Aruba");
                formPage.setNameField("Mustafa");
                formPage.setGender("male");
                formPage.submit();

                ProductPage productPage = new ProductPage(driver);
                productPage.addProduct("Converse All Star");
                productPage.addProduct("Jordan Lift Off");
                productPage.cartClick();

                CartPage cartPage = new CartPage(driver);
                Assert.assertEquals(cartPage.checkSum(), cartPage.getAppSum());

                cartPage.setCheckBox();
                cartPage.setSubmitButton();
                
                Thread.sleep(4000);
        }

}

