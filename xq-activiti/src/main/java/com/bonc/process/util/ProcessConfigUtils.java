package com.bonc.process.util;

import java.io.InputStream;
import java.util.Properties;


/**
 * 读取门户和沙盒所需要配置的工具类
 * @author Administrator
 *
 */
public class ProcessConfigUtils {
	
	public static Properties props = new Properties();;
	
	static{
		try {
			InputStream in = ProcessConfigUtils.class.getClassLoader().getResourceAsStream("processConfig.properties");
			props = new Properties();
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  String approvePassCallBack = getProperty("approvePassCallBack");

	public static String getProperty(String key){
		String value = props.getProperty(key);
		if (key !=null && key.startsWith("encrypt"))
			return DESUtil.decrypt(value);
		return value;
	}
	
	public static String getProperty(String key, String defaultValue){
		String value = props.getProperty(key, defaultValue);
		if (key !=null && key.startsWith("encrypt"))
			return DESUtil.decrypt(value);
		return value;
	}
	
}
