package SpringTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class BeanFactory {

    //hashTable的子类 根据输入流可以读取文件中的键值对
    private static Properties properties ;
    private static HashMap<String, String> map ;

    static {
        try {
            properties = new Properties();
            //加载配置文件
            InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("aa");
            properties.load(inputStream);
            //获取配置文件所有键
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String value = properties.getProperty(key);
                Class name = Class.forName(value);
                map = new HashMap<>();
                map.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化失败!");
        }
    }

    public static Object getBean(String name){
        return map.get(name);
    }
}
