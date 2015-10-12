package com.lavr.data;

import com.lavr.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by lavr on 5/27/15.
 *
 */


@Transactional
@Repository
public class BookDaoImpl implements BookDao {
    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly=true)
    public List findAllBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book a").list();
    }

    @Override
    public List findBookInfo() {
        String sql = "SELECT b.BOOK_ID, b.TITLE, b.AUTHOR , u.FIRST_NAME, u.LAST_NAME, bu.USED_SINCE\n" +
                "        FROM Book b\n" +
                "        LEFT JOIN User u ON u.USER_ID = b.USER_ID\n" +
                "        JOIN Book_usage bu ON bu.BOOK_ID = b.BOOK_ID";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List findRatingInfo() {
        String sql = "SELECT u.BOOK_ID, b.TITLE, b.AUTHOR ,u.RATING\n" +
                "FROM Book b\n" +
                "LEFT JOIN Rating u ON u.BOOK_ID = b.BOOK_ID";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    @Transactional(readOnly=true)
    public Book findByTitle(String title) {
        return (Book) sessionFactory.getCurrentSession().
                getNamedQuery("Book.findByTitle")
                .setParameter("title", title).uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public Book findByAuthor(String author) {
        return (Book) sessionFactory.getCurrentSession().
                getNamedQuery("Book.findByAuthor")
                .setParameter("author", author).uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public Book findById(Long id) {
        return (Book) sessionFactory.getCurrentSession().
                getNamedQuery("Book.findById")
                .setParameter("bookId", id).uniqueResult();
    }

    @Override
    public Book save(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
        return book;
    }

    @Override
    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        DataSource dataSource1 = dataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }
}
