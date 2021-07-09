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

	
	
	//정산으로 가는 버튼
	JButton settlementBt;

	//패널 내부 오더 내역
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
		this.setTitle("주문 음료");
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
	
		
		String[] presentCol = {"주문번호", "고객ID", "음료", "제조량"};
		presentDTM = new DefaultTableModel(presentCol, 100);
		presentTable = new JTable(presentDTM);
		presentScroll = new JScrollPane(presentTable);
		
		totalTab = new JTabbedPane(JTabbedPane.TOP);
		totalTab.setBounds(24, 250, 500, 300);
		
		totalTable = new JTable();
		totalTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		
		String[] totalCol = {"주문번호", "고객ID", "음료", "제조량"};
		totalDTM = new DefaultTableModel(totalCol,50);
		totalTable = new JTable(totalDTM);
		totalScroll = new JScrollPane(totalTable);
		
		settlementBt = new JButton();
		settlementBt.setText("정산");
		
		completeBt = new JButton();
		completeBt.setText("완성");
		
		titleL.setText("대기 음료");
		
//		totalPanel.setBounds(350, 350, 350, 350);
		presentTable.setBounds(24, 72, 200, 209);
		settlementBt.setBounds(500, 20, 60, 40);
		
		completeBt.setBounds(450, 150, 65, 50);
		
		
		titleL.setBounds(120, 50, 300, 60);
		titleL.setFont(new Font("HY그래픽M", Font.BOLD, 28));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		
		totalPanel.add(titleL);
		totalPanel.add(completeBt);
		totalPanel.add(settlementBt);
		
		totalPanel.add(presentTab);
		totalPanel.add(totalTab);
		totalTab.addTab("총 주문", totalScroll);
		presentTab.addTab("현재", presentScroll);

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
			JOptionPane.showConfirmDialog(null, "음료가 완성되었습니다.","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			
		}
		if(settlementBt == e.getSource()) {
			settle = new SettlementMain();
			dispose();
//			JOptionPane.showConfirmDialog(null, "내일 만들어야함 흘흙","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			
		}
	}


 public static void main(String[] args) {
	new ServerMain();
}


}