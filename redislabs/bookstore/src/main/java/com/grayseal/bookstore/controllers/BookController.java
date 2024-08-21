package com.grayseal.bookstore.controllers;

import com.grayseal.bookstore.models.Book;
import com.grayseal.bookstore.models.Category;
import com.grayseal.bookstore.repositories.BookRepository;
import com.grayseal.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves a paginated list of books.
     * Caches the result to avoid hitting the database on repeated requests with the same parameters.
     *
     * @param page the page number to retrieve (default is 0).
     * @param size the number of items per page (default is 10).
     * @return a ResponseEntity containing the paginated books, page number, total pages, and total elements.
     */
    @Cacheable(value = "booksCache", key = "#page + '-' + #size")
    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getBooks(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Book> pagedResult = bookRepository.findAll(paging);
        List<Book> books = pagedResult.hasContent() ? pagedResult.getContent() : Collections.emptyList();

        Map<String, Object> response = new HashMap<>();
        response.put("books", books);
        response.put("page", pagedResult.getNumber());
        response.put("pages", pagedResult.getTotalPages());
        response.put("total", pagedResult.getTotalElements());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Retrieves all categories.
     * Caches the result to avoid hitting the database on repeated requests.
     *
     * @return an Iterable of categories.
     */
    @Cacheable(value = "categoriesCache")
    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public Book get(@PathVariable("isbn") String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
