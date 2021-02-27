package protoff.services;

import protoff.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    void deleteUser(int id);
    User getUserById(int id);
    void updateUser(User user);
}
