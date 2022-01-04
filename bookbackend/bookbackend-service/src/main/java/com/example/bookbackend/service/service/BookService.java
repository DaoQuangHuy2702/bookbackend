package com.example.bookbackend.service.service;

import com.example.bookbackend.api.model.Book;
import com.example.bookbackend.api.model.BookRequest;
import com.example.bookbackend.api.model.Books;
import com.example.bookbackend.service.entity.BookEntity;
import com.example.bookbackend.service.mapper.BookMapper;
import com.example.bookbackend.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repo;
    private final BookMapper mapper;

    @Autowired
    public BookService(BookRepository repo, BookMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Books getAllBook() {
        Books list = new Books();
        list = mapper.mapBooksFromBookEntities(repo.findAll());

        return list;
    }

    public Book getBook(String id) {
        BookEntity bookEntity = repo.getById(id);
        Book book = mapper.mapBookFromBookEntity(bookEntity);

        return book;
    }

    public Book addBook(BookRequest bookReq) {
        BookEntity bookEntity = mapper.mapBookEntityFromBookRequest(bookReq);
        BookEntity saved = repo.save(bookEntity);

        return mapper.mapBookFromBookEntity(saved);
    }

    public Book updateBook(String id, BookRequest bookReq) {
        BookEntity bookEntity = mapper.mapBookEntityFromBookRequest(id, bookReq);
        BookEntity saved = repo.save(bookEntity);

        return mapper.mapBookFromBookEntity(saved);
    }

    public Books getBookByCategory(String category) {
        Books list = new Books();
        list = mapper.mapBooksFromBookEntities(repo.findAllByCategory(category));

        return list;
    }

    public void deleteBook(String id) {
        BookEntity bookEntity = repo.getById(id);
        repo.delete(bookEntity);
    }
}
