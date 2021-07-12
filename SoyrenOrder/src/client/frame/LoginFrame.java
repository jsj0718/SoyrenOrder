package client.frame;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class LoginFrame extends JFrame implements ActionListener {

	// 전체 패널
	// 배경 이미지 삽입
	static JPanel totalPanel = new JPanel() {
		// 이미지 읽어오기
		Image backGroundImg = new ImageIcon("./loginimg/coffee.jpg").getImage();
		// 창 크기에 맞게 변경
		Image scaledBackGroundImg = backGroundImg.getScaledInstance(600, 400, Image.SCALE_DEFAULT);
		Image backGroundFinalImg = new ImageIcon(scaledBackGroundImg).getImage();

		public void paintComponent(Graphics g) {// 그리는 함수
			g.drawImage(backGroundFinalImg, 0, 0, null);// backgroundImg를 그려줌
			setOpaque(false); // 투명하게
			super.paintComponent(g);

		}
	};

	JPanel loginPanel;
	JLabel loginL, idL, pwdL;

	JTextField idField;
	JPasswordField pwdField;

	JButton loginBt, joinBt;

	public JoinFrame join;
	public MainFrame main;

	String id;

	public LoginFrame() {

		this.setTitle("로그인");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 600, 400);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		this.setVisible(true);

	}

	public void setComponent() {
		
//		totalPanel = new JPanel();
		totalPanel.setLayout(null);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 0, 0, 0));
		loginPanel.setLayout(null);
		loginL = new JLabel();
		loginL.setForeground(new Color(255, 255, 255));
		loginL.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		idL = new JLabel();
		idL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		idL.setForeground(new Color(255, 255, 255));
		pwdL = new JLabel();
		pwdL.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		pwdL.setForeground(new Color(255, 255, 255));

		idField = new JTextField();
		pwdField = new JPasswordField();

		loginBt = new JButton();
		loginBt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		loginBt.setBorderPainted(false);
		loginBt.setBackground(new Color(255, 255, 255));
		loginBt.setForeground(new Color(0, 0, 0));
		joinBt = new JButton();
		joinBt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinBt.setBackground(new Color(255, 255, 255));
		joinBt.setBorderPainted(false);
		joinBt.setForeground(new Color(0, 0, 0));
	
		loginL.setText("로 그 인");
		idL.setText("아 이 디 : ");
		pwdL.setText("비 밀 번 호 : ");

		loginBt.setText("로그인");
		joinBt.setText("회원가입");

		loginL.setHorizontalAlignment(JLabel.CENTER);

		totalPanel.setBounds(12, 10, 771, 494);
		loginPanel.setBounds(27, 165, 300, 166);
		loginL.setBounds(0, 10, 300, 26);

		idL.setBounds(20, 46, 100, 30);
		pwdL.setBounds(20, 86, 100, 30);

		idField.setBounds(120, 47, 150, 30);
		pwdField.setBounds(120, 86, 150, 30);

		loginBt.setBounds(51, 126, 90, 30);
		joinBt.setBounds(152, 126, 90, 30);

		loginPanel.add(loginL);
		loginPanel.add(idL);
		loginPanel.add(pwdL);
		loginPanel.add(loginBt);
		loginPanel.add(joinBt);
		loginPanel.add(idField);
		loginPanel.add(pwdField);

		totalPanel.add(loginPanel);

		this.setContentPane(totalPanel);

		eventList();

	}
	public void backGoundPaint() {
		
	}
	public void change(JPanel beforePanel, JPanel afterPanel) {
		totalPanel.remove(beforePanel);
		totalPanel.add(afterPanel);
		this.setContentPane(totalPanel);
		revalidate();
		repaint();
	}

	public void eventList() {
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (joinBt == e.getSource()) {
			join = new JoinFrame();
		} else if (loginBt == e.getSource()) {
			String custID = idField.getText();
			String password = pwdField.getText();

			if (custID.equals("") || password.equals("")) {
				JOptionPane.showConfirmDialog(null, "계정을 입력하세요", "경고", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustID(custID);
			cvo.setPwd(password);
			cmsg.setState(3);
			cmsg.setCvo(cvo);
			id = idField.getText();
			try {
				ClientHandler.oos.writeObject(cmsg);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	// 로그인 성공
	public void loginResult(CustomerMessage cmsg) {

		if (cmsg.getResult() != 0) {
			main = null;
			JOptionPane.showMessageDialog(null, "로그인 성공");

			// 메인 서적 창 띄움
			main = new MainFrame(this, id);
			// 로그인창 꺼둠
			dispose();

		} else {
			JOptionPane.showConfirmDialog(null, "계정이 잘못되었습니다. 확인하세요.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}
}
