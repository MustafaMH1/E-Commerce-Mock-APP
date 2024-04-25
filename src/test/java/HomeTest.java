import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.PageObjects.android.FormPage;
import org.TestUtils.ExtentReporting;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class HomeTest extends BaseTest{

    public List<HashMap<String,String>> getJsonData(String jsonPath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    @BeforeMethod
    public void preSetup(){
        driver.terminateApp("com.androidsample.generalstore");
        driver.activateApp("com.androidsample.generalstore");
    }
   @DataProvider
    public Object [][] getData() throws IOException {
       List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\Data.json");
       return new Object[][] {{data.get(0)}};
    }



    @Test(dataProvider = "getData")
    public void HomeValidTC(HashMap<String,String> input) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));

        FormPage formpage = new FormPage(driver);
        formpage.setCountry(input.get("country"));
        formpage.setNameField(input.get("name"));
        formpage.setGender(input.get("gender"));
        formpage.submit();

        Thread.sleep(3000);


    }


    @Test
    public void HomeNotVaildTC(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("com.androidsample.generalstore:id/toolbar_title"))));
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toast = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("text");
        Assert.assertEquals(toast,"Please enter your name");
    }


}
