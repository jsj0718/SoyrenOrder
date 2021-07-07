package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;
import orders.OrdersVO;

public class ProductDAO {
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
	
	// 베스트 상품 (3개)
	public ArrayList<Integer> selectBestProductID() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * "
				+ "FROM (SELECT P.PRODID"
				+ "      FROM ORDERS O, PRODUCT P"
				+ "      WHERE O.PRODID = P.PRODID"
				+ "      GROUP BY P.PRODID"
				+ "      ORDER BY SUM(O.COUNT) DESC) "
				+ "WHERE ROWNUM <= 3";
		ArrayList<Integer> plist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				plist.add(rs.getInt("PRODID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return plist;
	}
	
	// 상품 번호로 상품 전체 찾기
	public ProductVO selectProduct(Integer prodID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM PRODUCT WHERE PRODID = ?";
		ProductVO pvo = null;
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, prodID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pvo = new ProductVO();
				pvo.setProdID(rs.getInt("PRODID"));
				pvo.setPname(rs.getString("PNAME"));
				pvo.setPrice(rs.getInt("PRICE"));
				pvo.setInfo(rs.getString("INFO"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setImgPath(rs.getString("IMGPATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return pvo;
	}
	
}
