package dao.daoImpl;

import config.Config;
import dao.UserDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection;
    @Override
    public void createUserTable() {
        String sql = "create table users (" +
                "id serial primary key," +
                "name varchar," +
                "age int );";
        try(Connection connection1 = Config.connectionToDataBase();
        Statement statement = connection1.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Users table id created...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into users (name,age)" +
                "values (?,?);";
        try(Connection connection1 = Config.connectionToDataBase();
        PreparedStatement preparedStatement = connection1.prepareStatement(sql)){
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        String sql = "select * from users where id = ?";
        try (Connection connection = Config.connectionToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User>users = new ArrayList<>();
        String sql = "select * from users;";
        try(Connection connection1 = Config.connectionToDataBase();
        Statement statement = connection1.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                ));

            }

        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        String sql = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        try (Connection connection = Config.connectionToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated...");
        } catch (SQLException e) {
            System.err.println("Error updating user information: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void cleanUserTable() {
        String sql = "truncate table users;";
        try(Connection connection1  = Config.connectionToDataBase();
        Statement statement = connection1.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Successfully cleaned...");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropUserTable() {
        String sql = "drop table if exists users;";
        try(Connection connection = Config.connectionToDataBase();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("users table is deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from users where id = ?;";
try(Connection connection1 = Config.connectionToDataBase();
    PreparedStatement preparedStatement = connection1.prepareStatement(sql)){
    preparedStatement.setLong(1,id);
    int rowsDeleteed = preparedStatement.executeUpdate();
    if (rowsDeleteed>0){
        System.out.println("Successfully deleted...");
    }else {
        System.out.println("Not found user...");
    }
    }catch (SQLException e){
    System.out.println(e.getMessage());
    }

    }

    @Override
    public List<User> getAllSortedUsers(String ascDesc) {
        List<User> sortedUsers = new ArrayList<>();
        String sql = "select * from users order by full_name desc;";
        try(Connection connection1 = Config.connectionToDataBase();
        Statement statement = connection1.createStatement()){
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        sortedUsers.add(new User(
                                resultSet.getLong("id"),
                                resultSet.getString("full_name"),
                                resultSet.getInt("age")
                        ));
                    }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return sortedUsers;
    }
}
