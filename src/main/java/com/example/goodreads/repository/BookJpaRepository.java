package com.example.goodreads.repository;

import com.example.goodreads.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
 
@Repository
public interface BookJpaRepository extends JpaRepository <Book, Integer> {
    ArrayList<Book> getBooks();

    Book getBookById(int bookId);
    
    Book addBook(Book book);
    
    Book updateBook(int bookId, Book book);
    
    void deleteBook(int bookId);
}