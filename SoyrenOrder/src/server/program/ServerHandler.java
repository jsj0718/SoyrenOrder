package server.program;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import customer.CustomerDAO;
import message.CustomerMessage;

public class ServerHandler extends Thread {
	Socket socket;
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;

	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream(); 

			oos = new ObjectOutputStream(out);
			ois = new ObjectInputStream(in);

			CustomerMessage inCMsg = null;
//			ProductMessage inPMsg = null;
//			OrdersMessage inOMsg = null;
//			SalgradeMessage inSMsg = null;
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) {
				if (obj instanceof CustomerMessage) {
					inCMsg = (CustomerMessage) obj;
					CustomerMessage outMsg = new CustomerMessage();
					CustomerDAO cdao = new CustomerDAO();
					// id 중복체크
					if (inCMsg.getState() == 1) { 
						String id = inCMsg.getCvo().getCustID();
						int result = cdao.idCheck(id);

						// state - idcheck에 대한 결과 값으로 1입력
						outMsg.setState(1);
						// result
						outMsg.setResult(result);
						// writeObject
						oos.writeObject(outMsg);
						oos.flush();

					} else if (inCMsg.getState() == 2) { // 회원가입
						outMsg = new CustomerMessage();

						int result = cdao.insert(inCMsg.getCvo());
						outMsg.setState(2);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 3) { // 로그인
						outMsg = new CustomerMessage();
						int result = cdao.login(inCMsg.getCvo());
						outMsg.setState(3);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();

					}
				}
//				} else if (obj instanceof ProductMessage) {
//					inPMsg = (ProductMessage) obj;
//					ProductMessage outMsg = new ProductMessage();
//					ProductDAO pdao = new ProductDAO();
//					if (inPMsg.getState() == 1) { // 책 리스트 조회
//						ArrayList<ProductVO> plist = pdao.selectAllBook();
//						outMsg.setState(1);
//						outMsg.setBlist(plist);
//						oos.writeObject(outMsg);
//						oos.flush();
//					}
//
//				} else if (obj instanceof OrdersMessage) {
//					inOMsg = (OrdersMessage) obj;
//					OrdersMessage outMsg = new OrdersMessage();
//					OrdersDAO odao = new OrdersDAO();
//					if (inOMsg.getState() == 1) { // 주문 리스트 조회
//						String custId = inOMsg.getOvo().getCustId();
//						ArrayList<OrdersVO> olist = odao.selectOrder(custId);
//						outMsg.setState(1);
//						outMsg.setOlist(olist);
//						oos.writeObject(outMsg);
//						oos.flush();
//					}else if (inOMsg.getState() == 2) { // 주문 
//						OrdersVO ovo = inOMsg.getOvo();
//						int result = odao.insertOrders(ovo);
//						outMsg.setState(2);
//						outMsg.setOvo(ovo);
//						outMsg.setResult(result);
//						oos.writeObject(outMsg);
//						oos.flush();
//					}else if (inOMsg.getState() == 3) { // 주문취소 
//						OrdersVO ovo = inOMsg.getOvo();
//						int result = odao.deleteOrders(ovo.getOrderId());
//						outMsg.setState(3);
//						outMsg.setResult(result);
//						oos.writeObject(outMsg);
//						oos.flush();
//					}
//
//				} 
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
