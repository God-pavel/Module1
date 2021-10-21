package src.dao;

import java.util.Collection;

import src.model.User;

public interface UserDAO {
    
    User getUserById(long userId);
    Collection<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long userId);
}
