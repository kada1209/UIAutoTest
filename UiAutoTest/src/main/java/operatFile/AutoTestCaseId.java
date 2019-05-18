package operatFile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import module.Config;
import module.ElementExist;
import module.SlidePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

/**
 * @author wangxinqi
 * @date 2019-04-01 01:34
 *
 *读取Excel中每一条测试用例
 */

public class AutoTestCaseId {
    ElementExist elementExist = new ElementExist();
    static AutoOperatExcelFile autoOperatExcelFile = new AutoOperatExcelFile();
    SlidePage slidePage = new SlidePage();
    public void testCaseId(AppiumDriver driver,String id) throws IOException, InterruptedException {
        int i,j,k,l,m,n,o,p,q;
        for (i=0;i<autoOperatExcelFile.ReadContent().size();i++){
            if (autoOperatExcelFile.ReadContent().get(i).contains(id)){
                for (j=0;j<autoOperatExcelFile.readTitle().size();j++){
                    if (autoOperatExcelFile.readTitle().get(j).contains("定位方式")){
                        break;
                    }
                }
                //读取出定位方式
                String caseidLocation = autoOperatExcelFile.ReadTitleContent(i+1,j);
                for (k=0;k<autoOperatExcelFile.readTitle().size();k++){
                    if (autoOperatExcelFile.readTitle().get(k).contains("控件元素")){
                        break;
                    }
                }
                String caseidElement = autoOperatExcelFile.ReadTitleContent(i+1,k);
                for (l = 0;l<autoOperatExcelFile.readTitle().size();l++){
                    if (autoOperatExcelFile.readTitle().get(l).contains("操作方法")){
                        break;
                    }
                }
                String caseidOperationMethod = autoOperatExcelFile.ReadTitleContent(i+1,l);
                for (m=0;m<autoOperatExcelFile.readTitle().size();m++){
                    if (autoOperatExcelFile.readTitle().get(m).contains("测试数据")){
                        break;
                    }
                }
                String caseidTestData = autoOperatExcelFile.ReadTitleContent(i+1,m);
                for (n=0;n<autoOperatExcelFile.readTitle().size();n++){
                    if (autoOperatExcelFile.readTitle().get(n).contains("验证数据")){
                        break;
                    }
                }
                String caseidValidationData = autoOperatExcelFile.ReadTitleContent(i+1,n);
                for (o=0;o<autoOperatExcelFile.readTitle().size();o++){
                    if (autoOperatExcelFile.readTitle().get(o).contains("延迟时间")){
                        break;
                    }
                }
                String caseidDelayTieme = autoOperatExcelFile.ReadTitleContent(i+1,o);
                if (caseidLocation.equals("By.xpath")){
                    if (caseidOperationMethod.equals("sendkeys")){
                        elementExist.waitForElementByXpath(caseidElement,driver);
                        driver.findElement(By.xpath(caseidElement)).sendKeys(caseidTestData);
                        if (caseidDelayTieme != null && caseidDelayTieme.length()!=0){
                            //String sleepTime = caseidDelayTieme.substring(0,caseidDelayTieme.indexOf("."));
                            int sleepTime = Integer.parseInt(caseidDelayTieme);
                            Thread.sleep(sleepTime);
                        }
                    }else if (caseidOperationMethod.equals("click")){
                        elementExist.waitForElementByXpath(caseidElement,driver);
                        driver.findElement(By.xpath(caseidElement)).click();
                        if (caseidDelayTieme != null && caseidDelayTieme.length()!=0){
                            int sleepTime = Integer.parseInt(caseidDelayTieme);
                            Thread.sleep(sleepTime);
                        }
                    }else if (caseidOperationMethod.equals("swipedown")){
                        elementExist.waitForElementByXpath(caseidElement,driver);
                        slidePage.down_Page(caseidElement,driver);
                        if (caseidDelayTieme != null && caseidDelayTieme.length()!=0){
                            int sleepTime = Integer.parseInt(caseidDelayTieme);
                            Thread.sleep(sleepTime);
                        }
                    }
                }else if (caseidLocation.equals("By.id")){
                    if (caseidOperationMethod.equals("sendkeys")){
                        elementExist.waitForElementById(caseidElement,driver);
                        driver.findElement(By.id(caseidElement)).sendKeys(caseidTestData);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }else if (caseidOperationMethod.equals("click")){
                        elementExist.waitForElementById(caseidElement,driver);
                        driver.findElement(By.id(caseidElement)).click();
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }

                    }else if (caseidOperationMethod.equals("swipedown")){
                        elementExist.waitForElementById(caseidElement,driver);
                        slidePage.down_Page(caseidElement,driver);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }else if (caseidOperationMethod.equals("swipeToDown")){
                        //elementExist.waitForElementById(caseidElement,driver);
                        slidePage.swipeToDown(1,driver);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    } else if (caseidOperationMethod.equals("DownPage")){
                        elementExist.waitForElementById(caseidElement,driver);
                        slidePage.downPage(caseidElement,driver);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }
                }else if (caseidLocation.equals("By.name")){
                    if (caseidOperationMethod.equals("sendkeys")){
                        elementExist.waitForElementByName(caseidElement,driver);
                        driver.findElement(By.name(caseidElement)).sendKeys(caseidTestData);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }else if (caseidOperationMethod.equals("click")){
                        elementExist.waitForElementByName(caseidElement,driver);
                        driver.findElement(By.name(caseidElement)).click();
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }else if (caseidOperationMethod.equals("press")){
                        elementExist.waitForElementByName(caseidElement,driver);
                        WebElement webElement = driver.findElement(By.name(caseidElement)); //长按拍视频
                        TouchAction touchAction = new TouchAction(driver);
                        touchAction.press(PointOption.point((Point) webElement)).
                                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release().perform();
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }

                    }
                }else if (caseidLocation.equals("By.driver")){
                    if (caseidOperationMethod.equals("swipetoup")){
                        slidePage.swipeToUp(Integer.parseInt(caseidElement),driver);
                        if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                            Thread.sleep(Integer.parseInt(caseidDelayTieme));
                        }
                    }
                }else if (caseidLocation.equals("By.name")){
                    if ("pass".equals(elementExist.waitForElementByNameSkip(caseidElement,driver))){
                        if (caseidOperationMethod.equals("click")){
                            driver.findElement(By.name(caseidElement)).click();
                            if (caseidDelayTieme !=null && caseidDelayTieme.length()!=0){
                                Thread.sleep(Integer.parseInt(caseidDelayTieme));
                            }
                        }
                    }else if ("faild".equals(elementExist.waitForElementByNameSkip(caseidElement,driver))){
                        continue;
                    }
                }
                if (caseidValidationData !=null && caseidValidationData.length()!=0){
                    elementExist.waitForElementByName(caseidValidationData,driver);
                }
                for (p=0;p<autoOperatExcelFile.readTitle().size();p++){
                    if (autoOperatExcelFile.readTitle().get(p).contains("测试结果")){
                        break;
                    }
                }
                autoOperatExcelFile.WriteTitleContent(i+1,(short)p);
            }else {

            }
        }
        Thread.sleep(2000);
    }



}
