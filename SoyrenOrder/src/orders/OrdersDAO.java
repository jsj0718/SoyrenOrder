package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	// 주문하기
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
		return -1;	// DB 오류
	}
	
	// 주문 삭제
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
		return -1;	// DB 오류
	}
	
	// 주문번호 가져오기
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
		return -1; // DB 오류
	}
	
	// 주문 승인
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
		return -1;	// DB 오류
	}
	
	// 주문 정보 가져오기
	public ArrayList<OrdersVO> selectOrders() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT ORDERID, CUSTID, ODATE "
				+ "FROM ORDERS "
				+ "WHERE ORDERFLAG = 'T'";
		ArrayList<OrdersVO> olist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrdersVO ovo = new OrdersVO();
				ovo.setOrderID(rs.getInt("ORDERID"));
				ovo.setCustID(rs.getString("CUSTID"));
				ovo.setOdate(rs.getString("ODATE"));
				
				olist.add(ovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return olist;
	}
	
	// 매출 계산
	public ArrayList<OrdersVO> selectSales(String select) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "";
		if (select.equals("날짜")) {
			SQL = "SELECT SUBSTR(O.ODATE, 1, 8), SUM(D.PRICE) "
				+ "FROM ORDERS O, DETAILORDERS D "
				+ "WHERE O.ORDERID = D.ORDERID "
				+ "GROUP BY SUBSTR(O.ODATE, 1, 8) "
				+ "ORDER BY SUBSTR(O.ODATE, 1, 8) DESC";			
		} else if (select.equals("고객")) {
			SQL = "SELECT O.CUSTID, SUM(D.PRICE) "
				+ "FROM ORDERS O, DETAILORDERS D "
				+ "WHERE O.ORDERID = D.ORDERID "
				+ "GROUP BY O.CUSTID "
				+ "ORDER BY SUM(D.PRICE) DESC";
		} 
		
		ArrayList<OrdersVO> olist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrdersVO ovo = new OrdersVO();
				
				if (select.equals("날짜")) {
					ovo.setOdate(rs.getString(1));
					ovo.setOprice(rs.getInt(2));
				} else if (select.equals("고객")) {
					ovo.setCustID(rs.getString(1));
					ovo.setOprice(rs.getInt(2));					
				}
				
				olist.add(ovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return olist;
	}
}
