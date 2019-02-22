package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CleaningRecord;

public interface CleaningRecordRepository extends JpaRepository<CleaningRecord, Integer> {
	
	@Query("SELECT "
			+ "cr "
			+ "FROM CleaningRecord cr "
			+ "WHERE YEAR(cr.executedDate) = YEAR(:executedDate) "
			+ "AND MONTH(cr.executedDate) = MONTH(:executedDate) "
			+ "AND cr.deleteFlag = 0 "
			+ "AND cr.item.deleteFlag = 0 "
			+ "ORDER BY cr.executedDate, cr.item.itemId ")
	List<CleaningRecord> findAllByExecutedDate(@Param("executedDate") LocalDate executedDate);
	
	@Transactional
	@Modifying
	@Query("UPDATE "
			+ "CleaningRecord cr "
			+ "SET cr.deleteFlag = 1 "
			+ "WHERE cr.recordId = :recordId ")
	void physicalDelete(@Param("recordId") Integer recordId);

}
