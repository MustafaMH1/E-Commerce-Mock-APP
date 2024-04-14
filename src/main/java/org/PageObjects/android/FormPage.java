package org.PageObjects.android;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class FormPage extends AndroidActions {
    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }




    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField ;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement radioFemale;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement radioMale;

    @AndroidFindBy(id ="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryBox;

    public void setNameField(String Name){
        setTextField(nameField,Name);
    }

    public void setGender(String gender){
        if(gender.equalsIgnoreCase("female"))
            clickElement(radioFemale);
        else if (gender.equalsIgnoreCase("male"))
            clickElement(radioMale);
        else
            System.out.println("Input Error" + gender);
    }

    public void setCountry(String country){
        clickElement(countryBox);
        scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text=\""+country+"\"]")).click();
    }

    public void submit(){
        clickElement(submitButton);
    }
}
