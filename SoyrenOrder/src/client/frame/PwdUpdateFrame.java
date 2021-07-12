package client.frame;

import java.awt.event.*;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;
import java.awt.Font;
import java.awt.Color;

public class PwdUpdateFrame extends JFrame implements ActionListener {
	
	JPanel pwdPanel;
	JLabel pwdUpdateL, pwdL, idL;

	JTextField pwdField, idField;
	
	JButton commitBt;
	
	String id;
	String pwd;
	
	public CustInfoFrame custInfo;
	public MainFrame main;


	public PwdUpdateFrame(CustInfoFrame custInfo, String id) {
		this.id = id;
		
		this.setTitle("비밀번호 변경");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 300, 218);
		getContentPane().setLayout(null); //absoulute
		this.setVisible(true);
		setComponent();
		
	}
	
	public void setComponent() {
		
		pwdPanel = new JPanel();
		pwdPanel.setBackground(new Color(238, 232, 170));
		
		pwdUpdateL = new JLabel();
		pwdUpdateL.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		pwdUpdateL.setBounds(68, 10, 151, 23);
		pwdL = new JLabel();
		pwdL.setBounds(68, 89, 100, 30);
		idL = new JLabel();
		idL.setBounds(68, 32, 100, 30);
		
		idField = new JTextField(id);
		idField.setBounds(68, 61, 151, 21);
		pwdField = new JTextField();
		pwdField.setBounds(68, 117, 151, 21);
		
		commitBt = new JButton();
		commitBt.setBackground(new Color(255, 255, 240));
		commitBt.setBounds(90, 148, 97, 23);
		
		pwdUpdateL.setText("비밀번호 변경");
		pwdL.setText("비밀번호");
		idL.setText("아이디");
		
		commitBt.setText("확인");
		
		pwdUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		pwdPanel.setBounds(0, 0, 300, 230);
		
		commitBt.setHorizontalAlignment(JButton.CENTER);
		pwdPanel.setLayout(null);
		
		pwdPanel.add(pwdUpdateL);
		pwdPanel.add(idL);
		pwdPanel.add(idField);
		pwdPanel.add(pwdL);
		pwdPanel.add(pwdField);
		pwdPanel.add(commitBt);
		
		this.setContentPane(pwdPanel);
		
		eventList();
		
	}
		
		public void eventList() {
			commitBt.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(commitBt == e.getSource()) {
				
				CustomerMessage cmsg = new CustomerMessage();
				CustomerDAO cdao = new CustomerDAO();
				CustomerVO cvo = new CustomerVO();
				
				cvo.setCustID(idField.getText());
				cvo.setPwd(pwdField.getText());
				
				cdao.updatePwd(cvo);
//				cmsg.setState(5);
//				cmsg.setCvo(cvo);
				pwd = pwdField.getText();
				JOptionPane.showMessageDialog(null, "비밀번호 수정이 완료되었습니다.");
				
				main = new MainFrame(this, id, pwd);
				dispose();
			}
		}
}