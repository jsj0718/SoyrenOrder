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

			System.out.println("서버 실행");
			//클라이언트 소켓연결 대기
			Socket socket = serverSocket.accept();
			System.out.println("클라인언트 접속");
			//서버핸들러 쓰레드 시작 
			//서버핸들러 역할은 클라이언트와 통신
			Thread serverHandler = new ServerHandler(socket, serverMain);
			serverHandler.start();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
