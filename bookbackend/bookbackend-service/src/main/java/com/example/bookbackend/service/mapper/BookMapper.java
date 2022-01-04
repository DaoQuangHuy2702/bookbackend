package com.example.bookbackend.service.mapper;

import com.example.bookbackend.api.model.Book;
import com.example.bookbackend.api.model.BookRequest;
import com.example.bookbackend.api.model.Books;
import com.example.bookbackend.service.entity.BookEntity;
import com.example.bookbackend.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookMapper {
    private final BookRepository repo;

    @Autowired
    public BookMapper(BookRepository repo) {
        this.repo = repo;
    }

    public BookEntity mapBookEntityFromBookRequest(BookRequest from) {
        BookEntity to = new BookEntity();

        to.setId(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setImage(from.getImage());
        to.setAuthor(from.getAuthor());
        to.setCategory(from.getCategory());
        to.setContent(from.getContent());

        return to;
    }

    public BookEntity mapBookEntityFromBookRequest(String id, BookRequest from) {
        BookEntity to = repo.getById(id);

        to.setName(from.getName());
        to.setImage(from.getImage());
        to.setAuthor(from.getAuthor());
        to.setCategory(from.getCategory());
        to.setContent(from.getContent());

        return to;
    }

    public Book mapBookFromBookEntity(BookEntity from) {
        Book to = new Book();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setImage(from.getImage());
        to.setAuthor(from.getAuthor());
        to.setCategory(from.getCategory());
        to.setContent(from.getContent());

        return to;
    }

    public Books mapBooksFromBookEntities(List<BookEntity> from) {
        Books to = new Books();
        from.stream().forEach(book -> {
            to.add(mapBookFromBookEntity(book));
        });

        return to;
    }
}
