package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import cart.CartDAO;
import cart.CartVO;
import detailorders.DetailOrdersDAO;
import orders.OrdersDAO;
import product.ProductDAO;
import product.ProductVO;
import server.frame.ServerAlarmFrame;

public class OrderFrame extends JFrame implements ActionListener, MouseListener {
	JPanel panel;
	
	JTable shopTable = new JTable();
	DefaultTableModel shopDTM = new DefaultTableModel();
	
	JTabbedPane tabbedPane;
	JTabbedPane tabbedPane_1;
	JPanel infoTab;
	JPanel shopTab;
	
	JLabel menuNameL;
	JLabel menuImg;
	JLabel menuInfoL;
	JRadioButton hotRadioBt;
	JRadioButton iceRadioBt;
	JLabel totalPriceL;
	
	SpinnerNumberModel spinnerModel;
	JSpinner spinner;
	
	JButton backSpace;
	JButton addBt;
	
	ProductDAO pdao;
	CartDAO cadao = new CartDAO();
	OrdersDAO odao = new OrdersDAO();
	DetailOrdersDAO dodao = new DetailOrdersDAO();
	
	String id;
	MainFrame main;
	String pname;
	
	ServerAlarmFrame alarm;
	
	public OrderFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;
		
		this.setTitle("주문창");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 857, 654);
		this.setLayout(null); // absoulute
		initialize();		
		
		eventList();
		this.setVisible(true);
		
	}

	public OrderFrame(MainFrame main, String id, String pname) {
		this.main = main;
		this.id = id;
		this.pname = pname;
		
		pdao = new ProductDAO();
		ProductVO pvo = pdao.selectProduct(pname);
		
		this.setTitle("주문창");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 857, 654);
		this.setLayout(null); // absoulute
		initialize();		
		
		if (pvo.getPoption().equals("T")) {
			getProductInfoT(pname);			
		} else if (pvo.getPoption().equals("O")){
			getProductInfoO(pname);
		}
		
		eventList();
		
		this.setVisible(true);
		
	}

	private void initialize() {
		
		panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 843, 617);
		panel.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.setBounds(36, 87, 418, 512);
		panel.add(tabbedPane);
		
		// 뒤로가기 버튼
		backSpace = new JButton("뒤로가기");
		backSpace.setBounds(700, 30, 100, 50);
		panel.add(backSpace);
		
		// 커피 탭
		JPanel coffeeTab = new JPanel();
//		coffeeTab.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		coffeeTab.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("커피", null, coffeeTab, null);
		coffeeTab.setLayout(null);
		
		// 커피 탭 라벨
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
		
		// 커피 탭 이미지
		JButton americanoImg = new JButton("");
		americanoImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/americano.jpg")));
		americanoImg.setForeground(new Color(240, 255, 240));
		americanoImg.setBounds(40, 33, 83, 60);
		coffeeTab.add(americanoImg);
		
		JButton latteImg = new JButton("");
		latteImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/latte.png")));
		latteImg.setBounds(40, 116, 83, 60);
		coffeeTab.add(latteImg);
		
		JButton mochaImg = new JButton("");
		mochaImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/mocha.png")));
		mochaImg.setBounds(40, 202, 83, 60);
		coffeeTab.add(mochaImg);
		
		JButton cappuccinoImg = new JButton("");
		cappuccinoImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/cappuccino.png")));
		cappuccinoImg.setBounds(40, 291, 83, 60);
		coffeeTab.add(cappuccinoImg);
		
		JButton banillaImg = new JButton("");
		banillaImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/banilla.png")));
		banillaImg.setBounds(40, 382, 83, 60);
		coffeeTab.add(banillaImg);
		
		JLabel banillaL = new JLabel("\uBC14\uB2D0\uB77C\uB77C\uB5BC");
		banillaL.setBounds(175, 392, 206, 41);
		coffeeTab.add(banillaL);
		
		// 스크롤
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
		
		// 음료 탭 라벨 및 이미지
		JLabel mangoL = new JLabel("\uB9DD\uACE0\uC2A4\uBB34\uB514");
		mangoL.setBounds(178, 32, 206, 41);
		panel_1.add(mangoL);
		
		JButton mangoImg = new JButton("");
		mangoImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mangoImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/mango.png")));
		mangoImg.setBounds(43, 22, 83, 60);
		panel_1.add(mangoImg);
		
		JButton strawberryImg = new JButton("");
		strawberryImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/strawberry.png")));
		strawberryImg.setBounds(43, 99, 83, 60);
		panel_1.add(strawberryImg);
		
		JLabel strawberryL = new JLabel("\uB538\uAE30\uC2A4\uBB34\uB514");
		strawberryL.setBounds(178, 109, 206, 41);
		panel_1.add(strawberryL);
		
		JButton lemonImg = new JButton("");
		lemonImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/lemon.png")));
		lemonImg.setBounds(43, 179, 83, 60);
		panel_1.add(lemonImg);
		
		JLabel lemonL = new JLabel("\uB808\uBAAC\uC5D0\uC774\uB4DC");
		lemonL.setBounds(178, 189, 206, 41);
		panel_1.add(lemonL);
		
		JButton grapeImg = new JButton("");
		grapeImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/grape.png")));
		grapeImg.setBounds(43, 259, 83, 60);
		panel_1.add(grapeImg);
		
		JLabel grapeL = new JLabel("\uC790\uBABD\uC5D0\uC774\uB4DC");
		grapeL.setBounds(178, 269, 206, 41);
		panel_1.add(grapeL);
		
		JButton chocoImg = new JButton("");
		chocoImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/choco.png")));
		chocoImg.setBounds(43, 341, 83, 60);
		panel_1.add(chocoImg);
		
		JLabel chocoL = new JLabel("\uCD08\uCF54\uB77C\uB5BC");
		chocoL.setBounds(178, 351, 206, 41);
		panel_1.add(chocoL);
		
		JButton greenteaImg = new JButton("");
		greenteaImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/greentea.png")));
		greenteaImg.setBounds(43, 421, 83, 60);
		panel_1.add(greenteaImg);
		
		JLabel greenteaL = new JLabel("\uB179\uCC28\uB77C\uB5BC");
		greenteaL.setBounds(178, 431, 206, 41);
		panel_1.add(greenteaL);
		
		JButton peachImg = new JButton("");
		peachImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/peach.png")));
		peachImg.setBounds(43, 501, 83, 60);
		panel_1.add(peachImg);
		
		JLabel peachL = new JLabel("\uBCF5\uC22D\uC544 \uC544\uC774\uC2A4\uD2F0");
		peachL.setBounds(178, 511, 206, 41);
		panel_1.add(peachL);
		
		JButton lemonteaImg = new JButton("");
		lemonteaImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/img/lemonice.png")));
		lemonteaImg.setBounds(43, 581, 83, 60);
		panel_1.add(lemonteaImg);
		
		JLabel lemonteaL = new JLabel("\uB808\uBAAC \uC544\uC774\uC2A4\uD2F0");
		lemonteaL.setBounds(178, 591, 206, 41);
		panel_1.add(lemonteaL);
		
		// 정보 탭
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(472, 87, 335, 512);
		panel.add(tabbedPane_1);
		
		infoTab = new JPanel();
		infoTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("정보", null, infoTab, null);
		infoTab.setLayout(null);
		
		menuNameL = new JLabel("menu");
		menuNameL.setBounds(26, 196, 274, 34);
		menuNameL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuNameL);
		
		menuInfoL = new JLabel("");
		menuInfoL.setBounds(26, 240, 274, 82);
		menuInfoL.setHorizontalAlignment(SwingConstants.CENTER);
		infoTab.add(menuInfoL);
		
		addBt = new JButton("\uB2F4\uAE30");
		addBt.setBounds(159, 421, 91, 23);
		infoTab.add(addBt);
		
		//라디오버튼
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
		
		// 수량
		spinnerModel = new SpinnerNumberModel();
		spinner = new JSpinner(spinnerModel);
		spinner.setBounds(93, 422, 30, 22);
		infoTab.add(spinner);
		
		menuImg = new JLabel("");
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/infoimg/coffee.png")));
		menuImg.setBounds(94, 38, 137, 127);
		infoTab.add(menuImg);
		
		shopTab = new JPanel();
		shopTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("장바구니", null, shopTab, null);
		
		
		//장바구니 탭에 테이블 설정
		String[] shopCol = {"메뉴명", "옵션", "수량", "가격"};
		shopDTM = new DefaultTableModel(shopCol, 0);
		shopTable = new JTable(shopDTM);
		
		//추가될때마다 사이즈 1씩 증가
		shopTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		shopTable.setAutoCreateRowSorter(true);
		shopTable.setCellSelectionEnabled(rootPaneCheckingEnabled);
		shopTab.setLayout(null);
		
		JScrollPane shopScroll = new JScrollPane(shopTable);
		shopScroll.setViewportView(shopTable);
		shopTab.add(shopScroll);
		shopScroll.setBounds(0, 0, 330, 380);
		
		// 총 금액
		totalPriceL = new JLabel();
		totalPriceL.setText("0\uC6D0");
		totalPriceL.setBounds(82, 390, 96, 21);
		shopTab.add(totalPriceL);		
		
		//주문취소 리스너
		JButton cancelBt = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		cancelBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = cadao.deleteCart(id);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "주문이 취소되었습니다.");
					shopDTM.setNumRows(0);
					initCartTable();								
				} else {
					JOptionPane.showConfirmDialog(null, "주문 취소를 할 수 없습니다.", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		cancelBt.setBounds(55, 432, 91, 23);
		shopTab.add(cancelBt);
		
		//주문하기 리스너
		JButton orderBt = new JButton("\uC8FC\uBB38\uD558\uAE30");
		orderBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resultOrders = odao.insertOrder(id);	// 주문 테이블에 등록
				// 주문 테이블에 insert 성공 시
				if (resultOrders > 0) {
					int orderID = odao.selectOrderID();	// 주문 번호 가져오기
					ArrayList<CartVO> calist = cadao.selectCartList(id);	// 카트 정보 가져오기
					for (CartVO cavo : calist) {
						int resultDO = dodao.insertDetailOrders(cavo, orderID);
						
						// 상세 주문 insert 실패 시 메소드 종료 (입력된 상세 주문 테이블, 주문 테이블 삭제)
						if (resultDO < 1) {
							dodao.deleteDetailOrders(orderID);
							odao.deleteOrder(orderID);
							JOptionPane.showConfirmDialog(null, "주문 실패", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					
					// 상세 주문 insert 성공 시
					JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.");
					
					// 장바구니 비우기
					cadao.deleteCart(id);
					shopDTM.setNumRows(0);
					initCartTable();
					
					alarm(id, orderID);
				// 주문 테이블에 insert 실패 시
				} else {
					JOptionPane.showConfirmDialog(null, "주문 완료를 할 수 없습니다.", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		orderBt.setBounds(178, 432, 91, 23);
		shopTab.add(orderBt);
		
		JLabel orderL = new JLabel("\uC8FC \uBB38 \uD558 \uAE30");
		orderL.setBounds(332, 37, 148, 40);
		orderL.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		panel.add(orderL);
		
		
		americanoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("아메리카노");
			}
		});
		
		latteImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("카페라떼");
			}
		});
		
		mochaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("카페모카");
			}
		});
		
		cappuccinoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("카푸치노");
			}
		});
		
		banillaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("바닐라라떼");
			}
		});
		
		mangoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("망고스무디");
			}
		});
		
		strawberryImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("딸기스무디");
			}
		});
		
		lemonImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("레몬에이드");
				
			}
		});
		
		grapeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("자몽에이드");
			}
		});
		
		chocoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("초코라떼");
			}
		});
		
		greenteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoT("녹차라떼");
			}
		});
		
		peachImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("복숭아 아이스티");
			}
		});
		
		lemonteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getProductInfoO("레몬 아이스티");
			}
		});
		
		this.setContentPane(panel);
	}
	
	// Ice만 있는 음료
	public void getProductInfoO(String pname) {
		pdao = new ProductDAO();
		ProductVO pvo = pdao.selectProduct(pname);
		
		menuNameL.setText(pvo.getPname());
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
		infoTab.add(menuImg);
		menuInfoL.setText(pvo.getInfo());
		hotRadioBt.setEnabled(false);
		iceRadioBt.setSelected(true);
	}
	
	// Hot, Ice 모두 존재하는 음료
	public void getProductInfoT(String pname) {
		pdao = new ProductDAO();
		ProductVO pvo = pdao.selectProduct(pname);
		
		menuNameL.setText(pvo.getPname());
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
		infoTab.add(menuImg);
		menuInfoL.setText(pvo.getInfo());
		hotRadioBt.setEnabled(true);
		iceRadioBt.setEnabled(true);
	}
	
	// 테이블에 장바구니 정보 담기
	public void initCartTable() {
		ArrayList<CartVO> calist = cadao.selectCart(id);
		addRowCart(calist);
		totalPriceL.setText("총 금액 : " + cadao.selectCartTotalPrice(id) + "원");
	}
	
	public void addRowCart(ArrayList<CartVO> calist) {
		String[] cartColumns = new String[4];
		for (CartVO cavo : calist) {
			cartColumns[0] = cavo.getPname() + "";
			cartColumns[1] = cavo.getCoption();
			cartColumns[2] = cavo.getCount() + ""; 
			cartColumns[3] = cavo.getCprice() + "";
			
			shopDTM.addRow(cartColumns);
		}
	}
	
	// 주문 알림 보내기
	public void alarm(String id, int orderID) {
		alarm = null;
		alarm = new ServerAlarmFrame(this, id, orderID);
	}
	
	// 이벤트
	public void eventList() {
		backSpace.addActionListener(this);
		addBt.addActionListener(this);
		tabbedPane_1.addMouseListener(this);
		
	}
	
	// 알림 메세지
	public void alarmMessage(String Message) {
		JOptionPane.showConfirmDialog(null, Message, "확인", JOptionPane.DEFAULT_OPTION,
				JOptionPane.DEFAULT_OPTION);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		
		if (tabbedPane_1 == e.getSource()) {	
			shopDTM.setNumRows(0);
			initCartTable();			
//			shopDTM.setColumnIdentifiers(new String[] {"메뉴명", "옵션", "수량", "가격"});
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 뒤로가기
		if (backSpace == e.getSource()) {

			main = null;
			main = new MainFrame(this, id);
			dispose();

		}
		
		// 담기 버튼
		else if (addBt == e.getSource()) {
			String menu = menuNameL.getText();
			int count = Integer.parseInt(spinner.getValue().toString());
			ProductVO pvo = null;
			if (!menu.equals("menu")) {
				pvo = pdao.selectProduct(menu);				
			}
			
			//메뉴 선택을 안했을 경우
			if(menuNameL.getText().equals("menu")) {
				JOptionPane.showConfirmDialog(null, "메뉴를 선택해주세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				
			//옵션 선택을 안했을 경우	
			} else if(!hotRadioBt.isSelected() && !iceRadioBt.isSelected()){
				JOptionPane.showConfirmDialog(null, "HOT/ICE를 선택해주세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				
			//수량을 0개 이하로 했을 경우	
			} else if(count <= 0) {
				System.out.println(count);
				JOptionPane.showConfirmDialog(null, "1잔 이상 선택해주세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				
			} else {
				String option = "";
				if(hotRadioBt.isSelected()) {
					option = "HOT";
				}else {
					option = "ICE";
				}
				cadao.insertCart(pvo.getProdID(), id, option, count);
				JOptionPane.showMessageDialog(null, "장바구니로 이동되었습니다.");
			}

		}

	}
}