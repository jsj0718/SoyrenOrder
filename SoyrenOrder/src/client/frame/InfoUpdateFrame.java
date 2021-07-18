package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;
import message.Message;

public class InfoUpdateFrame extends JFrame implements ActionListener {

	JPanel infoUpdatePanel; // ���� ����
	JLabel infoUpdateL, idL, nameL, phoneL; // ���̵�, �̸�, ��ȭ��ȣ
	JTextField idField, nameField, phoneField; // ���̵�,�̸�,��ȭ��ȣ
	JButton updateBt; // ����
	JButton cancelBt;

	String id;
	
	public CustInfoFrame custInfo;

	public InfoUpdateFrame(CustInfoFrame custInfo, String id) {
		this.custInfo = custInfo;
		this.id = id;

		this.setTitle("���� ����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 250, 350);
		getContentPane().setLayout(null);
		setComponent();
		initRequest();
		this.setVisible(true);

	}


	public void setComponent() {

		infoUpdatePanel = new JPanel();
		infoUpdatePanel.setBackground(new Color(238, 232, 170));
		infoUpdatePanel.setLayout(null);

		infoUpdateL = new JLabel();
		infoUpdateL.setFont(new Font("���� ���", Font.BOLD, 14));
		idL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();

		idField = new JTextField(id);
		idField.setEditable(false);
		nameField = new JTextField();
		phoneField = new JTextField();

		updateBt = new JButton();
		updateBt.setBackground(new Color(255, 255, 240));
		updateBt.setForeground(new Color(0, 0, 0));

		infoUpdateL.setText("���� ����");
		idL.setText("���̵�");
		nameL.setText("�̸�");
		phoneL.setText("��ȭ��ȣ");

		updateBt.setText("Ȯ��");
		updateBt.setBorderPainted(false);

		infoUpdateL.setHorizontalAlignment(JLabel.CENTER);

		infoUpdateL.setBounds(48, 10, 130, 38);
		idL.setBounds(41, 58, 90, 15);
		nameL.setBounds(41, 116, 90, 15);
		phoneL.setBounds(41, 174, 90, 15);

		idField.setBounds(41, 83, 148, 23);
		nameField.setBounds(41, 141, 148, 23);
		phoneField.setBounds(41, 199, 148, 23);

		updateBt.setBounds(41, 248, 66, 23);

		infoUpdatePanel.add(infoUpdateL);
		infoUpdatePanel.add(idL);
		infoUpdatePanel.add(nameL);
		infoUpdatePanel.add(phoneL);
		infoUpdatePanel.add(idField);
		infoUpdatePanel.add(nameField);
		infoUpdatePanel.add(phoneField);
		infoUpdatePanel.add(updateBt);

		this.setContentPane(infoUpdatePanel);
		
		cancelBt = new JButton();
		cancelBt.setText("\uCDE8\uC18C");
		cancelBt.setForeground(Color.BLACK);
		cancelBt.setBorderPainted(false);
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.setBounds(123, 248, 66, 23);
		infoUpdatePanel.add(cancelBt);

		eventList();
	}

	// �ʱⵥ���� ��û
	public void initRequest() {
		customerSelect();
	}

	// �ʱⵥ���� ����
	public void initResponse(Message msg) {
		if (msg instanceof CustomerMessage) {
			CustomerMessage cmsg = (CustomerMessage) msg;
			addTextFieldCustInfo(cmsg.getCvo());
		}
	}

	// �� ���� ��ȸ
	public void customerSelect() {
		CustomerMessage cmsg = new CustomerMessage();
		CustomerVO cvo = new CustomerVO();
		cvo.setCustID(id);
		cmsg.setState(9);
		cmsg.setCvo(cvo);

		try {
			ClientHandler.oos.writeObject(cmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
	}

	// �� ���� �ֱ�
	public void addTextFieldCustInfo(CustomerVO cvo) {
		nameField.setText(cvo.getCname());
		phoneField.setText(cvo.getPhone());
	}

	public void eventList() {
		updateBt.addActionListener(this);
		cancelBt.addActionListener(this);
	}
	
	// �� ���� ������Ʈ
	public void customerUpdate(CustomerMessage cmsg) {
		if (cmsg.getResult() > 0) {
			JOptionPane.showMessageDialog(null, "���������� �Ϸ�Ǿ����ϴ�.");

			infoUpdatePanel.removeAll();
			custInfo.nameField.setText(cmsg.getCvo().getCname());
			custInfo.phoneField.setText(cmsg.getCvo().getPhone());
			custInfo.setVisible(true);
			dispose();	
		} else {
			JOptionPane.showMessageDialog(null, "���������� �����߽��ϴ�.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (updateBt == e.getSource()) {

			if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
				return;
			}

			if (phoneField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�ڵ��� ��ȣ�� �Է����ּ���.");
				return;
			}

//			int phoneCheck = 0;
//			String[] check = new String[phoneField.getText().length()];
//			check = phoneField.getText().split("");
//			for (int i = 0; i < phoneField.getText().length(); i++) {
//				if (!check[3].equals("-") || !check[8].equals("-") || phoneField.getText().length() != 13) {
//					phoneCheck = 1;
//				}
//			}
//			if (phoneCheck == 1) {
//				JOptionPane.showConfirmDialog(null, "�ڵ�����ȣ Ȯ���� �ʿ��մϴ�.\n����) 010-0000-0000", "���",
//						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
//				return;
//			}

			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();

			cvo.setCustID(idField.getText());
			cvo.setCname(nameField.getText());
			cvo.setPhone(phoneField.getText());
			
			cmsg.setState(10);
			cmsg.setCvo(cvo);

			try {
				ClientHandler.oos.writeObject(cmsg);
				ClientHandler.oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
			
		} else if (cancelBt == e.getSource()) {
			infoUpdatePanel.removeAll();
			custInfo.setVisible(true);
			dispose();	
		}
	}

}
