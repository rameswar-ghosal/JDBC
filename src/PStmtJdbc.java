import com.rjdbc.util.JdbcUtil;
import java.sql.*;

public class PStmtJdbc {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		try {
			connection=JdbcUtil.getJdbcConnection();
			String sqlUpdateQuery="insert into student(sname,age,city) values(?,?,?)";
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlUpdateQuery);
			if(pstmt!=null) {
				pstmt.setString(1, "virat");
				pstmt.setInt(2, 38);
				pstmt.setString(3, "mumbai");
				int i=pstmt.executeUpdate();
			
				System.out.println(i+" row(s) affected.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
			
		}
		finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
