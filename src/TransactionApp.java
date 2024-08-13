import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Savepoint;

import com.rjdbc.util.JdbcUtil;

public class TransactionApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement=null;
		//Savepoint sp=null;
		try {
			
			connection=JdbcUtil.getJdbcConnection();
			
			if (connection!=null) 
				statement=connection.createStatement();
			System.out.println("Transaction started...");
			connection.setAutoCommit(false);
			
			statement.executeUpdate("insert into politicians(name,pname) values('Rahul','Congress')");
			statement.executeUpdate("insert into politicians(name,pname) values('Modi','BJP')");
			Savepoint sp=connection.setSavepoint();
			statement.executeUpdate("insert into politicians(name,pname) values('Mamata','Congress')");
			System.out.println("Something went wrong!");
			connection.rollback(sp);
			System.out.println("Operations are rolled back to savepoint...");
			connection.commit();
			System.out.println("Trasaction is done...");
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, statement, null);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
