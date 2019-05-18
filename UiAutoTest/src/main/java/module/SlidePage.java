package module;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;

/**
 * @author wangxinqi
 * @date 2019-03-25 23:04
 *
 * 滑动操作基础
 */
public class SlidePage {
    /**
     * 引导页滑动
     * @param driver
     */
    public void guide_page(AppiumDriver driver) throws InterruptedException {
        for (int i=0;i<3;i++){
            int startx = -1;
            int starty = -1;
            int endx = -1;
            int endy = -1;
            Thread.sleep(3000);
            WebElement webElement = driver.findElementById("com.hhdd.kada:id/datalist_banner_item_cover");
            //// 获得webElement的位置和大小
            Point point = webElement.getLocation();
            Dimension dimension = webElement.getSize();
            endx = point.getX();
            endy = point.getY()+dimension.getHeight()/2;
            startx = point.getX()+dimension.getWidth()-5;
            starty = endy;
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(endx,endy)).release().perform();
        }
        Thread.sleep(2000);
        driver.findElement(By.id("com.hhdd.kada:id/next")).click();
        Thread.sleep(1000);
    }

    /**
     * 根据屏幕向下滑动
     * @param xpath
     * @param driver
     */
    public void down_Page(String xpath,AppiumDriver driver){
        //JavascriptExecutor可以执行js的驱动程序
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement webElement = driver.findElementByXPath(xpath);
        HashMap<String,Double> hashMap = new HashMap<String,Double>();
        hashMap.put("startx",0.5);
        hashMap.put("starty",0.8);
        hashMap.put("endx", 0.5);
        hashMap.put("endy", 100.00);
        hashMap.put("element",Double.valueOf(((RemoteWebElement)webElement).getId()));
        javascriptExecutor.executeScript("mobile:flick",hashMap);
    }

    /**
     * 根据元素id向下滑动
     * @param id
     * @param driver
     */
    public void downPage(String id,AppiumDriver driver) throws InterruptedException {
        int startx =-1;
        int starty = -1;
        int endx = -1;
        int endy = -1;
        Thread.sleep(2000);
        WebElement webElement = driver.findElementById(id);
        Point point = webElement.getLocation();
        Dimension dimension = webElement.getSize();
        endy = dimension.getHeight();
        endx = point.getX()+dimension.getHeight()/2;
        starty = point.getY()+dimension.getHeight()-5;
        startx = endx;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startx,starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx,endy)).release().perform();
        Thread.sleep(1000);

    }

    /**
     * 根据xpath向左滑动
     * @param xpath
     * @param driver
     */
    public void left_Page(String xpath,AppiumDriver driver){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        WebElement webElement = driver.findElementByXPath(xpath);
        HashMap<String,Double> hashMap = new HashMap<String,Double>();
        hashMap.put("startx",0.5);
        hashMap.put("starty",0.8);
        hashMap.put("endx",0.6);
        hashMap.put("endy",0.2);
        hashMap.put("WebElement",Double.valueOf(((RemoteWebElement)webElement).getId()));
        javascriptExecutor.executeScript("mobile:flick",hashMap);
    }

    /**
     * 根据屏幕向上滑动
     * @param during
     * @param driver
     */
    public void swipeToUp(int during,AppiumDriver driver){
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(width/2,height*3/4)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(during)))
                .moveTo(PointOption.point(width/2,height/4)).release().perform();
    }

    /**
     * 根据屏幕向下滚动
     * @param during
     * @param driver
     */
    public void swipeToDown(int during,AppiumDriver driver){
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(width/2,height/4)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(during)))
                .moveTo(PointOption.point(width/2,height*3/4)).release().perform();

    }

    public void swipeToLeft(int during,AppiumDriver driver){
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(width/2,height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(during)))
                .moveTo(PointOption.point(width/4,height/2)).release().perform();

    }

    public void swipeToRight(int during,AppiumDriver driver){
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(width/4,height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(during)))
                .moveTo(PointOption.point(width/4,height/2)).release().perform();

    }

}
