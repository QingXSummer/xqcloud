package com.bonc.process.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于请求http服务
 * @author xiaqing
 *
 * Created on  2017年12月29日   下午4:20:43
 */
public class HttpUtil {
	
	//创建默认的httpclient客户端
	static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	
	
	public static void post(String  url, String jsonParam) throws ClientProtocolException, IOException{
        HttpPost httpPost = new HttpPost(url);
         CloseableHttpClient client = HttpClients.createDefault();
         String respContent = null;
    //     StringEntity entity = new StringEntity(jsonParam,"utf-8");//解决中文乱码问题    
    //     entity.setContentEncoding("UTF-8");    
      //   entity.setContentType("application/json");    
        // httpPost.setEntity(entity);
         List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(); 
         pairList.add(new BasicNameValuePair("param", jsonParam));
         httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8")); 
         HttpResponse resp = client.execute(httpPost);
                  if(resp.getStatusLine().getStatusCode() == 200) {
                      HttpEntity he = resp.getEntity();
                      respContent = EntityUtils.toString(he,"UTF-8");
                  }
	}
	
	/**
	 * 发起一个get方式的请求
	 * @param url 地址
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void get(String url) throws ClientProtocolException, IOException{
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse  response = httpClient.execute(httpGet);
		System.out.println(response.getStatusLine());
		System.out.println(response.getEntity());
		HttpEntity httpEntity = response.getEntity();
		System.out.println(httpEntity.getContentType());
		System.out.println(EntityUtils.toString(httpEntity));
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		get("http://www.baidu.com");
		
	}
}
