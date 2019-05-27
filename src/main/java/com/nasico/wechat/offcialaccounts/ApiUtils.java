package com.nasico.wechat.offcialaccounts;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ApiUtils {
	private static ApiFactroy factroy = new ApiFactroy();

	private static Map<String, Object> token = new HashMap<String, Object>(2);

	/**
	 * 获取access_token 返回码 说明 -1 系统繁忙，此时请开发者稍候再试 0 请求成功 40001
	 * AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性 40002
	 * 请确保grant_type字段值为client_credential 40164
	 * 调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）
	 * 
	 * @return {"access_token":"ACCESS_TOKEN","expires_in":7200}
	 */
	public static String getToken() {

		if (tokenIsInvalid()) {
			JSONObject obj = getResult(factroy.getToken(Config.getAppid(), Config.getAppsecret()));
			token.put("token", obj.getString("access_token"));
			token.put("expiresIn", new Date(System.currentTimeMillis() + 7000));
		}

		return token.get("token").toString();
	}

	private static JSONObject getResult(String result) {
		JSONObject obj = JSON.parseObject(result);
		System.out.println(result);
		if (obj.get("errcode") != null && obj.getIntValue("errcode") != 0) {
			throw new RuntimeException(obj.toString());
		}
		return obj;
	}

	private static boolean tokenIsInvalid() {
		boolean invalid = false;
		if (token.isEmpty()) {
			invalid = true;
		} else {
			Date expiresIn = (Date) token.get("expiresIn");
			if (expiresIn.before(new Date())) {
				invalid = true;
			}
		}
		return invalid;
	}

	public static List<String> getCallbackip(String token) {
		JSONObject obj = getResult(factroy.getCallbackip(token));
		JSONArray ipList = (JSONArray) obj.get("ip_list");
		return ipList.toJavaList(String.class);
	}

	public static String uploadimg(String token, File file) {
		try {
			JSONObject obj = getResult(factroy.uploadimg(token, file));
			return obj.getString("url");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String createCard(String token,Map<String, Object> params) {
		try {
			JSONObject obj = getResult(factroy.createCard(token, params));
			return obj.getString("card_id");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void addWhiteList(String token,JSONObject list) {
		try {
			getResult(factroy.addWhiteList(token, list));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String getTicket(String token) {
		try {
			JSONObject obj = getResult(factroy.getTicket(token));
			return obj.getString("card_id");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		String token = ApiUtils.getToken();
		System.out.println(ApiUtils.getTicket(token));
	}
}
