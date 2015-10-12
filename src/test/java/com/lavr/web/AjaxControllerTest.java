package com.lavr.web;

import com.lavr.data.BookDao;
import com.lavr.data.UserDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by lavr on 5/31/15.
 * tests
 */


public class AjaxControllerTest {
    private static MockMvc mockAjaxController;
    private static BookDao bookDao;
    private static UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        bookDao = mock(BookDao.class);
        userDao = mock(UserDao.class);
        AjaxController ajaxController = new AjaxController();

        ajaxController.setBookDao(bookDao);
        ajaxController.setUserDao(userDao);
        mockAjaxController = standaloneSetup(ajaxController).build();
    }

    @Test
    public void testMain() throws Exception {
        mockAjaxController.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
    }

    @Test
    public void testGetBooks() throws Exception {
        Map element1 = new HashMap();
        element1.put("good", "foo");

        Map element2 = new HashMap();
        element2.put("books", "bar");

        when(bookDao.findBookInfo()).thenReturn(Arrays.asList(element1, element2));

        mockAjaxController.perform(get("/getBooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].good", is("foo")))
                .andExpect(jsonPath("$[1].books", is("bar")));
    }

    @Test
    public void testGetUsers() throws Exception {
        Map element1 = new HashMap();
        element1.put("good", "foo");

        Map element2 = new HashMap();
        element2.put("users", "bar");

        when(userDao.findUserInfo()).thenReturn(Arrays.asList(element1, element2));

        mockAjaxController.perform(get("/getUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].good", is("foo")))
                .andExpect(jsonPath("$[1].users", is("bar")));
    }

    @Test
    public void testGetRatings() throws Exception {
        Map element1 = new HashMap();
        element1.put("good", "foo");

        Map element2 = new HashMap();
        element2.put("ratings", "bar");

        when(bookDao.findRatingInfo()).thenReturn(Arrays.asList(element1, element2));

        mockAjaxController.perform(get("/getRatings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].good", is("foo")))
                .andExpect(jsonPath("$[1].ratings", is("bar")));
    }

    @Test
    public void testGetHtml() throws Exception {
        mockAjaxController.perform(get("/getHtml").param("page", "pageToRender"))
                .andExpect(status().isOk())
                .andExpect(view().name("pageToRender"));
    }
}