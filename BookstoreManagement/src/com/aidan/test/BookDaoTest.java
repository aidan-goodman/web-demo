package com.aidan.test;

import com.aidan.dao.BookDao;
import com.aidan.dao.impl.BookDaoImpl;
import com.aidan.pojo.Book;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void testAddBook() {
        bookDao.addBook(new Book(null, "TestBookName", "Aidan",
                new BigDecimal(9999), 110000, 10, null));
    }

    @Test
    public void testDeleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void testUpdateBook() {
        bookDao.updateBook(new Book(21, "TestBookName", "Aidan",
                new BigDecimal(9999), 10000, 10, null));
    }

    @Test
    public void testQueryBookById() {
        System.out.println(bookDao.queryBookById(22));
    }

    @Test
    public void testQueryBooks() {
        for (Book bookListItem : bookDao.queryBooks()) {
            System.out.println(bookListItem);
        }
    }

    @Test
    public void testQueryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
}