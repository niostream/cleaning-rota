package com.example.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.CleaningRecord;
import com.example.domain.Dormitory;
import com.example.domain.Item;
import com.example.enums.FlagEnum;
import com.example.service.CleaningRecordService;
import com.example.service.DormitoryService;
import com.example.service.ItemService;
import com.example.service.LoginUserDetails;
import com.example.util.CreateMapUtil;
import com.example.util.OutputCleaningRota;

@Controller
@RequestMapping("cleaning_rota")
public class CleaningRotaController {
	
	@Autowired
	CleaningRecordService cleaningRecordService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	DormitoryService dormitoryService;
	
	/**
	 * 掃除当番表画面表示
	 * @param executedDate 実行日
	 * @param model モデル
	 * @param userDetails ログインユーザー情報
	 * @return 掃除当番表画面
	 */
	@GetMapping

	public String cleaningRota(@RequestParam String executedDate, Model model,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		// 実行日設定
		model.addAttribute("executedDate", executedDate);
		
		// 掃除当番表アイテムリスト
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		
		// 掃除当番表記録マップ
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setExecutedDate(LocalDate.parse(executedDate, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cleaningRecord.setCrDormitory(userDetails.getUser().getUserDormitory());
		List<CleaningRecord> cleaningRecords = cleaningRecordService.findAllByExecutedDate(cleaningRecord);
		Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap = CreateMapUtil.createCleaningRecordMap(
				cleaningRecords, cleaningRecord, items);
		model.addAttribute("cleaningRecordMap", cleaningRecordMap);
				
		// ページ遷移
		return "cleaning_rota/cleaning_rota";
		
	}
	
	/**
	 * 掃除当番表Excel出力
	 * @param executedDate 実行日
	 * @param userDetails ログインユーザー情報
	 * @return 掃除当番表表示機能
	 */
	@GetMapping(path = "output")
	public String outputCleaningRota(@RequestParam String executedDate,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		// 掃除当番表アイテムリスト
		List<Item> items = itemService.findAll();
		
		// 掃除当番表記録マップ
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setExecutedDate(LocalDate.parse(executedDate, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Optional<Dormitory> dormitory = dormitoryService.findByDormitoryId(userDetails.getUser().getUserDormitory().getDormitoryId());
		cleaningRecord.setCrDormitory(dormitory.get());
		List<CleaningRecord> cleaningRecords = cleaningRecordService.findAllByExecutedDate(cleaningRecord);
		Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap = CreateMapUtil.createCleaningRecordMap(
				cleaningRecords, cleaningRecord, items);
		
		// 掃除当番表Excel出力
		OutputCleaningRota.output(items, cleaningRecordMap, cleaningRecord);
		
		// ページ遷移
		return "redirect:/cleaning_rota?executedDate=" + executedDate;
		
	}
	
	/**
	 * 戻るボタンクリック
	 * @return メニュー表示機能
	 */
	@PostMapping(path = "navi", params = "goToMenu")
	public String goToMenu() {
		return "redirect:/menu";
	}
	
	/**
	 * 掃除当番表画面(前月)表示
	 * @param executedDate 実行日
	 * @return 掃除当番表表示機能
	 */
	@PostMapping(path = "beforeMonth")
	public String showBeforeMonthCleaningRota(@RequestParam String executedDate) {
		
		// 前月設定
		LocalDate localDate = LocalDate.parse(executedDate, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusMonths(1);
		
		// ページ遷移
		return "redirect:/cleaning_rota?executedDate=" + localDate;
		
	}
	
	/**
	 * 掃除当番表画面(次月)表示
	 * @param executedDate 実行日
	 * @return 掃除当番表表示機能
	 */
	@PostMapping(path = "afterMonth")
	public String showAfterMonthCleaningRota(@RequestParam String executedDate) {
		
		// 次月設定
		LocalDate localDate = LocalDate.parse(executedDate, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusMonths(1);
		
		// ページ遷移
		return "redirect:/cleaning_rota?executedDate=" + localDate;
		
	}

	/**
	 * 登録ボタンクリック
	 * @param executedDate 実行日
	 * @param itemId アイテムID
	 * @param userDetails ログインユーザー情報
	 * @return 掃除当番表表示機能
	 */
	@PostMapping(path = "regist")
	public String registCleaningRota(@RequestParam String executedDate,
			@RequestParam Integer itemId, @AuthenticationPrincipal LoginUserDetails userDetails) {
		
		// 登録項目設定
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setDeleteFlag(FlagEnum.FLAG_OFF.getFlag());
		// TODO 実行日付がMySQLで-1日登録されている。
//		cleaningRecord.setExecutedDate(LocalDate.parse(executedDate,
//				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cleaningRecord.setExecutedDate(LocalDate.parse(executedDate,
				DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1));
		Item item = new Item();
		item.setItemId(itemId);
		cleaningRecord.setItem(item);
		cleaningRecord.setUser(userDetails.getUser());	
		cleaningRecord.setCrDormitory(userDetails.getUser().getUserDormitory());
		cleaningRecordService.create(cleaningRecord);
		
		// ページ遷移
		return "redirect:/cleaning_rota?executedDate=" + executedDate;
		
	}
	
	/**
	 * 削除ボタンクリック
	 * @param recordId 掃除当番表記録ID
	 * @return 掃除当番表表示機能
	 */
	@PostMapping(path = "physical_delete")
	public String physicalDelete(@RequestParam String executedDate, @RequestParam Integer recordId) {
		
		// 掃除当番表レコード論理削除
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setRecordId(recordId);
		cleaningRecordService.physicalDelete(cleaningRecord);
		
		// ページ遷移
		return "redirect:/cleaning_rota?executedDate=" + executedDate;
		
	}
	
}
