package com.nasico.wechat.offcialaccounts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.nasico.net.HttpUtil;

class ApiFactroy implements IBase, ICard {

	private String host = Config.getWechatapiserverhost();

	@Override
	public String getToken(String appid, String secret) {
		String apiUrl = host + "/cgi-bin/token";

		Map<String, String> params = new HashMap<String, String>(3);
		params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", secret);
		try {
			return HttpUtil.doGet(apiUrl, params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCallbackip(String token) {
		String apiUrl = host + "/cgi-bin/getcallbackip";

		Map<String, String> params = new HashMap<String, String>(1);
		params.put("access_token", token);
		try {
			return HttpUtil.doGet(apiUrl, params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String uploadimg(String token, File file) throws ClientProtocolException, IOException {
		String apiUrl = host + "/cgi-bin/media/uploadimg";

		Map<String, String> params = new HashMap<String, String>(1);
		params.put("access_token", token);
		Map<String, File> fileParams = new HashMap<String, File>(1);
		fileParams.put("buffer", file);
		return HttpUtil.doPost(apiUrl, params, fileParams);
	}

	@Override
	public String createCard(String token, Map<String, Object> params) throws IOException {
		String apiUrl = host + "/card/create?access_token=" + token;
		return HttpUtil.doJsonPost(apiUrl, params);
	}

	@Override
	public String paycell(String token, Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selfConsumeCell(String token, Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addWhiteList(String token, JSONObject list) throws IOException {
		String apiUrl = host + "/card/testwhitelist/set?access_token=" + token;
		return HttpUtil.doJsonPost(apiUrl, list);
	}

	public String getTicket(String token) throws ClientProtocolException, IOException {
		String apiUrl = host + "/cgi-bin/ticket/getticket?access_token=" + token+"&type=wx_card";
		return HttpUtil.doGet(apiUrl, null);
	}

}
