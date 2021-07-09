package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import frame.OrderFrame_D;
import product.ProductDAO;
import product.ProductVO;

public class OrderFrame extends JFrame {

	JPanel infoTab;
	JLabel menuNameL;
	JLabel menuImg;
	JLabel menuInfoL;
	JRadioButton hotRadioBt;
	JRadioButton iceRadioBt;

	MainFrame main;
	String id;
	
	ProductDAO pdao = new ProductDAO();
	
	public OrderFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;

		this.setTitle("�ֹ�â");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBounds(100, 100, 857, 654);
		this.setLayout(null); // absoulute	
		initialize();
		this.setVisible(true);
	}

	private void initialize() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 843, 617);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.setBounds(36, 87, 418, 512);
		panel.add(tabbedPane);
		
		JPanel coffeeTab = new JPanel();
//		coffeeTab.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		coffeeTab.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Ŀ��", null, coffeeTab, null);
		coffeeTab.setLayout(null);
		
		JLabel americanoL = new JLabel("\uC544\uBA54\uB9AC\uCE74\uB178");
		americanoL.setHorizontalAlignment(SwingConstants.LEFT);
		americanoL.setBackground(new Color(240, 255, 240));
		americanoL.setBounds(175, 43, 206, 41);
		coffeeTab.add(americanoL);
		
		JLabel latteL = new JLabel("\uCE74\uD398\uB77C\uB5BC");
		latteL.setBounds(175, 126, 206, 41);
		coffeeTab.add(latteL);
		
		JLabel mochaL = new JLabel("\uCE74\uD398\uBAA8\uCE74");
		mochaL.setBounds(175, 212, 206, 41);
		coffeeTab.add(mochaL);
		
		JLabel cappuccinoL = new JLabel("\uCE74\uD478\uCE58\uB178");
		cappuccinoL.setBounds(175, 301, 206, 41);
		coffeeTab.add(cappuccinoL);
		
		JButton americanoImg = new JButton("");
		americanoImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/americano.jpg")));
		americanoImg.setForeground(new Color(240, 255, 240));
		americanoImg.setBounds(40, 33, 83, 60);
		coffeeTab.add(americanoImg);
		
		JButton latteImg = new JButton("");
		latteImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/latte.png")));
		latteImg.setBounds(40, 116, 83, 60);
		coffeeTab.add(latteImg);
		
		JButton mochaImg = new JButton("");
		mochaImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/mocha.png")));
		mochaImg.setBounds(40, 202, 83, 60);
		coffeeTab.add(mochaImg);
		
		JButton cappuccinoImg = new JButton("");
		cappuccinoImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/cappuccino.png")));
		cappuccinoImg.setBounds(40, 291, 83, 60);
		coffeeTab.add(cappuccinoImg);
		
		JButton banillaImg = new JButton("");
		banillaImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/banilla.png")));
		banillaImg.setBounds(40, 382, 83, 60);
		coffeeTab.add(banillaImg);
		
		JLabel banillaL = new JLabel("\uBC14\uB2D0\uB77C\uB77C\uB5BC");
		banillaL.setBounds(175, 392, 206, 41);
		coffeeTab.add(banillaL);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(0, 0));
		tabbedPane.addTab("����", null, scrollPane, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
//		JScrollPane jScrollPane = new JScrollPane();//��ũ���� ����

//		JPanel panel_ex = new JPanel();//��ũ���ҿ� ���� �г� ����

		Dimension size = new Dimension();//����� �����ϱ� ���� ��ü ����

		size.setSize(1000, 1000);//��ü�� ����� ����

		panel_1.setPreferredSize(size);//������ ������ ������ �ִ� ��ü�� �̿��� �г��� ������ ����

		scrollPane.setViewportView(panel_1);
		
		JLabel mangoL = new JLabel("\uB9DD\uACE0\uC2A4\uBB34\uB514");
		mangoL.setBounds(178, 32, 206, 41);
		panel_1.add(mangoL);
		
		JButton mangoImg = new JButton("");
		mangoImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mangoImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/mango.png")));
		mangoImg.setBounds(43, 22, 83, 60);
		panel_1.add(mangoImg);
		
		JButton strawberryImg = new JButton("");
		strawberryImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/strawberry.png")));
		strawberryImg.setBounds(43, 99, 83, 60);
		panel_1.add(strawberryImg);
		
		JLabel strawberryL = new JLabel("\uB538\uAE30\uC2A4\uBB34\uB514");
		strawberryL.setBounds(178, 109, 206, 41);
		panel_1.add(strawberryL);
		
		JButton lemonImg = new JButton("");
		lemonImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/lemon.png")));
		lemonImg.setBounds(43, 179, 83, 60);
		panel_1.add(lemonImg);
		
		JLabel lemonL = new JLabel("\uB808\uBAAC\uC5D0\uC774\uB4DC");
		lemonL.setBounds(178, 189, 206, 41);
		panel_1.add(lemonL);
		
		JButton grapeImg = new JButton("");
		grapeImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/grape.png")));
		grapeImg.setBounds(43, 259, 83, 60);
		panel_1.add(grapeImg);
		
		JLabel grapeL = new JLabel("\uC790\uBABD\uC5D0\uC774\uB4DC");
		grapeL.setBounds(178, 269, 206, 41);
		panel_1.add(grapeL);
		
		JButton chocoImg = new JButton("");
		chocoImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/choco.png")));
		chocoImg.setBounds(43, 341, 83, 60);
		panel_1.add(chocoImg);
		
		JLabel chocoL = new JLabel("\uCD08\uCF54\uB77C\uB5BC");
		chocoL.setBounds(178, 351, 206, 41);
		panel_1.add(chocoL);
		
		JButton greenteaImg = new JButton("");
		greenteaImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/greentea.png")));
		greenteaImg.setBounds(43, 421, 83, 60);
		panel_1.add(greenteaImg);
		
		JLabel greenteaL = new JLabel("\uB179\uCC28\uB77C\uB5BC");
		greenteaL.setBounds(178, 431, 206, 41);
		panel_1.add(greenteaL);
		
		JButton peachImg = new JButton("");
		peachImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/peach.png")));
		peachImg.setBounds(43, 501, 83, 60);
		panel_1.add(peachImg);
		
		JLabel peachL = new JLabel("\uBCF5\uC22D\uC544 \uC544\uC774\uC2A4\uD2F0");
		peachL.setBounds(178, 511, 206, 41);
		panel_1.add(peachL);
		
		JButton lemonteaImg = new JButton("");
		lemonteaImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/Img/lemonice.png")));
		lemonteaImg.setBounds(43, 581, 83, 60);
		panel_1.add(lemonteaImg);
		
		JLabel lemonteaL = new JLabel("\uB808\uBAAC \uC544\uC774\uC2A4\uD2F0");
		lemonteaL.setBounds(178, 591, 206, 41);
		panel_1.add(lemonteaL);
		
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(472, 87, 335, 512);
		panel.add(tabbedPane_1);
		
		infoTab = new JPanel();
		infoTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("����", null, infoTab, null);
		infoTab.setLayout(null);
		
		menuNameL = new JLabel("menu");
		menuNameL.setBounds(26, 196, 274, 34);
		menuNameL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuNameL);
		
		menuInfoL = new JLabel("");
		menuInfoL.setBounds(26, 240, 274, 82);
		menuInfoL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuInfoL);
		
		JButton addBt = new JButton("\uB2F4\uAE30");
		addBt.setBounds(159, 421, 91, 23);
		infoTab.add(addBt);
		
		//������ư
		hotRadioBt = new JRadioButton("HOT");
		iceRadioBt = new JRadioButton("ICE");
		ButtonGroup groupBt = new ButtonGroup();
		groupBt.add(hotRadioBt);
		groupBt.add(iceRadioBt);
		
		hotRadioBt.setBounds(72, 337, 79, 23);
		hotRadioBt.setBackground(new Color(240, 255, 240));
		infoTab.add(hotRadioBt);
		
		iceRadioBt.setBounds(202, 337, 79, 23);
		iceRadioBt.setBackground(new Color(240, 255, 240));
		infoTab.add(iceRadioBt);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(93, 422, 30, 22);
		infoTab.add(spinner);
		
		menuImg = new JLabel("");
		menuImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\Downloads\\coffee.png"));
		menuImg.setBounds(94, 38, 137, 127);
		infoTab.add(menuImg);
		
		JPanel shopTab = new JPanel();
		shopTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("��ٱ���", null, shopTab, null);

		JTable shopTable = new JTable();
//		presentTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
	
		
		String[] shopCol = {"�޴���", "�ɼ�", "����", "����"};
		DefaultTableModel shopDTM = new DefaultTableModel(shopCol, 100);
		shopTab.setLayout(null);
		shopTable = new JTable(shopDTM);
		JScrollPane presentScroll = new JScrollPane(shopTable);
		presentScroll.setBounds(0, 0, 330, 380);
		shopTab.add(presentScroll);
		
//		tabbedPane_1.add(shopTab);
		
		
		
		JButton cancelBt = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		cancelBt.setBounds(55, 432, 91, 23);
		shopTab.add(cancelBt);
		
		JButton orderBt = new JButton("\uC8FC\uBB38\uD558\uAE30");
		orderBt.setBounds(178, 432, 91, 23);
		shopTab.add(orderBt);
		
		JLabel orderL = new JLabel("\uC8FC \uBB38 \uD558 \uAE30");
		orderL.setBounds(332, 37, 148, 40);
		orderL.setFont(new Font("���� ���", Font.BOLD, 27));
		panel.add(orderL);
		
		
		americanoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfo("�Ƹ޸�ī��");
			}
		});
		
		latteImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("ī���");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/latte.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>ǳ���ϰ� ���� ���� ���������Ұ�<br> ������ ���� ������� ��� �� �ִ� Ŀ�� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		mochaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("ī���ī");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/mocha.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>���� ���ݸ� ��ī �÷��� ǳ���� ���������Ҹ� <br>������ ���� ����ũ������ �������� �����<br> ���� ���������ҿ� ���ݸ� ���� ��췯�� Ŀ�� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		cappuccinoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("īǪġ��");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/cappuccino.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>ǳ���ϰ� ���� ���������ҿ�<br> ������ ���� ���� ���� ��ǰ��<br> 1:1 ������ ��췯�� �������� Ŀ�� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		banillaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("�ٴҶ��");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/banilla.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>�����̾� �ٴҶ� �� �÷���<br> �ε巴�� ��췯�� ī�� �� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		mangoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("��������");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/mango.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>���� �м� �ĸ��� �ֽ��� �� Ƽ��<br> ����ϰ� ��췯�� ���� �����</html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		strawberryImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("���⽺����");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/strawberry.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>�� �� ���� ǳ���� ���� ������ <br>������ ������ ¥���ϰ� ������ ���� �����</html>");
				
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		lemonImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("�����̵�");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/lemon.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>������ ��ŭ�԰� �� ��� ź����<br> �ÿ����� �Բ� ��� �� �ִ� ���� </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				
			}
		});
		
		grapeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("�ڸ����̵�");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/grape.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>�ڸ��� �Խθ��� ���� ��ŭ����<br> ���ÿ� ���� �� ������,<br> ź���� û������ �Բ� ���� �� �ִ� ����</html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		chocoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("���ڶ�");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/choco.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>���� ��ī�÷��� �ε巯�� ����,<br> ������ ����ũ���� ����ڰ� ��ȭ�� �̷�� ���� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		greenteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("������");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/greentea.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>��� ���� ���� ������ ���� ���� <br>�ÿ��ϰ� �ε巴�� ��� �� �ִ� �� </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		peachImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("������ ���̽�Ƽ");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/peach.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>ȫ���� ���� ���� ǳ���� ������ ����<br> ��췯�� ������ ����ö �α� ���� </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				}
		});
		
		lemonteaImg.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("���� ���̽�Ƽ");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/lemonice.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>�Ծ� ���� ��ŭ�� �������� ������<br> ���޴����� ���� ���̽�Ƽ </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				

			}
		});
		
		this.setContentPane(panel);
	}
		
	// ��ǰ ���� ��������
	public void getProductInfo(String pname) {
		ProductVO pvo = pdao.selectProduct(pname);
		
		menuNameL.setText(pvo.getPname());
		menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource(pvo.getImgPath())));
		infoTab.add(menuImg);
		menuInfoL.setText(pvo.getInfo());
		hotRadioBt.setEnabled(false);
		iceRadioBt.setSelected(true);
	}
}
