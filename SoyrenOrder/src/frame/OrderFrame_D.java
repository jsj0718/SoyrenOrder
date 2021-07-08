package frame;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class OrderFrame_D {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame_D window = new OrderFrame_D();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderFrame_D() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uC8FC\uBB38");
		frame.setBounds(100, 100, 857, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 843, 617);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.setBounds(36, 87, 418, 512);
		panel.add(tabbedPane);
		
		JPanel coffeeTab = new JPanel();
//		coffeeTab.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		coffeeTab.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("커피", null, coffeeTab, null);
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
		tabbedPane.addTab("음료", null, scrollPane, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
//		JScrollPane jScrollPane = new JScrollPane();//스크롤팬 생성

//		JPanel panel_ex = new JPanel();//스크롤팬에 붙일 패널 생성

		Dimension size = new Dimension();//사이즈를 지정하기 위한 객체 생성

		size.setSize(1000, 1000);//객체의 사이즈를 지정

		panel_1.setPreferredSize(size);//사이즈 정보를 가지고 있는 객체를 이용해 패널의 사이즈 지정

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
		
		JPanel infoTab = new JPanel();
		infoTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("정보", null, infoTab, null);
		infoTab.setLayout(null);
		
		JLabel menuNameL = new JLabel("menu");
		menuNameL.setBounds(26, 196, 274, 34);
		menuNameL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuNameL);
		
		JLabel menuInfoL = new JLabel("");
		menuInfoL.setBounds(26, 240, 274, 82);
		menuInfoL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuInfoL);
		
		JButton addBt = new JButton("\uB2F4\uAE30");
		addBt.setBounds(159, 421, 91, 23);
		infoTab.add(addBt);
		
		//라디오버튼
		JRadioButton hotRadioBt = new JRadioButton("HOT");
		JRadioButton iceRadioBt = new JRadioButton("ICE");
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
		
		JLabel menuImg = new JLabel("");
		menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/coffee.png")));
		menuImg.setBounds(94, 38, 137, 127);
		infoTab.add(menuImg);
		
		JPanel shopTab = new JPanel();
		shopTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("장바구니", null, shopTab, null);

		JTable shopTable = new JTable();
	
		
		String[] shopCol = {"메뉴명", "옵션", "수량", "가격"};
		DefaultTableModel shopDTM = new DefaultTableModel(shopCol, 100);
		shopTab.setLayout(null);
		shopTable = new JTable(shopDTM);
		JScrollPane presentScroll = new JScrollPane(shopTable);
		presentScroll.setBounds(0, 0, 330, 380);
		shopTab.add(presentScroll);
		
		
		
		
		
		
		JButton cancelBt = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		cancelBt.setBounds(55, 432, 91, 23);
		shopTab.add(cancelBt);
		
		JButton orderBt = new JButton("\uC8FC\uBB38\uD558\uAE30");
		orderBt.setBounds(178, 432, 91, 23);
		shopTab.add(orderBt);
		
		JLabel orderL = new JLabel("\uC8FC \uBB38 \uD558 \uAE30");
		orderL.setBounds(332, 37, 148, 40);
		orderL.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		panel.add(orderL);
		
		
		americanoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("아메리카노");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/americano.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>진한 에스프레소에 정수물을 더하여<br>깔끔하고 강렬한 에스프레소를 <br>가장 부드럽게 즐길 수 있는 커피</html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		latteImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("카페라떼");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/latte.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>풍부하고 진한 농도의 에스프레소가<br> 우유와 만나 고소함을 즐길 수 있는 커피 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		mochaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("카페모카");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/mocha.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>진한 초콜릿 모카 시럽과 풍부한 에스프레소를 <br>우유와 섞어 휘핑크림으로 마무리한 음료로<br> 진한 에스프레소와 초콜릿 맛이 어우러진 커피 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		cappuccinoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("카푸치노");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/cappuccino.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>풍부하고 진한 에스프레소에<br> 우유와 벨벳 같은 우유 거품이<br> 1:1 비율로 어우러져 마무리된 커피 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		banillaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("바닐라라떼");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/banilla.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>프리미엄 바닐라 빈 시럽이<br> 부드럽게 어우러진 카페 라떼 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		mangoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("망고스무디");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/mango.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>망고 패션 후르츠 주스와 블랙 티가<br> 깔끔하게 어우러진 과일 블렌디드</html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		strawberryImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("딸기스무디");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/strawberry.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>입 안 가득 풍부한 딸기 과육이 <br>무더운 여름을 짜릿하게 날려줄 딸기 블렌디드</html>");
				
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		lemonImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("레몬에이드");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/lemon.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>레몬의 상큼함과 톡 쏘는 탄산의<br> 시원함을 함께 즐길 수 있는 음료 </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				
			}
		});
		
		grapeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("자몽에이드");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/grape.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>자몽의 쌉싸름한 맛과 상큼함을<br> 동시에 느낄 수 있으며,<br> 탄산의 청량함을 함께 맛볼 수 있는 음료</html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
			}
		});
		
		chocoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("초코라떼");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/choco.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>진한 모카시럽과 부드러운 우유,<br> 달콤한 휘핑크림의 삼박자가 조화를 이루는 음료 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		greenteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("녹차라떼");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/greentea.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>깊고 진한 말차 본연의 맛과 향을 <br>시원하고 부드럽게 즐길 수 있는 라떼 </html>");
				hotRadioBt.setEnabled(true);
				iceRadioBt.setEnabled(true);
			}
		});
		
		peachImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("복숭아 아이스티");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/peach.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>홍차의 깊은 맛과 풍부한 복숭아 향이<br> 어우러진 달콤한 여름철 인기 음료 </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				}
		});
		
		lemonteaImg.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				menuNameL.setText("레몬 아이스티");
				menuImg.setIcon(new ImageIcon(OrderFrame_D.class.getResource("/frame/infoImg/lemonice.png")));
				infoTab.add(menuImg);
				menuInfoL.setText("<html>입안 가득 상큼한 레몬향이 퍼지는<br> 새콤달콤한 맛의 아이스티 </html>");
				hotRadioBt.setEnabled(false);
				iceRadioBt.setSelected(true);
				

			}
		});
		
	}
}
