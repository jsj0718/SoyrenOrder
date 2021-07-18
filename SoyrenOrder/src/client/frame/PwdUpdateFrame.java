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
import javax.swing.SwingConstants;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class PwdUpdateFrame extends JFrame implements ActionListener {

	JPanel pwdPanel;
	JLabel pwdUpdateL, pwdL, idL;

	JTextField pwdField, idField;

	JButton commitBt;
	JButton cancelBt;

	String id;
	String pwd;

	public CustInfoFrame custInfo;
	public MainFrame main;

	public PwdUpdateFrame(CustInfoFrame custInfo, String id) {
		this.custInfo = custInfo;
		this.id = id;

		this.setTitle("��й�ȣ ����");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 300, 218);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		this.setVisible(true);

	}

	public void setComponent() {

		pwdPanel = new JPanel();
		pwdPanel.setBackground(new Color(238, 232, 170));

		pwdUpdateL = new JLabel();
		pwdUpdateL.setFont(new Font("���� ���", Font.BOLD, 14));
		pwdUpdateL.setBounds(68, 10, 151, 23);
		pwdL = new JLabel();
		pwdL.setBounds(68, 89, 100, 30);
		idL = new JLabel();
		idL.setBounds(68, 32, 100, 30);

		idField = new JTextField(id);
		idField.setBounds(68, 61, 151, 21);
		pwdField = new JTextField();
		pwdField.setBounds(68, 117, 151, 21);

		commitBt = new JButton();
		commitBt.setBackground(new Color(255, 255, 240));
		commitBt.setBounds(29, 146, 97, 23);

		pwdUpdateL.setText("��й�ȣ ����");
		pwdL.setText("��й�ȣ");
		idL.setText("���̵�");

		commitBt.setText("Ȯ��");

		pwdUpdateL.setHorizontalAlignment(JLabel.CENTER);

		pwdPanel.setBounds(0, 0, 300, 230);

		commitBt.setHorizontalAlignment(JButton.CENTER);
		pwdPanel.setLayout(null);

		pwdPanel.add(pwdUpdateL);
		pwdPanel.add(idL);
		pwdPanel.add(idField);
		pwdPanel.add(pwdL);
		pwdPanel.add(pwdField);
		pwdPanel.add(commitBt);

		this.setContentPane(pwdPanel);
		
		cancelBt = new JButton();
		cancelBt.setText("\uCDE8\uC18C");
		cancelBt.setHorizontalAlignment(SwingConstants.CENTER);
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.setBounds(154, 146, 97, 23);
		pwdPanel.add(cancelBt);

		eventList();

	}

	public void eventList() {
		commitBt.addActionListener(this);
		cancelBt.addActionListener(this);
	}

	// ȸ�� Ż��
	public void pwdUpdate(CustomerMessage cmsg) {
		if (cmsg.getResult() > 0) {
			JOptionPane.showMessageDialog(null, "��й�ȣ ������ �Ϸ�Ǿ����ϴ�.");

			pwdPanel.removeAll(); // infoPanel �гο� �ִ� ������Ʈ ����
			custInfo.pwdField.setText(cmsg.getCvo().getPwd());
			custInfo.setVisible(true);
			dispose();	
		} else {
			JOptionPane.showConfirmDialog(null, "��й�ȣ ������ �����߽��ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Ȯ�� ��ư
		if (commitBt == e.getSource()) {

			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();

			cvo.setCustID(idField.getText());
			cvo.setPwd(pwdField.getText());
			cmsg.setState(8);
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
		// ��� ��ư
		} else if (cancelBt == e.getSource()) {
			pwdPanel.removeAll(); // infoPanel �гο� �ִ� ������Ʈ ����
			custInfo.setVisible(true);
			dispose();
		}
	}
}