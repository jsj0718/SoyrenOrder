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

	JPanel infoPanel; // 회원정보
	JLabel infoL, idL, pwdL, nameL, phoneL; // 아이디, 비밀번호, 이름, 전화번호
	JButton updateBt, pwdUpdateBt, deleteBt, commitBt; // 수정, 비밀번호수정, 탈퇴
	JTextField idField, pwdField, nameField, phoneField; // 아이디, 비밀번호, 이름, 전화번호

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

		this.setTitle("내 정보");
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

			this.setTitle("내 정보");
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

		this.setTitle("내 정보");
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
		infoL.setFont(new Font("맑은 고딕", Font.BOLD, 14));
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

		infoL.setText("내 정보");
		idL.setText("아이디");
		pwdL.setText("비밀번호");
		nameL.setText("이름");
		phoneL.setText("전화번호");

		updateBt.setText("정보수정");
		deleteBt.setText("탈퇴");
		pwdUpdateBt.setText("비밀번호수정");
		commitBt.setText("확인");

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

		updateBt.setBounds(12, 328, 97, 23); // 정보수정
		commitBt.setBounds(117, 328, 97, 23); // 확인
		deleteBt.setBounds(226, 328, 97, 23); // 탈퇴
		pwdUpdateBt.setBounds(208, 136, 116, 23); // 비밀번호 수정

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
			// 정보수정버튼
		if (updateBt == e.getSource()) {
			infoUpdate = new InfoUpdateFrame(this, id);
			dispose();
			
			// 탈퇴버튼
		} else if (deleteBt == e.getSource()) {
			
			int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.CLOSED_OPTION) {
				// 사용자가 yes, no 선택없이 창을 닫은 경우
			}else if(result == JOptionPane.NO_OPTION){
				// 사용자가 no를 선택했을 경우
			}else {
				// 사용자가 yes를 선택했을 경우
				CustomerDAO cdao = new CustomerDAO();
				
				cdao.delete(id);
				JOptionPane.showConfirmDialog(null, "회원탈퇴 되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.YES_NO_CANCEL_OPTION);
				login = new LoginFrame();
				
				dispose();
				
				
			}

		}
		
			// 확인버튼
		else if (commitBt == e.getSource()) {
				main = new MainFrame(this, id);
				dispose();

		} else if (pwdUpdateBt == e.getSource()) {
			pwdUpdate = new PwdUpdateFrame(this, id);
			dispose();
		
		}
	}
}