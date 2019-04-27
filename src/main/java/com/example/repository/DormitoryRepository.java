package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Dormitory;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
	
	@Query("SELECT "
			+ "d "
			+ "FROM Dormitory d "
			+ "WHERE d.dormitoryId = :dormitoryId "
			+ "AND d.deleteFlag = 0")
	Optional<Dormitory> findByDormitoryId(@Param("dormitoryId") Integer dormitoryId);
	
}
