package com.lavr.web;

import com.lavr.data.BookDao;
import com.lavr.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lavr on 5/28/15.
 * ajax
 */


@Controller
public class AjaxController {
    private BookDao bookDao;
    private UserDao userDao;

    @RequestMapping(value="/", method=GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/getBooks", method = RequestMethod.GET)
    public @ResponseBody List getBooks() {
        return bookDao.findBookInfo();
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public @ResponseBody List getUsers() {
        return userDao.findUserInfo();
    }

    @RequestMapping(value = "/getRatings", method = RequestMethod.GET)
    public @ResponseBody List getRatings() {
        return bookDao.findRatingInfo();
    }

    @RequestMapping(value = "/getHtml", method = RequestMethod.GET)
    public String getHtml(@RequestParam("page") String data) {
        return data;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
