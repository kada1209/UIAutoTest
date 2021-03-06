package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import module.InitSetUp;
import module.SlidePage;
import operatFile.AutoOperatExcelFile;
import operatFile.AutoTestCaseId;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wangxinqi
 * @date 2019-04-02 19:54
 */
public class LoginTest {
    AppiumDriver driver;
    AutoOperatExcelFile autoOperatExcelFile = new AutoOperatExcelFile();
    InitSetUp initSetUp = new InitSetUp();
    AutoTestCaseId autoTestCaseId = new AutoTestCaseId();
    @BeforeClass
    public void beforeClass(){
        autoOperatExcelFile.SetContentInit((short)9);
    }
    @BeforeMethod
    public void beforeMethod() throws IOException, InterruptedException {
        //配置手机信息
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),initSetUp.initSetupCFG(new DesiredCapabilities()));

    }
    @Test
    public void abdroidAppTest() throws IOException, InterruptedException {
        autoTestCaseId.testCaseId(driver,"login1");
        autoTestCaseId.testCaseId(driver,"tabcut");
        autoTestCaseId.testCaseId(driver,"medal");
        autoTestCaseId.testCaseId(driver,"book1");
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }



}
