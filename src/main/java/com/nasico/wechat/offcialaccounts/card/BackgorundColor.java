package com.nasico.wechat.offcialaccounts.card;

/**
 * 卡券背景色
 * @author nasico
 *
 */
public enum BackgorundColor {
	/**
	 * 浅绿 "#63b359"
	 */
	Color010("Color010"),
	/**
	 * 深绿 "#2c9f67"
	 */
	Color020("Color020"),
	/**
	 * 浅蓝"#509fc9"
	 */
	Color030("Color030"),
	/**
	 * 深蓝"#5885cf"
	 */
	Color040("Color040"),
	/**
	 * 紫色"#9062c0"
	 */
	Color050("Color050"),
	/**
	 * 棕色"#d09a45"
	 */
	Color060("Color060"),
	/**
	 * 卡其色"#e4b138"
	 */
	Color070("Color070"),
	/**
	 * 橙色"#ee903c"
	 */
	Color080("Color080"),
	/**
	 *  橙色加深"#f08500"
	 */
	Color081("Color081"),
	/**
	 * 嫩绿色"#a9d92d"
	 */
	Color082("Color082"),
	/**
	 * 橘红色"#dd6549"
	 */
	Color090("Color090"),
	/**
	 * 夕阳红"#cc463d"
	 */
	Color100("Color100"),
	/**
	 * 夕阳红加深"#cf3e36"
	 */
	Color101("Color101"),
	/**
	 *  铅笔黑色"#5E6671"
	 */
	Color102("Color102");
	
	private String colorHex;

	BackgorundColor(String colorHex) {
		this.colorHex = colorHex;
	}

	public String getColorHex() {
		return colorHex;
	}
	
}
