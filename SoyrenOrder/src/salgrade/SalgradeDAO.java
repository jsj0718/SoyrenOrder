package salgrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconn.DBConnect;

public class SalgradeDAO {
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 등급 가져오기
	public String getGrade(String custID, String odate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT GRADE "
				+ "FROM SALGRADE "
				+ "WHERE (SELECT * "
				+ "       FROM ( SELECT SUM(O.COUNT * P.PRICE)"
				+ "              FROM CUSTOMER C, ORDERS O, PRODUCT P"
				+ "              WHERE C.CUSTID = O.CUSTID"
				+ "              AND P.PRODID = O.PRODID"
				+ "              AND C.CUSTID = ?"
				+ "              AND O.ODATE LIKE ?"
				+ "              GROUP BY rollup((O.ODATE, C.CUSTID))"
				+ "              ORDER BY O.ODATE DESC"
				+ "              )"
				+ "        WHERE ROWNUM <= 1) "
				+ "BETWEEN LOSAL AND HISAL";
		try {			
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
			pstmt.setString(2, odate);

			rs = pstmt.executeQuery();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return null;	// DB 오류
	}
	
	
}
