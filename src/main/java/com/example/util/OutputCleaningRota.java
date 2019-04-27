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
	 * @param items アイテムリスト
	 * @param cleaningRecordMap 掃除当番表記録マップ
	 * @param cleaningRecord 掃除当番表レコード
	 */
	public static void output(List<Item> items, Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap,
			CleaningRecord cleaningRecord) {
		
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
		cell.setCellValue(cleaningRecord.getExecutedDate().getYear() + "年"
				+ cleaningRecord.getExecutedDate().getMonthValue() + "月 "
		        + cleaningRecord.getCrDormitory().getDormitoryDetail());
		
		// テーブルヘッダー作成
		row = outputSheet.createRow(2);
		row.createCell(0).setCellValue("日付");
		for (int i = 0; i < items.size(); i++) {
//			row.setRowStyle(tableHeaderStyle);
			row.createCell(i + 1).setCellValue(items.get(i).getItemName());
		}
		
		// テーブルボディ作成
		int rowIndex = 3;
		for (Entry<LocalDate, Map<Item, CleaningRecord>> executedDateEntry : cleaningRecordMap.entrySet()) {
			row = outputSheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(executedDateEntry.getKey().toString());
			for (Entry<Item, CleaningRecord> itemEntry : executedDateEntry.getValue().entrySet()) {
				if (itemEntry.getValue() != null) {
					row.createCell(itemEntry.getKey().getItemId()).setCellValue(
							itemEntry.getValue().getUser().getFirstName());
				}
			}
		}
		
		// 出力ストリーム設定
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(ResourceBundle.getBundle(PropertiesEnum.PATH.getFileName())
					.getString(PathPropertiesEnum.OUTPUT_CLEANING_ROTA_FILE_PATH.getPath())
					+ "東京寮担当表_" + cleaningRecord.getExecutedDate().getYear() + "年"
					+ cleaningRecord.getExecutedDate().getMonthValue() + "月_"
					+ cleaningRecord.getCrDormitory().getDormitoryDetail() + ".xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Excel出力
		try {
			outputWorkbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
