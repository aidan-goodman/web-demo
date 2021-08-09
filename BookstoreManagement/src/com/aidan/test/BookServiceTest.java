package com.aidan.test;

import com.aidan.pojo.Book;
import com.aidan.service.BookService;
import com.aidan.service.impl.BookServiceImpl;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void testAddBook() {
        bookService.addBook(new Book(null, "国哥在手，天下我有！", "1125",
                new BigDecimal(1000000), 100000000, 0, null));
    }

    @Test
    public void testDeleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void testUpdateBook() {
        bookService.updateBook(new Book(22, "社会我国哥，人狠话不多！", "1125",
                new BigDecimal(999999), 10, 111110, null));
    }

    @Test
    public void testQueryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void testQueryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}