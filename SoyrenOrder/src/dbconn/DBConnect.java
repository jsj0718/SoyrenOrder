package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static Connection conn = null;
	
	private DBConnect() {
		
	}
	
	public static Connection getInstance() {
		// ����
		String user = "soyren";
		// ��й�ȣ
		String password = "1234";
		// url
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
			// ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc.jar�� �����ϴ�. (����̹��� �������� �ʽ��ϴ�.)");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url, user, password �� DB�� �����ִ��� Ȯ���ϼ���.");
			e.printStackTrace();
		}
			
		return conn;
	}
}
