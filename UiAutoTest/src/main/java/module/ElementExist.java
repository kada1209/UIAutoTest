package module;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author wangxinqi
 * @date 2019-03-18 19:37
 * 判断Excel表中的元素是否存在
 */
public class ElementExist {
    public static String result;
    public static void setResult(WebDriverWait driverWait,AppiumDriver driver){
        if (null == driverWait){
            ScreenShot.takeScreenShot(driver);
            result = "failed";
            Assert.assertTrue(false);
        }else {
            result = "pass";
        }
    }

    //根据xpath定位
    public String waitForElementByXpath(final String ID, AppiumDriver driver){
        try {
            WebDriverWait driverWait = new WebDriverWait(driver,20);
            //设置智能等待超时时间，在20秒内，元素存在就直接操作
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ID)));
            setResult(driverWait,driver);
        }catch (Exception e){
            //若元素不存在，则自动保存截图
            ScreenShot.takeScreenShot(driver);
            result = "failed";
            Assert.assertTrue(false);
        }

        return result;
    }
    //根据name定位
    public String waitForElementByName(String ID,AppiumDriver driver){
        try {
            WebDriverWait driverWait = new WebDriverWait(driver,20);
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(ID)));
            setResult(driverWait,driver);
        }catch (Exception e){
            ScreenShot.takeScreenShot(driver);
            result = "failed";
            Assert.assertTrue(false);
        }
        return result;
    }

    public String waitForElementByNameSkip(String ID,AppiumDriver driver){
        try {
            WebDriverWait driverWait = new WebDriverWait(driver,20);
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(ID)));
            setResult(driverWait,driver);
        }catch (Exception e){
            ScreenShot.takeScreenShot(driver);
            result = "failed";
            Assert.assertTrue(false);
        }
        return result;
    }

    public String waitForElementById(String ID,AppiumDriver driver){
        try {
            WebDriverWait driverWait = new WebDriverWait(driver,20);
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID)));
            setResult(driverWait,driver);
        }catch (Exception e){
            ScreenShot.takeScreenShot(driver);
            result = "failed";
            Assert.assertTrue(false);
        }
        return result;
    }


}
