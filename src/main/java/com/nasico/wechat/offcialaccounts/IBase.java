package com.nasico.wechat.offcialaccounts;

/**
 * 基础接口
 * @author nasico
 *
 */
public interface IBase {
	/**
	 * 获取access_token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	String getToken(String appid, String secret);

	/**
	 * 获取微信服务器IP地址
	 * 
	 * @param token
	 * @return
	 */
	String getCallbackip(String token);
}
