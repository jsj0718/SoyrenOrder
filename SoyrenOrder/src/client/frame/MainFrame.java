package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;
import message.Message;
import message.ProductMessage;
import message.SalgradeMessage;
import product.ProductVO;
import salgrade.SalgradeVO;

public class MainFrame extends JFrame implements ActionListener {
	JPanel totalPanel;
	JPanel infoPanel;
	JButton infoBt;
	JButton logOutBt;
	JTextField gradeF;
	JTextField totalPriceF;
	JLabel mentL;
	JLabel gradeL;
	JLabel totalPriceL;

	JPanel bestPanel;
	JLabel bestBeverageL;
	JLabel bestL1;
	JLabel bestL2;
	JLabel bestL3;
	JButton bestBt1;
	JButton bestBt2;
	JButton bestBt3;

	JButton orderBt;

	LoginFrame login;
	String id;

	ArrayList<ProductVO> plist;

	public OrderFrame order;
	public CustInfoFrame custInfo;

	public MainFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;

		this.setTitle("Main창");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(300, 100, 416, 543);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		initTableRequest();
		this.setVisible(true);

	}

	public MainFrame(OrderFrame order, String id) {
		this.order = order;
		this.id = id;

		this.setTitle("Main창");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(300, 100, 416, 543);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		initTableRequest();
		this.setVisible(true);

	}

	public MainFrame(CustInfoFrame custInfo, String id) {
		this.custInfo = custInfo;
		this.id = id;

		this.setTitle("Main창");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(300, 100, 416, 543);
		getContentPane().setLayout(null); // absoulute
		setComponent();
		initTableRequest();
		this.setVisible(true);
	}

	public void setComponent() {

		totalPanel = new JPanel();
		totalPanel.setBackground(new Color(85, 107, 47));
		totalPanel.setForeground(new Color(85, 107, 47));
		totalPanel.setLayout(null);

		infoPanel = new JPanel();
		infoPanel.setSize(376, 146);
		infoPanel.setLocation(12, 10);
		gradeF = new JTextField();
		gradeF.setEditable(false);
		gradeF.setBounds(118, 87, 116, 21);
		totalPriceF = new JTextField();
		totalPriceF.setEditable(false);
		totalPriceF.setBounds(118, 126, 116, 21);
		mentL = new JLabel();
		mentL.setBounds(43, 39, 352, 38);
		gradeL = new JLabel();
		gradeL.setBounds(27, 90, 57, 15);
		totalPriceL = new JLabel();
		totalPriceL.setBounds(27, 127, 69, 18);

		bestPanel = new JPanel();
		bestPanel.setLayout(null);
		bestBeverageL = new JLabel();
		bestL1 = new JLabel();
		bestL2 = new JLabel();
		bestL3 = new JLabel();


		mentL.setText("Hope to spend your nice time with Soyren");
		gradeL.setText("등급");
		totalPriceL.setText("\uCD1D \uAD6C\uB9E4 \uAE08\uC561");
		bestBeverageL.setText("BEST BERVERAGE");

		bestBt1 = new JButton();
		bestBt2 = new JButton();
		bestBt3 = new JButton();
		bestL1.setText("Best1");
		bestL2.setText("Best2");
		bestL3.setText("Best3");

		// info panel
		infoPanel.setBounds(12, 54, 376, 165);

		// best panel
		bestPanel.setBounds(12, 221, 376, 165);
		bestBeverageL.setBounds(12, 10, 127, 41);
		bestBt1.setBounds(22, 48, 73, 72);
		bestBt2.setBounds(151, 48, 73, 72);
		bestBt3.setBounds(267, 48, 73, 72);
		bestL1.setBounds(32, 130, 63, 25);
		bestL2.setBounds(161, 130, 63, 25);
		bestL3.setBounds(277, 130, 63, 25);

		gradeF.setColumns(10);
		totalPriceF.setColumns(10);

		infoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		infoPanel.setBackground(new Color(240, 255, 240));

		mentL.setForeground(new Color(0, 0, 0));
		mentL.setFont(new Font("Garamond", Font.BOLD, 16));

		bestPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bestPanel.setBackground(new Color(240, 255, 255));

		bestBeverageL.setFont(new Font("Sitka Banner", Font.BOLD, 14));

		bestL1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));

		bestL2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));

		bestL3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		infoPanel.setLayout(null);
		infoPanel.add(gradeF);
		infoPanel.add(totalPriceF);
		infoPanel.add(mentL);
		infoPanel.add(gradeL);
		infoPanel.add(totalPriceL);
		totalPanel.add(infoPanel);

		JLabel custID = new JLabel(id);
		custID.setFont(new Font("Garamond", Font.BOLD, 16));
		custID.setBounds(164, 10, 116, 31);
		infoPanel.add(custID);

		bestPanel.add(bestBeverageL);
		bestPanel.add(bestBt1);
		bestPanel.add(bestBt2);
		bestPanel.add(bestBt3);
		bestPanel.add(bestL1);
		bestPanel.add(bestL2);
		bestPanel.add(bestL3);
		totalPanel.add(bestPanel);

		this.setContentPane(totalPanel);

		infoBt = new JButton();
		infoBt.setBackground(new Color(255, 255, 240));
		infoBt.setBounds(12, 21, 97, 23);
		totalPanel.add(infoBt);

		infoBt.setText("내 정보");
		infoBt.setBorderPainted(false);
		logOutBt = new JButton();
		logOutBt.setBounds(291, 21, 97, 23);
		logOutBt.setBorderPainted(false);
		totalPanel.add(logOutBt);
		logOutBt.setText("로그아웃");

		logOutBt.setBackground(new Color(255, 255, 240));
		orderBt = new JButton();
		orderBt.setBounds(108, 407, 185, 71);
		totalPanel.add(orderBt);
		orderBt.setBackground(new Color(255, 255, 240));
		orderBt.setBorderPainted(false);

		orderBt.setText("Order Now!");

		orderBt.setFont(new Font("Lucida Sans Unicode", Font.ITALIC, 20));

		eventList();
	}

	public void eventList() {
		logOutBt.addActionListener(this);
		orderBt.addActionListener(this);
		infoBt.addActionListener(this);
		bestBt1.addActionListener(this);
		bestBt2.addActionListener(this);
		bestBt3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (infoBt == e.getSource()) {

			custInfo = null;

			custInfo = new CustInfoFrame(this, id);

			dispose();
		}
		if (logOutBt == e.getSource()) {
			JOptionPane.showConfirmDialog(this, "로그아웃 되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);

			id = null;
			totalPanel.removeAll();
			login.idField.setText("");
			login.pwdField.setText("");
			login.setVisible(true);
			dispose();

		}

		// 주문 버튼 클릭 시 주문창 이동
		else if (orderBt == e.getSource()) {

			order = null;
			order = new OrderFrame(this, id);
			dispose();

		}
		// 베스트 상품 클릭 시 해당 제품 주문창으로 이동
		else if (bestBt1 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL1.getText());
			dispose();
		} else if (bestBt2 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL2.getText());
			dispose();
		} else if (bestBt3 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL3.getText());
			dispose();
		}

	}

	// 초기데이터 요청
	public void initTableRequest() {
		customerBuySelect();
		customerGradeSelect();
		bestProductselect();
	}

	// 초기데이터 응답
	public void initTableResponse(Message msg) {
		if (msg instanceof CustomerMessage) {
			CustomerMessage cmsg = (CustomerMessage) msg;
			addLabelCustBuy(cmsg.getResult());
		} else if (msg instanceof SalgradeMessage) {
			SalgradeMessage smsg = (SalgradeMessage) msg;
			addLabelCustGrade(smsg.getGrade());
		} else if (msg instanceof ProductMessage) {
			ProductMessage pmsg = (ProductMessage) msg;
			addBestProduct(pmsg.getPlist());
		}

	}

	// 월 고객 구매 금액 조회
	public void customerBuySelect() {
		CustomerMessage cmsg = new CustomerMessage();
		CustomerVO cvo = new CustomerVO();
		cvo.setCustID(id);
		cmsg.setState(4);
		cmsg.setCvo(cvo);

		try {
			ClientHandler.oos.writeObject(cmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
	}

	// 월 고객 등급 조회
	public void customerGradeSelect() {
		SalgradeMessage smsg = new SalgradeMessage();
		SalgradeVO svo = new SalgradeVO();
		svo.setCustID(id);
		smsg.setState(1);
		smsg.setSvo(svo);

		try {
			ClientHandler.oos.writeObject(smsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
	}

	// 베스트 상품 조회
	public void bestProductselect() {
		ProductMessage pmsg = new ProductMessage();
		pmsg.setState(1);

		try {
			ClientHandler.oos.writeObject(pmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
	}

	// 고객 월 구매 금액 넣기
	public void addLabelCustBuy(int result) {
		totalPriceF.setText(result + "원");
	}

	// 고객 월 등급 넣기
	public void addLabelCustGrade(String result) {
		gradeF.setText(result);
	}

	// 베스트 상품 3개 넣기
	public void addBestProduct(ArrayList<ProductVO> plist) {

		if (plist.size() >= 3) {
			bestBt1.setIcon(imageSetSize(plist.get(0).getImgPath(), 73, 72));
			bestBt2.setIcon(imageSetSize(plist.get(1).getImgPath(), 73, 72));
			bestBt3.setIcon(imageSetSize(plist.get(2).getImgPath(), 73, 72));
			bestL1.setText(plist.get(0).getPname());
			bestL2.setText(plist.get(1).getPname());
			bestL3.setText(plist.get(2).getPname());
		}

	}

	// 이미지 아이콘 만드는 메소드
	public ImageIcon getImgIcon(String imgPath) {
		Image selectedImg = new ImageIcon(imgPath).getImage();
		Image scaledImg = selectedImg.getScaledInstance(171, 165, Image.SCALE_DEFAULT);
		ImageIcon scaledImgIcon = new ImageIcon(scaledImg);

		return scaledImgIcon;
	}

	// 이미지 크기 조절 메소드
	public ImageIcon imageSetSize(String imgPath, int i, int j) {
		ImageIcon selectedImg = new ImageIcon("src/" + imgPath);
		Image ximg = selectedImg.getImage();
		Image yimg = ximg.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}

}
