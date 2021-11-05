package study.dao.impl;

import study.dao.UserDAO;
import study.model.User;
import study.storage.Storage;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class UserDAOImpl implements UserDAO {

    private Storage storage;
    private Random random;

    public void setStorage(final Storage storage) {
        this.storage = storage;
    }

    public void setRandom(final Random random) {
        this.random = random;
    }

    @Override
    public User getUserById(final long userId) {
        return storage.getUsers().get(String.valueOf(userId));
    }

    @Override
    public Collection<User> getAllUsers() {
        return storage.getUsers().values();
    }

    @Override
    public User createUser(final User user) {

        long userId = random.nextLong();

        while (getUserById(userId) != null) {
            userId = random.nextLong();
        }

        user.setId(userId);

        storage.getUsers().put(String.valueOf(user.getId()), user);

        return user;
    }

    @Override
    public User updateUser(final User user) {
        return storage.getUsers().computeIfPresent(String.valueOf(user.getId()), (k, v) -> user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        return Objects.nonNull(storage.getUsers().remove(String.valueOf(userId)));
    }
}
