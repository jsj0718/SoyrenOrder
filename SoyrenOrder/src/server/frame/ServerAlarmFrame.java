package server.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import message.AlarmMessage;
import orders.OrdersDAO;
import server.program.ServerHandler;

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

	ServerMain serverMain;
	OrderFrame order;

	OrdersDAO odao = new OrdersDAO();
	DetailOrdersDAO dodao = new DetailOrdersDAO();

	String id;
	int orderID;

	/**
	 * @wbp.parser.constructor
	 */
	public ServerAlarmFrame(OrderFrame order, String id, int orderID) {
		this.order = order;
		this.id = id;
		this.orderID = orderID;

		this.setTitle("주문이 들어왔습니다.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 300, 400, 250);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		initDetailOrdersTable();
		this.setVisible(true);

	}

	public ServerAlarmFrame(String id, int orderID) {
		this.id = id;
		this.orderID = orderID;

		this.setTitle("주문이 들어왔습니다.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 300, 400, 250);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		initDetailOrdersTable();
		this.setVisible(true);

	}

	public void setComponent() {
		alarmPanel = new JPanel();
		alarmPanel.setBackground(new Color(240, 255, 240));
		alarmPanel.setBounds(0, 0, 500, 500);
		alarmPanel.setLayout(null);

		custIdL = new JLabel("\uACE0\uAC1D\uBA85 : " + id);
		custIdL.setBounds(130, 24, 126, 15);
		alarmPanel.add(custIdL);

		custIdVal = new JLabel();
		custIdVal.setBounds(187, 35, 57, 15);
		alarmPanel.add(custIdVal);

		confirmPanel = new JPanel();
		confirmPanel.setBounds(42, 60, 311, 99);
//	      confirmPanel.setLayout(null);
		alarmPanel.add(confirmPanel);

		confirmTable = new JTable();
		confirmTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		confirmTable.setBounds(79, 58, 55, 42);
//	      confirmPanel.add(confirmTable);

		String[] confirmCol = { "음료", "옵션", "수량" };
		confirmDTM = new DefaultTableModel(confirmCol, 0);
		confirmPanel.setLayout(null);
		confirmTable_1 = new JTable(confirmDTM);
		confirmTable_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.PINK, null, null, null));
		confirmScroll = new JScrollPane(confirmTable_1);
		confirmScroll.setBounds(0, 0, 311, 92);
		confirmPanel.add(confirmScroll);

		yesBt = new JButton("\uC2B9\uC778");
		yesBt.setBackground(new Color(255, 255, 255));
		yesBt.setBounds(68, 169, 97, 23);
		yesBt.setBorderPainted(false);
		alarmPanel.add(yesBt);

		noBt = new JButton("\uAC70\uC808");
		noBt.setBackground(new Color(255, 255, 255));
		noBt.setBounds(235, 169, 97, 23);
		noBt.setBorderPainted(false);
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

			AlarmMessage amsg = new AlarmMessage();
			amsg.setState(1);

			try {
				ServerHandler.oos.writeObject(amsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showConfirmDialog(null, " 승인!  고객님께 전송됩니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.DEFAULT_OPTION);

			dispose();

		}
		if (noBt == e.getSource()) {
			dodao.deleteDetailOrders(orderID);
			odao.deleteOrder(orderID);

			AlarmMessage amsg = new AlarmMessage();
			amsg.setState(2);

			try {
				ServerHandler.oos.writeObject(amsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
