package com.example.loanappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.model.ItemIssue;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{
	
	@Query("SELECT DISTINCT i.itemMake FROM Item i WHERE i.itemCategory=?1")
	public List<String> getCategorySpecificItemMakes(String category);
	
	@Query("SELECT i FROM Item i WHERE i.itemCategory=?1 AND i.itemMake=?2")
	public List<Item> getItemsForSpecficCategoryAndMake(String category, String make);
	
//	@Query("SELECT i.itemId from Item i WHERE i.itemCategory=?1 AND i.itemDescription=?2 AND i.itemValuation=?3 AND i.itemMake=?4")
//	public String findItemDetails(String category, String description, int value, String make);
	
	 @Query("SELECT i FROM Item i " +
	           "WHERE i.itemCategory = :itemCategory " +
	           "AND i.itemDescription = :itemDescription " +
	           "AND i.itemValuation = :itemValuation " +
	           "AND i.itemMake = :itemMake")
	public Item findItemDetails(@Param("itemCategory") String itemCategory,
          @Param("itemDescription") String itemDescription,
          @Param("itemValuation") int itemValuation,
          @Param("itemMake") String itemMake);
	
}
