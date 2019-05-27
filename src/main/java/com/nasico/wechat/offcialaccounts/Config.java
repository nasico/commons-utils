package com.nasico.wechat.offcialaccounts;

/**
 *   公众号配置
 * @author nasico
 *
 */
public class Config {
	private static final String APPID = "wx4e09a64e3e29379c";
	private static final String APPSECRET = "b458cf3f7505fee3abf307405220f759";
	private static final String wechatApiServerHost = "https://api.weixin.qq.com";
	
	public static String getWechatapiserverhost() {
		return wechatApiServerHost;
	}
	public static String getAppid() {
		return APPID;
	}
	public static String getAppsecret() {
		return APPSECRET;
	}
	
}
