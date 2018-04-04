package com.tof.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/***
 * http工具
 * 
 * @author Administrator
 *
 */
public class HttpClientUtil {

	/**
	 * 发�?get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		String content = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			response.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 发 post请求
	 * 
	 * @param url
	 * @param mapParams
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String post(String url, Map<String, String> mapParams) throws ClientProtocolException, IOException {
		String content = "";
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httpPost
		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 60).setConnectTimeout(1000 * 60)
				.build();// 设置请求和传输超时时�?
		httpPost.setConfig(requestConfig);

		CloseableHttpResponse response = null;

		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (mapParams != null) {
			Set<Entry<String, String>> entrySet = mapParams.entrySet();
			for (Entry<String, String> entry : entrySet) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		httpPost.setEntity(uefEntity);
		System.out.println("executing request " + httpPost.getURI());
			response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			content = EntityUtils.toString(entity, "UTF-8");
			System.out.println("--------------------------------------");
			System.out.println("Status: " + response.getStatusLine());
			System.out.println("Response content: " + content);
			System.out.println("--------------------------------------");
		}
		// 关闭连接,释放资源
		response.close();
		httpclient.close();
		return content;
	}


}
