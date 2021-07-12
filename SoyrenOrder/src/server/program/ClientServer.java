package server.program;

import java.io.IOException;
import java.net.*;
import java.net.Socket;


public class ClientServer {
	public static void main(String[] args) {
		try {
			
			ServerSocket serverSocket = new ServerSocket(7777);

			System.out.println("클라이언트서버 실행");
			//클라이언트 소켓연결 대기
			Socket socket = serverSocket.accept();
			System.out.println("클라인언트서버 접속");
			//서버핸들러 쓰레드 시작 
			//서버핸들러 역할은 클라이언트와 통신
			Thread clientServerHandler = new ClientServerHandler(socket);
			clientServerHandler.start();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
