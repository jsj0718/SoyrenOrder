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
					if(inCMsg.getState()==1) { //id �ߺ�üũ 
						login.join.idCheckResult(inCMsg.getResult());
					} else if (inCMsg.getState()==2) { //ȸ������
						login.join.joinCheck(inCMsg);
					} else if (inCMsg.getState()==3) { //�α���
						login.loginResult(inCMsg);
					} else if (inCMsg.getState()==4) { // �� �� ���� �ݾ� ��ȸ
						login.main.initTableResponse(inCMsg);
					} else if (inCMsg.getState()==5) { // �ڷΰ���
//						login.main.order.moveToMain(inCMsg);
					} else if (inCMsg.getState()==6) {	// ����â ������ ��������
						login.main.custInfo.initResponse(inCMsg);
					} else if (inCMsg.getState()==7) {	// ȸ��Ż��
						login.main.custInfo.deleteCustomer(inCMsg);
					} else if (inCMsg.getState()==8) {	// ��й�ȣ ����
						login.main.custInfo.pwdUpdate.pwdUpdate(inCMsg);
					} else if (inCMsg.getState()==9) {	// ���� ����â ������ ��������
						login.main.custInfo.infoUpdate.initResponse(inCMsg);
					} else if (inCMsg.getState()==10) {	// ����(�̸�, ��ȭ��ȣ) ����
						login.main.custInfo.infoUpdate.customerUpdate(inCMsg);
					}
				}
				else if (obj instanceof SalgradeMessage) {	
					inSMsg = (SalgradeMessage) obj;
					if(inSMsg.getState()==1) {	// �� ��� ��ȸ
						login.main.initTableResponse(inSMsg);
					}
				}
				
				else if (obj instanceof ProductMessage) {
					inPMsg = (ProductMessage) obj;
					if (inPMsg.getState()==1) {	// ����Ʈ ��ǰ ��ȸ
						login.main.initTableResponse(inPMsg);
					} else if (inPMsg.getState()==2) {	// ��ǰ ���� ��ȸ
						login.main.order.productResponse(inPMsg);
					} else if (inPMsg.getState()==3) {	// ��ǰ ���� ��ȸ
						login.main.order.addCart(inPMsg);
					} 
					else if (inPMsg.getState()==4) {	// ��ü ��ǰ ���� ��ȸ
						login.main.order.addToMenu(inPMsg);
					} else if (inPMsg.getState()==5) {	// ��ü ��ǰ ���� ��ȸ
						login.main.order.addToMenu(inPMsg);
					} 
				}
				
				else if (obj instanceof CartMessage) {
					inCaMsg = (CartMessage) obj;
					if (inCaMsg.getState()==1) {	// ��ٱ��� �ʱⰪ ��������
						login.main.order.initTableResponse(inCaMsg);
					} else if (inCaMsg.getState()==2) {	// ��ٱ��� ���
						login.main.order.insertCart(inCaMsg);
					} else if (inCaMsg.getState()==3) {	// ��ٱ��� ����
						login.main.order.deleteCart(inCaMsg);
					} else if (inCaMsg.getState()==4) {	// �� �ֹ��� ���� ��ٱ��� ��������
						login.main.order.insertOrders(inCaMsg);
					} else if (inCaMsg.getState()==5) {	// �ʱ�ȭ
						login.main.order.initCart(inCaMsg);
					} 
				}
				
				else if (obj instanceof OrdersMessage) {
					inOMsg = (OrdersMessage)obj;
					if(inOMsg.getState() == 2) { // �ֹ� ��ȣ ��ȸ
						login.main.order.insertOrders(inOMsg);
					}
				}
				
				else if (obj instanceof DetailOrdersMessage) {
					inDoMsg = (DetailOrdersMessage)obj;
					if(inDoMsg.getState() == 2) { //�ֹ� ���	(�� �ֹ� ���̺�)
						login.main.order.insertOrders(inDoMsg);
					}
				}
				
				else if (obj instanceof AlarmMessage) {
					inAMsg = (AlarmMessage)obj;
					if(inAMsg.getState() == 1) { // ���� �޼���
						login.main.order.alarmMessage("�ֹ��� ���εǾ����ϴ�.");
					} else if(inAMsg.getState() == 2) { // ���� �޼���
						login.main.order.alarmMessage("�ֹ��� �����Ǿ����ϴ�.");
					} else if(inAMsg.getState() == 3) { // �ϼ� �޼���
						login.main.order.alarmMessage("�ϼ� �Ǿ����ϴ�.");
					}
				}
				
			}
					
			
		} catch (IOException e) {
//			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
