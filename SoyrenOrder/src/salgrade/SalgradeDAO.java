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
	
	// 등급 가져오기 (이번 달)
	public String getGrade(String custID) {
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
				+ "              AND O.ODATE LIKE SUBSTR(SYSDATE, 1,6) || '%'"
				+ "              GROUP BY rollup((O.ODATE, C.CUSTID))"
				+ "              ORDER BY O.ODATE DESC"
				+ "              )"
				+ "        WHERE ROWNUM <= 1) "
				+ "BETWEEN LOSAL AND HISAL";
		String result = "일반 고객";
		try {			
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result =  rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	
}
