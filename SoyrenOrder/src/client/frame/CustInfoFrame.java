package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import client.program.ClientHandler;
import customer.CustomerDAO;
import customer.CustomerVO;
import message.CustomerMessage;
import java.awt.Font;
import java.awt.Color;

public class CustInfoFrame extends JFrame implements ActionListener {

	JPanel infoPanel; // ȸ������
	JLabel infoL, idL, pwdL, nameL, phoneL; // ���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ
	JButton updateBt, pwdUpdateBt, deleteBt, commitBt; // ����, ��й�ȣ����, Ż��
	JTextField idField, pwdField, nameField, phoneField; // ���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ

	String id, pwd;

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
		getContentPane().setLayout(null);
		this.setVisible(true);
		
		CustomerVO cvo = initTableRequest();
		setComponent(cvo);
	}
		
		public CustInfoFrame(InfoUpdateFrame infoUpdate, String id) {
			this.infoUpdate = infoUpdate;
			this.id = id;

			this.setTitle("�� ����");
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setBounds(100,100,350,400);
			getContentPane().setLayout(null);
			this.setVisible(true);
			
			CustomerVO cvo = initTableRequest();
			setComponent(cvo);
		}

	/**
	 * @wbp.parser.constructor
	 */
	public CustInfoFrame(PwdUpdateFrame pwdUpdateFrame, String id, String pwd) {
		this.pwdUpdate = pwdUpdateFrame;
		this.id = id;
		this.pwd = pwd;

		this.setTitle("�� ����");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100,100,350,400);
		getContentPane().setLayout(null);
		this.setVisible(true);
		
		CustomerVO cvo = initTableRequest();
		setComponent(cvo);
		}

	public void setComponent(CustomerVO cvo) {

		infoPanel = new JPanel();
		infoPanel.setBackground(new Color(238, 232, 170));
		infoPanel.setLayout(null);

		infoL = new JLabel();
		infoL.setFont(new Font("���� ���", Font.BOLD, 14));
		idL = new JLabel();
		pwdL = new JLabel();
		nameL = new JLabel();
		phoneL = new JLabel();

		idField = new JTextField(id);
		pwdField = new JTextField(cvo.getPwd());
		nameField = new JTextField(cvo.getCname());
		phoneField = new JTextField(cvo.getPhone());

		updateBt = new JButton();
		updateBt.setBackground(new Color(255, 255, 240));
		deleteBt = new JButton();
		deleteBt.setBackground(new Color(255, 255, 240));
		pwdUpdateBt = new JButton();
		pwdUpdateBt.setBackground(new Color(255, 255, 240));
		commitBt = new JButton();
		commitBt.setBackground(new Color(255, 255, 240));

		infoL.setText("�� ����");
		idL.setText("���̵�");
		pwdL.setText("��й�ȣ");
		nameL.setText("�̸�");
		phoneL.setText("��ȭ��ȣ");

		updateBt.setText("��������");
		deleteBt.setText("Ż��");
		pwdUpdateBt.setText("��й�ȣ����");
		commitBt.setText("Ȯ��");

		infoL.setHorizontalAlignment(JLabel.CENTER);

		infoL.setBounds(102, 10, 130, 33);
		idL.setBounds(23, 53, 90, 15);
		pwdL.setBounds(23, 111, 90, 15);
		nameL.setBounds(23, 169, 90, 15);
		phoneL.setBounds(23, 237, 90, 15);

		idField.setBounds(23, 78, 148, 23);
		pwdField.setBounds(23, 136, 148, 23);
		nameField.setBounds(23, 194, 148, 23);
		phoneField.setBounds(23, 262, 148, 23);

		updateBt.setBounds(12, 328, 97, 23); // ��������
		commitBt.setBounds(117, 328, 97, 23); // Ȯ��
		deleteBt.setBounds(226, 328, 97, 23); // Ż��
		pwdUpdateBt.setBounds(208, 136, 116, 23); // ��й�ȣ ����

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
		infoPanel.add(commitBt);

		this.setContentPane(infoPanel);

		eventList();
	}

	public CustomerVO initTableRequest() {
		cdao = new CustomerDAO();
		CustomerVO cvo = cdao.select(id);
		return cvo;
	}

	public void eventList() {

		updateBt.addActionListener(this);
		deleteBt.addActionListener(this);
		pwdUpdateBt.addActionListener(this);
		commitBt.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
			// ����������ư
		if (updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();
			
			// Ż���ư
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
				login = new LoginFrame();
				
				dispose();
				
				
			}

		}
		
			// Ȯ�ι�ư
		else if (commitBt == e.getSource()) {
				main = new MainFrame(this, id);
				dispose();

		} else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this, id);
			dispose();
		
		}
	}
}