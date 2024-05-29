package dao;

import models.User;

public class UserDaoProxy extends UserDao {
    private UserDao userDao;

    // Constructor to initialize the real UserDao instance
    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean save(User user) {
        // Add proxy behavior here (e.g., logging, security checks)
        System.out.println("Proxy: Saving user with email " + user.getEmail());
        return userDao.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        // Add proxy behavior here (e.g., logging, security checks)
        System.out.println("Proxy: Finding user with email " + email);
        return userDao.findByEmailAndPassword(email, password);
    }
}
