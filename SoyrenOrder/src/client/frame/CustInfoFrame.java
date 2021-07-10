package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import customer.CustomerDAO;
import customer.CustomerVO;

public class CustInfoFrame extends JFrame implements ActionListener{
	
	JPanel infoPanel; // ȸ������
	JLabel infoL, idL, pwdL, nameL, phoneL; // ���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ
	JButton updateBt, pwdUpdateBt, deleteBt; // ����, ��й�ȣ����, Ż��
	JTextField idField, pwdField, nameField, phoneField; // ���̵� ��й�ȣ �̸� ��ȭ��ȣ

	String id;
	CustomerDAO cdao;
	
	public MainFrame main;
	public infoUpdateFrame infoUpdate;
	
	public CustInfoFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;
		
		this.setTitle("�� ����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,350,400);
		this.setLayout(null);
		this.setVisible(true);
		
		setComponent();
	}
	
	public void setComponent() {
		
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		infoL = new JLabel();
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField();
		nameField = new JTextField();
		phoneField = new JTextField();
		
		updateBt = new JButton();
		deleteBt = new JButton();
		pwdUpdateBt = new JButton();
		
		infoL.setText("�� ����");
		idL.setText("���̵�");
		pwdL.setText("��й�ȣ");
		nameL.setText("�̸�");
		phoneL.setText("��ȭ��ȣ");
		
		updateBt.setText("��������");
		deleteBt.setText("Ż��");
		pwdUpdateBt.setText("��й�ȣ����");
		
		infoL.setHorizontalAlignment(JLabel.CENTER);
		
		infoL.setBounds(102, 10, 130, 23);
		idL.setBounds(12, 32, 90, 15);
		pwdL.setBounds(12,90,90,15);
		nameL.setBounds(12, 148, 90, 15);
		phoneL.setBounds(12, 216, 90, 15);
		
		idField.setBounds(12, 57, 148, 23);
		pwdField.setBounds(12,115,148,23);
		nameField.setBounds(12, 173, 148, 23);
		phoneField.setBounds(12, 241, 148, 23);
		
		updateBt.setBounds(12, 328, 116, 23); // ��������
		deleteBt.setBounds(206,328,116,23); // Ż��
		pwdUpdateBt.setBounds(206,115,116,23);
		
		infoPanel.add(infoL);
		infoPanel.add(idL);
		infoPanel.add(pwdL);
		infoPanel.add(nameL);
		infoPanel.add(phoneL);
		infoPanel.add(idField);
		infoPanel.add(pwdField);
		infoPanel.add(nameField);
		infoPanel.add(phoneField);
		infoPanel.add(updateBt);
		infoPanel.add(deleteBt);
		infoPanel.add(pwdUpdateBt);
		
		this.setContentPane(infoPanel);
		
		eventList();
	}
		
	public void eventList() {
		
		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(updateBt == e.getSource()) {
			infoUpdate = new infoUpdateFrame();
			
		} else if (deleteBt == e.getSource()) {
			
			int result = JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "ȸ��Ż��", JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.CLOSED_OPTION) {
				// ����ڰ� yes, no ���þ��� â�� ���� ���
			}else if(result == JOptionPane.NO_OPTION){
				// ����ڰ� no�� �������� ���
			}else {
				// ����ڰ� yes�� �������� ���
				CustomerDAO cdao = new CustomerDAO();
//				CustomerVO cvo = new CustomerVO();
				
				cdao.delete(id);
				JOptionPane.showConfirmDialog(null, "ȸ��Ż�� �Ǿ����ϴ�", "Ȯ��", JOptionPane.DEFAULT_OPTION,
						JOptionPane.YES_NO_CANCEL_OPTION);
				dispose();
				
				
			}
			
//			String custID = idField.getText();
//			public void mouseClicked(MouseEvent e) {
//				if (infoBt == e.getSource()) {
//					bookRow = bookTable.getSelectedRow(); // ���õ� ���� �����´�. bookrow��
//
//					String bookId = bookTable.getValueAt(bookRow, 0).toString();
//					String bookName = bookTable.getValueAt(bookRow, 1).toString();
//					String publisher = bookTable.getValueAt(bookRow, 2).toString();
//					String price = bookTable.getValueAt(bookRow, 3).toString();
//			
//			bookIdVal.setText(bookId);
//			bookNameVal.setText(bookName);
//			publisherVal.setText(publisher);
//			priceVal.setText(price);
		}
	}

}
