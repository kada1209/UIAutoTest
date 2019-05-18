package module;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangxinqi
 * @date 2019-03-23 19:46
 *
 * 截屏保存图片，并自动当天的日期分天保存在文件夹下面
 */
public class ScreenShot {
    public static void takeScreenShot(WebDriver driver){
        File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile,new File("screenshot/"+getDatePath()+"/"+getCurrentDateTime()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //得到当前时间
    public static String getCurrentDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return simpleDateFormat.format(new Date());
    }
    //得到路径
    public static File getDatePath(){
        Date date = new Date();
        String path = new SimpleDateFormat("yyyyMMdd").format(date);
        File file = new File(path);
        return file;
    }
}
