package com.example.loanappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{
	
	@Query("SELECT DISTINCT i.itemMake FROM Item i WHERE i.itemCategory=?1")
	public List<String> getCategorySpecificItemMakes(String category);
	
	@Query("SELECT i FROM Item i WHERE i.itemCategory=?1 AND i.itemMake=?2")
	public List<Item> getItemsForSpecficCategoryAndMake(String category, String make);
}
