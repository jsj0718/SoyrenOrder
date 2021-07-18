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

public class InfoUpdateFrame extends JFrame implements ActionListener {

	JPanel infoUpdatePanel; // 정보 수정
	JLabel infoUpdateL, idL, nameL, phoneL; // 아이디, 이름, 전화번호
	JTextField idField, nameField, phoneField; // 아이디,이름,전화번호
	JButton updateBt; // 수정
	JButton cancelBt;

	String id;
	
	public CustInfoFrame custInfo;

	public InfoUpdateFrame(CustInfoFrame custInfo, String id) {
		this.custInfo = custInfo;
		this.id = id;

		this.setTitle("정보 수정");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 250, 350);
		getContentPane().setLayout(null);
		setComponent();
		initRequest();
		this.setVisible(true);

	}


	public void setComponent() {

		infoUpdatePanel = new JPanel();
		infoUpdatePanel.setBackground(new Color(238, 232, 170));
		infoUpdatePanel.setLayout(null);

		infoUpdateL = new JLabel();
		infoUpdateL.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		idL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();

		idField = new JTextField(id);
		idField.setEditable(false);
		nameField = new JTextField();
		phoneField = new JTextField();

		updateBt = new JButton();
		updateBt.setBackground(new Color(255, 255, 240));
		updateBt.setForeground(new Color(0, 0, 0));

		infoUpdateL.setText("정보 수정");
		idL.setText("아이디");
		nameL.setText("이름");
		phoneL.setText("전화번호");

		updateBt.setText("확인");
		updateBt.setBorderPainted(false);

		infoUpdateL.setHorizontalAlignment(JLabel.CENTER);

		infoUpdateL.setBounds(48, 10, 130, 38);
		idL.setBounds(41, 58, 90, 15);
		nameL.setBounds(41, 116, 90, 15);
		phoneL.setBounds(41, 174, 90, 15);

		idField.setBounds(41, 83, 148, 23);
		nameField.setBounds(41, 141, 148, 23);
		phoneField.setBounds(41, 199, 148, 23);

		updateBt.setBounds(41, 248, 66, 23);

		infoUpdatePanel.add(infoUpdateL);
		infoUpdatePanel.add(idL);
		infoUpdatePanel.add(nameL);
		infoUpdatePanel.add(phoneL);
		infoUpdatePanel.add(idField);
		infoUpdatePanel.add(nameField);
		infoUpdatePanel.add(phoneField);
		infoUpdatePanel.add(updateBt);

		this.setContentPane(infoUpdatePanel);
		
		cancelBt = new JButton();
		cancelBt.setText("\uCDE8\uC18C");
		cancelBt.setForeground(Color.BLACK);
		cancelBt.setBorderPainted(false);
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.setBounds(123, 248, 66, 23);
		infoUpdatePanel.add(cancelBt);

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
		cmsg.setState(9);
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
		nameField.setText(cvo.getCname());
		phoneField.setText(cvo.getPhone());
	}

	public void eventList() {
		updateBt.addActionListener(this);
		cancelBt.addActionListener(this);
	}
	
	// 고객 정보 업데이트
	public void customerUpdate(CustomerMessage cmsg) {
		if (cmsg.getResult() > 0) {
			JOptionPane.showMessageDialog(null, "정보수정이 완료되었습니다.");

			infoUpdatePanel.removeAll();
			custInfo.nameField.setText(cmsg.getCvo().getCname());
			custInfo.phoneField.setText(cmsg.getCvo().getPhone());
			custInfo.setVisible(true);
			dispose();	
		} else {
			JOptionPane.showMessageDialog(null, "정보수정이 실패했습니다.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (updateBt == e.getSource()) {

			if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
				return;
			}

			if (phoneField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요.");
				return;
			}

//			int phoneCheck = 0;
//			String[] check = new String[phoneField.getText().length()];
//			check = phoneField.getText().split("");
//			for (int i = 0; i < phoneField.getText().length(); i++) {
//				if (!check[3].equals("-") || !check[8].equals("-") || phoneField.getText().length() != 13) {
//					phoneCheck = 1;
//				}
//			}
//			if (phoneCheck == 1) {
//				JOptionPane.showConfirmDialog(null, "핸드폰번호 확인이 필요합니다.\n예시) 010-0000-0000", "경고",
//						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
//				return;
//			}

			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();

			cvo.setCustID(idField.getText());
			cvo.setCname(nameField.getText());
			cvo.setPhone(phoneField.getText());
			
			cmsg.setState(10);
			cmsg.setCvo(cvo);

			try {
				ClientHandler.oos.writeObject(cmsg);
				ClientHandler.oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "마감되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
			
		} else if (cancelBt == e.getSource()) {
			infoUpdatePanel.removeAll();
			custInfo.setVisible(true);
			dispose();	
		}
	}

}
