package test;

import io.appium.java_client.AppiumDriver;
import module.InitSetUp;
import operatFile.AutoOperatExcelFile;
import operatFile.AutoTestCaseId;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @author wangxinqi
 * @date 2019-04-02 11:32
 */
public class BookSearch {
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
    public void searchTest() throws IOException, InterruptedException {
        autoTestCaseId.testCaseId(driver,"login1");
        autoTestCaseId.testCaseId(driver,"booksearch");
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
