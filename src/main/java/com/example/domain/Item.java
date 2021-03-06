package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	/** アイテムID */
	@Id
	@GeneratedValue
	private Integer itemId;
	
	/** 削除フラグ */
	@Column(nullable = false)
	private Integer deleteFlag;
	
	/** アイテム名 */
	@Column(nullable = false)
	private String itemName;
	
	/** アイテムID(:CleaningRecord) */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
	private List<CleaningRecord> cleaningRecords;

}
