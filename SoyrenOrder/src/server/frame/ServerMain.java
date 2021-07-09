package server.frame;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class ServerMain extends JFrame implements ActionListener{

	
	
	//�������� ���� ��ư
	JButton settlementBt;

	//�г� ���� ���� ����
	JPanel totalPanel;
	JTabbedPane presentTab;
	JTabbedPane totalTab;


	JButton completeBt;
	
	
	JLabel titleL;
	
	JTable presentTable;
	JTable totalTable;
	DefaultTableModel presentDTM;
	DefaultTableModel totalDTM;
	JScrollPane presentScroll;
	JScrollPane totalScroll;
	
	
	public SettlementMain settle;
//	public SettleFrame settle;
	
	
	public ServerMain() {
		this.setTitle("�ֹ� ����");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(400, 200, 600, 600);
		this.setLayout(null);
		setComponent();
	
		this.setVisible(true);
	}

	public void setComponent() {
		titleL = new JLabel();
		
		
		totalPanel = new JPanel();
		totalPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		totalPanel.setLayout(null);
		
		presentTab = new JTabbedPane(JTabbedPane.TOP);
		presentTab.setBounds(24, 112, 400, 100);
		

		presentTable = new JTable();
		presentTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
	
		
		String[] presentCol = {"�ֹ���ȣ", "��ID", "����", "������"};
		presentDTM = new DefaultTableModel(presentCol, 100);
		presentTable = new JTable(presentDTM);
		presentScroll = new JScrollPane(presentTable);
		
		totalTab = new JTabbedPane(JTabbedPane.TOP);
		totalTab.setBounds(24, 250, 500, 300);
		
		totalTable = new JTable();
		totalTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		
		String[] totalCol = {"�ֹ���ȣ", "��ID", "����", "������"};
		totalDTM = new DefaultTableModel(totalCol,50);
		totalTable = new JTable(totalDTM);
		totalScroll = new JScrollPane(totalTable);
		
		settlementBt = new JButton();
		settlementBt.setText("����");
		
		completeBt = new JButton();
		completeBt.setText("�ϼ�");
		
		titleL.setText("��� ����");
		
//		totalPanel.setBounds(350, 350, 350, 350);
		presentTable.setBounds(24, 72, 200, 209);
		settlementBt.setBounds(500, 20, 60, 40);
		
		completeBt.setBounds(450, 150, 65, 50);
		
		
		titleL.setBounds(120, 50, 300, 60);
		titleL.setFont(new Font("HY�׷���M", Font.BOLD, 28));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		
		totalPanel.add(titleL);
		totalPanel.add(completeBt);
		totalPanel.add(settlementBt);
		
		totalPanel.add(presentTab);
		totalPanel.add(totalTab);
		totalTab.addTab("�� �ֹ�", totalScroll);
		presentTab.addTab("����", presentScroll);

		this.setContentPane(totalPanel);
		
		eventList();

	}
	public void eventList() {
		completeBt.addActionListener(this);
		settlementBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(completeBt == e.getSource()) {
			JOptionPane.showConfirmDialog(null, "���ᰡ �ϼ��Ǿ����ϴ�.","Ȯ��",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			
		}
		if(settlementBt == e.getSource()) {
			settle = new SettlementMain();
			dispose();
//			JOptionPane.showConfirmDialog(null, "���� �������� ����","Ȯ��",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			
		}
	}


 public static void main(String[] args) {
	new ServerMain();
}


}