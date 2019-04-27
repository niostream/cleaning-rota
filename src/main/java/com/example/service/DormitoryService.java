package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Dormitory;
import com.example.repository.DormitoryRepository;

@Service
@Transactional
public class DormitoryService {
	
	@Autowired
	DormitoryRepository dormitoryRepository;
	
	public Dormitory save(Dormitory dormitory) {
		return dormitoryRepository.save(dormitory);
	}
	
	public List<Dormitory> findAll() {
		return dormitoryRepository.findAll();
	}
	
	public Optional<Dormitory> findById(Integer id) {
		return dormitoryRepository.findById(id);
	}
	
	public Optional<Dormitory> findByDormitoryId(Integer dormitoryId) {
		return dormitoryRepository.findByDormitoryId(dormitoryId);
	}
	
	public Dormitory create(Dormitory dormitory) {
		return dormitoryRepository.save(dormitory);
	}
	
	public Dormitory update(Dormitory dormitory) {
		return dormitoryRepository.save(dormitory);
	}
	
	public void deleteById(Integer id) {
		dormitoryRepository.deleteById(id);
	}

}
