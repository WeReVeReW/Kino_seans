package com.example.kino_seans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class Database{

    public static Connection connectDb() {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/kino_seans_db","root","12345");

            return connection;
        } catch (Exception e){e.printStackTrace();}

        return null;
    }

    /*public void signUpUser(String username, String password){
        String insert = "INSERT INTO" + Const.USER_TABLE + "(" + Const.USER_USERNAME;
    }*/
}
