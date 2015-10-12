package com.lavr.data;

import com.lavr.config.RootConfig;
import com.lavr.domain.User;
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
 * test
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void testFindByName() {
        User user = userDao.findByName("Sarah", "Cunningham");
        assertEquals(user.getFirstName(),"Sarah");
        assertEquals((long) user.getUserId(), 1);

        user = userDao.findByName("Carl", "Perry");
        assertEquals(user.getFirstName(),"Carl");
        assertEquals((long) user.getUserId(), 3);
    }

    @Test
    @Transactional
    public void testFindUserInfo() {
        List userInfo = userDao.findUserInfo();
        Map map = (Map) userInfo.get(0);
        User user = userDao.findById((long) 1);

        assertEquals(map.get("FIRST_NAME"), user.getFirstName());
    }
}
