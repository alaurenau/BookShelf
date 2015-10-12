package com.lavr.web;

import com.lavr.data.BookDao;
import com.lavr.data.UserDao;
import com.lavr.domain.Book;
import com.lavr.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by lavr on 5/31/15.
 * tests
 */
public class EventsControllerTest {
    private static MockMvc mockEventsController;
    private static BookDao bookDao;
    private static UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        bookDao = mock(BookDao.class);
        userDao = mock(UserDao.class);
        EventsController eventsController = new EventsController();

        eventsController.setBookDao(bookDao);
        eventsController.setUserDao(userDao);
        mockEventsController = standaloneSetup(eventsController).build();
    }

    @Test
    public void testTakeBook() throws Exception {
        User user = new User();
        user.setUserId((long) 2);
        user.setFirstName("John");
        user.setLastName("Doe");

        Book book = new Book();
        book.setBookId((long) 1);
        book.setTitle("Good Book");
        book.setAuthor("Man");

        when(userDao.findByName("John", "Doe")).thenReturn(user);
        when(bookDao.findById((long) 1)).thenReturn(book);
        when(bookDao.save(book)).thenReturn(book);

        mockEventsController.perform(post("/events/takeBook")
                .param("book_id", "1")
                .param("fName", "John")
                .param("lName", "Doe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.user.userId", is(2)))
                .andExpect(jsonPath("$.bookUsage.usedSince", greaterThan((long)0)))
                .andExpect(jsonPath("$.rating.rating", is(1)));
    }

    @Test
    public void testReturnBook() throws Exception {
        User user = new User();
        user.setUserId((long) 2);
        user.setFirstName("John");
        user.setLastName("Doe");

        Book book = new Book();
        book.setBookId((long) 1);
        book.setTitle("Good Book");
        book.setAuthor("Man");
        book.setUser(user);

        when(userDao.findByName("John", "Doe")).thenReturn(user);
        when(bookDao.findById((long) 1)).thenReturn(book);
        when(bookDao.save(book)).thenReturn(book);

        mockEventsController.perform(post("/events/returnBook")
                .param("book_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.user", is(nullValue())))
                .andExpect(jsonPath("$.bookUsage.usedUntil", greaterThan((long) 0)))
                .andExpect(jsonPath("$.bookUsage.usedSince", is(nullValue())));

    }
}