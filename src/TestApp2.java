import java.sql.*;

public class TestApp2 {
	public static void main(String[] args) {
		Connection connection=null;
		try {
			
			//Driver loading
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded...");
			
			//Connection establish
			String url="jdbc:mysql://localhost:3306/java";
			String userName="root";
			String password="ramu123";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection Established...");
			
			//Statement object creation and sending the query
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from student");
			
			//Process the result set
			System.out.println("STUDENT_NAME\tSTUDENT_ID\tAGE\tCITY");
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1)+"\t\t"+resultSet.getInt(2)+"\t\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
					System.out.println("Disconnected...");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
