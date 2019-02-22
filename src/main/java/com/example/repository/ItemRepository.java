package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	@Query("SELECT i FROM Item i WHERE i.deleteFlag = 0 ORDER BY i.itemId")
	public List<Item> findAll();

}
