package com.nasico.wechat;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nasico.wechat.offcialaccounts.ApiUtils;
import com.nasico.wechat.offcialaccounts.card.BackgorundColor;
import com.nasico.wechat.offcialaccounts.card.CardType;
import com.nasico.wechat.offcialaccounts.card.CodeType;

public class WechatCardTest {
	public static void main(String[] args) {
		// TODO 1、获取token
//		String token = ApiUtils.getToken();
		String token = "21_eTiCLAB9IyF2A2sr3IAqyOpgg02UbPwNTRG-4gBzmjHRQI7cg0sju8ire8Bzz6Hgl3iyEFCBplClBjd-FvPzzdl7THVERM5ysMcKZl2L1Dj7KEb96rgyjuy2AzS-A8X-nGUQNzYSs1Z7U1ToRIMiACALCG";
		// TODO 2、上传logo 如果有logo就不需要再上传 直接那链接用就行
//			File logo = new File("D:\\当前项目\\tictac\\userfiles\\1\\images\\activity\\2018\\11\\WeChat 圖片_20181121104930.jpg");
//			String logoUrl = ApiUtils.uploadimg(token,logo);
		String logoUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/9vibRegKSe0A9IbdupB6icFWtU3aHJ1lIwSp6dnBv0jYQoag9Yznr4Y9IEvebYS6oAcpYCM9FuIdYSc7SVFqGmsQ/0";
//		System.out.println(logoUrl);
//		// TODO 3、创建卡券
		JSONObject cardParams = new JSONObject();

		JSONObject card = new JSONObject();
		card.put("card_type", CardType.GENERAL_COUPON);

		JSONObject groupon = new JSONObject();
		card.put(CardType.GENERAL_COUPON.getCardType().toLowerCase(), groupon);// key 与上面card_type的类型保持一致
		JSONObject baseInfo = new JSONObject();
		groupon.put("base_info", baseInfo);

		baseInfo.put("logo_url", logoUrl);
		baseInfo.put("code_type", CodeType.CODE_TYPE_QRCODE);
		baseInfo.put("brand_name", "垃圾佬有限公司");// 商户名字,字数上限为12个汉字。
		baseInfo.put("title", "测试卡券名");// 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
		baseInfo.put("color", BackgorundColor.Color010.getColorHex());
		baseInfo.put("notice", "请出示二维码");// 卡券使用提醒，字数上限为16个汉字。
		baseInfo.put("description", "不可与其他优惠同享\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，");// 卡券使用说明，字数上限为1024个汉字。
		/**
		 * "sku": { "quantity": 500000 }
		 */
		JSONObject sku = new JSONObject();
		sku.put("quantity", 10000);

		baseInfo.put("sku", sku);// 商品信息。
		/**
		 * "date_info": { "type": "DATE_TYPE_FIX_TIME_RANGE", "begin_timestamp":
		 * 1397577600, "end_timestamp": 1472724261 }
		 */
		JSONObject dateInfo = new JSONObject();
//		dateInfo.put("type","DATE_TYPE_FIX_TIME_RANGE");//DATE_TYPE_FIX_TIME_RANGE 表示固定日期区间，DATETYPE FIX_TERM 表示固定时长 （自领取后按天算。
//		dateInfo.put("begin_timestamp","请出示二维码");//type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间,UTC+8，单位为秒）
//		dateInfo.put("end_timestamp","请出示二维码");//表示结束时间 ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒 ）
		dateInfo.put("type", "DATE_TYPE_FIX_TERM");
		dateInfo.put("fixed_term", 30);// type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。
		dateInfo.put("fixed_begin_term", 0);// type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
		// dateInfo.put("end_timestamp","请出示二维码");//可用于DATE_TYPE_FIX_TERM时间类型，表示卡券统一过期时间
		// ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒
		// ），设置了fixed_term卡券，当时间达到end_timestamp时卡券统一过期

		baseInfo.put("date_info", dateInfo);// 使用日期，有效期的信息。
		// TODO 其他更多非必填字段或高级信息字段自行查看添加
		// https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025056
		// 非必填字段 start
		baseInfo.put("get_limit", 50);// 每人可领券的数量限制,不填写默认为50。
		baseInfo.put("use_limit", 50);// 每人可核销的数量限制,不填写默认为50。
		baseInfo.put("can_share", true);// 卡券领取页面是否可分享。
		baseInfo.put("can_give_friend", true);// 卡券是否可转赠。
		baseInfo.put("bind_openid", false);// 是否指定用户领取，填写true或false 。默认为false。通常指定特殊用户群体 投放卡券或防止刷券时选择指定用户领取。
		baseInfo.put("use_custom_code", false);//是否自定义Code码 。填写true或false，默认为false。 通常自有优惠码系统的开发者选择 自定义Code码，并在卡券投放时带入 Code码，详情见 是否自定义Code码 。
		groupon.put("default_detail", "优惠券详情");
		// 非必填字段 end
		cardParams.put("card", card);
		System.out.println(cardParams);
		ApiUtils.createCard(token, cardParams);
//		String cardId = "phW_H1TSih29LJijTUl6O7mlZwKo";
		// TODO 4、创建二维码投放 ：主动将优惠券推送给客户
		// TODO 5、显示二维码
		// TODO 6、设置测试白名单 这一步是测试开发时使用。正式环境不需要
//		JSONObject list = new JSONObject();
//		JSONArray openidItems = new JSONArray();
//		JSONArray usernameItems = new JSONArray();
//		usernameItems.add("haoyangdeyouxi");
//		usernameItems.add("WX_825940390");
//		list.put("username", usernameItems);
//		list.put("openid", openidItems);
//		ApiUtils.addWhiteList(token,list);
		// TODO 6、核销卡劵
	}
}
