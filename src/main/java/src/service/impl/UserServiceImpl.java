package src.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import src.dao.UserDAO;
import src.model.User;
import src.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserById(final long userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getName().contains(name))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(final User user) {
        return userDAO.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        return userDAO.deleteUser(userId);
    }
}
