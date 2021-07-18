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
	// pwdCheckL은 비밀번호 일치 여부를 나타내는 라벨
	JLabel joinL, idL, pwdL, pwdOkL, pwdChekL, nameL, phoneL, idChekL, nameChekL, phoneChekL, pwdLengL;

	JTextField idField, nameField, phoneField;
	JPasswordField pwdField, pwdOkField;

	JButton idCheckBt, joinBt, cancelBt;

	int idCheck;
	int pwdCheck;
	private JLabel pwdLengthL;

	public JoinFrame() {
		this.setTitle("회원가입");
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
		idL.setText("아이디");
		pwdL = new JLabel();
		pwdL.setBounds(12, 126, 85, 32);
		pwdL.setText("비밀번호");
		pwdOkL = new JLabel();
		pwdOkL.setBounds(12, 168, 85, 32);
		pwdOkL.setText("비밀번호 확인");
		pwdChekL = new JLabel();
		pwdChekL.setBounds(98, 196, 185, 15);
		nameL = new JLabel();
		nameL.setBounds(12, 210, 85, 32);
		nameL.setText("이름");
		phoneL = new JLabel();
		phoneL.setBounds(12, 252, 85, 32);
		phoneL.setText("핸드폰");
		joinL = new JLabel();
		joinL.setBounds(56, 22, 227, 44);
		joinL.setText("회원가입");

		joinL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
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
		idCheckBt.setText("중복 확인");
		joinBt = new JButton();
		joinBt.setBounds(56, 345, 97, 35);
		joinBt.setBackground(new Color(255, 255, 240));
		joinBt.setText("가입");
		cancelBt = new JButton();
		cancelBt.setBounds(186, 345, 97, 35);
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.setText("취소");
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
		cancelBt.addActionListener(this);	// 회원가입 취소 버튼
		idCheckBt.addActionListener(this);	// 아이디 중복체크 버튼
		joinBt.addActionListener(this);		// 회원가입 버튼
		pwdOkField.addKeyListener(this);	// 패스워드 확인
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(cancelBt == e.getSource()) {
			dispose();
		} else if(idCheckBt == e.getSource()) {
			//id 입력 -> 서버로 id를 보내야하는 상황
			String id = idField.getText();
			if(id.equals("")) {
				JOptionPane.showConfirmDialog(null, "아이디를 입력하세요.",
						"경고",JOptionPane.DEFAULT_OPTION);
				idField.requestFocus();
				return;
			}
			if(idField.getText().length() > 15 || idField.getText().length() < 3) {
				JOptionPane.showConfirmDialog(null, "아이디 길이 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showConfirmDialog(null, "마감되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}



		}else if(joinBt == e.getSource()) {	// 회원가입
			// idCheck를 하면 1, 안하면 0
			if(idCheck==0) {
				JOptionPane.showConfirmDialog(null, "아이디 중복 체크 해주세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdField.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "비밀번호를 입력하세요","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdOkField.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "비밀번호 확인을 입력하세요","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdCheck==0 ) {
				JOptionPane.showConfirmDialog(null, "비밀번호 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(idField.getText().length() > 15 || idField.getText().length() <= 3) {
				JOptionPane.showConfirmDialog(null, "아이디 길이 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdField.getText().length() > 15 || pwdField.getText().length() < 6) {
				JOptionPane.showConfirmDialog(null, "비밀번호 길이 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}

			//이름에 숫자가 들어가는지 여부 확인
			int nameCheck = 0;
			for(int i = 0; i < nameField.getText().length(); i++) {
				char check = nameField.getText().charAt(i);
				if( check < 48 || check > 58)
				{
					//해당 char값이 문자일 경우

				}else {
					nameCheck += 1;
				}
			}
			if(nameCheck >= 1) {
				JOptionPane.showConfirmDialog(null, "이름 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showConfirmDialog(null, "핸드폰번호 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showConfirmDialog(null, "마감되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
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

	//키보드 입력 이벤트 (완벽하게 눌렀다 떼는 경우만 동작)
	@Override
	public void keyReleased(KeyEvent e) {

		String pwd = pwdField.getText();
		String pwdOk = pwdOkField.getText();


		if(pwd.equals(pwdOk) && pwdField.getText().length() < 15 && pwdField.getText().length() >= 6) {
			//폰트 색상
			pwdChekL.setForeground(Color.black);
			pwdChekL.setText("비밀번호 확인 완료");
			pwdCheck=1;
		}else {
			//폰트 색상
			pwdChekL.setForeground(Color.red);
			pwdChekL.setText("비밀번호를 다시 확인해주세요.");
			pwdCheck=0;
		}




	}



	//아이디 중복체크 결과 처리
	public void idCheckResult(int result) {
		if(result == 0) {
			//result == 0
			//사용가능한 아이디 입니다.
			JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");
			idCheck = 1;
		}else {
			//result ==? 
			//이미존재하는 아이디 입니다.
			//idField에 있는 값을 지원주고 포커스 맞춰준다.
			JOptionPane.showConfirmDialog(null, "이미 존재하는 아이디 입니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			idField.setText("");
			idField.requestFocus();
			idCheck = 0;
		}
	}

	//회원가입 결과 처리
	public void joinCheck(CustomerMessage cm) {
		if(cm.getResult()!=0) {
			JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
			dispose();

		}else {
			JOptionPane.showConfirmDialog(null, "잘못된 정보를 입력하였습니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
		}
	}
}
