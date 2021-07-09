package server.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class SettlementMain extends JFrame implements ActionListener{
	
	//date tab 테이블
	DefaultTableModel dateDTM;
	DefaultTableModel custDTM;
	
	JTable dateTable;
	JTable custTable;
	JScrollPane dateScroll;
	JScrollPane custScroll;
	
	
	JPanel totalPanel;
	JButton closeBt;
	JButton backBt;
	JTabbedPane tab;
	JPanel settlePanel;
	JComboBox dateCombo;
	JPanel custPanel;
	
	public ServerMain serverMain;
	
	public SettlementMain() {
		this.setTitle("정산");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(100, 100, 550, 531);
		this.setLayout(new FlowLayout());
		setComponent();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void setComponent() {
		
		totalPanel = new JPanel();
		totalPanel.setLayout(null);
		closeBt = new JButton("\uB9C8\uAC10");
		backBt = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		tab = new JTabbedPane(JTabbedPane.TOP);
		
		String[] comboSel = {"일별", "월별", "고객별"};
		dateCombo = new JComboBox(comboSel);

		settlePanel = new JPanel();
		settlePanel.setLayout(null);
		custPanel = new JPanel();
		custPanel.setLayout(null);

		closeBt.setBounds(437, 10, 97, 23);
		backBt.setBounds(328, 10, 97, 23);
		tab.setBounds(25, 43, 482, 417);
		dateCombo.setBounds(170, 40, 173, 21);
		
//		totalPanel.setBounds(0, 0, 534, 492);
		
		totalPanel.setBackground(new Color(107, 142, 35));
		totalPanel.setForeground(new Color(95, 158, 160));
		
		settlePanel.setBackground(new Color(240, 255, 240));
		settlePanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		dateTable = new JTable();
		dateTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		dateTable.setBounds(24, 72, 426, 279);

		custTable = new JTable();
		custTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		custTable.setBounds(24, 72, 426, 279);
		
		
		String[] dateCol = {"Date", "totalPrice", "누적금액", "ProdId"};
		dateDTM = new DefaultTableModel(dateCol,0);
		dateTable = new JTable(dateDTM);
		dateScroll = new JScrollPane(dateTable);

		String[] custCol = {"CustId", "ProdId", "totalPrice"};
		custDTM = new DefaultTableModel(custCol,0);
		custTable = new JTable(custDTM);
		custScroll = new JScrollPane(custTable);
		
		
		
		
		
		totalPanel.add(closeBt);
		totalPanel.add(backBt);
		totalPanel.add(tab);
		totalPanel.add(dateCombo);
		totalPanel.add(settlePanel);
//		settlePanel.add(dateTable);
		
		tab.addTab("날짜별", dateScroll);
		tab.addTab("고객별", custScroll);
		 
		this.setContentPane(totalPanel);
		eventList();
	}

	public void eventList() {
		closeBt.addActionListener(this);
		backBt.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(closeBt == e.getSource()) {
			JOptionPane.showConfirmDialog(null, "오늘도 수고하셨습니다. 마감!", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);
			dispose();
		}
		if(backBt == e.getSource()) {
			serverMain = new ServerMain();
			dispose();
			
		}
	}
	public static void main(String[] args) {
		new SettlementMain();
	}
	
}
