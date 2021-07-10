package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class infoUpdateFrame extends JFrame implements ActionListener {
	
	JPanel infoUpdatePanel; // 정보 수정
	JLabel infoUpdateL, idL, nameL, phoneL; // 아이디, 이름, 전화번호
	JButton updateBt; // 수정
	JTextField idField, nameField, phoneField; 
	// 아이디,이름,전화번호
	
	public CustInfoFrame custInfo;
	
	public infoUpdateFrame() {
	
		this.setTitle("정보 수정");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,250,350);
		this.setLayout(null);
		this.setVisible(true);
		
		setComponent();
	}
	
	public void setComponent() {
		
		infoUpdatePanel = new JPanel();
		infoUpdatePanel.setLayout(null);
		
		infoUpdateL = new JLabel();
		idL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField();
		nameField = new JTextField();
		phoneField = new JTextField();
		
		updateBt = new JButton();
		
		infoUpdateL.setText("정보 수정");
		idL.setText("아이디");
		nameL.setText("이름");
		phoneL.setText("전화번호");
		
		updateBt.setText("확인");
		
		infoUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		infoUpdateL.setBounds(48, 10, 130, 23);
		idL.setBounds(12, 32, 90, 15);
		nameL.setBounds(12, 90, 90, 15);
		phoneL.setBounds(12, 148, 90, 15);
		
		idField.setBounds(12, 57, 148, 23);
		nameField.setBounds(12, 115, 148, 23);
		phoneField.setBounds(12, 173, 148, 23);
		
		updateBt.setBounds(48, 250, 90, 23);
		
		infoUpdatePanel.add(infoUpdateL);
		infoUpdatePanel.add(idL);
		infoUpdatePanel.add(nameL);
		infoUpdatePanel.add(phoneL);
		infoUpdatePanel.add(idField);
		infoUpdatePanel.add(nameField);
		infoUpdatePanel.add(phoneField);
		infoUpdatePanel.add(updateBt);
	
		this.setContentPane(infoUpdatePanel);
		
		eventList();
	}
	
		public void eventList() {
			updateBt.addActionListener(this);
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(updateBt == e.getSource()) {
				
				CustomerMessage cmsg = new CustomerMessage();
				CustomerVO cvo = new CustomerVO();
				
				cvo.setCustID(idField.getText());
				cvo.setCname(nameField.getText());
				cvo.setPhone(phoneField.getText());
				
				cmsg.setState(4);
				cmsg.setCvo(cvo);
				dispose();
			}
		}
		
		public void updateCheck(CustomerMessage cm) {
			if(cm.getResult() != 0) {
				JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
				dispose();
			}
		}

}
