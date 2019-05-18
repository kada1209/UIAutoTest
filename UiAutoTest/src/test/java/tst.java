import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2019/3/21.
 */
public class tst {
    @Test
    public void  test() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "小米MIX2");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0.0");
       // capabilities.setCapability("app","D:\\\\tools\\\\debug.apk");
        capabilities.setCapability("appPackage", "com.hhdd.kada");
        capabilities.setCapability("appActivity", ".main.ui.activity.LaunchActivity");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        Thread.sleep(3000);
        driver.findElement(By.id("com.hhdd.kada:id/leftIconView")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.hhdd.kada:id/phoneEditTextView")).sendKeys("13671828697");
        driver.findElement(By.id("com.hhdd.kada:id/codeTextView")).click();
        driver.findElement(By.id("com.hhdd.kada:id/codeEditTextView")).sendKeys("123456");
        driver.findElement(By.id("com.hhdd.kada:id/loginTextView")).click();
        //driver.quit();
    }
}
