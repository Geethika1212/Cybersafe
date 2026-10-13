
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = System.getenv("MYSQLHOST");
            String port = System.getenv("MYSQLPORT");
            String database = System.getenv("MYSQLDATABASE");
            String user = System.getenv("MYSQLUSER");
            String password = System.getenv("MYSQLPASSWORD");

            if (host == null || host.isEmpty()) {
                throw new IllegalStateException(
                    "Database environment variables are missing."
                );
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" 
                    + database + "?sslMode=VERIFY_IDENTITY";

            Connection con = DriverManager.getConnection(
                url, user, password
            );

            System.out.println("Database Connected Successfully");
            return con;

        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
