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
	public ArrayList<ProductVO> selectBestProduct() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * "
				+ "FROM PRODUCT "
				+ "WHERE PRODID IN (SELECT * "
				+ "                 FROM (SELECT PRODID"
				+ "                       FROM DETAILORDERS"
				+ "                       GROUP BY PRODID"
				+ "                       ORDER BY SUM(COUNT) DESC"
				+ "                       )"
				+ "                 WHERE ROWNUM <= 3)";
		ArrayList<ProductVO> plist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setProdID(rs.getInt("PRODID"));
				pvo.setPname(rs.getString("PNAME"));
				pvo.setPrice(rs.getInt("PRICE"));
				pvo.setInfo(rs.getString("INFO"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setImgPath(rs.getString("IMGPATH"));
				
				plist.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return plist;
	}
	
	// 상품 이름으로 상품 정보 가져오기
	public ProductVO selectProduct(String pname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM PRODUCT WHERE PNAME = ?";
		
		ProductVO pvo = null;
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pname);
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
