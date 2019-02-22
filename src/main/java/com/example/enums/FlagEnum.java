package com.example.enums;

public enum FlagEnum {
	
	/** OFF */
	FLAG_OFF(0),
	
	/** ON */
	FLAG_ON(1);
	
	private int flag;
	
	/**
	 * コンストラクタ
	 * @param flag フラグ
	 */
	private FlagEnum(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

}
