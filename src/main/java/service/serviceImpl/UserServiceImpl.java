package service.serviceImpl;

import config.Config;
import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import model.User;
import service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public void createUserTable() {
      userDao.createUserTable();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        userDao.updateUserInfo(id,user);
    }

    @Override
    public void cleanUserTable() {

        userDao.cleanUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();
    }

    @Override
    public void deleteById(Long id) {
    userDao.deleteById(id);
    }
}
