package protoff.dao;

import protoff.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void deleteUser(int id);
    void createUser(User user);
    User getUserById(int id);
    void updateUser(User user);
}
