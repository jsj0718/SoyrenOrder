package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ServerAlarmFrame extends JFrame implements ActionListener{

	JPanel alarmPanel;
	JLabel custIdL;
	JLabel prodNameL;
	JLabel countL;
	JButton yesBt;
	JButton noBt;
	
	public ServerMainRR serverMain;
	
public ServerAlarmFrame() {
		
		this.setTitle("주문이 들어왔습니다.");
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
		
		prodNameL = new JLabel("beverage:");
		prodNameL.setBounds(68, 76, 70, 21);
		alarmPanel.add(prodNameL);
		
		 countL = new JLabel("\uC794");
		countL.setBounds(315, 76, 30, 21);
		alarmPanel.add(countL);
		
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
		
		this.setContentPane(alarmPanel);
		eventList();
	}
	
	
	
	
	
	
	
	
	
	public void eventList() {
		yesBt.addActionListener(this);
		noBt.addActionListener(this);
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( yesBt == e.getSource()) {
			JOptionPane.showConfirmDialog(null, " 승인! 고객님께 전송됩니다","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
			serverMain = new ServerMainRR();
			dispose();
		}
		if(noBt ==e.getSource()) {
			JOptionPane.showConfirmDialog(null, " 거절! 고객님께 전송됩니다","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
			dispose();
		}
	}
	
	
	public static void main(String[] args) {
		new ServerAlarmFrame();
	}

}
