import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class BaseTest {

        AndroidDriver driver;
        AppiumDriverLocalService service;

        @BeforeClass
        public void InitConfig() throws URISyntaxException, MalformedURLException {
            //Android Driver
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp("C:\\Users\\musta\\IdeaProjects\\AppiumFramework\\src\\main\\resources\\General-Store.apk");
            options.setDeviceName("Pixel");

            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);


            //Appium Automation
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:\\Users\\musta\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .build();
            service.start();
        }

        @AfterClass
        public void Terminate(){
            driver.quit();
            service.stop();
        }
}
