package com.nasico.net;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtil {

	public static void main(String[] args) throws IOException {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			File file = new File(
					"D:\\当前项目\\tictac\\userfiles\\1\\images\\activity\\2018\\11\\WeChat 圖片_20181121104930.jpg");
			String message = "21_TOe0Ul1l5f6vRdTIiEjIptS53LsfeyPz1ETFe5dETEwUhzh54kJ6Pua6h0bZFx49e9XsjgcD0ETssczx3eOpxOS4A8VJUmn27RRCmseama_tPyqPKpz7ZHy8QvJaIS1wwc8tfktV4DrZbB5IZCIeAJAJMS";

			// build multipart upload request
			HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addBinaryBody("buffer", file, ContentType.DEFAULT_BINARY, file.getName())
					.addTextBody("access_token", message, ContentType.DEFAULT_BINARY).build();

			// build http request and assign multipart upload data
			HttpUriRequest request = RequestBuilder.post("https://api.weixin.qq.com/cgi-bin/media/uploadimg")
					.setEntity(data).build();

			System.out.println("Executing request " + request.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			String responseBody = httpclient.execute(request, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
		}
	}


	public static String doGet(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		StringBuilder builder = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (builder.length() != 0) {
					builder.append("&");
				}
				builder.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "utf-8"));
			}
			url += "?" + builder;
		}
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			String result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			return result;
		} finally {
			response.close();
		}
	}

	public static String doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpClient.execute(httpPost);

		try {
			String result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			return result;
		} finally {
			response.close();
		}
	}

	private static String httpJsonRequest(String requestUrl, RequestMethod method, Map<String, String> params) {
		
		return requestUrl;
	}

	public static String doJsonGet(String url, Map<String, String> params) {
		return httpJsonRequest(url, RequestMethod.GET, params);
	}

	public static String doJsonPost(String url, Map<String, Object> params) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		
		StringEntity se = new StringEntity(JSON.toJSONString(params),"utf-8");
		se.setContentType("application/json");
		se.setContentEncoding("utf-8");
		httpPost.setEntity(se);
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			String result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			return result;
		} finally {
			response.close();
		}
	}

	public static String doPost(String apiUrl, Map<String, String> params, Map<String, File> fileParams)
			throws ClientProtocolException, IOException {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			// build multipart upload request
			MultipartEntityBuilder builder = MultipartEntityBuilder.create()
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

			for (String key : params.keySet()) {
				builder.addTextBody(key, params.get(key), ContentType.DEFAULT_BINARY).build();
			}

			for (String key : fileParams.keySet()) {
				File file = fileParams.get(key);
				builder.addBinaryBody(key, file, ContentType.DEFAULT_BINARY, file.getName());
			}
			HttpEntity data = builder.build();
					
			// build http request and assign multipart upload data
			HttpUriRequest request = RequestBuilder.post(apiUrl).setEntity(data).build();

//            System.out.println("Executing request " + request.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			return httpclient.execute(request, responseHandler);
		}
	}

}
