import java.sql.SQLException;

import dbService.DBService;

public class TestMain {
	public static void main(String[] args) throws SQLException {
		DBService dbService = new DBService();
		System.out.println("UpdatedTest");
		dbService.createTable();
		dbService.addUser("first", "mol");
		dbService.addUser("second", "mol88");
		dbService.addUser("third", "mol88");
		System.out.println(dbService.getAll());
	}
}
