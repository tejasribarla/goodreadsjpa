package com.example.goodreads.repository;

import com.example.goodreads.model.Book;


import java.util.ArrayList;

public interface BookRepository {
    ArrayList<Book> getBooks();

    Book getBookById(int bookId);
    
    Book addBook(Book book);
    
    Book updateBook(int bookId, Book book);
    
    void deleteBook(int bookId);

}
