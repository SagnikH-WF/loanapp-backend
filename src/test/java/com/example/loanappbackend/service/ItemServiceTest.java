package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	

    @Test
    public void getAllItemsDetails() {
        // Mock data
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(new Item("I1","chair",'Y',"wooden","furniture",50));
        itemsList.add(new Item("I2","table",'Y',"wooden","furniture",100));

        // Mock the behavior of the itemRepository.findAll() method
        when(itemRepository.findAll()).thenReturn(itemsList);

        // Call the service layer method
        List<Item> result = itemService.getAllItemsDetails();

        // Assertions
        assert result != null;
        assert result.size() == 2;
        
    }
    
    @Test
    public void getItemById() {
        // Mock data
        String itemId = "I1";
        Item item = new Item("I1","chair",'Y',"wooden","furniture",50);
        
        // Mock the behavior of itemRepository.findById(id)
        when(itemRepository.findById(itemId)).thenReturn(java.util.Optional.of(item));
        
        // Call the service layer method
        Item result = itemService.getItemById(itemId);
        
        // Assertions
        assertNotNull(result);
        assertEquals(itemId, result.getItemId());
        assertEquals("chair", result.getItemDescription());
    }
    
    @Test
    public void updateItemByIdSuccess() {
        // Mock data
        String itemId = "I1";
        Item item = new Item("I1","chair",'Y',"wooden","furniture",50);
        Item updatedItem = new Item("I1","table",'Y',"wooden","furniture",150);

        // Mock the behavior of itemRepository.findById(id)
        when(itemRepository.findById(itemId)).thenReturn(java.util.Optional.of(item));
        // Mock the behavior of itemRepository.save(item)
        when(itemRepository.save(updatedItem)).thenReturn(updatedItem);

        // Call the service layer method
        ResponseEntity<Item> response = itemService.updateItemById(itemId, updatedItem);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedItem, response.getBody());
    }
    
    
    @Test
    public void deleteItemById() {
        // Mock data
        String itemId = "I1";

        // Mock the behavior of itemRepository.findById(id)
        when(itemRepository.findById(itemId)).thenReturn(java.util.Optional.of(new Item("I1","chair",'Y',"wooden","furniture",50)));

        // Call the service layer method
        ResponseEntity<?> response = itemService.deleteItemById(itemId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item deleted successfully", response.getBody());
        // Verify that itemRepository.deleteById(id) was called
        verify(itemRepository, times(1)).deleteById(itemId);
    }
	
}
