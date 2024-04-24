import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.Android.utils.AndroidActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class BaseTest {

        AndroidDriver driver;
        AppiumDriverLocalService service;



    @BeforeClass
        public void InitConfig() throws URISyntaxException, IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
        prop.load(fis);

        //Android Driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir")+"\\src\\main\\resources\\General-Store.apk");
        options.setDeviceName(prop.getProperty("androidDeviceName"));

        driver = new AndroidDriver(new URI("http://"+prop.getProperty("ipAddress")+":"+prop.getProperty("port")).toURL(),options);


        //Appium Automation
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(String.valueOf(prop.get("appiumJsNode"))))
                .withIPAddress(prop.getProperty("ipAddress"))
                .usingPort(Integer.parseInt(prop.getProperty("port")))
                .build();
        service.start();
        }

        @AfterClass
        public void Terminate(){
            driver.quit();
            service.stop();
        }
}
