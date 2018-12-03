package com.chaika.dao;

import com.chaika.model.User;
import com.chaika.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echaika on 30.11.2018
 */
public class UserDAO {

    private final List<User> store = new ArrayList<>();

    public User getById(int id) {
        User result = new User();
        result.setId(-1);

        for (User user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    public User getUserByLoginPassword(final String login, String password) {
        User result = new User();
        result.setId(-1);

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    public boolean add(final User user) {
        for (User usr : store) {
            if (usr.getLogin().equals(user.getLogin()) && usr.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        return store.add(user);
    }

    public Role getRoleByLoginPassword(final String login, final String password) {
        Role result = Role.UNKNOWN;

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }
        return result;
    }

    public boolean userIsExist(final String login, final String password) {
        boolean result = false;

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
