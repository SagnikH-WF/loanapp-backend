package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.service.ItemService;
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

    @Test
    public void testSaveItem() throws Exception {
        // Create a sample item to save
        Item itemToSave = new Item();
        itemToSave.setItemDescription("Sample Item");

        // Define the expected saved item
        Item savedItem = new Item();
        savedItem.setItemValuation(50);
        savedItem.setItemDescription("Sample Item");

        // Mock the behavior of itemService.saveItem
        when(itemService.saveItem(any(Item.class))).thenReturn(savedItem);

        // Perform a POST request to /item with JSON content
        mockMvc.perform(post("/item")
                .contentType("application/json")
                .content("{\"itemDescription\":\"Sample Item\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"itemValuation\":50,\"itemDescription\":\"Sample Item\"}"));
    }

    @Test
    public void testGetCategorySpecificItems() throws Exception {
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
    public void testGetItemsForSpecficCategoryAndMake() throws Exception {
        // Define sample category and make parameters
        String category = "furniture";
        String make = "wooden";

        // Define a list of sample items for the expected response
        List<Item> expectedItems = Arrays.asList(
            new Item("L1","table",'Y',"wooden","furniture",50),
            new Item("L2","chair",'Y',"wooden","furniture",100)
        );

        // Mock the behavior of itemService.getItemsForSpecficCategoryAndMake
        when(itemService.getItemsForSpecficCategoryAndMake(category, make)).thenReturn(expectedItems);

        // Perform a GET request to /item/allItems with 'category' and 'make' request parameters
        mockMvc.perform(get("/item/allItems")
                .param("category", category)
                .param("make", make))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedItems)));

        // You can add more assertions as needed based on your item object's properties.
    }
}

