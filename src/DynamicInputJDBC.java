import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DynamicInputJDBC {
	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		//ResultSet resultSet=null;
		Scanner s=new Scanner(System.in);
		try {
			String url="jdbc:mysql:///java";
			String userName="root";
			String password="ramu123";
			connection=DriverManager.getConnection(url, userName, password);
			System.out.println("Connection established to Database...");
			
			statement=connection.createStatement();
			System.out.println("Enter name ::");
			String sname=s.next();
			System.out.println("Enter age ::");
			int age=s.nextInt();
			System.out.println("Enter city ::");
			String city=s.next();
			//String sqlUpdateQuery="insert into student(sname,age,city) values('"+sname+"',"+age+",'"+city+"')";
			String sqlUpdateQuery=String.format("insert into student(sname,age,city) values('%s',%d,'%s')", sname,age,city);
			int n=statement.executeUpdate(sqlUpdateQuery);
			System.out.println(n+" row(s) affected.");
			
			/*
			 * System.out.println("sname\tsid\tage\tcity"); while(resultSet.next()) {
			 * System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2)+"\t"+
			 * resultSet.getInt(3)+"\t"+resultSet.getString(4)); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			s.close();
		}
		
	}
}
