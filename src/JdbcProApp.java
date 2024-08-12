import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rjdbc.util.*;

public class JdbcProApp {
	public static void main(String[] args) {
		try {
			Connection connection=JdbcUtil.getJdbcConnection();
			
			Statement statement=connection.createStatement();
			String sqlSelectQuery="select sname,sid,age,city from student";
			ResultSet resultSet=statement.executeQuery(sqlSelectQuery);
			
			System.out.println("sname\tsid\tage\tcity");
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
			}
			
			JdbcUtil.cleanUp(connection, statement, resultSet);
			
		} catch (SQLException se) {
			se.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
