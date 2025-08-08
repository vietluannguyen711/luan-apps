package com.example.quizitemservice.service;

import com.example.quizitemservice.entity.Item;
import com.example.quizitemservice.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item insertNewItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item updatedItem) {
        Item item = getItemById(updatedItem.getItemId());
        BeanUtils.copyProperties(updatedItem, item);

        return itemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
