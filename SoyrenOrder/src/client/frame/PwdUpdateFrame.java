package client.frame;

import java.awt.event.*;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;

public class PwdUpdateFrame extends JFrame implements ActionListener {
	
	JPanel pwdPanel;
	JLabel pwdUpdateL, pwdL;

	JTextField pwdField;
	
	JButton commitBt;
	
	public CustInfoFrame custInfo;


	public PwdUpdateFrame(CustInfoFrame custInfo) {
		
		this.setTitle("��й�ȣ ����");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 300, 200);
		this.setLayout(null); //absoulute
		setComponent();
		this.setVisible(true);
		
	}
	
	public void setComponent() {
		
		pwdPanel = new JPanel();
		pwdPanel.setLayout(null);
		
		pwdUpdateL = new JLabel();
		pwdL = new JLabel();
		
		pwdField = new JTextField();
		
		commitBt = new JButton();
		
		pwdUpdateL.setText("��й�ȣ ����");
		pwdL.setText("��й�ȣ");
		
		commitBt.setText("Ȯ��");
		
		pwdUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		pwdPanel.setBounds(0, 0, 300, 230);
		pwdUpdateL.setBounds(90, 10, 97, 23);
		
		pwdL.setBounds(20, 25, 100, 30);
		pwdField.setBounds(12, 46, 151, 21);
		
		commitBt.setBounds(12, 128, 97, 23);
		
		commitBt.setHorizontalAlignment(JButton.CENTER);
		
		pwdPanel.add(pwdUpdateL);
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
				
				cvo.setPwd(pwdField.getText());
				
				cdao.update(cvo);
				cmsg.setState(5);
				cmsg.setCvo(cvo);
				
			}
		}
		public void PwdUpdateCheck(CustomerMessage cm) {
			if(cm.getResult() != 0) {
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				dispose();
			}
	
}
}
