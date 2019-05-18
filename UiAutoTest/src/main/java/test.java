import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import module.Config;
import module.ElementExist;
import module.SlidePage;
import operatFile.AutoOperatExcelFile;
import operatFile.AutoTestCaseId;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by Administrator on 2019/3/21.
 */

public class test {
    static AppiumDriver driver;
    ElementExist elementExist = new ElementExist();
    static AutoOperatExcelFile autoOperatExcelFile = new AutoOperatExcelFile();
    SlidePage slidePage = new SlidePage();
    public static void main(String[] args) throws IOException, InterruptedException {

        Map<Integer,String> map = autoOperatExcelFile.readExcelContent(new FileInputStream(Config.getInstance().getCfg("filePath")));
        for (Map.Entry<Integer,String> entry :map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        String content = autoOperatExcelFile.ReadTitleContent(2,8);
        int x = Integer.parseInt(content);
        System.out.println(x);

        //String sleepTime = content.substring(0,content.indexOf("."));

        System.out.println(content);

        List list = autoOperatExcelFile.ReadContent();
        System.out.println(list);

        AutoTestCaseId testCaseId   = new AutoTestCaseId();
        testCaseId.testCaseId(driver,"login1");



        }




    }

