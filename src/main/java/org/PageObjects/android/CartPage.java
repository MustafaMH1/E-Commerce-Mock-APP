package org.PageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.Android.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement appSum;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private  WebElement submitButton;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public double checkSum(){
        int itemsCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).size();
        double sum = 0 ;

        for(int i = 0; i < itemsCount ; i++){
            sum = sum + Double.parseDouble(driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).get(i).getText().substring(1));
        }
        return sum;
    }

    public double getAppSum(){
        return Double.parseDouble(appSum.getText().substring(1));
    }

    public void setCheckBox(){
        checkBox.click();
    }

    public void setSubmitButton(){
        submitButton.click();
    }

}
