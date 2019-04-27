package com.example.enums;

public enum CharacterEnum {
	
	/** ブランク */
	BLANK("");
	
	/** 文字 */
	private String character;
	
	/**
	 * コンストラクタ
	 * @param character 文字
	 */
	private CharacterEnum(String character) {
		this.character = character;
	}

	/**
	 * 文字取得
	 * @return 文字
	 */
	public String getCharacter() {
		return character;
	}

}
