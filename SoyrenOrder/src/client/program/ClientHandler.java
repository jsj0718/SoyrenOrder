package client.program;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.frame.LoginFrame;
import message.AlarmMessage;
import message.CartMessage;
import message.CustomerMessage;
import message.DetailOrdersMessage;
import message.OrdersMessage;
import message.ProductMessage;
import message.SalgradeMessage;

public class ClientHandler extends Thread {
	Socket socket;
	LoginFrame login;
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	
	public ClientHandler(Socket socket, LoginFrame login) {
		this.socket = socket;
		this.login = login;
	}
	
	
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			oos = new ObjectOutputStream(out);
			ois = new ObjectInputStream(in);
			
			
			CustomerMessage inCMsg = null;
			ProductMessage inPMsg = null;
			OrdersMessage inOMsg = null;
			SalgradeMessage inSMsg = null;
			CartMessage inCaMsg = null;
			DetailOrdersMessage inDoMsg = null;
			AlarmMessage inAMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof CustomerMessage) {
					inCMsg = (CustomerMessage)obj;
					if(inCMsg.getState()==1) { //id 중복체크 
						login.join.idCheckResult(inCMsg.getResult());
					} else if (inCMsg.getState()==2) { //회원가입
						login.join.joinCheck(inCMsg);
					} else if (inCMsg.getState()==3) { //로그인
						login.loginResult(inCMsg);
					} else if (inCMsg.getState()==4) { // 월 고객 구매 금액 조회
						login.main.initTableResponse(inCMsg);
					} else if (inCMsg.getState()==5) { // 뒤로가기
//						login.main.order.moveToMain(inCMsg);
					} else if (inCMsg.getState()==6) {	// 정보창 데이터 가져오기
						login.main.custInfo.initResponse(inCMsg);
					} else if (inCMsg.getState()==7) {	// 회원탈퇴
						login.main.custInfo.deleteCustomer(inCMsg);
					} else if (inCMsg.getState()==8) {	// 비밀번호 수정
						login.main.custInfo.pwdUpdate.pwdUpdate(inCMsg);
					} else if (inCMsg.getState()==9) {	// 정보 수정창 데이터 가져오기
						login.main.custInfo.infoUpdate.initResponse(inCMsg);
					} else if (inCMsg.getState()==10) {	// 정보(이름, 전화번호) 수정
						login.main.custInfo.infoUpdate.customerUpdate(inCMsg);
					}
				}
				else if (obj instanceof SalgradeMessage) {	
					inSMsg = (SalgradeMessage) obj;
					if(inSMsg.getState()==1) {	// 고객 등급 조회
						login.main.initTableResponse(inSMsg);
					}
				}
				
				else if (obj instanceof ProductMessage) {
					inPMsg = (ProductMessage) obj;
					if (inPMsg.getState()==1) {	// 베스트 상품 조회
						login.main.initTableResponse(inPMsg);
					} else if (inPMsg.getState()==2) {	// 상품 정보 조회
						login.main.order.productResponse(inPMsg);
					} else if (inPMsg.getState()==3) {	// 상품 정보 조회
						login.main.order.addCart(inPMsg);
					} 
					else if (inPMsg.getState()==4) {	// 전체 상품 정보 조회
						login.main.order.addToMenu(inPMsg);
					} else if (inPMsg.getState()==5) {	// 전체 상품 정보 조회
						login.main.order.addToMenu(inPMsg);
					} 
				}
				
				else if (obj instanceof CartMessage) {
					inCaMsg = (CartMessage) obj;
					if (inCaMsg.getState()==1) {	// 장바구니 초기값 가져오기
						login.main.order.initTableResponse(inCaMsg);
					} else if (inCaMsg.getState()==2) {	// 장바구니 담기
						login.main.order.insertCart(inCaMsg);
					} else if (inCaMsg.getState()==3) {	// 장바구니 삭제
						login.main.order.deleteCart(inCaMsg);
					} else if (inCaMsg.getState()==4) {	// 상세 주문에 담을 장바구니 가져오기
						login.main.order.insertOrders(inCaMsg);
					} else if (inCaMsg.getState()==5) {	// 초기화
						login.main.order.initCart(inCaMsg);
					} 
				}
				
				else if (obj instanceof OrdersMessage) {
					inOMsg = (OrdersMessage)obj;
					if(inOMsg.getState() == 2) { // 주문 번호 조회
						login.main.order.insertOrders(inOMsg);
					}
				}
				
				else if (obj instanceof DetailOrdersMessage) {
					inDoMsg = (DetailOrdersMessage)obj;
					if(inDoMsg.getState() == 2) { //주문 등록	(상세 주문 테이블)
						login.main.order.insertOrders(inDoMsg);
					}
				}
				
				else if (obj instanceof AlarmMessage) {
					inAMsg = (AlarmMessage)obj;
					if(inAMsg.getState() == 1) { // 승인 메세지
						login.main.order.alarmMessage("주문이 승인되었습니다.");
					} else if(inAMsg.getState() == 2) { // 거절 메세지
						login.main.order.alarmMessage("주문이 거절되었습니다.");
					} else if(inAMsg.getState() == 3) { // 완성 메세지
						login.main.order.alarmMessage("완성 되었습니다.");
					}
				}
				
			}
					
			
		} catch (IOException e) {
//			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "마감되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
