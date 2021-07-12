package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconn.DBConnect;
import customer.CustomerVO;

public class CustomerDAO {
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

	// ID 중복 체크
	public int idCheck(String custID) {
		String SQL = "SELECT COUNT(*) FROM CUSTOMER WHERE CUSTID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);

			rs = pstmt.executeQuery();
			while (rs.next()) { // 하나의 레코드(행)을 지시함
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}
	
	// 로그인
	public int login(CustomerVO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = DBConnect.getInstance();
			String SQL = "SELECT COUNT(*) FROM CUSTOMER WHERE CUSTID = ? and PWD =?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cvo.getCustID());
			pstmt.setString(2, cvo.getPwd());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				closeAll(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public CustomerVO select(String custId) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "SELECT * FROM CUSTOMER WHERE CUSTID = ?";
	      CustomerVO cvo = null;
	      try {
	         conn = DBConnect.getInstance();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, custId);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            cvo = new CustomerVO();

	            cvo.setCustID(rs.getString("CUSTID"));
	            cvo.setPwd(rs.getString("PWD"));
	            cvo.setCname(rs.getString("CNAME"));
	            cvo.setPhone(rs.getString("PHONE"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(null, pstmt, conn);
	      }
	      return cvo;
	   }
	
	// 회원가입
	public int insert(CustomerVO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnect.getInstance();
			String quary = "INSERT INTO CUSTOMER (CUSTID, PWD, CNAME, PHONE)" 
						+ " VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(quary);
			pstmt.setString(1, cvo.getCustID());
			pstmt.setString(2, cvo.getPwd());
			pstmt.setString(3, cvo.getCname());
			pstmt.setString(4, cvo.getPhone());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;	// DB 오류
	}
	
	// 회원 탈퇴
	public int delete(String custID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM CUSTOMER WHERE CUSTID= ?";
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
	
	// 회원 정보 수정 (추가 예정)
	public int update(CustomerVO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE CUSTOMER "
				+ "SET CNAME = ?, "
				+ "    PHONE = ? "
				+ "WHERE CUSTID =?";
		try {
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cvo.getCname());
			pstmt.setString(2, cvo.getPhone());
			pstmt.setString(3, cvo.getCustID());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return -1;
	}
		
		public int updatePwd(CustomerVO cvo) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String SQL = "UPDATE CUSTOMER "
					+ "SET PWD = ? "
					+ "WHERE CUSTID =?";
			try {
				conn = DBConnect.getInstance();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, cvo.getPwd());
				pstmt.setString(2, cvo.getCustID());
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeAll(null, pstmt, conn);
			}
			return -1;
			
		
		
	}
	
	
	// 월 고객별 구매 금액 (이번 달)
	public int monthCustBuy(String custID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * "
				+ "FROM (SELECT SUM(P.PRICE * D.COUNT)"
				+ "      FROM ORDERS O, DETAILORDERS D, PRODUCT P"
				+ "      WHERE O.ORDERID = D.ORDERID"
				+ "      AND D.PRODID = P.PRODID"
				+ "      AND CUSTID = ?"
				+ "      AND ODATE LIKE SUBSTR(SYSDATE,1, 6) || '%'"
				+ "      GROUP BY ROLLUP((O.CUSTID, O.ODATE))"
				+ "      ORDER BY SUM(P.PRICE * D.COUNT) DESC"
				+ "      )"
				+ "WHERE ROWNUM <= 1";
		int result = 0;
		try {			
			conn = DBConnect.getInstance();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, custID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
}
