package com.lavr.data;

import com.lavr.domain.User;

import java.util.List;

/**
 * Created by lavr on 5/29/15.
 * user dao
 */

public interface UserDao {

    User findById(long id);
    User findByName(String fName, String lName);

    List findUserInfo();

}
