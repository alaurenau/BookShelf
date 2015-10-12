package com.lavr.data;

import com.lavr.config.RootConfig;
import com.lavr.domain.Book;
import com.lavr.web.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by lavr on 5/31/15.
 * tests
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @Transactional
    public void testAdd() {
        Book book = new Book();
        book.setBookId((long) 2);
        book.setTitle("Good Book");
        book.setAuthor("Man");

        bookDao.save(book);
        Book dbBook = bookDao.findById((long) 2);

        assertEquals(dbBook.getTitle(), "Good Book");
        assertEquals(dbBook.getAuthor(), "Man");
    }

    @Test
    @Transactional
    public void testEdit() {
        Book book = new Book();
        book.setBookId((long) 1);
        book.setTitle("Good Book");
        book.setAuthor("Man");

        bookDao.save(book);
        book = bookDao.findById((long) 1);

        book.setTitle("Bad book");
        bookDao.save(book);

        book = bookDao.findById((long) 1);
        assertEquals(book.getTitle(), "Bad book");
    }

    @Test
    @Transactional
    public void testDelete() {
        Book book = new Book();
        book.setBookId((long) 1);
        book.setTitle("Good Book");
        book.setAuthor("Man");

        bookDao.save(book);
        bookDao.delete(book);
        book = bookDao.findById((long) 1);

        assertEquals(book, null);
    }

    @Test
    @Transactional
    public void testFindBookInfo() {
        List books = bookDao.findBookInfo();
        Map map = (Map) books.get(0);
        Book book = bookDao.findById((long) 1);

        assertEquals(map.get("TITLE"), book.getTitle());
    }

    @Test
    @Transactional
    public void testFindRatingInfo() {
        List ratingInfo = bookDao.findRatingInfo();
        Map map = (Map) ratingInfo.get(0);
        Book book = bookDao.findById((long) 1);

        assertEquals(map.get("TITLE"), book.getTitle());
    }
}
