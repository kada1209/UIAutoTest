package module;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * c初始化配置文件信息，根据配置文件是android_config.properties还是ios_config_properties进行判断
 * @author wangxinqi
 * @date 2019-03-26 23:30
 */
public class InitSetUp {
    public DesiredCapabilities initSetupCFG(final DesiredCapabilities desiredCapabilities){
        if (Config.CONFIG_FILE.equals("android_config.properties")){
            final File appDir = new File(System.getProperty("user.dir"),"app");
            final File app = new File(appDir,Config.getInstance().getCfg("appDir"));
            //设置app所在的路径
            //desiredCapabilities.setCapability("app",Config.getInstance().getCfg("app"));
            //测试的web浏览器，如果是测app则忽略
           // desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,"");
            //设置Android版本号
            desiredCapabilities.setCapability("platformVersion",Config.getInstance().getCfg("platformVersion"));
            //设置Android系统
            desiredCapabilities.setCapability("platformName",Config.getInstance().getCfg("platformName"));
            //设置连接的手机名称
            desiredCapabilities.setCapability("deviceName",Config.getInstance().getCfg("deviceName"));
            //设置App的包名
            desiredCapabilities.setCapability("appPackage",Config.getInstance().getCfg("appPackage"));
            //设置App的启动入口Activity
            desiredCapabilities.setCapability("appActivity",Config.getInstance().getCfg("appActivity"));
            //设置支持中文输入
            desiredCapabilities.setCapability("unicodeKeyboard",Config.getInstance().getCfg("unicodeKeyboard"));
            //重置Appium为默认输入法
            desiredCapabilities.setCapability("resetKeyboard",Config.getInstance().getCfg("resetKeyboard"));
            //设置Appium命令超时时间
            desiredCapabilities.setCapability("newCommandTimeout",Config.getInstance().getCfg("newCommandTimeout"));

        }else if (Config.CONFIG_FILE.equals("ios_config.properties")){
            final File appDir = new File(System.getProperty("user.dir"),"app");
            final File app = new File(appDir,Config.getInstance().getCfg("appDir"));
            //设置ios版本号
            desiredCapabilities.setCapability("platformVersion",Config.getInstance().getCfg("platformVersion"));
            //设置ios系统
            desiredCapabilities.setCapability("platformName",Config.getInstance().getCfg("platformName"));
            //设置连接的手机名称
            desiredCapabilities.setCapability("deviceName",Config.getInstance().getCfg("deviceName"));
            //设置App的包名
            desiredCapabilities.setCapability("app",Config.getInstance().getCfg("app"));
            //设置iphone手机的UDID
            desiredCapabilities.setCapability("udid",Config.getInstance().getCfg("udid"));
        }
        return desiredCapabilities;
    }
    public void TearDownCFG(AppiumDriver driver){
        driver.quit();
    }
}
