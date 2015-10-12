package com.lavr.web;

import com.lavr.data.BookDao;
import com.lavr.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lavr on 5/28/15.
 * crud
 */

@Controller
public class CrudController {
    private BookDao bookDao;

    @RequestMapping(value = "/crud/deleteBook", method = RequestMethod.POST)
    public @ResponseBody void deleteBook(@RequestParam("id") long id) {
        bookDao.delete(bookDao.findById(id));
    }

    @RequestMapping(value = "/crud/addBook", method = RequestMethod.POST)
    public @ResponseBody void addBook(@RequestParam("TITLE") String title,
                                      @RequestParam("AUTHOR") String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookDao.save(book);
    }

    @RequestMapping(value = "/crud/editBook", method = RequestMethod.POST)
    public @ResponseBody void editBook( @RequestParam("TITLE") String title,
                                        @RequestParam("AUTHOR") String author,
                                        @RequestParam("id") long id) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        book.setAuthor(author);
        bookDao.save(book);
    }


    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
