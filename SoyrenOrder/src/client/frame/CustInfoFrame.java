package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

<<<<<<< HEAD
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
		  //정보수정버튼
		if(updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();
			
		 //탈퇴버튼
		} else if (deleteBt == e.getSource()) {
			
			int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?","회원탈퇴",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				CustomerDAO cdao = new CustomerDAO();
				CustomerVO cvo = new CustomerVO();
				cvo.setCustID(id);
				cdao.delete(cvo.getCustID());
				JOptionPane.showMessageDialog(null, "회원탈퇴가 완료되었습니다.");
				login = new LoginFrame();
				dispose();
				
			}else {
				
			}
			
		} else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this);
		}
	}
//	public static void main(String[] args) {
//		new CustInfoFrame(null, "2");
//	}
=======
import customer.CustomerDAO;
import customer.CustomerVO;

public class CustInfoFrame extends JFrame implements ActionListener{
	
	JPanel infoPanel; // 회원정보
	JLabel infoL, idL, pwdL, nameL, phoneL; // 아이디, 비밀번호, 이름, 전화번호
	JButton updateBt, pwdUpdateBt, deleteBt; // 수정, 비밀번호수정, 탈퇴
	JTextField idField, pwdField, nameField, phoneField; // 아이디 비밀번호 이름 전화번호

	String id;
	CustomerDAO cdao;
	
	public MainFrame main;
	public infoUpdateFrame infoUpdate;
	
	public CustInfoFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;
		
		this.setTitle("내 정보");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,350,400);
		this.setLayout(null);
		this.setVisible(true);
		
		setComponent();
	}
	
	public void setComponent() {
		
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		infoL = new JLabel();
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField();
		nameField = new JTextField();
		phoneField = new JTextField();
		
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
		
	public void eventList() {
		
		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(updateBt == e.getSource()) {
			infoUpdate = new infoUpdateFrame();
			
		} else if (deleteBt == e.getSource()) {
			
			int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.CLOSED_OPTION) {
				// 사용자가 yes, no 선택없이 창을 닫은 경우
			}else if(result == JOptionPane.NO_OPTION){
				// 사용자가 no를 선택했을 경우
			}else {
				// 사용자가 yes를 선택했을 경우
				CustomerDAO cdao = new CustomerDAO();
				
				cdao.delete(id);
				JOptionPane.showConfirmDialog(null, "회원탈퇴 되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.YES_NO_CANCEL_OPTION);
				dispose();
				
				
			}

		}
	}

>>>>>>> refs/remotes/origin/kangjisoo
}
