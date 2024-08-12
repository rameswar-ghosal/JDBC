import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.rjdbc.util.JdbcUtil;

public class TestDateApp {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		SimpleDateFormat sdf=null;
		Scanner sc=null;
		try {
			//Connection establishment
			connection=JdbcUtil.getJdbcConnection();
			
			//PreparedStatement object creation and execute query
			if(connection!=null) {
				String sqlInsertQuery="insert into users(usrname,dob,city) values(?,?,?)";
				pstmt=connection.prepareStatement(sqlInsertQuery);
				//Taking values from user or console
				sc=new Scanner(System.in);
				System.out.println("Enter your first name :");
				String usrName=sc.next();
				System.out.println("Enter your date of birth(dd-mm-yyyy) :");
				String dob=sc.next();
				//Changing date format to sql supported type
				sdf=new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate=sdf.parse(dob);
				long date=udate.getTime();
				java.sql.Date sqlDate=new java.sql.Date(date);
				System.out.println("Enter your city name :");
				String city=sc.next();
				//Set information to the query
				pstmt.setString(1, usrName);
				pstmt.setDate(2, sqlDate);
				pstmt.setString(3, city);
				
				//execute the query
				int rowCount=pstmt.executeUpdate();
				System.out.println(rowCount+" row(s) affected.");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
