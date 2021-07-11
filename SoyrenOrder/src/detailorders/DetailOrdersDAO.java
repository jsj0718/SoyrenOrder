package detailorders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cart.CartVO;
import dbconn.DBConnect;

public class DetailOrdersDAO {
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
	
	// Cart에 있는 내용 insert
	public int insertDetailOrders(CartVO cavo, int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO DETAILORDERS (DETAILORDERID, ORDERID, PRODID, COUNT, PRICE)" 
				+ " VALUES (DETAILORDERS_DETAILORDERID_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, orderID);
			pstmt.setInt(2, cavo.getProdID());
			pstmt.setInt(3, cavo.getCount());
			pstmt.setInt(4, cavo.getCprice());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1; // DB 오류
	}
	
	// DetailOrders 삭제
	public int deleteDetailOrders(int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM DETAILORDERS "
				+ "WHERE ORDERID = ?";
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, orderID);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;	// DB 오류
	}
	
	
}
