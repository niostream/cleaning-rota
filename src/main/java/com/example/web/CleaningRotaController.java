package com.example.web;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.CleaningRecord;
import com.example.domain.Item;
import com.example.enums.FlagEnum;
import com.example.service.CleaningRecordService;
import com.example.service.ItemService;
import com.example.service.LoginUserDetails;
import com.example.util.CreateMapUtil;

@Controller
@RequestMapping("cleaning_rota")
public class CleaningRotaController {
	
	@Autowired
	CleaningRecordService cleaningRecordService;
	
	@Autowired
	ItemService itemService;
	
	/**
	 * 画面表示
	 * @param model
	 * @return
	 */
	@GetMapping
	public String cleaningRota(@RequestParam boolean showButton, Model model) {
		
		// ボタン表示
		model.addAttribute("showButton", showButton);
		
		// 掃除当番表項目リスト
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		
		// 掃除当番表記録リスト
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setExecutedDate(LocalDate.now());
		List<CleaningRecord> cleaningRecords = cleaningRecordService.findAllByExecutedDate(cleaningRecord);
		Map<LocalDate, Map<Item, CleaningRecord>> cleaningRecordMap = CreateMapUtil.createCleaningRecordMap(
				cleaningRecords, cleaningRecord, items);
		model.addAttribute("cleaningRecordMap", cleaningRecordMap);
				
		// ページ遷移
		return "cleaning_rota/cleaning_rota";
	}
	
	@GetMapping(path = "switch_button")
	public String switchButton(@RequestParam boolean showButton, Model model) {
		showButton = !showButton;
		return "redirect:/cleaning_rota?showButton=" + showButton;
	}
	
	/**
	 * 登録ボタンクリック
	 * @param executedDate
	 * @param itemId
	 * @param userDetails
	 * @return
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
		cleaningRecordService.create(cleaningRecord);
		
		// ページ遷移
		return "redirect:/cleaning_rota";
	}
	
	/**
	 * 削除ボタンクリック
	 * @param recordId
	 * @return
	 */
	@PostMapping(path = "physical_delete")
	public String physicalDelete(@RequestParam Integer recordId) {
		
		CleaningRecord cleaningRecord = new CleaningRecord();
		cleaningRecord.setRecordId(recordId);
		cleaningRecordService.physicalDelete(cleaningRecord);
		
		return "redirect:/cleaning_rota";
	}

}
