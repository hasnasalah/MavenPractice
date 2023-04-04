package Config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnectionDirectly {

    public static void main(String[] args) {
        directDatabaseConnection();





    }

    public static void directDatabaseConnection() {
        // Database connection: JDBC connection
        String url = "jdbc:mysql://localhost:3306/qe_summer2022?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "Rootroot123456";
        String driver = "com.mysql.cj.jdbc.Driver";

        // Create connection
        Connection connection = null;
        Statement statement = null;


        // Movie class is using to insert data

        Movie titanic = new Movie(10,"Titanic",1997,"Romantic","PG-13");
        Movie topGun1986 = new Movie(12,"Top Gun 1986",1986,"Action","PG-14");
        Movie noTimeToDie = new Movie(14,"No Time To Die",2021,"Action","R18");
        Movie trueSpirit = new Movie(15,"True Spirit",2023,"Teen","PG");



        System.out.println(titanic.getMovieTitle());
        System.out.println(titanic.getMovieGenre());

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(titanic);
        movies.add(topGun1986);
        movies.add(noTimeToDie);
        movies.add(trueSpirit);

        for (Movie mo:movies         ) {
            System.out.println(mo);
        }


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Database Connection Successful");



        String query= "select * from customers";

        try {
            statement.execute(query);
            System.out.println(statement.execute(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            assert statement != null;
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
