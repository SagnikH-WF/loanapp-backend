package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.loanappbackend.dto.ItemDto;
import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.service.ItemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Import other necessary classes...

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelMapper modelMapper;

   

    @Test
    public void getCategorySpecificItems() throws Exception {
        // Define a sample category
        String category = "Electronics";

        // Define the expected list of item makes for the category
        List<String> expectedMakes = Arrays.asList("Make1", "Make2");

        // Mock the behavior of itemService.getCategorySpecificItemMakes
        when(itemService.getCategorySpecificItemMakes(category)).thenReturn(expectedMakes);

        // Perform a GET request to /item with the 'category' request parameter
        mockMvc.perform(get("/item")
                .param("category", category))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Make1\",\"Make2\"]"));
    }

    @Test
    public void getItemsForSpecificCategoryAndMake() throws Exception {
        // Create example parameters for category and make
        String category = "Electronics";
        String make = "BrandX";

        // Mock the behavior of the itemService to return an empty list when called with the specified parameters
        when(itemService.getItemsForSpecficCategoryAndMake(eq(category), eq(make)))
                .thenReturn(Collections.emptyList());

        // Perform the GET request to retrieve items for a specific category and make
        mockMvc.perform(get("/items")
                .param("category", category)
                .param("make", make));
    }
    
    @Test
    public void getAllItemsDetails() throws Exception {
        // Create a list of example Item objects for the response
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemId("I1");
        
        items.add(item1);

        // Mock the behavior of the itemService to return the list of Item objects
        when(itemService.getAllItemsDetails()).thenReturn(items);

        // Mock the behavior of the modelMapper for mapping each Item to ItemDto
        when(modelMapper.map(any(Item.class), eq(ItemDto.class)))
                .thenAnswer(invocation -> {
                    Item source = invocation.getArgument(0);
                    ItemDto target = new ItemDto();
                    target.setItemId(source.getItemId());
                    
                    return target;
                });

        // Perform the GET request to retrieve all items
        mockMvc.perform(get("/item/allItems"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    

    @Test
    public void getItemById() throws Exception {
        // Create an example item ID
        String itemId = "1";

        // Create an example ItemDto for the response
        Item item = new Item();
        item.setItemId(itemId);
    

        // Mock the behavior of the itemService to return the example ItemDto
        when(itemService.getItemById(eq(itemId))).thenReturn(item);

        // Mock the behavior of the modelMapper for mapping the ItemDto
        when(modelMapper.map(eq(item), eq(ItemDto.class))).thenReturn(new ItemDto());

        // Perform the GET request to retrieve an item by ID
        mockMvc.perform(get("/item/{id}", itemId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void deleteItem() throws Exception {
        // Create an example item ID for testing
        String itemId = "1";

        // Mock the behavior of itemService to return a successful response
        when(itemService.deleteItemById(eq(itemId))).thenReturn(ResponseEntity.noContent().build());

        // Perform the DELETE request to delete an item
        mockMvc.perform(delete("/item/{id}", itemId))
                .andExpect(status().isNoContent()); // Expect a 204 No Content response
    }
}

