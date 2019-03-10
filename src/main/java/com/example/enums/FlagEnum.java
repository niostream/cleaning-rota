package com.example.enums;

public enum FlagEnum {
	
	/** OFF */
	FLAG_OFF(0),
	
	/** ON */
	FLAG_ON(1);
	
	/** フラグ */
	private int flag;
	
	/**
	 * コンストラクタ
	 * @param flag フラグ
	 */
	private FlagEnum(int flag) {
		this.flag = flag;
	}

	/**
	 * フラグ取得
	 * @return フラグ
	 */
	public int getFlag() {
		return flag;
	}

}
