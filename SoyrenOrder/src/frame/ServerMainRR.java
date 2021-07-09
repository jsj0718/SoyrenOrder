package frame;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import orders.OrdersVO;


public class ServerMainRR extends JFrame implements ActionListener, MouseListener{

	
	
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
	
	int totalRow; //��ü������ �� 
	
	public SettlementMain settle;
//	public SettleFrame settle;
	
	
	public ServerMainRR() {
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
	
		
		String[] presentCol = {"�ֹ���ȣ", "����", "������"};
		presentDTM = new DefaultTableModel(presentCol, 100);
		presentTable = new JTable(presentDTM);
		presentScroll = new JScrollPane(presentTable);
		
		totalTab = new JTabbedPane(JTabbedPane.TOP);
		totalTab.setBounds(24, 250, 500, 300);
		
		totalTable = new JTable();
		totalTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		
		String[] totalCol = {"�ֹ���ȣ", "��ID"};
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
	//���� ������ �� �׸�.
//	public void addRowOrder(ArrayList<DetailOrdersVO> dlist) {
//		String[] DetailOrdersCol = new String[3];
//		for(DetailOrdersVO dvo : dlist) {
//			DetailOrdersCol[0]= String.valueOf(dvo.getOrderID());
//			DetailOrdersCol[1]= String.valueOf(dvo.getProdID());
//			DetailOrdersCol[2]= String.valueOf(dvo.getCount());
//		}
//	}
	
//	//���ֹ������� �� �׸�
//	
//	public void addRowOrder(ArrayList<OrdersVO> olist) {
//		String[] OrdersCol = new String[2];
//		for(OrdersVO ovo : olist) {
//			OrdersCol[0] = String.valueOf(ovo.getOrderID());
//			OrdersCol[1] = String.valueOf(ovo.getCustID());
//		}
//	}
	
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
	
	



@Override
public void mouseClicked(MouseEvent e) {
	if(totalTable == e.getSource()) {
		//totaltable�� �� ���ý� msg�� orderId�� custId�� ����.
		OrderMessage omsg = new OrderMessage();
		OrdersVO ovo = new OrdersVO();
		totalRow = totalTable.getSelectedRow();
		
		ovo.setOrderId(Integer.parseInt(orderId.getText()));
		ovo.setCustID(Integer.parseInt(custId.getText()));
		
		//�޽����� ������
		omsg.setState(1);
		omsg.set(ovo);
		//���۵� ������ CustId�� �̿��Ͽ� OrderMessage�� �ٸ� ������ �����
		DetailOrderVO dvo = new DetailOrderVO();
		dvo.getOrderId(Integer.parseInt(orderId.getText()));
		dvo.getProdId(Integer.parseInt(prodId.getText()));
		dvo.getCount(Integer.parseInt(count.getText()));
		//Ʈ����ĳġ �������.
		
		
//		presentTable.addRowSelectionInterval(0, 1);		
//		totalTable.removeRowSelectionInterval(0, 1);
//		totalTable.setVisible(true);
		System.out.println(totalRow);
	}
	
	
	
}

@Override
public void mousePressed(MouseEvent e) {
}

@Override
public void mouseReleased(MouseEvent e) {
}

@Override
public void mouseEntered(MouseEvent e) {
}

@Override
public void mouseExited(MouseEvent e) {
}


public static void main(String[] args) {
	new ServerMainRR();
}




}