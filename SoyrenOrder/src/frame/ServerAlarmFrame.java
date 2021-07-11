package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;
import message.OrdersMessage;
import orders.OrdersVO;
import server.program.ServerHandler;

public class ServerAlarmFrame extends JFrame implements ActionListener{

	JPanel alarmPanel;
	JLabel custIdL;
	JLabel prodNameL;
	JLabel countL;
	JButton yesBt;
	JButton noBt;
	
	JLabel custIdVal;
	JLabel prodNameVal;
	JLabel countVal;
	
	public ServerMainRR serverMain;
	
public ServerAlarmFrame() {
		
		this.setTitle("�ֹ��� ���Խ��ϴ�.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 300, 400, 250);
		this.setLayout(null); //absoulute
		setComponent();
		this.setVisible(true);
		
		
	}
	
	public void setComponent() {
		alarmPanel = new JPanel();
		alarmPanel.setBounds(0, 0, 500, 500);
		alarmPanel.setLayout(null);
		
		custIdL = new JLabel("\uACE0\uAC1D\uBA85 : ");
		custIdL.setBounds(138, 26, 57, 15);
		alarmPanel.add(custIdL);
		
		custIdVal = new JLabel();
		custIdL.setBounds(210, 26, 57, 15);
		alarmPanel.add(custIdVal);
		
		prodNameL = new JLabel("beverage:");
		prodNameL.setBounds(68, 76, 70, 21);
		alarmPanel.add(prodNameL);

		prodNameVal = new JLabel();
		prodNameVal.setBounds(120, 76, 70, 21);
		alarmPanel.add(prodNameVal);
		
		countL = new JLabel("\uC794");
		countL.setBounds(315, 76, 30, 21);
		alarmPanel.add(countL);

		countVal = new JLabel();
		countVal.setBounds(215, 76, 30, 21);
		alarmPanel.add(countVal);
		
		yesBt = new JButton("\uC2B9\uC778");
		yesBt.setBounds(70, 133, 97, 23);
		alarmPanel.add(yesBt);
		
		 noBt = new JButton("\uAC70\uC808");
		noBt.setBounds(229, 133, 97, 23);
		alarmPanel.add(noBt);
		
		alarmPanel.add(custIdL);
		alarmPanel.add(prodNameL);
		alarmPanel.add(countL);
		alarmPanel.add(yesBt);
		alarmPanel.add(noBt);
		
		alarmPanel.add(custIdVal);
		alarmPanel.add(prodNameVal);
		alarmPanel.add(countVal);
		
		this.setContentPane(alarmPanel);
		eventList();
	}
	
	
	
	
	
	
	
	
	
	public void eventList() {
		yesBt.addActionListener(this);
		noBt.addActionListener(this);
	}
	
	
	
	
	//�´°Ŵ�..?
	@Override
	public void actionPerformed(ActionEvent e) {
		if( yesBt == e.getSource()) {
			OrdersMessage omsg = new OrdersMessage();
			OrdersVO ovo = new OrdersVO();
			ovo.setOrderFlag("T");
			omsg.setState(2);
			omsg.setOvo(ovo);
			
			JOptionPane.showConfirmDialog(null, " ����! ���Բ� ���۵˴ϴ�","Ȯ��",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
			serverMain = new ServerMainRR();
			dispose();
			
			try {
				ServerHandler.oos.writeObject(omsg);
				ServerHandler.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
		if(noBt ==e.getSource()) {
			OrdersMessage omsg = new OrdersMessage();
			OrdersVO ovo = new OrdersVO();
			ovo.setOrderFlag("F");
			omsg.setState(2);
			omsg.setOvo(ovo);
			
			
			JOptionPane.showConfirmDialog(null, " ����! ���Բ� ���۵˴ϴ�","Ȯ��",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
			dispose();
		}
	}
	
	
	public static void main(String[] args) {
		new ServerAlarmFrame();
	}

}
