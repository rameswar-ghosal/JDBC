import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.File;

import com.rjdbc.util.JdbcUtil;

public class JdbcBlobOperation {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner sc=null;
		String name = null;
		String imageLoc = null;
		try {
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null) {
				String sqlInsertQuery="insert into person(name,image) values(?,?)";
				pstmt=connection.prepareStatement(sqlInsertQuery);
			}
			
			if(pstmt!=null) {
				sc=new Scanner(System.in);
				if(sc!=null) {
					System.out.print("Enter your name ::");
					name=sc.next();
					System.out.print("Give your image location ::");
					imageLoc=sc.next();
				}
			}
			
			pstmt.setString(1,name);
			pstmt.setBinaryStream(2, new FileInputStream(new File(imageLoc)));
			
			int rowCount=pstmt.executeUpdate();
			System.out.println(rowCount+" row(s) affected.");
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
