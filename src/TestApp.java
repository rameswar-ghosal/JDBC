import java.sql.*;
public class TestApp {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			//Driver loading
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded succesfully...");
			
			//Connection with Database
			String url="jdbc:mysql://localhost:3306/mydb";
			connection=DriverManager.getConnection(url, "root", "ramu123");
			System.out.println("Connection with db has established...");
			
			//Create Statement object and send the query
			String sqlselectQuery="SELECT * FROM employee";
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sqlselectQuery);
			
			//Process the ResultSet(read/write)
			System.out.println("eid\tename\tsalary\tdept");
			while(resultSet.next()) {
				int eid=resultSet.getInt(1);
				String ename=resultSet.getString(2);
				int salary=resultSet.getInt(3);
				String dept=resultSet.getString(4);
				System.out.println(eid+"\t"+ename+"\t"+salary+"\t"+dept);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connection!=null) {
				try {
					connection.close();
					System.out.println("Connection closed succesfully...");
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
