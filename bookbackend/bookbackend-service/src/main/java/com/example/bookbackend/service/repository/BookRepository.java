package com.example.bookbackend.service.repository;

import com.example.bookbackend.service.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
    List<BookEntity> findAllByCategory(String category);
}
