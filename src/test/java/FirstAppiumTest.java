import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstAppiumTest {
     public static UiAutomator2Options options;
    public static AppiumDriver driver;
    public static AppiumDriverLocalService service;

    @BeforeTest
    public void setup() {
        //Setup by programmatically starting Appium server
        //startAppiumServer();  - uncomment this line if you want to start Appium server automatically

        // Set up Appium driver and initialize necessary elements
        // Define the options or Desired Capabilities for the Appium driver

        //You can below commended code as well as Desired Capabilities based on your requirements

        /*options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2"); // Automation Driver Name
        options.setUdid("2ead659b"); // Replace with your device UDID displayed in adb devices command
        options.setPlatformName("Android"); // Replace with your platform name
        options.setPlatformVersion("12");  // Replace with your device OS version
        options.setApp("/Users/kusha/AppiumTestingApps/testproject-demo-app.apk");  // Replace with your app package name*/

        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "2ead659b");
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:app", "/Users/kusha/AppiumTestingApps/testproject-demo-app.apk");



        // Initialize the Appium driver
        try {
            driver  = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegistration() {


            // Add a wait or interact with elements if needed here
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Replace with your desired wait time
            WebElement fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/name\"]")));
            //fullName.click();
            fullName.sendKeys("Kushal Parikh");

            WebElement password= driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/password\"]"));
            password.sendKeys("12345");

            WebElement login = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"io.testproject.demo:id/login\"]"));
            login.click();

            WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/country\"]")));
            country.sendKeys("India");

            WebElement address = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/address\"]"));
            address.sendKeys("Test Address");

            WebElement emailAddress = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/email\"]"));
            emailAddress.sendKeys("test@example.com");

            WebElement phoneNumber = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.testproject.demo:id/phone\"]"));
            phoneNumber.sendKeys("test@example.com");

            WebElement save = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"io.testproject.demo:id/save\"]"));
            save.click();

        WebElement profileSaveTest = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"io.testproject.demo:id/saved\"]"));
        String profileMsg =   profileSaveTest.getText();

        if (profileMsg!=null && !profileMsg.isEmpty())
        {
            System.out.println("Profile saved successfully: " + profileMsg);
            System.out.println("Appium test passed!");

            WebElement logout = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"io.testproject.demo:id/logout\"]"));
            logout.click();

        }


    }

    @AfterTest
    public void tearDown() {
        // Close the Appium driver
        if (driver!= null) {
            driver.quit();
        }

        // Stop Appium server method - Programmatically stop Appium server
        //stopAppiumServer();  - uncomment this line if you want to stop Appium server automatically

    }

    //Start and stop Appium server methods - Programmatically start and stop Appium server
    public void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/kusha/AppData/Roaming/npm/node_modules/appium/build/lib/main.js")) // Specify the path to Appium
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();
        System.out.println("Appium Server Started...");
    }

    // Stop Appium server method - Programmatically stop Appium server
    public void stopAppiumServer() {
        if (service != null) {
            service.stop();
            System.out.println("Appium Server Stopped.");
        }
    }
}
