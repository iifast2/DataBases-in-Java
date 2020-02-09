package com.company;
import com.company.Helper.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    static private Connection connection;
    static private PreparedStatement preparedStatement;
        //preparedStatement → allow us to create the sql query that will allow us to add things to our db.

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DBHandler dbHandler = new DBHandler();
            connection = dbHandler.getDbConnection();

    // ↓↓↓↓↓↓↓↓↓↓↓↓ remove it from comment and compile to use it :
              writeToDB();
    //        readFromDB();
    //        updateDB("John", "Wick", "Mr.Wick", "1001 USA America", 25, 2);
    //        deleteUser(1);


    }

        public static void writeToDB() throws SQLException{

            String insert = " INSERT INTO user(firstname,lastname,username,address,age)"
                    + "VALUES(?,?,?,?,?)";  // the userid is auto-incremented + PrimaryKey, shouldn't be added in here !
            preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1,"Hammadi");
            preparedStatement.setString(2,"Jebali");
            preparedStatement.setString(3,"hammouda");
            preparedStatement.setString(4,"1234 Ariana,tunisie");
            preparedStatement.setInt(5,21);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted in DataBase :   " + connection.getCatalog());
                                // connection.getCatalog() → wil display your database name in the output.

        }

        public static void readFromDB() throws SQLException{
            String query = "select * From user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Resultset → can hold all our info/data that we are getting from out table

            while(resultSet.next()){
                System.out.println("Names :"  + resultSet.getString("firstname")+
                   " "+ resultSet.getString("lastname")+
                   " "+ resultSet.getInt("age")+
                   " "+ resultSet.getString("address") );

                            }
                        }

    public static void updateDB(String firstName, String lastName, String username,
                                String address, int age, int id) {

        String query = "UPDATE user SET firstname = ?, lastname = ?, username = ?, address = ?, age = ? "
                + " where userid = ? ";

        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, age);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void deleteUser(int id) {
        String query = "DELETE FROM user where userid = ?";

        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        }}


