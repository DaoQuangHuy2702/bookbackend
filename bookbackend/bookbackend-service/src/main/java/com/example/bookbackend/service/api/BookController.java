package com.example.bookbackend.service.api;

import com.example.bookbackend.api.BooksApi;
import com.example.bookbackend.api.model.Book;
import com.example.bookbackend.api.model.BookRequest;
import com.example.bookbackend.api.model.Books;
import com.example.bookbackend.service.service.BookService;
import com.example.bookbackend.service.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book/backend/v1")
@CrossOrigin
public class BookController implements BooksApi {
    private final BookService service;
    private final BookValidator validator;

    @Autowired
    public BookController(BookService service, BookValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<Books> getAllBooks(String apikey) {
        Books response = service.getAllBook();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> getBook(String apikey, String bookId) {
        Book response = service.getBook(bookId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Books> getBookByCategory(String apikey, String bookCategory) {
        Books response = service.getBookByCategory(bookCategory);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> addBook(BookRequest body, String apikey) {
        validator.validateAddBook(body);
        Book response = service.addBook(body);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> updateBook(BookRequest body, String apikey, String bookId) {
        validator.validateUpdateBook(bookId, body);
        Book response = service.updateBook(bookId, body);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteBook(String apikey, String bookId) {
        service.deleteBook(bookId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
