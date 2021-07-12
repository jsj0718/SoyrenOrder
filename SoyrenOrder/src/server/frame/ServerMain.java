package server.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import detailorders.DetailOrdersDAO;
import detailorders.DetailOrdersVO;
import orders.OrdersDAO;
import orders.OrdersVO;


public class ServerMain extends JFrame implements ActionListener, MouseListener {

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
	
	int totalRow;
	
	DetailOrdersDAO dodao = new DetailOrdersDAO();
	OrdersDAO odao = new OrdersDAO();
	
	ServerAlarmFrame alarm;
	String id;
	int orderID;
	
	public SettlementMain settle;
//	public SettleFrame settle;
	
	
	public ServerMain() {
		this.setTitle("�ֹ� ����");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(400, 200, 600, 600);
		getContentPane().setLayout(null);
		setComponent();
		
		initOrdersTable();
		this.setVisible(true);
		
	}

	public void setComponent() {
		titleL = new JLabel();
		titleL.setForeground(new Color(255, 255, 255));
		titleL.setBounds(124, 44, 300, 60);
		
		
		totalPanel = new JPanel();
		totalPanel.setBackground(new Color(107, 142, 35));
		totalPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(176, 196, 222)));
		
		presentTab = new JTabbedPane(JTabbedPane.TOP);
		presentTab.setBounds(24, 112, 400, 159);
		

		presentTable = new JTable();
		presentTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
	
		
		String[] presentCol = {"����", "�ɼ�", "������"};
		presentDTM = new DefaultTableModel(presentCol, 0);
		presentTable = new JTable(presentDTM);
		presentScroll = new JScrollPane(presentTable);
		
		totalTab = new JTabbedPane(JTabbedPane.TOP);
		totalTab.setBounds(24, 301, 500, 249);
		
		totalTable = new JTable();
		totalTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		
		String[] totalCol = {"�ֹ���ȣ", "��ID", "�ֹ���¥"};
		totalDTM = new DefaultTableModel(totalCol, 0);
		totalTable = new JTable(totalDTM);
		totalScroll = new JScrollPane(totalTable);
		
		settlementBt = new JButton();
		settlementBt.setBackground(new Color(255, 255, 240));
		settlementBt.setFont(new Font("����", Font.PLAIN, 14));
		settlementBt.setBounds(490, 44, 73, 40);
		settlementBt.setText("����");
		settlementBt.setBorderPainted(false);
		
		completeBt = new JButton();
		completeBt.setBackground(new Color(255, 255, 240));
		completeBt.setFont(new Font("����", Font.PLAIN, 13));
		completeBt.setBounds(431, 138, 73, 47);
		completeBt.setText("�ϼ�");
		completeBt.setBorderPainted(false);
		
		titleL.setText("��� ����");
		
//		totalPanel.setBounds(350, 350, 350, 350);
		presentTable.setBounds(24, 72, 200, 209);
		totalPanel.setLayout(null);
		titleL.setFont(new Font("���� ���", Font.BOLD, 28));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		
		totalPanel.add(titleL);
		totalPanel.add(completeBt);
		totalPanel.add(settlementBt);
		
		totalPanel.add(presentTab);
		totalPanel.add(totalTab);
		totalTab.addTab("�ֹ�", totalScroll);
		presentTab.addTab("�� �ֹ�", presentScroll);

		this.setContentPane(totalPanel);
		
		eventList();

	}
	
	// ���ΰ�ħ
	public void refresh() {
		this.setContentPane(totalPanel);
		totalPanel.revalidate();
		totalPanel.repaint();
	}
	
	// ���̺� �� �ֹ� ���� ���
	public void initDetailOrdersTable(int orderNumber) {
		ArrayList<DetailOrdersVO> dolist = dodao.selectDetailOrders(orderNumber);
		addRowDetailOrders(dolist);

	}
	
	public void addRowDetailOrders(ArrayList<DetailOrdersVO> dolist) {
		String[] presentColumns = new String[3];
		for (DetailOrdersVO dovo : dolist) {
			presentColumns[0] = dovo.getPname();
			presentColumns[1] = dovo.getDoption();
			presentColumns[2] = dovo.getCount() + "";
			
			presentDTM.addRow(presentColumns);
		}
	}
	
	public void initOrdersTable() {
		ArrayList<OrdersVO> olist = odao.selectOrders();
		addRowOrders(olist);

	}
	
	public void addRowOrders(ArrayList<OrdersVO> olist) {
		String[] totalColumns = new String[3];
		for (OrdersVO ovo : olist) {
			totalColumns[0] = ovo.getOrderID() + "";
			totalColumns[1] = ovo.getCustID();
			totalColumns[2] = ovo.getOdate();
			
			totalDTM.addRow(totalColumns);
		}
	}
		
	public void eventList() {
		completeBt.addActionListener(this);
		settlementBt.addActionListener(this);
		totalTable.addMouseListener(this);
		totalTab.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(completeBt == e.getSource()) {
			totalRow = totalTable.getSelectedRow();
			if (totalRow != -1) {
				int orderNumber = Integer.parseInt(totalTable.getValueAt(totalRow, 0).toString());
				odao.updateFlag(orderNumber, "F");
				presentDTM.setNumRows(0);
				totalDTM.setNumRows(0);
				initOrdersTable();
				JOptionPane.showConfirmDialog(null, "���ᰡ �ϼ��Ǿ����ϴ�.","Ȯ��",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);				
			} else {
				JOptionPane.showConfirmDialog(null, "�ֹ��� �����ϼ���.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			}
			
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

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if (totalTable == e.getSource()) {
		presentDTM.setNumRows(0);
		totalRow = totalTable.getSelectedRow();
		int orderNumber = Integer.parseInt(totalTable.getValueAt(totalRow, 0).toString());
		initDetailOrdersTable(orderNumber);
	}
	
	else if (totalTab == e.getSource()) {
		totalDTM.setNumRows(0);
		initOrdersTable();
	}
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


}