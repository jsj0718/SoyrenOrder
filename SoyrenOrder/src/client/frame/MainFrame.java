package client.frame;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	LoginFrame login;
	String id;
	
	
	public MainFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;

		this.setTitle("���� ������");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 800, 550);
		this.setLayout(new FlowLayout());
		// ȭ�鱸��
//		setComponent();

		// �ʱ� ������ ����
//		initTableRequest();

		// �̺�Ʈ ����
//		eventList();

		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame(null, "RSE");
	}
}
