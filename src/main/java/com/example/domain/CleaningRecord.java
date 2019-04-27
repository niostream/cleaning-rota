package com.example.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cleaning_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CleaningRecord {
	
	/** レコードID */
	@Id
	@GeneratedValue
	private Integer recordId;
	
	/** 削除フラグ */
	@Column(nullable = false)
	private Integer deleteFlag;
	
	/** 実行日 */
	@Column(nullable = false)
	private LocalDate executedDate;
	
	/** アイテムID(:Item) */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "itemId")
	private Item item;
	
	/** ユーザーID(:User) */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private User user;
	
	/** 寮ID(:Dormitory) */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "dormitoryId")
	private Dormitory crDormitory;

}
