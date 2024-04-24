package org.Android.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;


public class AndroidActions {

    AndroidDriver driver ;
    public AndroidActions(AndroidDriver driver){
        this.driver = driver ;
    }

    public void scrollToText(String Text){
     driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\""+Text+"\"));"));

    }



    public void scrollToCoordinates(int percent){
        boolean canScrollMore = (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left",100,"top",100,"width",200,"height",200,
                "direction","down",
                "percent", percent
        ));
    }

    public void longPress(WebElement Element){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)Element).getId(),
                "duration",2000
        ));
    }

    public void clickElement(WebElement Element){
        Element.click();
    }

    public void setTextField(WebElement Element,String text){
        Element.sendKeys(text);
        driver.hideKeyboard();
    }

    public void setActivity(String packageName , String activityName){
        Activity activity = new Activity(packageName,activityName);
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",activityName));
    }



}
