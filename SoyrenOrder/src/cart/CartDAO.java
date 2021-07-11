package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class CartDAO {
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
	
	// 카트에 담기
	public int insertCart(int prodID, String custID, String coption, int count) {
		String SQL = "INSERT INTO CART (CARTID, PRODID, CUSTID, COPTION, COUNT) "
				+ "VALUES (CART_CARTID_SEQ.NEXTVAL, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, prodID);
			pstmt.setString(2, custID);
			pstmt.setString(3, coption);
			pstmt.setInt(4, count);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;	// DB 오류
	}
	
	// 장바구니 화면에 정보 띄우기
	public ArrayList<CartVO> selectCart(String custID) {
		String SQL = "SELECT P.PNAME, C.COPTION, C.COUNT, (P.PRICE * C.COUNT) "
				+ "FROM CART C, PRODUCT P "
				+ "WHERE C.PRODID = P.PRODID "
				+ "AND CUSTID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> calist = new ArrayList<>();
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartVO cavo = new CartVO();
				cavo.setPname(rs.getString(1));
				cavo.setCoption(rs.getString(2));
				cavo.setCount(rs.getInt(3));
				cavo.setCprice(rs.getInt(4));
				
				calist.add(cavo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return calist;	// DB 오류
	}
	
	// 장바구니 총 금액
	public int selectCartTotalPrice(String custID) {
		String SQL = "SELECT SUM(P.PRICE * C.COUNT) "
				+ "FROM CART C, PRODUCT P "
				+ "WHERE C.PRODID = P.PRODID "
				+ "AND C.CUSTID = ? "
				+ "GROUP BY C.CUSTID";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
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
		return -1;	// DB 오류
	}
	
	// 주문 취소
	public int deleteCart(String custID) {
		String SQL = "DELETE FROM CART WHERE CUSTID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
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
	
	// DETAILORDERS에 담을 장바구니 리스트 가져오기
	public ArrayList<CartVO> selectCartList(String custID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT C.PRODID, C.COPTION, C.COUNT, (P.PRICE * C.COUNT) "
				+ "FROM PRODUCT P, CART C "
				+ "WHERE P.PRODID = C.PRODID "
				+ "AND C.CUSTID = ?";
		ArrayList<CartVO> calist = new ArrayList<>();
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVO cavo = new CartVO();
				cavo.setProdID(rs.getInt(1));
				cavo.setCoption(rs.getString(2));
				cavo.setCount(rs.getInt(3));
				cavo.setCprice(rs.getInt(4));
				
				calist.add(cavo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return calist;
	}
}
