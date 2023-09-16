package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	@Mock
	ItemRepository itemRepository ;
	
	@InjectMocks
	ItemServiceImpl itemService;
	
	private Item item;
	
	
	@BeforeEach
	void setUp() {
		this.item=new Item("I1","chair",'Y',"wooden","furniture",50);
	}
	
	@Test
	void saveItem() {
		
		when(itemRepository.save(item)).thenReturn(item);
		
		Item savedItem = itemService.saveItem(item);
		
		assertThat(savedItem).isNotNull();
		verify(itemRepository, times(1)).save(item);
	}
	
	@Test
	void getCategorySpecificItemMakes() {
		
		 String category = "furniture";
		 
		 List<String> expectedMakes = Arrays.asList("chair", "table");
		 
		 when(itemRepository.getCategorySpecificItemMakes(category)).thenReturn(expectedMakes);
		 
		 List<String> makes = itemService.getCategorySpecificItemMakes(category);

		 assertThat(makes).isNotNull();
		 assertEquals(expectedMakes,makes);
		 verify(itemRepository, times(1)).getCategorySpecificItemMakes(category);

	}
	
	@Test
	void getItemsForSpecficCategoryAndMake() {
		 String category = "furniture";
	        String make = "wooden";
	        
	        List<Item> expectedItems = Arrays.asList(
	        		new Item("I1","chair",'Y',"wooden","furniture",50),
	        		new Item("I2","table",'Y',"wooden","furniture",100)
	            );
	        when(itemRepository.getItemsForSpecficCategoryAndMake(category, make)).thenReturn(expectedItems);
	        List<Item> items = itemService.getItemsForSpecficCategoryAndMake(category, make);

	        assertThat(items).isNotNull();
	        assertEquals(expectedItems,items);
	        verify(itemRepository, times(1)).getItemsForSpecficCategoryAndMake(category, make);

	}
	
}
