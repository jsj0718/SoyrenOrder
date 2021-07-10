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
				+ "       FROM (SELECT SUM(D.COUNT * P.PRICE)"
				+ "             FROM PRODUCT P, ORDERS O, DETAILORDERS D"
				+ "             WHERE O.ORDERID = D.ORDERID"
				+ "             AND D.PRODID = P.PRODID"
				+ "             AND O.CUSTID = ?"
				+ "             GROUP BY ROLLUP ((O.CUSTID, O.ODATE))"
				+ "             ORDER BY SUM(D.COUNT * P.PRICE) DESC"
				+ "             )"
				+ "       WHERE ROWNUM <= 1) BETWEEN LOSAL AND HISAL";
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
