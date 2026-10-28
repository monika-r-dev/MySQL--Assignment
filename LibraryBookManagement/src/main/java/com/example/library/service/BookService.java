package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    private int nextId = 1;

    // Get all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Search book by ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getBookId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with ID: " + id));
    }

    // Add a new book
    public Book addBook(Book book) {
        book.setBookId(nextId++);
        book.setAvailable(true);
        books.add(book);
        return book;
    }

    // Update book availability
    public Book updateAvailability(int id, boolean available) {
        Book book = getBookById(id);
        book.setAvailable(available);
        return book;
    }

    // Delete a book
    public void deleteBook(int id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}