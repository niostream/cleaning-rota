package com.example.util;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.domain.CleaningRecord;
import com.example.domain.Item;

public class CreateMapUtil {
	
	/** インスタンス生成禁止 */
	private CreateMapUtil() {
		
	}
	
	/**
	 * 掃除当番表レコードマップ生成
	 * @param cleaningRecords 掃除当番表レコードリスト
	 * @param cleaningRecord 掃除当番表レコード
	 * @param items 掃除当番表項目リスト
	 * @return 掃除当番表レコードマップ
	 */
	public static Map<LocalDate, Map<Item, CleaningRecord>> createCleaningRecordMap(
			List<CleaningRecord> cleaningRecords, CleaningRecord cleaningRecord, List<Item> items) {
		
		// 当月一覧日付取得
		List<LocalDate> monthList = Stream.iterate(LocalDate.of(cleaningRecord.getExecutedDate().getYear(),
				cleaningRecord.getExecutedDate().getMonth(), 1), d -> d.plusDays(1))
                .limit(cleaningRecord.getExecutedDate().lengthOfMonth())
                .collect(Collectors.toList());
		
		// 掃除当番表レコードマップ生成
		Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap = new LinkedHashMap<>();
		for (LocalDate date : monthList) {
			
			// アイテム項目設定
			Map<Item, CleaningRecord> itemMap = new LinkedHashMap<>();
			for (Item item : items) {
				itemMap.put(item, null);
			}
			cleaningRecordMap.put(date, itemMap);
			
			// 掃除当番表レコード設定
			for (CleaningRecord record : cleaningRecords) {
				if (date.equals(record.getExecutedDate())) {
					Map<Item, CleaningRecord> inputItemMap = cleaningRecordMap.get(date);
					for (Item inputItem : items) {
						if (inputItem.equals(record.getItem())) {
							inputItemMap.put(inputItem, record);
						}
					}
					cleaningRecordMap.put(date, itemMap);
				}
			}
			
		}
		
		return cleaningRecordMap;
		
	}
	
}
