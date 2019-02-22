package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	public Item save(Item item) {
		return itemRepository.save(item);
	}
	
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	
	public Optional<Item> findById(Integer id) {
		return itemRepository.findById(id);
	}
	
	public Item create(Item item) {
		return itemRepository.save(item);
	}
	
	public Item update(Item item) {
		return itemRepository.save(item);
	}
	
	public void deleteById(Integer id) {
		itemRepository.deleteById(id);
	}

}
