package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;
import message.Message;

public class CustInfoFrame extends JFrame implements ActionListener {

	JPanel infoPanel; // 회원정보
	JLabel infoL, idL, pwdL, nameL, phoneL; // 아이디, 비밀번호, 이름, 전화번호
	JButton updateBt, pwdUpdateBt, deleteBt, commitBt; // 수정, 비밀번호수정, 탈퇴
	JTextField idField, pwdField, nameField, phoneField; // 아이디, 비밀번호, 이름, 전화번호

	String id;

	public LoginFrame login;
	public MainFrame main;
	public InfoUpdateFrame infoUpdate;
	public PwdUpdateFrame pwdUpdate;

	public CustInfoFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;

		this.setTitle("내 정보");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 350, 400);
		getContentPane().setLayout(null);
		setComponent();
		initRequest();
		this.setVisible(true);

	}

	public void setComponent() {

		infoPanel = new JPanel();
		infoPanel.setBackground(new Color(238, 232, 170));
		infoPanel.setLayout(null);

		infoL = new JLabel();
		infoL.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField();
		nameField = new JTextField();
		phoneField = new JTextField();
		
		idField.setEditable(false);
		pwdField.setEditable(false);
		nameField.setEditable(false);
		phoneField.setEditable(false);

		updateBt = new JButton();
		updateBt.setBackground(new Color(255, 255, 240));
		deleteBt = new JButton();
		deleteBt.setBackground(new Color(255, 255, 240));
		pwdUpdateBt = new JButton();
		pwdUpdateBt.setBackground(new Color(255, 255, 240));
		commitBt = new JButton();
		commitBt.setBackground(new Color(255, 255, 240));

		infoL.setText("내 정보");
		idL.setText("아이디");
		pwdL.setText("비밀번호");
		nameL.setText("이름");
		phoneL.setText("전화번호");

		updateBt.setText("정보수정");
		deleteBt.setText("탈퇴");
		pwdUpdateBt.setText("비밀번호수정");
		commitBt.setText("확인");

		infoL.setHorizontalAlignment(JLabel.CENTER);

		infoL.setBounds(102, 10, 130, 33);
		idL.setBounds(23, 53, 90, 15);
		pwdL.setBounds(23, 111, 90, 15);
		nameL.setBounds(23, 169, 90, 15);
		phoneL.setBounds(23, 237, 90, 15);

		idField.setBounds(23, 78, 148, 23);
		pwdField.setBounds(23, 136, 148, 23);
		nameField.setBounds(23, 194, 148, 23);
		phoneField.setBounds(23, 262, 148, 23);

		updateBt.setBounds(12, 328, 97, 23); // 정보수정
		commitBt.setBounds(117, 328, 97, 23); // 확인
		deleteBt.setBounds(226, 328, 97, 23); // 탈퇴
		pwdUpdateBt.setBounds(208, 136, 116, 23); // 비밀번호 수정

		infoPanel.add(infoL);
		infoPanel.add(idL);
		infoPanel.add(pwdL);
		infoPanel.add(nameL);
		infoPanel.add(phoneL);
		infoPanel.add(idField);
		infoPanel.add(pwdField);
		infoPanel.add(nameField);
		infoPanel.add(phoneField);
		infoPanel.add(updateBt);
		infoPanel.add(deleteBt);
		infoPanel.add(pwdUpdateBt);
		infoPanel.add(commitBt);

		this.setContentPane(infoPanel);

		eventList();
	}

	// 초기데이터 요청
	public void initRequest() {
		customerSelect();
	}
	
	// 초기데이터 응답
	public void initResponse(Message msg) {
		if (msg instanceof CustomerMessage) {
			CustomerMessage cmsg = (CustomerMessage) msg;
			addTextFieldCustInfo(cmsg.getCvo());
		}
	}
	
	// 고객 정보 조회
	public void customerSelect() {
		CustomerMessage cmsg = new CustomerMessage();
		CustomerVO cvo = new CustomerVO();
		cvo.setCustID(id);
		cmsg.setState(6);
		cmsg.setCvo(cvo);

		try {
			ClientHandler.oos.writeObject(cmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
	}

	// 고객 정보 넣기
	public void addTextFieldCustInfo(CustomerVO cvo) {
		pwdField.setText(cvo.getPwd());
		nameField.setText(cvo.getCname());
		phoneField.setText(cvo.getPhone());
	}
	
	// 회원 탈퇴
	public void deleteCustomer(CustomerMessage cmsg) {
		if (cmsg.getResult() > 0) {
			JOptionPane.showConfirmDialog(null, "회원탈퇴 되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			infoPanel.removeAll(); // infoPanel 패널에 있는 컴포넌트 삭제
			main.login.idField.setText("");
			main.login.pwdField.setText("");
			main.login.setVisible(true);
			dispose();			
		} else {
			JOptionPane.showConfirmDialog(null, "회원탈퇴가 성사되지 않았습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);
		}
	}
	
	public void eventList() {

		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		commitBt.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 정보수정버튼
		if (updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();

		// 탈퇴버튼
		} else if (deleteBt == e.getSource()) {

			int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.CLOSED_OPTION) {
				// 사용자가 yes, no 선택없이 창을 닫은 경우
			} else if (result == JOptionPane.NO_OPTION) {
				// 사용자가 no를 선택했을 경우
			} else {
				// 사용자가 yes를 선택했을 경우
				CustomerMessage cmsg = new CustomerMessage();
				CustomerVO cvo = new CustomerVO();
				cvo.setCustID(id);
				cmsg.setState(7);
				cmsg.setCvo(cvo);

				try {
					ClientHandler.oos.writeObject(cmsg);
					ClientHandler.oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "마감되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
	
			}

		}

		// 확인버튼
		else if (commitBt == e.getSource()) {
			infoPanel.removeAll(); // total 패널에 있는 컴포넌트 삭제
			main.initTableRequest();
			main.setVisible(true);
			dispose();
		}
		// 비밀번호 수정
		else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this, id);
			dispose();

		}
	}
}