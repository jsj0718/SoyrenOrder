package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;

public class CustInfoFrame extends JFrame implements ActionListener{
	
	JPanel infoPanel; // 회원정보
	JLabel infoL, idL, pwdL, nameL, phoneL; // 아이디, 비밀번호, 이름, 전화번호
	JButton updateBt, pwdUpdateBt, deleteBt; // 수정, 비밀번호수정, 탈퇴
	JTextField idField, pwdField, nameField, phoneField; // 아이디 비밀번호 이름 전화번호

	String id;
	
	CustomerVO cvo;
	CustomerDAO cdao;
	public MainFrame main;
	public InfoUpdateFrame infoUpdate;
	public LoginFrame login;
	public PwdUpdateFrame pwdUpdate;
	
	public CustInfoFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;

		this.setTitle("내 정보");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100,100,350,400);
		this.setLayout(null);
		this.setVisible(true);
		
		CustomerVO cvo = initTableRequest();
		setComponent(cvo);
		
	}
	
	public void setComponent(CustomerVO cvo) {
		
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		infoL = new JLabel();
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField(cvo.getPwd());
		nameField = new JTextField(cvo.getCname());
		phoneField = new JTextField(cvo.getPhone());
		
		updateBt = new JButton();
		deleteBt = new JButton();
		pwdUpdateBt = new JButton();
		
		infoL.setText("내 정보");
		idL.setText("아이디");
		pwdL.setText("비밀번호");
		nameL.setText("이름");
		phoneL.setText("전화번호");
		
		updateBt.setText("정보수정");
		deleteBt.setText("탈퇴");
		pwdUpdateBt.setText("비밀번호수정");
		
		infoL.setHorizontalAlignment(JLabel.CENTER);
		
		infoL.setBounds(102, 10, 130, 23);
		idL.setBounds(12, 32, 90, 15);
		pwdL.setBounds(12,90,90,15);
		nameL.setBounds(12, 148, 90, 15);
		phoneL.setBounds(12, 216, 90, 15);
		
		idField.setBounds(12, 57, 148, 23);
		pwdField.setBounds(12,115,148,23);
		nameField.setBounds(12, 173, 148, 23);
		phoneField.setBounds(12, 241, 148, 23);
		
		updateBt.setBounds(12, 328, 116, 23); // 정보수정
		deleteBt.setBounds(206,328,116,23); // 탈퇴
		pwdUpdateBt.setBounds(206,115,116,23);
		
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
		
		this.setContentPane(infoPanel);
		
		eventList();
	}
	
	public CustomerVO initTableRequest() {
		cdao = new CustomerDAO();
		CustomerVO cvo = cdao.select(id);
		return cvo;
	}
	
//	public void select(CustomerDAO cdao) {
//		CustomerMessage cmsg = new CustomerMessage();
//		cmsg.setState(4);
//		try {
//			ClientHandler.oos.writeObject(cmsg);
//			ClientHandler.oos.flush();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
//	public void initTableResponse(Message msg) {
//		if (msg instanceof CustomerMessage) {
//			CustomerMessage cmsg = (CustomerMessage) msg;
//			add
//		}
//	}
	
	public void eventList() {
		
		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();
		} else if (deleteBt == e.getSource()) {
			CustomerDAO cdao = new CustomerDAO();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustID(id);
			cdao.delete(cvo.getCustID());
			
			login = new LoginFrame();
			dispose();
			
		} else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this);
		}
	}
//	public static void main(String[] args) {
//		new CustInfoFrame(null, "2");
//	}
}
