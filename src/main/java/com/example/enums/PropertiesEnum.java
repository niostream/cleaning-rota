package com.example.enums;

public enum PropertiesEnum {
	
	/** パス */
	PATH("path");
	
	/** パス */
	private String fileName;
	
	/**
	 * コンストラクタ
	 * @param fileName パス
	 */
	private PropertiesEnum(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * パス取得
	 * @return パス
	 */
	public String getFileName() {
		return fileName;
	}

}
