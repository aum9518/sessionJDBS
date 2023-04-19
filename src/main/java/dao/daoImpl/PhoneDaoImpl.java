package dao.daoImpl;

import config.Config;
import dao.PhoneDao;
import model.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {
    @Override
    public void createPhoneTable() {
        String sql = "create table phones(" +
                "id serial primary key," +
                "madel varchar," +
                "brand varchar," +
                "price int," +
                "user_id int references users(id));";
        try(Connection connection = Config.connectionToDataBase();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Phone table create....");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void savePhoneTable(Phone phone) {
        String sql = "insert into phones (" +
                "madel,brand , price,user_id)" +
                "values (?,?,?,?);";
        try(Connection connection = Config.connectionToDataBase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setString(1,phone.getModel());
           preparedStatement.setString (2,phone.getBrand());
           preparedStatement.setInt(3,phone.getPrice());
           preparedStatement.setLong(4,phone.getUserId());
           preparedStatement.executeUpdate();
            System.out.println("Successfully saved...");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updatePhone(Long id, Phone phone) {

        String sql = "update phones set madel = ?,brand =?,price = ? where id = ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,phone.getModel());
            preparedStatement.setString(2,phone.getBrand());
            preparedStatement.setInt(3,phone.getPrice());
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated...");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Phone> getAllUsersPhone(Long userId) {
        List<Phone> phones = new ArrayList<>();
        String sql = "select * from phones where user_id= ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                phones.add(new Phone(
                        resultSet.getLong("id"),
                        resultSet.getString("madel"),
                        resultSet.getString("brand"),
                        resultSet.getInt("price"),
                        resultSet.getLong("user_id")
                ));

            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return phones;
    }

    @Override
    public List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc) {
        List<Phone> phones1 = new ArrayList<>();
        String sql = "select * from phones WHERE user_id = ? order by madel   "+ascOrDesc;
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
           // statement.setString(2,ascOrDesc);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                phones1.add(new Phone(
                        resultSet.getLong("id"),
                        resultSet.getString("madel"),
                        resultSet.getString("brand"),
                        resultSet.getInt("price"),
                        resultSet.getLong("user_id")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return phones1;
    }

    @Override
    public Phone getYoungerUserPhone() {
        Phone phone = null;
        String sql = "select phones.* from phones join users u on phones.user_id = u.id order by u.age asc limit 1";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet result = statement.executeQuery();
            while (result.next()){
                phone = new Phone();
                phone.setModel(result.getString("madel"));
                phone.setBrand(result.getString("brand"));
                phone.setPrice(result.getInt("price"));
                phone.setUserId(result.getLong("user_id"));
            }

        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public Phone getPhoneById(Long id) {
        Phone phone = null;
        String sql = "select * from phones where id = ?;";
        try(Connection  connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                phone = new Phone();
                phone.setModel(result.getString("madel"));
                phone.setBrand(result.getString("brand"));
                phone.setPrice(result.getInt("price"));
                phone.setUserId(result.getLong("user_id"));


            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public void deletedPhoneById(Long id) {
        String sql = "delete from phones where id = ?";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
            System.out.println("Successfully deleted");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}
