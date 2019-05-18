package module;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangxinqi
 * @date 2019-03-21 23:41
 *
 * 读取文件配置信息
 */
public class Config {
    static final String CONFIG_FILE = "android_config.properties";
    private Map<String,String> configMap = new HashMap<String,String>();
    private static Config testResManager;
    public static Config getInstance(){
        if (testResManager ==null){
            testResManager = new Config();
        }
        return testResManager;
    }
    public Config(){
        configMap = loadFile(CONFIG_FILE);
    }
    protected Map<String,String> loadFile(String fileName){
        Map<String,String> map = null;
        if ((fileName != null)&&(!fileName.trim().equals(""))){
            InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null){
                map = new HashMap<String,String>();
                Properties properties = new Properties();
                try {
                    properties.load(new InputStreamReader(inputStream,"GBK"));
                    for (Map.Entry<Object,Object> entry:properties.entrySet()){
                        String key = (String)entry.getKey();
                        String value = (String) entry.getValue();
                        map.put(key,value);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
    public String getCfg(String key){
        return configMap.get(key);
    }
}
