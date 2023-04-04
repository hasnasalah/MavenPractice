package Config.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectDatabase {

    // Secret.properties file
    // jdbc drive
    // jdbc url
    // jdbc userName
    // jdbc password
    // MySql query


    public static Connection connection = null;
    public static Statement statement = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;


    public static void main(String[] args) {
        //  Properties prop= loadProperties("configProperty/Secret.properties");
        //  System.out.println(prop.getProperty("MYSQL_USER_NAME"));

        // getDatabaseConnection();
        //  closeDatabaseConnection();
        String query = "SELECT * FROM customers;";
       // directDatabaseQueryExecution(query, "customer_id");
        //  directDatabaseQueryExecution(query, "c_first_name");
        //  readDatabaseTableColumn("customers","c_mobile");

        //  insertDataFromStringToTable("204","employees","employee_id");
        //   insertDataFromStringToTable("Fokrul","employees","employee_firstname");
        //    insertDataFromStringToTableMultipleColumn("303","Fokrul","employees","employee_id","employee_firstname");

        List<String> employeeIdList = new ArrayList<>();
        employeeIdList.add("101");
        employeeIdList.add("102");

        List<String> employeeFirstNameList = new ArrayList<>();
        employeeFirstNameList.add("Cohen");
        employeeFirstNameList.add("Cohen1");

        //  insertDataInMultipleColumnFromListString("employees", "employee_id", "employee_firstname", employeeIdList, employeeFirstNameList);
        //   insertDataInSingleColumnFromListString("employees","employee_id",listData1);

        readUserProfileFromSQLTable("movie");

    }


    // Load properties file
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Exception -- IOException | FileNotFoundException :" + e.getMessage());
        }
        return properties;
    }

    /**
     * This method will develop a connection with Database.
     *
     * @return Author: Mahmud
     */
    public static Connection getDatabaseConnection() {
        Properties prop = loadProperties("configProperty/Secret.properties");
        String driverClass = prop.getProperty("MYSQLJDBC.DRIVER");
        String url = prop.getProperty("MYSQLJDBC.URL");
        String userName = prop.getProperty("MYSQL_USER_NAME");
        String password = prop.getProperty("MYSQL_PASSWORD");
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception -- ClassNotFoundException | SQLException :" + e.getMessage());
            // throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * This Method will close the Database connection
     * Author: Mahmud
     */
    public static void closeDatabaseConnection() {
        try {
            assert resultSet != null;
            resultSet.close();
            assert statement != null;
            statement.close();
            assert connection != null;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection not closed " + e.getMessage());
            //  throw new RuntimeException(e);
        }

    }

    public static List<String> getResultSetData(ResultSet resultSet, String columnName) {
        List<String> dataList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                String items = null;
                items = resultSet.getString(columnName);
                dataList.add(items);
            } catch (SQLException e) {
                // throw new RuntimeException(e);
                System.out.println("SQL Exception : " + e.getMessage());
            }
        }
        return dataList;
    }


    public static List<String> directDatabaseQueryExecution(String query, String columnName) {
        List<String> data = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will create a connection to DB
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            data = getResultSetData(resultSet, columnName);
            System.out.println("Data Value : " + data);
            for (String dt : data) {
                System.out.println(dt);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception : " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }
        return data;
    }

    public static List<String> readDatabaseTableColumn(String tableName, String columnName) {
        List<String> data = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will create a connection to DB
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            resultSet = statement.executeQuery(query);
            data = getResultSetData(resultSet, columnName);
            System.out.println("Data Value : " + data);
//            for (String dt : data) {
//                System.out.println(dt);
//            }
        } catch (SQLException e) {
            System.out.println("SQL Exception : " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }
        return data;
    }

    public static void insertDataFromStringToTable(String arrayData, String tableName, String columnName) {
        getDatabaseConnection();
        // INSERT INTO `qe_summer2022`.`employees` (`employee_id`, `employee_firstname`, `employee_lastname`, `employee_email`, `employee_phone`, `employee_address`, `employee_salary`, `employee_manager`) VALUES ('202', 'Jack', 'Cohen', 'jack@gmail.com', '9276543216', 'Queens Village,NYC', '10000', 'salma');
        // INSERT INTO `qe_summer2022`.`employees` (`employee_id`, `employee_firstname`, `employee_lastname`) VALUES ('202', 'Jack', 'Cohen');
        // Insert into tableName(columnName1, columnName2, columnName3) values (columnValue1, columnValue2, columnValue3);

        try {
            String query = "Insert into " + tableName + "(" + columnName + ") values(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrayData);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }


    }


    public static void insertDataFromStringToTableMultipleColumn(String arrayData1, String arrayData2, String tableName, String columnName1, String columnName2) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + ") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrayData1);
            preparedStatement.setString(2, arrayData2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(" SQLException : " + e.getMessage());
        }


    }

    public static void insertDataInMultipleColumnFromListString(String tableName, String columnName1, String columnName2, List<String> data1, List<String> data2) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + ") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1, data1.get(i));
                preparedStatement.setObject(2, data2.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" SQLException : " + e.getMessage());
        }


    }


    public static void insertDataInSingleColumnFromListString(String tableName, String columnName1, List<String> data1) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + ") values(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1, data1.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" SQLException : " + e.getMessage());
        }
    }


    public static List<Movie> readUserProfileFromSQLTable(String tableName) {
        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        Connection connection = getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("movieId");
                String title = resultSet.getString("movieTitle");
                int releaseYear = resultSet.getInt("releaseYear");
                String genre = resultSet.getString("movieGenre");
                String rating = resultSet.getString("movieRating");
                System.out.format("%s,%s,%s,%s,%s\n",id,title,releaseYear,genre,rating);
                movie=new Movie(id,title,releaseYear,genre,rating);
                movieList.add(movie);
            }
          //  closeDatabaseConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }


}
