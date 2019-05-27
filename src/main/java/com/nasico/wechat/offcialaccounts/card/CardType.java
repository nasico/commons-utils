package com.nasico.wechat.offcialaccounts.card;

/**
 * 优惠券类型
 * @author nasico
 *
 */
public enum CardType {
	/**
	 * 团购券
	 */
	GROUPON("GROUPON"),
	/**
	 * 代金券
	 */
	CASH("CASH"),
	/**
	 * 折扣券
	 */
	DISCOUNT("DISCOUNT"),
	/**
	 * 兑换券
	 */
	GIFT("GIFT"),
	/**
	 * 优惠券
	 */
	GENERAL_COUPON("GENERAL_COUPON");
	
	private String cardType;

	CardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardType() {
		return cardType;
	}
	
}
