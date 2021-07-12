package server.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import detailorders.DetailOrdersDAO;
import detailorders.DetailOrdersVO;
import orders.OrdersDAO;
import orders.OrdersVO;

public class SettlementMain extends JFrame implements ActionListener, MouseListener{
	
	//date tab 테이블
	DefaultTableModel dateDTM;
	DefaultTableModel custDTM;
	DefaultTableModel prodDTM;
	
	JTable dateTable;
	JTable custTable;
	JTable prodTable;
	JScrollPane dateScroll;
	JScrollPane custScroll;
	JScrollPane prodScroll;
	
	JPanel settlePanel;
	JPanel totalPanel;
	JPanel custPanel;
	JTabbedPane tab;
	JButton closeBt;
	JButton backBt;
	JComboBox dateCombo;
	
	OrdersDAO odao = new OrdersDAO();
	DetailOrdersDAO dodao = new DetailOrdersDAO();
	
	public ServerMain serverMain;
	
	public SettlementMain() {
		this.setTitle("정산");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(100, 100, 550, 531);
		this.setLayout(new FlowLayout());
		setComponent();
		initSales("날짜");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void setComponent() {
		
		totalPanel = new JPanel();
		totalPanel.setLayout(null);
		closeBt = new JButton("\uB9C8\uAC10");
		backBt = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		tab = new JTabbedPane(JTabbedPane.TOP);
		
		String[] comboSel = {"날짜", "고객별", "제품별"};
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
		
		prodTable = new JTable();
		prodTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		prodTable.setBounds(24, 72, 426, 279);
		
		
		String[] dateCol = {"날짜", "매출"};
		dateDTM = new DefaultTableModel(dateCol, 0);
		dateTable = new JTable(dateDTM);
		dateScroll = new JScrollPane(dateTable);

		String[] custCol = {"고객", "매출"};
		custDTM = new DefaultTableModel(custCol, 0);
		custTable = new JTable(custDTM);
		custScroll = new JScrollPane(custTable);
		
		String[] prodCol = {"제품", "수량", "매출"};
		prodDTM = new DefaultTableModel(prodCol, 0);
		prodTable = new JTable(prodDTM);
		prodScroll = new JScrollPane(prodTable);

		totalPanel.add(closeBt);
		totalPanel.add(backBt);
		totalPanel.add(tab);
//		totalPanel.add(dateCombo);
		totalPanel.add(settlePanel);
//		settlePanel.add(dateTable);
		
		tab.addTab("날짜별", dateScroll);
		tab.addTab("고객별", custScroll);
		tab.addTab("제품별", prodScroll);
		 
		this.setContentPane(totalPanel);
		eventList();
	}

	public void eventList() {
		closeBt.addActionListener(this);
		backBt.addActionListener(this);
		tab.addMouseListener(this);
	}
	
	public void initSales(String kind) {
		if (!kind.equals("제품")) {
			ArrayList<OrdersVO> olist = odao.selectSales(kind);
			addRowSalesO(olist, kind);
		} else {
			ArrayList<DetailOrdersVO> dolist = dodao.selectSales();		
			addRowSalesDO(dolist, kind);
		}
	}
	
	public void addRowSalesO(ArrayList<OrdersVO> olist, String kind) {
		if (kind.equals("고객")) {
			String[] custColumns = new String[2];
			for (OrdersVO ovo : olist) {
				custColumns[0] = ovo.getCustID();
				custColumns[1] = ovo.getOprice() + "";
				
				custDTM.addRow(custColumns);
			}			
		} else if (kind.equals("날짜")) {
			String[] dateColumns = new String[2];
			for (OrdersVO ovo : olist) {
				dateColumns[0] = ovo.getOdate();
				dateColumns[1] = ovo.getOprice() + "";
				
				dateDTM.addRow(dateColumns);
			}	
		}
	}
	
	public void addRowSalesDO(ArrayList<DetailOrdersVO> dolist, String kind) {
		String[] prodColumns = new String[3];
		for (DetailOrdersVO dovo : dolist) {
			prodColumns[0] = dovo.getPname();
			prodColumns[1] = dovo.getCount() + "";
			prodColumns[2] = dovo.getPrice() + "";
			
			prodDTM.addRow(prodColumns);
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (tab.getSelectedIndex() == 0) {
			String kind = "날짜";
			dateDTM.setNumRows(0);
			initSales(kind);
		} else if (tab.getSelectedIndex() == 1) {
			String kind = "고객";
			custDTM.setNumRows(0);
			initSales(kind);
		} else if (tab.getSelectedIndex() == 2) {
			String kind = "제품";
			prodDTM.setNumRows(0);
			initSales(kind);
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
	
	public static void main(String[] args) {
		new SettlementMain();
	}
	
}
