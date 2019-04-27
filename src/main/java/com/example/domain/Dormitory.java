package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dormitories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {
	
	/** 寮ID */
	@Id
	private Integer dormitoryId;
	
	/** 削除フラグ */
	@Column(nullable = false)
	private Integer deleteFlag;
	
	/** 寮郵便番号 */
	@Column(nullable = false)
	private String dormitoryPostalCode;
	
	/** 寮住所(県) */
	@Column(nullable = false)
	private String dormitoryPrefecture;
	
	/** 寮住所(市) */
	@Column(nullable = false)
	private String dormitoryCity;
	
	/** 寮住所(町) */
	@Column(nullable = false)
	private String dormitoryTown;
	
	/** 寮住所(番地) */
	@Column(nullable = false)
	private String dormitoryBlock;
	
	/** 寮住所(その他詳細) */
	@Column(nullable = true)
	private String dormitoryDetail;
	
	/** 寮名 */
	@Column(nullable = false)
	private String dormitoryName;
	
	/** 寮ID(:CleaningRecord) */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "crDormitory")
	private List<CleaningRecord> cleaningRecords;
	
	/** 寮ID(:User) */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDormitory")
	private List<User> users;
	
}
