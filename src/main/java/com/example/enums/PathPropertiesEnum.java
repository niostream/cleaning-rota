package com.example.enums;

public enum PathPropertiesEnum {
	
	/** Excel出力ファイルパス */
	OUTPUT_CLEANING_ROTA_FILE_PATH("output-cleaning-rota-file-path");
	
	/** パス */
	private String path;
	
	/**
	 * コンストラクタ
	 * @param path パス
	 */
	private PathPropertiesEnum(String path) {
		this.path = path;
	}

	/**
	 * パス取得
	 * @return パス
	 */
	public String getPath() {
		return path;
	}

}
