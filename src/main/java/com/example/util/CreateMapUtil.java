package com.example.util;

import java.time.LocalDate;
import java.util.ArrayList;
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
	 * 掃除当番表レコードマップ
	 * @param cleaningRecords 掃除当番表レコードリスト
	 * @param cleaningRecord 掃除当番表レコード
	 * @param items 掃除当番表項目リスト
	 * @return
	 */
	public static Map<LocalDate, Map<Item, CleaningRecord>> createCleaningRecordMap(
			List<CleaningRecord> cleaningRecords, CleaningRecord cleaningRecord, List<Item> items) {
		
		// 当月一覧日付取得
		List<LocalDate> monthList = Stream.iterate(LocalDate.of(cleaningRecord.getExecutedDate().getYear(),
				cleaningRecord.getExecutedDate().getMonth(), 1), x -> x.plusDays(1))
                .limit(cleaningRecord.getExecutedDate().lengthOfMonth())
                .collect(Collectors.toList());
		
		// 掃除当番表レコードマップ生成
		Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap = new LinkedHashMap<>();
		for (LocalDate date : monthList) {
			// Item項目設定
			Map<Item, CleaningRecord> itemMap = new LinkedHashMap<>();
			for (Item item : items) {
				itemMap.put(item, null);
			}
			cleaningRecordMap.put(date, itemMap);
			
			// 掃除当番表レコード設定
			for (CleaningRecord record : cleaningRecords) {
				if (date.equals(record.getExecutedDate())) {
					Map<Item, CleaningRecord> itemMap2 = cleaningRecordMap.get(date);
					for (Item item2 : items) {
						if (item2.equals(record.getItem())) {
							itemMap2.put(item2, record);
						}
					}
					cleaningRecordMap.put(date, itemMap);
				}
			}
		}
		
		return cleaningRecordMap;
	}
	
//	public static Map<LocalDate, List<CleaningRecord>> createCleaningRecordMap3(
//			List<CleaningRecord> cleaningRecords, CleaningRecord cleaningRecord) {
//		
//		// 当月一覧日付取得
//		List<LocalDate> monthList = Stream.iterate(LocalDate.of(cleaningRecord.getExecutedDate().getYear(),
//				cleaningRecord.getExecutedDate().getMonth(), 1), x -> x.plusDays(1))
//                .limit(cleaningRecord.getExecutedDate().lengthOfMonth())
//                .collect(Collectors.toList());
//		
//		// 掃除当番表レコードマップ生成
//		Map<LocalDate, List<CleaningRecord>> cleaningRecordMap = new LinkedHashMap<>();
//		for (LocalDate date : monthList) {
//			cleaningRecordMap.put(date, new ArrayList<>());
//		}
//		
//		// 掃除当番表記録があるデータを設定
//		for (LocalDate date : monthList) {
//			for (CleaningRecord record : cleaningRecords) {
//				if (date.equals(record.getExecutedDate())) {
//					if (cleaningRecordMap.containsKey(date)) {
//						List<CleaningRecord> mapRecord = cleaningRecordMap.get(date);
//						mapRecord.add(record);
//						cleaningRecordMap.put(date, mapRecord);
//					} 
//				}
//			}
//		}
//		
//		return cleaningRecordMap;
//	}

}
