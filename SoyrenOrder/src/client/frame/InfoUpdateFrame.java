package client.frame;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;

public class InfoUpdateFrame extends JFrame implements ActionListener {
	
	JPanel infoUpdatePanel; // ���� ����
	JLabel infoUpdateL, idL, nameL, phoneL; // ���̵�, �̸�, ��ȭ��ȣ
	JButton updateBt; // ����
	JTextField idField, nameField, phoneField; 
	// ���̵�,�̸�,��ȭ��ȣ
	
	String id;
	
	public CustInfoFrame custInfo;
	public MainFrame main;
	
	public InfoUpdateFrame(CustInfoFrame custInfo, String id) {
		this.id = id;
	
		this.setTitle("���� ����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,250,300);
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
		
		idField = new JTextField(id);
		nameField = new JTextField();
		phoneField = new JTextField();
		
		updateBt = new JButton();
		
		infoUpdateL.setText("���� ����");
		idL.setText("���̵�");
		nameL.setText("�̸�");
		phoneL.setText("��ȭ��ȣ");
		
		updateBt.setText("Ȯ��");
		
		infoUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		infoUpdateL.setBounds(48, 10, 130, 23);
		idL.setBounds(12, 32, 90, 15);
		nameL.setBounds(12, 90, 90, 15);
		phoneL.setBounds(12, 148, 90, 15);
		
		idField.setBounds(12, 57, 148, 23);
		nameField.setBounds(12, 115, 148, 23);
		phoneField.setBounds(12, 173, 148, 23);
		
		updateBt.setBounds(60, 210, 90, 23);
		
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
				CustomerDAO cdao = new CustomerDAO();
				CustomerVO cvo = new CustomerVO();
				
				cvo.setCustID(idField.getText());
				cvo.setCname(nameField.getText());
				cvo.setPhone(phoneField.getText());
				
				cdao.update(cvo);
//				cmsg.setState(4);
//				cmsg.setCvo(cvo);
				JOptionPane.showMessageDialog(null, "���������� �Ϸ�Ǿ����ϴ�.");
				custInfo = new CustInfoFrame(this, id);
				dispose();
			}
		}
		
//		public void updateCheck(CustomerMessage cm) {
//			if(cm.getResult() != 0) {
//				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
//				dispose();
//			} 


}
