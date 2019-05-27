package com.nasico.wechat.offcialaccounts;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;

/**
 * 卡券接口
 * 
 * @author nasico
 *
 */
public interface ICard {
	/**
	 * 上传logo 
	 * 1.上传的图片限制文件大小限制1MB，仅支持JPG、PNG格式。
	 * 
	 * 2.调用接口获取图片url仅支持在微信相关业务下使用。
	 * 
	 * @param token
	 * @param bis   图片输出流
	 * @return {"url":"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0"}
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	String uploadimg(String token,File file) throws ClientProtocolException, IOException;

	/**
	 * 创建卡券
	 * 
	 * 创建卡券接口是微信卡券的基础接口，用于创建一类新的卡券，获取card_id，创建成功并通过审核后，商家可以通过文档提供的其他接口将卡券下发给用户，每次成功领取，库存数量相应扣除。
	 * 
	 * @param token
	 * @param params 创建卡券所需字段
	 * @return { "errcode":0, "errmsg":"ok",
	 *         "card_id":"p1Pj9jr90_SQRaVqYI239Ka1erkI"}
	 * @throws IOException 
	 */
	String createCard(String token, Map<String, Object> params) throws IOException;

	/**
	 * 设置买单
	 * 
	 * 微信卡券买单功能是微信卡券的一项新的能力，可以方便消费者买单时，直接录入消费金额，自动使用领到的优惠（券或卡）抵扣，并拉起微信支付快速完成付款。
	 * 
	 * 微信买单（以下统称微信买单）的好处：
	 * 
	 * 1、无需商户具备微信支付开发能力，即可完成订单生成，与微信支付打通。
	 * 
	 * 2、可以通过手机公众号、电脑商户后台，轻松操作收款并查看核销记录，交易对账，并支持离线下载。
	 * 
	 * 3、支持会员营销，二次营销，如会员卡交易送积分，抵扣积分，买单后赠券等。
	 * 
	 * 创建卡券之后，开发者可以通过设置微信买单接口设置该card_id支持微信买单功能。值得开发者注意的是，设置买单的card_id必须已经配置了门店，否则会报错。
	 * 
	 * @param token
	 * @param params
	 * @return
	 */
	String paycell(String token, Map<String, String> params);
	
	
	String selfConsumeCell(String token, Map<String, String> params);


}
