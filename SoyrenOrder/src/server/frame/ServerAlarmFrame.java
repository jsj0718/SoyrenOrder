package server.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import client.frame.OrderFrame;
import detailorders.DetailOrdersDAO;
import detailorders.DetailOrdersVO;
import frame.ServerMainRR;
import orders.OrdersDAO;

public class ServerAlarmFrame extends JFrame implements ActionListener {

	JPanel alarmPanel;
	JPanel confirmPanel;
	JLabel custIdL;

	JButton yesBt;
	JButton noBt;

	JLabel custIdVal;

	DefaultTableModel confirmDTM;
	JTable confirmTable;
	JTable confirmTable_1;
	JScrollPane confirmScroll;

	ServerMainRR serverMain;
	OrderFrame order;
	
	OrdersDAO odao = new OrdersDAO();
	DetailOrdersDAO dodao = new DetailOrdersDAO();
	
	String id;
	int orderID;

	public ServerAlarmFrame(OrderFrame order, String id, int orderID) {
		
		this.order = order;
		this.id = id;
		this.orderID = orderID;

		this.setTitle("주문이 들어왔습니다.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 300, 400, 250);
		this.setLayout(null); // absoulute
		setComponent();
		initDetailOrdersTable();
		this.setVisible(true);

	}


	public ServerAlarmFrame(String id, int OrderID) {
		this.id = id;
		
		this.setTitle("주문이 들어왔습니다.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 300, 400, 250);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		this.setVisible(true);

	}

	public void setComponent() {
		alarmPanel = new JPanel();
		alarmPanel.setBounds(0, 0, 500, 500);
		alarmPanel.setLayout(null);

		custIdL = new JLabel("\uACE0\uAC1D\uBA85 : " + id);
		custIdL.setBounds(118, 35, 100, 15);
		alarmPanel.add(custIdL);

		custIdVal = new JLabel();
		custIdVal.setBounds(187, 35, 57, 15);
		alarmPanel.add(custIdVal);

		confirmPanel = new JPanel();
		confirmPanel.setBounds(42, 60, 311, 63);
//	      confirmPanel.setLayout(null);
		alarmPanel.add(confirmPanel);

		confirmTable = new JTable();
		confirmTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		confirmTable.setBounds(79, 58, 55, 42);
//	      confirmPanel.add(confirmTable);

		String[] confirmCol = { "음료", "옵션", "수량" };
		confirmDTM = new DefaultTableModel(confirmCol, 0);
		confirmPanel.setLayout(new BorderLayout(0, 0));
		confirmTable_1 = new JTable(confirmDTM);
		confirmTable_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.PINK, null, null, null));
		confirmScroll = new JScrollPane(confirmTable_1);
		confirmPanel.add(confirmScroll);

		yesBt = new JButton("\uC2B9\uC778");
		yesBt.setBounds(66, 156, 97, 23);
		alarmPanel.add(yesBt);

		noBt = new JButton("\uAC70\uC808");
		noBt.setBounds(233, 156, 97, 23);
		alarmPanel.add(noBt);

		alarmPanel.add(custIdL);

		alarmPanel.add(yesBt);
		alarmPanel.add(noBt);
		alarmPanel.add(custIdVal);

		this.setContentPane(alarmPanel);
		eventList();
	}

	public void eventList() {
		yesBt.addActionListener(this);
		noBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (yesBt == e.getSource()) {
			odao.updateFlag(orderID, "T");

			JOptionPane.showConfirmDialog(null, " 승인! 고객님께 전송됩니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.DEFAULT_OPTION);
			
			dispose();

		}
		if (noBt == e.getSource()) {
			dodao.deleteDetailOrders(orderID);
			odao.deleteOrder(orderID);

			JOptionPane.showConfirmDialog(null, " 거절! 고객님께 전송됩니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.DEFAULT_OPTION);
			dispose();
		}
	}
	
	// 테이블에 상세 주문 정보 담기
	public void initDetailOrdersTable() {
		ArrayList<DetailOrdersVO> dolist = dodao.selectDetailOrders(orderID);
		addRowDetailOrders(dolist);

	}
	
	public void addRowDetailOrders(ArrayList<DetailOrdersVO> dolist) {
		String[] confirmColumns = new String[3];
		for (DetailOrdersVO dovo : dolist) {
			confirmColumns[0] = dovo.getPname();
			confirmColumns[1] = dovo.getDoption();
			confirmColumns[2] = dovo.getCount() + "";
			
			confirmDTM.addRow(confirmColumns);
		}
	}

//	public static void main(String[] args) {
//		new ServerAlarmFrame();
//	}
}
