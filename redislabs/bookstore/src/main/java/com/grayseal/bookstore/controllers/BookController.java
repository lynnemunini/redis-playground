package com.grayseal.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bookstore")
public class BookController {
    private static final String STRING_KEY_PREFIX = "bookstore:book:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Map.Entry<String, String> createBook(@RequestBody Map.Entry<String, String> book) {
        String key = STRING_KEY_PREFIX + book.getKey();
        String value = book.getValue();
        redisTemplate.opsForValue().set(key, value);
        return book;
    }

    @GetMapping("/book/{key}")
    public Map.Entry<String, String> getBook(@PathVariable("key") String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return new AbstractMap.SimpleEntry<String, String>(key, value);
    }
}
