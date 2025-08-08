package com.example.quizitemservice.controller;

import com.example.quizitemservice.entity.Item;
import com.example.quizitemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PostMapping
    public ResponseEntity<?> insertNewItem(@RequestBody Item item) {
        try {
            Item newItem = itemService.insertNewItem(item);
            return ResponseEntity
                    .created(URI.create("/item/" + newItem.getItemId()))
                    .body(newItem);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateItem(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemService.updateItem(item));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItemById(id);
            return ResponseEntity.ok("item with " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

