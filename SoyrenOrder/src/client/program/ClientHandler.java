package client.program;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import client.frame.LoginFrame;
import message.CustomerMessage;
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
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof CustomerMessage) {
					inCMsg = (CustomerMessage)obj;
					if(inCMsg.getState()==1) { //id �ߺ�üũ 
						login.join.idCheckResult(inCMsg.getResult());
					}else if (inCMsg.getState()==2) { //ȸ������
						login.join.joinCheck(inCMsg);
					}else if (inCMsg.getState()==3) { //�α���
						login.loginResult(inCMsg);
					}
				}
//				else if(obj instanceof ProductMessage) {
//					inPMsg = (ProductMessage)obj;
//					if(inPMsg.getState() == 1) { // å ����Ʈ ��ȸ
//						login.bookStore.initTableResponse(inPMsg);
//					}
//				}else if(obj instanceof OrdersMessage) {
//					inOMsg = (OrdersMessage)obj;
//					if(inOMsg.getState() == 1) { //�ֹ� ����Ʈ ��ȸ
//						login.bookStore.initTableResponse(inOMsg);
//					}else if(inOMsg.getState() == 2) { //�ֹ� �ֱ�
//						login.bookStore.orderResult(inOMsg.getResult());
//					}else if(inOMsg.getState() == 3) { //�ֹ� ��� �ϱ�
//						login.bookStore.orderCancelResult(inOMsg.getResult());
//					}
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
