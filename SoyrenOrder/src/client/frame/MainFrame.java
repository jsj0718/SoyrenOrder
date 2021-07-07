package client.frame;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	LoginFrame login;
	String id;
	
	
	public MainFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;

		this.setTitle("메인 프레임");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 800, 550);
		this.setLayout(new FlowLayout());
		// 화면구성
//		setComponent();

		// 초기 데이터 설정
//		initTableRequest();

		// 이벤트 모음
//		eventList();

		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame(null, "RSE");
	}
}
