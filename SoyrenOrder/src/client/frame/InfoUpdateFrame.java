package client.frame;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;
import java.awt.Color;
import java.awt.Font;

public class InfoUpdateFrame extends JFrame implements ActionListener {
	
	JPanel infoUpdatePanel; // ���� ����
	JLabel infoUpdateL, idL, nameL, phoneL; // ���̵�, �̸�, ��ȭ��ȣ
	JButton updateBt; // ����
	JTextField idField, nameField, phoneField; 
	// ���̵�,�̸�,��ȭ��ȣ
	
	String id = null;
	
	public CustInfoFrame custInfo;
	public MainFrame main;
	
	public InfoUpdateFrame(CustInfoFrame custInfo, String id) {
		this.id = id;
	
		this.setTitle("���� ����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,250,350);
		getContentPane().setLayout(null);
		this.setVisible(true);
		
		setComponent();
	}
	
	public void setComponent() {
		
		infoUpdatePanel = new JPanel();
		infoUpdatePanel.setBackground(new Color(238, 232, 170));
		infoUpdatePanel.setLayout(null);
		
		infoUpdateL = new JLabel();
		infoUpdateL.setFont(new Font("���� ���", Font.BOLD, 14));
		idL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		nameField = new JTextField();
		phoneField = new JTextField();
		
		updateBt = new JButton();
		updateBt.setBackground(new Color(255, 255, 240));
		updateBt.setForeground(new Color(0, 0, 0));
		
		infoUpdateL.setText("���� ����");
		idL.setText("���̵�");
		nameL.setText("�̸�");
		phoneL.setText("��ȭ��ȣ");
		
		updateBt.setText("Ȯ��");
		updateBt.setBorderPainted(false);
		
		infoUpdateL.setHorizontalAlignment(JLabel.CENTER);
		
		infoUpdateL.setBounds(48, 10, 130, 38);
		idL.setBounds(41, 58, 90, 15);
		nameL.setBounds(41, 116, 90, 15);
		phoneL.setBounds(41, 174, 90, 15);
		
		idField.setBounds(41, 83, 148, 23);
		nameField.setBounds(41, 141, 148, 23);
		phoneField.setBounds(41, 199, 148, 23);
		
		updateBt.setBounds(65, 251, 90, 23);
		
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
				cmsg.setCvo(cvo);
				JOptionPane.showMessageDialog(null, "���������� �Ϸ�Ǿ����ϴ�.");
				custInfo = new CustInfoFrame(this, id);
				dispose();
			}
		}
		


}

