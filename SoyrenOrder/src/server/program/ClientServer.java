package server.program;

import java.io.IOException;
import java.net.*;
import java.net.Socket;


public class ClientServer {
	public static void main(String[] args) {
		try {
			
			ServerSocket serverSocket = new ServerSocket(7777);

			System.out.println("Ŭ���̾�Ʈ���� ����");
			//Ŭ���̾�Ʈ ���Ͽ��� ���
			Socket socket = serverSocket.accept();
			System.out.println("Ŭ���ξ�Ʈ���� ����");
			//�����ڵ鷯 ������ ���� 
			//�����ڵ鷯 ������ Ŭ���̾�Ʈ�� ���
			Thread clientServerHandler = new ClientServerHandler(socket);
			clientServerHandler.start();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
