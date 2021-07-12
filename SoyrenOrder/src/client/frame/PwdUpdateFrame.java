package client.frame;

import java.awt.event.*;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;

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
		this.setBounds(100, 100, 300, 200);
		this.setLayout(null); //absoulute
		this.setVisible(true);
		setComponent();
		
	}
	
	public void setComponent() {
		
		pwdPanel = new JPanel();
		pwdPanel.setLayout(null);
		
		pwdUpdateL = new JLabel();
		pwdL = new JLabel();
		idL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField();
		
		commitBt = new JButton();
		
		pwdUpdateL.setText("비밀번호 변경");
		pwdL.setText("비밀번호");
		idL.setText("아이디");
		
		commitBt.setText("확인");
		
		pwdUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		pwdPanel.setBounds(0, 0, 300, 230);
		pwdUpdateL.setBounds(90, 10, 97, 23);
		
		idL.setBounds(20, 25, 100, 30);
		idField.setBounds(12, 46, 151, 21);
		
		pwdL.setBounds(20, 65, 100, 30);
		pwdField.setBounds(12, 90, 151, 21);
		
		commitBt.setBounds(12, 128, 97, 23);
		
		commitBt.setHorizontalAlignment(JButton.CENTER);
		
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
//		public void PwdUpdateCheck(CustomerMessage cm) {
//			if(cm.getResult() != 0) {
//				JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
//				dispose();
//			}
	
//}
}
