package log;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.testng.Reporter;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**日志信息
 * @author wangxinqi
 * @date 2019-03-28 23:56
 */
public class AutoLogger {
    private static Logger logger = null;
    private static AutoLogger autoLogger =null;
    public static AutoLogger getLogger(Class<?>T){
        if (logger==null){
            Properties properties = new Properties();
            try {
                InputStream inputStream =  new FileInputStream("log4j.properties");
                properties.load(inputStream);
            }catch (IOException e){
                e.printStackTrace();
            }
            PropertyConfigurator.configure(properties);
            logger=Logger.getLogger(T);
            autoLogger = new AutoLogger();

        }
        return autoLogger;
    }

    /**
     * 重写logger方法
     * @param msg
     */
    public void log(String msg){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        logger.info(msg);
        Reporter.log("Report:"+simpleDateFormat.format(calendar.getTime())+"===>"+msg);
    }
    public void debug(String msg){
        logger.debug(msg);
    }
    public void warn(String msg){
        logger.warn(msg);
        Reporter.log("Report:"+msg);
    }
    public void error(String msg){
        logger.error(msg);
        Reporter.log("Report:"+msg);
    }
}
