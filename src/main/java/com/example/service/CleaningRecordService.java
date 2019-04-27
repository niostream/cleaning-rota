package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CleaningRecord;
import com.example.repository.CleaningRecordRepository;

@Service
@Transactional
public class CleaningRecordService {
	
	@Autowired
	CleaningRecordRepository cleaningRecordRepository;
	
	public CleaningRecord save(CleaningRecord cleaningRecord) {
		return cleaningRecordRepository.save(cleaningRecord);
	}
	
	public List<CleaningRecord> findAll() {
		return cleaningRecordRepository.findAll();
	}
	
	public List<CleaningRecord> findAllByExecutedDate(CleaningRecord cleaningRecord) {
		return cleaningRecordRepository.findAllByExecutedDate(cleaningRecord.getExecutedDate(),
				cleaningRecord.getCrDormitory().getDormitoryId());
	}
	
	public Optional<CleaningRecord> findById(Integer id) {
		return cleaningRecordRepository.findById(id);
	}
	
	public CleaningRecord create(CleaningRecord cleaningRecord) {
		return cleaningRecordRepository.save(cleaningRecord);
	}
	
	public CleaningRecord update(CleaningRecord cleaningRecord) {
		return cleaningRecordRepository.save(cleaningRecord);
	}
	
	public void delete(Integer id) {
		cleaningRecordRepository.deleteById(id);
	}
	
	public void physicalDelete(CleaningRecord cleaningRecord) {
		cleaningRecordRepository.physicalDelete(cleaningRecord.getRecordId());
	}
	
}
