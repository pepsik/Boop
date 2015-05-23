package org.pepsik.persistence;

import org.pepsik.model.User;

/**
 * Created by pepsik on 5/16/15.
 */
public interface UserAccountDao {

    void addUser(User user);

    User getUserById(long id);

    User getUserByUsername(String username);

    void updateUser(User user);

    void deleteUser(long id);

    void setUserAuthority(User user);
}
