package dao.daoImpl;

import config.Config;
import dao.ContactDao;
import model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public void createContactTable() {

        String sql = " create table contacts(" +
                "id serial primary key," +
                "name varchar," +
                "phone_number varchar," +
                "phone_id int references phones(id));";
        try(Connection connection = Config.connectionToDataBase();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("successfully created...");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveContact(Contact contact) {
        String sql = "insert into contacts (name,phone_number, phone_id)" +
                "values (?,?,?);";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, contact.getContactName());
            statement.setString(2,contact.getPhoneNumber());
            statement.setLong(3,contact.getPhone_id());
            statement.executeUpdate();
            System.out.println("Successfully saved!");

        }catch(SQLException e ){

            System.out.println(e.getMessage());
        }

    }

    @Override
    public Contact getContactById(Long id) {
        Contact contact =null;
        String sql = "select * from contacts where id = ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                contact = new Contact ();
                contact.setContactName(result.getString("name"));
                contact.setPhoneNumber(result.getString("phone_number"));
                contact.setPhone_id(result.getLong("phone_id"));
                System.out.println("Successfully saved...");

            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts1 = new ArrayList<>();
        String sql = "select * from contacts;";
        try(Connection connection = Config.connectionToDataBase();
        Statement statement = connection.createStatement()){

            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                contacts1.add(new Contact(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("phone_number"),
                        result.getLong("phone_id")

                ));

            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return contacts1;
    }

    @Override
    public List<Contact> getAllPhoneContacts(Long contactId) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "select contacts.* from contacts join phones p on contacts.phone_id = p.id where contacts.id = ?  ";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,contactId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                contacts.add(new Contact(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("phone_number"),
                        result.getLong("phone_id")
                ));
            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllUserContacts(Long userId) {
        List<Contact>contacts = new ArrayList<>();
        String sql = "select contacts.* from contacts join phones p on contacts.phone_id = p.id join users u on p.user_id = u.id where u.id = ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                contacts.add(new Contact(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("phone_number"),
                        result.getLong("phone_id")
                ));
            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    @Override
    public void getPhoneContactsCount(Long phoneId) {
        String sql = "select count (*) from contacts join phones p on contacts.phone_id = p.id   where p.id = ? ;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong (1,phoneId);
            statement.execute();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("count"));


            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void getUserContactsCount(Long userId) {
        String sql = "select count(*) from contacts join phones p on contacts.phone_id = p.id join users u on p.user_id= u.id where u.id = ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("count"));
            }

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateContactInfo(Long id, Contact contact) {
            String sql = "update contacts set name = ?,phone_number = ?,phone_id = ? where id = ?;";
            try(Connection connection = Config.connectionToDataBase();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, contact.getContactName());
                statement.setString(2,contact.getPhoneNumber());
                statement.setLong(3,contact.getPhone_id());
                statement.setLong(4,id);
                statement.executeUpdate();
                System.out.println("Successfully updated!");

            }catch(SQLException e ){
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void deleteAllPhoneContactsByPhoneId(Long phoneId) {
        String sql = "delete from contacts where phone_id = ?;";
        try(Connection connection = Config.connectionToDataBase();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,phoneId);
            statement.executeUpdate();
            System.out.println("Successfully deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
