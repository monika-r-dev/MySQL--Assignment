package com.example.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Search book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.status(201).body(bookService.addBook(book));
    }

    // Update book availability
    @PatchMapping("/{id}/availability")
    public Book updateAvailability(
            @PathVariable int id,
            @RequestParam boolean available) {
        return bookService.updateAvailability(id, available);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}