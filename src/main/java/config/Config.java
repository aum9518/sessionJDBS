package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static final String url = "jdbc:postgresql://localhost:5432/for_practice";
    private static final String userName = "postgres";
    private static final String password = "postgres";
    public static Connection connectionToDataBase(){
        Connection connection = null;
       try {
          connection= DriverManager.getConnection(
                   url,
                   userName,
                   password
           );
           System.out.println("Successfully connected to database...");
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
       return connection;
    }
}
