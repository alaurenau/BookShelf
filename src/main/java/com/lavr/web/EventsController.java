package com.lavr.web;

import com.lavr.data.BookDao;
import com.lavr.data.UserDao;
import com.lavr.domain.Book;
import com.lavr.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by lavr on 5/28/15.
 *
 */

@Controller
public class EventsController {
    private UserDao userDao;
    private BookDao bookDao;

    @RequestMapping(value = "/events/takeBook", method = RequestMethod.POST)
    public @ResponseBody
    Book takeBook(@RequestParam("book_id") long book_id,
                  @RequestParam("fName") String fName,
                  @RequestParam("lName") String lName) {

        User user = userDao.findByName(fName, lName);

        if (user == null) {
            throw new NotFoundException("User not found");
        }
        Book book = bookDao.findById(book_id);
        book.setUser(user);
        book.getRating().incRating();
        book.getBookUsage().setUsedSince(new Date());
        book.getBookUsage().setUsedUntil(null);
        bookDao.save(book);

        return book;
    }

    @RequestMapping(value = "/events/returnBook", method = RequestMethod.POST)
    public @ResponseBody
    Book returnBook(@RequestParam("book_id") long book_id) {

        Book book = bookDao.findById(book_id);
        book.setUser(null);
        book.getBookUsage().setUsedSince(null);
        book.getBookUsage().setUsedUntil(new Date());
        bookDao.save(book);

        return book;
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
