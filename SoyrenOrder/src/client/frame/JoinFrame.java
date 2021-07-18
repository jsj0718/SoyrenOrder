package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class JoinFrame extends JFrame implements ActionListener, KeyListener {
	JPanel joinPanel;
	// pwdCheckL�� ��й�ȣ ��ġ ���θ� ��Ÿ���� ��
	JLabel joinL, idL, pwdL, pwdOkL, pwdChekL, nameL, phoneL, idChekL, nameChekL, phoneChekL, pwdLengL;

	JTextField idField, nameField, phoneField;
	JPasswordField pwdField, pwdOkField;

	JButton idCheckBt, joinBt, cancelBt;

	int idCheck;
	int pwdCheck;
	private JLabel pwdLengthL;

	public JoinFrame() {
		this.setTitle("ȸ������");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(400, 200, 350, 436);
		getContentPane().setLayout(null); //absoulute
		setComponent();
		this.setVisible(true);

	}

	public void setComponent() {
		joinPanel = new JPanel();
		joinPanel.setBackground(new Color(238, 232, 170));

		idL = new JLabel();
		idL.setBounds(12, 84, 85, 32);
		idL.setText("���̵�");
		pwdL = new JLabel();
		pwdL.setBounds(12, 126, 85, 32);
		pwdL.setText("��й�ȣ");
		pwdOkL = new JLabel();
		pwdOkL.setBounds(12, 168, 85, 32);
		pwdOkL.setText("��й�ȣ Ȯ��");
		pwdChekL = new JLabel();
		pwdChekL.setBounds(98, 196, 185, 15);
		nameL = new JLabel();
		nameL.setBounds(12, 210, 85, 32);
		nameL.setText("�̸�");
		phoneL = new JLabel();
		phoneL.setBounds(12, 252, 85, 32);
		phoneL.setText("�ڵ���");
		joinL = new JLabel();
		joinL.setBounds(56, 22, 227, 44);
		joinL.setText("ȸ������");

		joinL.setFont(new Font("���� ���", Font.BOLD, 30));
		joinL.setHorizontalAlignment(JLabel.CENTER);

		idField = new JTextField();
		idField.setBounds(98, 90, 103, 21);
		pwdField = new JPasswordField();
		pwdField.setBounds(98, 133, 171, 21);
		pwdOkField = new JPasswordField();
		pwdOkField.setBounds(98, 174, 171, 21);
		nameField = new JTextField();
		nameField.setBounds(98, 216, 171, 21);
		phoneField = new JTextField();
		phoneField.setBounds(98, 258, 171, 21);

		idCheckBt = new JButton();
		idCheckBt.setBounds(213, 89, 97, 23);
		idCheckBt.setBackground(new Color(255, 255, 240));
		idCheckBt.setText("�ߺ� Ȯ��");
		joinBt = new JButton();
		joinBt.setBounds(56, 345, 97, 35);
		joinBt.setBackground(new Color(255, 255, 240));
		joinBt.setText("����");
		cancelBt = new JButton();
		cancelBt.setBounds(186, 345, 97, 35);
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.setText("���");
		joinPanel.setLayout(null);

		joinPanel.add(joinL);
		joinPanel.add(idL);
		joinPanel.add(pwdL);
		joinPanel.add(pwdOkL);
		joinPanel.add(pwdChekL);
		joinPanel.add(nameL);
		joinPanel.add(phoneL);
		joinPanel.add(idField);
		joinPanel.add(pwdField);
		joinPanel.add(pwdOkField);
		joinPanel.add(nameField);
		joinPanel.add(phoneField);
		joinPanel.add(idCheckBt);
		joinPanel.add(joinBt);
		joinPanel.add(cancelBt);


		this.setContentPane(joinPanel);

		JLabel idChekL = new JLabel("3\uC790 \uC774\uC0C1, 15\uC790 \uC774\uB0B4");
		idChekL.setBounds(98, 108, 171, 21);
		joinPanel.add(idChekL);

		JLabel nameChekL = new JLabel("15\uC790 \uC774\uB0B4, \uC22B\uC790 \uC81C\uC678");
		nameChekL.setBounds(98, 235, 171, 21);
		joinPanel.add(nameChekL);

		JLabel phoneChekL = new JLabel("\uC608\uC2DC) \"010-0000-0000\"");
		phoneChekL.setBounds(98, 281, 171, 23);
		joinPanel.add(phoneChekL);

		pwdLengthL = new JLabel("6\uC790 \uC774\uC0C1, 15\uC790 \uC774\uB0B4");
		pwdLengthL.setBounds(98, 150, 171, 21);
		joinPanel.add(pwdLengthL);
		eventList();
	}


	public void eventList() {
		cancelBt.addActionListener(this);	// ȸ������ ��� ��ư
		idCheckBt.addActionListener(this);	// ���̵� �ߺ�üũ ��ư
		joinBt.addActionListener(this);		// ȸ������ ��ư
		pwdOkField.addKeyListener(this);	// �н����� Ȯ��
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(cancelBt == e.getSource()) {
			dispose();
		} else if(idCheckBt == e.getSource()) {
			//id �Է� -> ������ id�� �������ϴ� ��Ȳ
			String id = idField.getText();
			if(id.equals("")) {
				JOptionPane.showConfirmDialog(null, "���̵� �Է��ϼ���.",
						"���",JOptionPane.DEFAULT_OPTION);
				idField.requestFocus();
				return;
			}
			if(idField.getText().length() > 15 || idField.getText().length() < 3) {
				JOptionPane.showConfirmDialog(null, "���̵� ���� Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustID(id);
			cmsg.setCvo(cvo);
			cmsg.setState(1);//1 : id check	


			//write
			ObjectOutputStream oos =  ClientHandler.oos;
			try {
				oos.writeObject(cmsg);
			} catch (IOException e1) {
//				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}



		}else if(joinBt == e.getSource()) {	// ȸ������
			// idCheck�� �ϸ� 1, ���ϸ� 0
			if(idCheck==0) {
				JOptionPane.showConfirmDialog(null, "���̵� �ߺ� üũ ���ּ���.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdField.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "��й�ȣ�� �Է��ϼ���","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdOkField.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "��й�ȣ Ȯ���� �Է��ϼ���","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdCheck==0 ) {
				JOptionPane.showConfirmDialog(null, "��й�ȣ Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(idField.getText().length() > 15 || idField.getText().length() <= 3) {
				JOptionPane.showConfirmDialog(null, "���̵� ���� Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdField.getText().length() > 15 || pwdField.getText().length() < 6) {
				JOptionPane.showConfirmDialog(null, "��й�ȣ ���� Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}

			//�̸��� ���ڰ� ������ ���� Ȯ��
			int nameCheck = 0;
			for(int i = 0; i < nameField.getText().length(); i++) {
				char check = nameField.getText().charAt(i);
				if( check < 48 || check > 58)
				{
					//�ش� char���� ������ ���

				}else {
					nameCheck += 1;
				}
			}
			if(nameCheck >= 1) {
				JOptionPane.showConfirmDialog(null, "�̸� Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;

			}



			int phoneCheck = 0;
			String[] check = new String[phoneField.getText().length()];
			check = phoneField.getText().split("");
			for(int i = 0; i < phoneField.getText().length(); i++) {
				if(!check[3].equals("-") || !check[8].equals("-") || 
						phoneField.getText().length() != 13	) {
					phoneCheck = 1;
				}
			}
			if(phoneCheck == 1) {
				JOptionPane.showConfirmDialog(null, "�ڵ�����ȣ Ȯ���� �ʿ��մϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}




			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();

			cvo.setCustID(idField.getText());
			cvo.setPwd(pwdField.getText()); 
			cvo.setCname(nameField.getText());
			cvo.setPhone(phoneField.getText());

			cmsg.setState(2);
			cmsg.setCvo(cvo);
			try {
				ClientHandler.oos.writeObject(cmsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		}


	}


	public static void main(String[] args) {
		new JoinFrame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	//Ű���� �Է� �̺�Ʈ (�Ϻ��ϰ� ������ ���� ��츸 ����)
	@Override
	public void keyReleased(KeyEvent e) {

		String pwd = pwdField.getText();
		String pwdOk = pwdOkField.getText();


		if(pwd.equals(pwdOk) && pwdField.getText().length() < 15 && pwdField.getText().length() >= 6) {
			//��Ʈ ����
			pwdChekL.setForeground(Color.black);
			pwdChekL.setText("��й�ȣ Ȯ�� �Ϸ�");
			pwdCheck=1;
		}else {
			//��Ʈ ����
			pwdChekL.setForeground(Color.red);
			pwdChekL.setText("��й�ȣ�� �ٽ� Ȯ�����ּ���.");
			pwdCheck=0;
		}




	}



	//���̵� �ߺ�üũ ��� ó��
	public void idCheckResult(int result) {
		if(result == 0) {
			//result == 0
			//��밡���� ���̵� �Դϴ�.
			JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�.");
			idCheck = 1;
		}else {
			//result ==? 
			//�̹������ϴ� ���̵� �Դϴ�.
			//idField�� �ִ� ���� �����ְ� ��Ŀ�� �����ش�.
			JOptionPane.showConfirmDialog(null, "�̹� �����ϴ� ���̵� �Դϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			idField.setText("");
			idField.requestFocus();
			idCheck = 0;
		}
	}

	//ȸ������ ��� ó��
	public void joinCheck(CustomerMessage cm) {
		if(cm.getResult()!=0) {
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			dispose();

		}else {
			JOptionPane.showConfirmDialog(null, "�߸��� ������ �Է��Ͽ����ϴ�.","���",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
		}
	}
}
