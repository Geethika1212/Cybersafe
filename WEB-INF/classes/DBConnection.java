import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = System.getenv("MYSQLHOST");
            String port = System.getenv("MYSQLPORT");
            String database = System.getenv("MYSQLDATABASE");
            String user = System.getenv("MYSQLUSER");
            String password = System.getenv("MYSQLPASSWORD");

            // If Railway variables are not available,
            // use the local MySQL database.
            if (host == null || host.isEmpty()) {

                host = "localhost";
                port = "3306";
                database = "cybersafe";
                user = "root";
                password = "Geethika@12";
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

            con = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}