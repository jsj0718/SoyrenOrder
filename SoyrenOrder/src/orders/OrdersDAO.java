package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbcon.DBconnect;
import dbconn.DBConnect;
import product.ProductVO;

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
	

	public ArrayList<OrdersVO> selectOrdersAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM ORDERS";
		ArrayList<OrdersVO> olist = new ArrayList<>();
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersVO ovo = new OrdersVO();
				ovo.setOrderID(rs.getInt("ORDERID"));
				ovo.setCustID(rs.getString("CUSTID"));
				ovo.setOdate(rs.getString("ODATE"));
				ovo.setOrderFlag(rs.getString("ORDERFLAG"));
				olist.add(ovo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return olist;
	}
	
	public int insertOrders(OrdersVO ovo) {
		Connection conn = DBConnect.getInstance();
		String sql = "INSERT INTO ORDERS (ORDERID, CUSTID, ODATE, ORDERFLAG) "
				+ "VALUES (ORDERS_ORDERID_SEQ.NEXTVAL,?,SYSDATE,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ovo.getOrderID());
			pstmt.setString(2, ovo.getCustID());
			pstmt.setString(3, ovo.getOdate());
			pstmt.setString(4, ovo.getOrderFlag());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public int updateOrders(OrdersVO ovo) {
		//DB connection 연결
		Connection conn = DBConnect.getInstance();
		//실행쿼리
		String sql = "UPDATE BOOK "
				+ "SET ORDERFLAG = ?, "
				+ "WHERE ORDERID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//preparedstatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ? 인자값 넣어주기
			pstmt.setString(1, ovo.getOrderFlag());
			pstmt.setInt(2, ovo.getOrderID());
			
			//Resultset 결과값 담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	//delete
		public int deleteOrders(int orderId) {
			//DB connection 연결
			Connection conn = DBConnect.getInstance();
			//실행쿼리
			String sql = "DELETE FROM ORDERS "
					+ "WHERE ORDERID = ?";
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				//preparedstatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ? 인자값 넣어주기
				pstmt.setInt(1, orderId);
				
				//Resultset 결과값 담기
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeAll(null, pstmt, conn);
			}
			return result;
		}
	
}
