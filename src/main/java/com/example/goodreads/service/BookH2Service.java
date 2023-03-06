package com.example.goodreads.service;
import com.example.goodreads.model.Book;

import java.util.*;

import com.example.goodreads.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.goodreads.model.BookRowMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service 
public class BookH2Service implements BookRepository {
    @Autowired 
    private JdbcTemplate db;
    @Override 
    public ArrayList <Book> getBooks() {
        List <Book> bookList=db.query("Select * from book", new BookRowMapper());
        ArrayList<Book> books=new ArrayList<>(bookList);
        return books;
    }
    @Override 
    public Book getBookById(int bookId) {
        try{
            Book book=db.queryForObject("Select * from book where id=?", new BookRowMapper(),bookId);
            return book;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override 
    public Book addBook(Book book) {
        db.update("insert into book (name,imageUrl) values(?,?)", book.getName(),book.getImageUrl());
        Book SavedBook=db.queryForObject("Select * From book Where name=? and imageUrl=?", new BookRowMapper(), book.getName(),book.getImageUrl());
        return SavedBook;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        if(book.getName()!=null) {
            db.update("update book setName=? where id=?",book.getName(),bookId);
        }
        if(book.getImageUrl()!=null) {
            db.update("update book set image url=? where id=?",book.getImageUrl(),bookId);
        }
        return getBookById(bookId);
    }
    @Override
    public void deleteBook(int bookId) {
        db.update("delete from book where id=?",bookId);
    }
}