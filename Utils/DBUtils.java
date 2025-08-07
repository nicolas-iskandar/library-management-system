package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME_STRING = "library_management_system";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "P4ss4c4gl14%";

	private DBUtils() {
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME_STRING, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
