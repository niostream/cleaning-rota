package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	/** ユーザーID */
	@Id
	private String userId;
	
	/** 削除フラグ */
	@Column(nullable = false)
	private Integer deleteFlag;
	
	/** エンコード済みパスワード */
	@Column(nullable = false)
	private String encodedPassword;
	
	/** 名字 */
	@Column(nullable = false)
	private String firstName;
	
	/** 名前 */
	@Column(nullable = false)
	private String lastName;
	
	/** 管理フラグ */
	@Column(nullable = false)
	private Integer adminFlag;
	
	/** 寮ID(:Dormitory) */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "dormitoryId")
	private Dormitory userDormitory;
	
	/** ユーザーID(:CleaningRecord) */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private List<CleaningRecord> cleaningRecords;
	
}
