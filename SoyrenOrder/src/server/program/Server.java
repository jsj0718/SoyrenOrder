package server.program;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import server.frame.ServerMain;

public class Server {
	public static void main(String[] args) {
		try {
			ServerMain serverMain = new ServerMain();
			
			ServerSocket serverSocket = new ServerSocket(7777);

			System.out.println("���� ����");
			//Ŭ���̾�Ʈ ���Ͽ��� ���
			Socket socket = serverSocket.accept();
			System.out.println("Ŭ���ξ�Ʈ ����");
			//�����ڵ鷯 ������ ���� 
			//�����ڵ鷯 ������ Ŭ���̾�Ʈ�� ���
			Thread serverHandler = new ServerHandler(socket, serverMain);
			serverHandler.start();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
