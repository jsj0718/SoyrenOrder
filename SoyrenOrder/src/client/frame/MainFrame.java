package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import customer.CustomerDAO;
import product.ProductDAO;
import product.ProductVO;
import salgrade.SalgradeDAO;

public class MainFrame extends JFrame implements ActionListener {
	JPanel totalPanel;
	JPanel infoPanel;
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
	
	CustomerDAO cdao;
	SalgradeDAO sdao;
	ProductDAO pdao;
	ArrayList<ProductVO> plist;
	
	public OrderFrame order;
	
	public MainFrame() {
		
		this.setTitle("Main창");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(450, 500, 416, 543);
		this.setLayout(null); // absoulute
		setComponent();
		this.setVisible(true);

	}
	
	public MainFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;
		
		this.setTitle("Main창");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(450, 500, 416, 543);
		this.setLayout(null); // absoulute
		setComponent();
		this.setVisible(true);

	}

	public void setComponent() {
		
		
		totalPanel = new JPanel();
		totalPanel.setLayout(null);

		infoPanel = new JPanel();
		infoPanel.setLayout(null);

		logOutBt = new JButton();
		gradeF = new JTextField();
		totalPriceF = new JTextField();
		mentL = new JLabel();
		gradeL = new JLabel();
		totalPriceL = new JLabel();

		bestPanel = new JPanel();
		bestPanel.setLayout(null);
		bestBeverageL = new JLabel();
		bestL1 = new JLabel();
		bestL2 = new JLabel();
		bestL3 = new JLabel();
		
		cdao = new CustomerDAO();
		sdao = new SalgradeDAO();
		pdao = new ProductDAO();
		
		plist = pdao.selectBestProduct();
		
		bestBt1 = new JButton(imageSetSize(plist.get(0).getImgPath(), 73, 72));
		bestBt2 = new JButton(imageSetSize(plist.get(1).getImgPath(), 73, 72));
		bestBt3 = new JButton(imageSetSize(plist.get(2).getImgPath(), 73, 72));

		orderBt = new JButton();
		
		
		logOutBt.setText("로그아웃");
		mentL.setText(id + "님, HOPE to spend your nice time with Soyren");
		gradeL.setText("등급");
		gradeF.setText(sdao.getGrade(id));
		totalPriceL.setText("TotalPrice");
		totalPriceF.setText(cdao.monthCustBuy(id) + "원");
		bestBeverageL.setText("BEST BERVERAGE");
		bestL1.setText(plist.get(0).getPname());
		bestL2.setText(plist.get(1).getPname());
		bestL3.setText(plist.get(2).getPname());
		orderBt.setText("Order Now!");

		// info panel
		infoPanel.setBounds(12, 10, 376, 146);
		logOutBt.setBounds(279, 0, 97, 23);

		gradeF.setBounds(190, 65, 116, 21);
		totalPriceF.setBounds(190, 104, 116, 21);

		mentL.setBounds(123, 35, 253, 20);
		gradeL.setBounds(121, 68, 57, 15);
		totalPriceL.setBounds(121, 105, 69, 18);

		// best panel
		bestPanel.setBounds(12, 155, 376, 165);
		bestBeverageL.setBounds(12, 10, 105, 41);
		bestBt1.setBounds(22, 48, 73, 72);
		bestBt2.setBounds(151, 48, 73, 72);
		bestBt3.setBounds(267, 48, 73, 72);
		bestL1.setBounds(32, 130, 63, 25);
		bestL2.setBounds(161, 130, 63, 25);
		bestL3.setBounds(277, 130, 63, 25);

		orderBt.setBounds(187, 341, 185, 141);

		gradeF.setColumns(10);
		totalPriceF.setColumns(10);

//		frame.getContentPane().setBackground(new Color(224, 255, 255));
//		frame.setEnabled(false);

		infoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		infoPanel.setBackground(new Color(224, 255, 255));

		logOutBt.setBackground(new Color(245, 245, 245));

		mentL.setForeground(new Color(0, 0, 0));
		mentL.setFont(new Font("Garamond", Font.BOLD, 12));

		bestPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bestPanel.setBackground(new Color(135, 206, 235));

		bestBeverageL.setFont(new Font("Sitka Banner", Font.PLAIN, 14));

		bestL1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));

		bestL2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));

		bestL3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));

		orderBt.setFont(new Font("Lucida Sans Unicode", Font.ITALIC, 20));

		infoPanel.add(logOutBt);
		infoPanel.add(gradeF);
		infoPanel.add(totalPriceF);
		infoPanel.add(mentL);
		infoPanel.add(gradeL);
		infoPanel.add(totalPriceL);
		totalPanel.add(infoPanel);

		bestPanel.add(bestBeverageL);
		bestPanel.add(bestBt1);
		bestPanel.add(bestBt2);
		bestPanel.add(bestBt3);
		bestPanel.add(bestL1);
		bestPanel.add(bestL2);
		bestPanel.add(bestL3);
		totalPanel.add(bestPanel);

		totalPanel.add(orderBt);

		this.setContentPane(totalPanel);

		eventList();
	}

	public void eventList() {
		logOutBt.addActionListener(this);
		orderBt.addActionListener(this);
		bestBt1.addActionListener(this);
		bestBt2.addActionListener(this);
		bestBt3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (logOutBt == e.getSource()) {
			JOptionPane.showConfirmDialog(null, "로그아웃 되었습니다", "확인", JOptionPane.DEFAULT_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION);
			dispose();

		}
		else if (orderBt == e.getSource()) {
			
			order = null;
			
			order = new OrderFrame(this, id);
			
			dispose();
			
		}
		// 베스트 상품 클릭 시 바로 주문창 이동
		else if (bestBt1 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL1.getText());
			
		}
		else if (bestBt2 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL2.getText());
			
		}
		else if (bestBt3 == e.getSource()) {
			order = null;
			order = new OrderFrame(this, id, bestL3.getText());
			
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
	

	public static void main(String[] args) {
		new MainFrame();
	}
}
