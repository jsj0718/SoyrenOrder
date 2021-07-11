package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import customer.CustomerVO;
import dbconn.DBConnect;

public class OrdersDAO {

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
	
	// �ֹ��ϱ�
	public int insertOrder(String custID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO ORDERS (ORDERID, CUSTID)" 
				+ " VALUES (ORDERS_ORDERID_SEQ.NEXTVAL, ?)";
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;	// DB ����
	}
	
	// �ֹ� ����
	public int deleteOrder(int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM ORDERS " 
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
		return -1;	// DB ����
	}
	
	// �ֹ���ȣ ��������
	public int selectOrderID() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT MAX(ORDERID) FROM ORDERS";
		int result = 0;
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return -1; // DB ����
	}
	
	// �ֹ� ����
	public int updateFlag(int orderID, String flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE ORDERS "
				+ "SET ORDERFLAG = ? "
				+ "WHERE ORDERID = ?";
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, flag);
			pstmt.setInt(2, orderID);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;	// DB ����
	}
	
	
	
}
