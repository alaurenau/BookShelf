package com.lavr.data;

import com.lavr.domain.Book;
import com.lavr.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by lavr on 5/29/15.
 * user impl
 */

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(long id) {
        return (User) sessionFactory.getCurrentSession().
                getNamedQuery("User.findById")
                .setParameter("userId", id).uniqueResult();
    }

    @Override
    public User findByName(String fName, String lName) {
        return (User) sessionFactory.getCurrentSession()
                .getNamedQuery("User.findByName")
                .setParameter("firstName", fName).setParameter("lastName", lName)
                .uniqueResult();
    }

    @Override
    public List findUserInfo() {
        String sql = "SELECT u.USER_ID, u.FIRST_NAME, u.LAST_NAME, b.TITLE, b.AUTHOR \n" +
                "FROM Book b\n" +
                "RIGHT JOIN User u ON u.USER_ID = b.USER_ID";
        return jdbcTemplate.queryForList(sql);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }
}
