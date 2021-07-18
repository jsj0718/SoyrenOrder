package server.program;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cart.CartDAO;
import cart.CartVO;
import customer.CustomerDAO;
import customer.CustomerVO;
import detailorders.DetailOrdersDAO;
import message.AlarmMessage;
import message.CartMessage;
import message.CustomerMessage;
import message.DetailOrdersMessage;
import message.OrdersMessage;
import message.ProductMessage;
import message.SalgradeMessage;
import orders.OrdersDAO;
import product.ProductDAO;
import product.ProductVO;
import salgrade.SalgradeDAO;
import server.frame.ServerAlarmFrame;
import server.frame.ServerMain;

public class ServerHandler extends Thread {
	public static Socket socket;
	ServerMain serverMain;
	
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;

	public ServerHandler(Socket socket, ServerMain serverMain) {
		this.socket = socket;
		this.serverMain = serverMain;
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
			ServerAlarmFrame alarm = null;

			Object obj = null;
			
	
			while ((obj = ois.readObject()) != null) {
				if (obj instanceof CustomerMessage) {
					inCMsg = (CustomerMessage) obj;
					CustomerMessage outMsg = null;
					CustomerDAO cdao = new CustomerDAO();			
					if (inCMsg.getState() == 1) { // id �ߺ�üũ
						outMsg = new CustomerMessage();
						String id = inCMsg.getCvo().getCustID();
						int result = cdao.idCheck(id);

						// state - idcheck�� ���� ��� ������ 1�Է�
						outMsg.setState(1);
						// result
						outMsg.setResult(result);
						// writeObject
						oos.writeObject(outMsg);
						oos.flush();

					} else if (inCMsg.getState() == 2) { // ȸ������
						outMsg = new CustomerMessage();

						int result = cdao.insert(inCMsg.getCvo());
						outMsg.setState(2);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 3) { // �α���
						outMsg = new CustomerMessage();
						int result = cdao.login(inCMsg.getCvo());
						outMsg.setState(3);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();	
					} else if (inCMsg.getState() == 4) { // ���� �� ���� �ݾ� ��ȸ
						outMsg = new CustomerMessage();
						String id = inCMsg.getCvo().getCustID();
						int result = cdao.monthCustBuy(id);
						outMsg.setState(4);
						outMsg.setResult(result);						
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 5) { // �ڷΰ���
//						outMsg = new CustomerMessage();
//						outMsg.setState(5);
//						oos.writeObject(outMsg);
//						oos.flush();
					} else if (inCMsg.getState() == 6) { // ����â ������ ��������
						outMsg = new CustomerMessage();
						CustomerVO cvo = cdao.select(inCMsg.getCvo().getCustID());
						outMsg.setState(6);
						outMsg.setCvo(cvo);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 7) { // ȸ��Ż��
						outMsg = new CustomerMessage();
						int result = cdao.delete(inCMsg.getCvo().getCustID());
						outMsg.setState(7);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 8) { // ��й�ȣ ����
						outMsg = new CustomerMessage();
						int result = cdao.updatePwd(inCMsg.getCvo());
						outMsg.setState(8);
						outMsg.setCvo(inCMsg.getCvo());
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 9) { // ���� ����â ������ ��������
						outMsg = new CustomerMessage();
						CustomerVO cvo = cdao.select(inCMsg.getCvo().getCustID());
						outMsg.setState(9);
						outMsg.setCvo(cvo);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCMsg.getState() == 10) { // ����(�̸�, ��ȭ��ȣ) ����
						outMsg = new CustomerMessage();
						int result = cdao.update(inCMsg.getCvo());
						outMsg.setState(10);
						outMsg.setCvo(inCMsg.getCvo());
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
					}
				} 
				else if (obj instanceof SalgradeMessage) {	
					inSMsg = (SalgradeMessage) obj;
					SalgradeMessage outMsg = null;
					SalgradeDAO sdao = new SalgradeDAO();
					if (inSMsg.getState() == 1) {	// �� ��� ��ȸ
						outMsg = new SalgradeMessage();
						String id = inSMsg.getSvo().getCustID();
						String grade = sdao.getGrade(id);
						outMsg.setState(1);
						outMsg.setGrade(grade);						
						oos.writeObject(outMsg);
						oos.flush();		
					}
				}
				else if (obj instanceof ProductMessage) {
					inPMsg = (ProductMessage) obj;
					ProductMessage outMsg = new ProductMessage();
					ProductDAO pdao = new ProductDAO();
					if (inPMsg.getState() == 1) { // ����Ʈ ��ǰ ��ȸ
						outMsg = new ProductMessage();
						ArrayList<ProductVO> plist = pdao.selectBestProduct();
						outMsg.setState(1);
						outMsg.setPlist(plist);
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inPMsg.getState() == 2) {	// ��ǰ ���� ��ȸ
						outMsg = new ProductMessage();
						ProductVO pvo = pdao.selectProduct(inPMsg.getPvo().getPname());
						outMsg.setState(2);
						outMsg.setPvo(pvo);
						oos.writeObject(outMsg);
						oos.flush();			
					} else if (inPMsg.getState() == 3) {	// ��ǰ ���� ��ȸ
						outMsg = new ProductMessage();
						ProductVO pvo = pdao.selectProduct(inPMsg.getPvo().getPname());
						outMsg.setState(3);
						outMsg.setPvo(pvo);
						outMsg.setCount(inPMsg.getCount());
						oos.writeObject(outMsg);
						oos.flush();			
					} 
					else if (inPMsg.getState() == 4) {	// Ŀ�� ��ǰ ���� ��ȸ
						outMsg = new ProductMessage();
						ArrayList<ProductVO> plist = pdao.productSelectCoffee();
						outMsg.setState(4);
						outMsg.setPlist(plist);
						outMsg.setCount(inPMsg.getCount());
						oos.writeObject(outMsg);
						oos.flush();			
					} else if (inPMsg.getState() == 5) {	// ���� ��ǰ ���� ��ȸ
						outMsg = new ProductMessage();
						ArrayList<ProductVO> plist = pdao.productSelectBaverage();
						outMsg.setState(5);
						outMsg.setPlist(plist);
						outMsg.setCount(inPMsg.getCount());
						oos.writeObject(outMsg);
						oos.flush();			
					} 
				} 
				
				else if (obj instanceof CartMessage) {
					inCaMsg = (CartMessage) obj;
					CartMessage outMsg = null;
					CartDAO cadao = new CartDAO();
					if (inCaMsg.getState() == 1) {	// ��ٱ��� �ʱⰪ ��������
						outMsg = new CartMessage();
						String id = inCaMsg.getCavo().getCustID();
						ArrayList<CartVO> calist = cadao.selectCart(id);
						int result = cadao.selectCartTotalPrice(id);

						outMsg.setState(1);
						outMsg.setCalist(calist);
						outMsg.setResult(result);
						
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCaMsg.getState() == 2) {	// ��ٱ��� ���
						System.out.println("22");
						outMsg = new CartMessage();
						int result = cadao.insertCart(inCaMsg.getCavo().getProdID(), inCaMsg.getCavo().getCustID(), inCaMsg.getCavo().getCoption(), inCaMsg.getCavo().getCount());
						
						outMsg.setState(2);
						outMsg.setResult(result);
						
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCaMsg.getState() == 3) {	// ��ٱ��� ����
						outMsg = new CartMessage();
						int result = cadao.deleteCart(inCaMsg.getCavo().getCustID());
						
						outMsg.setState(3);
						outMsg.setResult(result);
						
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCaMsg.getState() == 4) {	// �� �ֹ��� ���� ��ٱ��� ��������
						outMsg = new CartMessage();
						ArrayList<CartVO> calist = cadao.selectCartList(inCaMsg.getCavo().getCustID());
						
						outMsg.setState(4);
						outMsg.setCalist(calist);
						
						oos.writeObject(outMsg);
						oos.flush();
					} else if (inCaMsg.getState() == 5) {	// �ʱ�ȭ
						outMsg = new CartMessage();
						int result = cadao.deleteCart(inCaMsg.getCavo().getCustID());
						
						outMsg.setState(5);
						outMsg.setResult(result);
						
						oos.writeObject(outMsg);
						oos.flush();
					}
				} 
				
				else if (obj instanceof OrdersMessage) {
					inOMsg = (OrdersMessage) obj;
					OrdersMessage outMsg = new OrdersMessage();
					OrdersDAO odao = new OrdersDAO();
					if (inOMsg.getState() == 2) { // �ֹ� ��� �� �ֹ� ��ȣ ��ȸ
						odao.insertOrder(inOMsg.getOvo().getCustID());
						int orderID = odao.selectOrderID();
						outMsg.setState(2);
						outMsg.setResult(orderID);
						oos.writeObject(outMsg);
						oos.flush();
					}
				}
				
				else if (obj instanceof DetailOrdersMessage) {
					inDoMsg = (DetailOrdersMessage) obj;
					DetailOrdersMessage outMsg = new DetailOrdersMessage();
					DetailOrdersDAO dodao = new DetailOrdersDAO();
					if (inDoMsg.getState() == 2) { // �� ���̺� ��ٱ��� ��� ��� �� ������ �˸� �޼��� ������
						int result = 0;
						int orderID = inDoMsg.getDovo().getOrderID();
						String custID = inDoMsg.getDovo().getCustID();
						ArrayList<CartVO> calist = inDoMsg.getDovo().getCalist();
						for (CartVO cavo : calist) {
							result += dodao.insertDetailOrders(cavo, orderID);
						}
						outMsg.setState(2);
						outMsg.setResult(result);
						oos.writeObject(outMsg);
						oos.flush();
						
						alarm = new ServerAlarmFrame(custID, orderID);
					}
				}
					 
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {

		}

	}
}
