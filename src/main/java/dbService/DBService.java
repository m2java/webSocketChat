package dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dbService.dao.UserProfileDAO;
import dbService.entity.UserProfile;

public class DBService {
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:file:C:\\Users\\zemli\\Documents\\GitHub\\WebSocketChat\\Chat";
	private static final String DB_USERNAME = "test";
	private static final String DB_PASSWORD = "test";
	private final Connection connection;

	public DBService() {
		this.connection = getH2Connection();
	}
	
	public UserProfile getUserByLogin(String login) throws SQLException {
		return (new UserProfileDAO(connection).getUserByLogin(login));
	}

	public void addUser(String name, String pass) throws SQLException {
			UserProfileDAO dao = new UserProfileDAO(connection);
			dao.insertUser(name, pass);
	}

	public static Connection getH2Connection() {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("Connection to DB created");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Not connected to H2DS" + e.getMessage());
		}
		return null;
	}

	public void createTable() {
		new UserProfileDAO(connection).createTable();
	}
	
	public List<UserProfile> getAll(){
		return new UserProfileDAO(connection).getAll();
	}

	public void closeConection() {
		System.out.println("Connection to DB closed");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}