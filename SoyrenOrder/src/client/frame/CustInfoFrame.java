package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

<<<<<<< HEAD
import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;

public class CustInfoFrame extends JFrame implements ActionListener{
	
	JPanel infoPanel; // ȸ������
	JLabel infoL, idL, pwdL, nameL, phoneL; // ���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ
	JButton updateBt, pwdUpdateBt, deleteBt; // ����, ��й�ȣ����, Ż��
	JTextField idField, pwdField, nameField, phoneField; // ���̵� ��й�ȣ �̸� ��ȭ��ȣ

	String id;
	
	CustomerVO cvo;
	CustomerDAO cdao;
	public MainFrame main;
	public InfoUpdateFrame infoUpdate;
	public LoginFrame login;
	public PwdUpdateFrame pwdUpdate;
	
	public CustInfoFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;

		this.setTitle("�� ����");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100,100,350,400);
		this.setLayout(null);
		this.setVisible(true);
		
		CustomerVO cvo = initTableRequest();
		setComponent(cvo);
		
	}
	
	public void setComponent(CustomerVO cvo) {
		
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		infoL = new JLabel();
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();
		
		idField = new JTextField(id);
		pwdField = new JTextField(cvo.getPwd());
		nameField = new JTextField(cvo.getCname());
		phoneField = new JTextField(cvo.getPhone());
		
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
	
	public CustomerVO initTableRequest() {
		cdao = new CustomerDAO();
		CustomerVO cvo = cdao.select(id);
		return cvo;
	}
	
//	public void select(CustomerDAO cdao) {
//		CustomerMessage cmsg = new CustomerMessage();
//		cmsg.setState(4);
//		try {
//			ClientHandler.oos.writeObject(cmsg);
//			ClientHandler.oos.flush();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
//	public void initTableResponse(Message msg) {
//		if (msg instanceof CustomerMessage) {
//			CustomerMessage cmsg = (CustomerMessage) msg;
//			add
//		}
//	}
	
	public void eventList() {
		
		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		  //����������ư
		if(updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();
			
		 //Ż���ư
		} else if (deleteBt == e.getSource()) {
			
			int result = JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?","ȸ��Ż��",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				CustomerDAO cdao = new CustomerDAO();
				CustomerVO cvo = new CustomerVO();
				cvo.setCustID(id);
				cdao.delete(cvo.getCustID());
				JOptionPane.showMessageDialog(null, "ȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
				login = new LoginFrame();
				dispose();
				
			}else {
				
			}
			
		} else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this);
		}
	}
//	public static void main(String[] args) {
//		new CustInfoFrame(null, "2");
//	}
=======
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
				
				cdao.delete(id);
				JOptionPane.showConfirmDialog(null, "ȸ��Ż�� �Ǿ����ϴ�", "Ȯ��", JOptionPane.DEFAULT_OPTION,
						JOptionPane.YES_NO_CANCEL_OPTION);
				dispose();
				
				
			}

		}
	}

>>>>>>> refs/remotes/origin/kangjisoo
}
