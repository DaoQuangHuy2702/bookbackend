package com.example.bookbackend.service.validator;

import com.example.bookbackend.api.model.BookRequest;
import com.example.bookbackend.service.exception.BadRequestException;
import com.example.bookbackend.service.exception.EntityNotFoundException;
import com.example.bookbackend.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    private static final String BOOK_DOES_NOT_EXIST = "Book does not exist";
    private static final String NAME_REQUEST = "Book name is required";
    private static final String CATEGORY_REQUEST = "Book category is required";
    private final BookRepository repo;

    @Autowired
    public BookValidator(BookRepository repo) {
        this.repo = repo;
    }

    private void validateBookExist(String id) {
        if (repo.existsById(id)) {
            return;
        }
        throw new EntityNotFoundException(BOOK_DOES_NOT_EXIST);
    }

    private void checkRequiredField(String value, String msgError) {
        if(value == null || value.trim().isEmpty()) {
            throw new BadRequestException(msgError);
        }
    }

    public void validateAddBook(BookRequest request) {
        checkRequiredField(request.getName(), NAME_REQUEST);
        checkRequiredField(request.getCategory(), CATEGORY_REQUEST);
    }

    public void validateUpdateBook(String id, BookRequest request) {
        validateBookExist(id);
        checkRequiredField(request.getName(), NAME_REQUEST);
        checkRequiredField(request.getCategory(), CATEGORY_REQUEST);
    }
}
