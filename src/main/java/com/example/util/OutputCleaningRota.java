package com.example.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.domain.CleaningRecord;
import com.example.domain.Item;
import com.example.enums.PathPropertiesEnum;
import com.example.enums.PropertiesEnum;

public class OutputCleaningRota {
	
	/** インスタンス生成禁止 */
	private OutputCleaningRota() {
		
	}
	
	/**
	 * Excel出力
	 * @param items 項目リスト
	 * @param cleaningRecordMap 掃除当番表記録マップ
	 */
	public static void output(List<Item> items, Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap,
			LocalDate localDate) {
		
		// ファイル生成
		Workbook outputWorkbook = new XSSFWorkbook();
		
		// シート生成
		Sheet outputSheet = outputWorkbook.createSheet("担当表");
		
		// テーブルヘッダースタイル
//      CellStyle tableHeaderStyle = outputWorkbook.createCellStyle();
//		tableHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		
		// 列幅設定 
		outputSheet.setColumnWidth(0, 2816); 
		
		// ヘッダー作成
		Row row = outputSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(localDate.getYear() + "年" + localDate.getMonthValue() + "月"
				+ " マンション名：クレール・ド・ジュール  部屋番号:201");
		
		// テーブルヘッダー作成
		row = outputSheet.createRow(2);
		row.createCell(0).setCellValue("日付");
		for (int i = 0; i < items.size(); i++) {
//			row.setRowStyle(tableHeaderStyle);
			row.createCell(i + 1).setCellValue(items.get(i).getItemName());
		}
		
		// テーブルボディ作成
		int rowIndex = 3;
		for (Entry<LocalDate, Map<Item, CleaningRecord>> entry : cleaningRecordMap.entrySet()) {
			row = outputSheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(entry.getKey().toString());
			for (Entry<Item, CleaningRecord> entry2 : entry.getValue().entrySet()) {
				if (entry2.getValue() != null) {
					row.createCell(entry2.getKey().getItemId()).setCellValue(
							entry2.getValue().getUser().getFirstName());
				}
			}
		}
		
		// 出力ストリーム設定
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(ResourceBundle.getBundle(PropertiesEnum.PATH.getFileName())
					.getString(PathPropertiesEnum.OUTPUT_CLEANING_ROTA_FILE_PATH.getPath())
					+ "東京寮担当表_" + localDate.getYear() + "年" + localDate.getMonthValue()
					+ "月_クレール・ド・ジュール.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// ファイル出力
		try {
			outputWorkbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
