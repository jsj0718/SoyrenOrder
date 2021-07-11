package detailorders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	// Cart�� �ִ� ���� insert
	public int insertDetailOrders(CartVO cavo, int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO DETAILORDERS (DETAILORDERID, ORDERID, PRODID, COUNT, DOPTION, PRICE)" 
				+ " VALUES (DETAILORDERS_DETAILORDERID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, orderID);
			pstmt.setInt(2, cavo.getProdID());
			pstmt.setInt(3, cavo.getCount());
			pstmt.setString(4, cavo.getCoption());
			pstmt.setInt(5, cavo.getCprice());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1; // DB ����
	}
	
	// DetailOrders ����
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
		return -1;	// DB ����
	}
	
	// DetailOrders ���� ��������
	public ArrayList<DetailOrdersVO> selectDetailOrders(int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT P.PNAME, D.DOPTION, D.COUNT "
				+ "FROM PRODUCT P, DETAILORDERS D "
				+ "WHERE P.PRODID = D.PRODID "
				+ "AND D.ORDERID = ?";
		ArrayList<DetailOrdersVO> dolist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, orderID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DetailOrdersVO dovo = new DetailOrdersVO();
				dovo.setPname(rs.getString(1));
				dovo.setDoption(rs.getString(2));
				dovo.setCount(rs.getInt(3));
				
				dolist.add(dovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return dolist;
	}
	
}
