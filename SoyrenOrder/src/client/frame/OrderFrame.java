package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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

import cart.CartVO;
import client.program.ClientHandler;
import detailorders.DetailOrdersVO;
import message.CartMessage;
import message.DetailOrdersMessage;
import message.Message;
import message.OrdersMessage;
import message.ProductMessage;
import orders.OrdersVO;
import product.ProductVO;

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

	String id;
	MainFrame main;
	String pname;

	int orderID;
	ArrayList<CartVO> calist;

	public OrderFrame(MainFrame main, String id) {
		this.main = main;
		this.id = id;

		this.setTitle("�ֹ�â");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 857, 654);
		getContentPane().setLayout(null); // absoulute
		initialize();

		eventList();
		this.setVisible(true);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public OrderFrame(MainFrame main, String id, String pname) {
		this.main = main;
		this.id = id;
		this.pname = pname;

		this.setTitle("�ֹ�â");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 857, 654);
		getContentPane().setLayout(null); // absoulute
		initialize();

		productSelect(pname);

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

		// �ڷΰ��� ��ư
		backSpace = new JButton("\u25C0");
		backSpace.setFont(new Font("����", Font.BOLD, 25));
		backSpace.setBorderPainted(false);
		backSpace.setBackground(new Color(107, 142, 35));
		backSpace.setBounds(36, 27, 100, 50);
		panel.add(backSpace);

		// Ŀ�� ��
		JPanel coffeeTab = new JPanel();
//		coffeeTab.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		coffeeTab.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Ŀ��", null, coffeeTab, null);
		coffeeTab.setLayout(null);

		// Ŀ�� �� ��
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

		// Ŀ�� �� �̹���
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

		// ��ũ��
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

		Dimension size = new Dimension();// ����� �����ϱ� ���� ��ü ����

		size.setSize(1000, 700);// ��ü�� ����� ����

		panel_1.setPreferredSize(size);// ������ ������ ������ �ִ� ��ü�� �̿��� �г��� ������ ����

		scrollPane.setViewportView(panel_1);

		// ���� �� �� �� �̹���
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

		// ���� ��
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
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

		addBt = new JButton("\uB2F4\uAE30");
		addBt.setBackground(new Color(255, 255, 240));
		addBt.setBounds(159, 421, 91, 23);
		addBt.setBorderPainted(false);
		infoTab.add(addBt);

		// ������ư
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

		// ����
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		spinner = new JSpinner(spinnerModel);
		spinner.setBounds(93, 422, 30, 22);
//		spinner.setValue(0);
		infoTab.add(spinner);

		menuImg = new JLabel("");
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource("/image/infoimg/coffee.png")));
		menuImg.setBounds(94, 38, 137, 127);
		infoTab.add(menuImg);

		shopTab = new JPanel();
		shopTab.setBackground(new Color(240, 255, 240));
		tabbedPane_1.addTab("��ٱ���", null, shopTab, null);

		// ��ٱ��� �ǿ� ���̺� ����
		String[] shopCol = { "�޴���", "�ɼ�", "����", "����" };
		shopDTM = new DefaultTableModel(shopCol, 0);
		shopTable = new JTable(shopDTM);

		// �߰��ɶ����� ������ 1�� ����
		shopTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		shopTable.setAutoCreateRowSorter(true);
		shopTable.setCellSelectionEnabled(rootPaneCheckingEnabled);
		shopTab.setLayout(null);

		JScrollPane shopScroll = new JScrollPane(shopTable);
		shopScroll.setViewportView(shopTable);
		shopTab.add(shopScroll);
		shopScroll.setBounds(0, 0, 330, 380);

		// �� �ݾ�
		totalPriceL = new JLabel();
		totalPriceL.setText("0\uC6D0");
		totalPriceL.setBounds(82, 390, 96, 21);
		shopTab.add(totalPriceL);

		// �ֹ���� ������
		JButton cancelBt = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		cancelBt.setBackground(new Color(255, 255, 240));
		cancelBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartMessage camsg = new CartMessage();
				CartVO cavo = new CartVO();
				cavo.setCustID(id);
				camsg.setState(3);
				camsg.setCavo(cavo);

				try {
					ClientHandler.oos.writeObject(camsg);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
				cartSelectAll();
			}
		});
		cancelBt.setBounds(55, 432, 91, 23);
		shopTab.add(cancelBt);

		// �ֹ��ϱ� ������
		JButton orderBt = new JButton("\uC8FC\uBB38\uD558\uAE30");
		orderBt.setBackground(new Color(255, 255, 240));
		orderBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartMessage camsg = new CartMessage();
				CartVO cavo = new CartVO();
				cavo.setCustID(id);
				camsg.setState(4);
				camsg.setCavo(cavo);

				try {
					ClientHandler.oos.writeObject(camsg);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}

				OrdersMessage omsg = new OrdersMessage();
				OrdersVO ovo = new OrdersVO();
				ovo.setCustID(id);
				omsg.setState(2);
				omsg.setOvo(ovo);

				try {
					ClientHandler.oos.writeObject(omsg);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		orderBt.setBounds(178, 432, 91, 23);
		shopTab.add(orderBt);

		JLabel orderL = new JLabel("\uC8FC \uBB38 \uD558 \uAE30");
		orderL.setBounds(347, 27, 148, 40);
		orderL.setFont(new Font("���� ���", Font.BOLD, 27));
		panel.add(orderL);

		americanoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("�Ƹ޸�ī��");
				productSelect("�Ƹ޸�ī��");
			}
		});

		latteImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("ī���");
				productSelect("ī���");
			}
		});

		mochaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("ī���ī");
				productSelect("ī���ī");
			}
		});

		cappuccinoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("īǪġ��");
				productSelect("īǪġ��");
			}
		});

		banillaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("�ٴҶ��");
				productSelect("�ٴҶ��");
			}
		});

		mangoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("��������");
				productSelect("��������");
			}
		});

		strawberryImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("���⽺����");
				productSelect("���⽺����");
			}
		});

		lemonImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("�����̵�");
				productSelect("�����̵�");

			}
		});

		grapeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("�ڸ����̵�");
				productSelect("�ڸ����̵�");
			}
		});

		chocoImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("���ڶ�");
				productSelect("���ڶ�");
			}
		});

		greenteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoT("������");
				productSelect("������");
			}
		});

		peachImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("������ ���̽�Ƽ");
				productSelect("������ ���̽�Ƽ");
			}
		});

		lemonteaImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getProductInfoO("���� ���̽�Ƽ");
				productSelect("���� ���̽�Ƽ");
			}
		});

		this.setContentPane(panel);
	}

//	// Ice�� �ִ� ����
//	public void getProductInfoO(String pname) {
//		pdao = new ProductDAO();
//		ProductVO pvo = pdao.selectProduct(pname);
//
//		menuNameL.setText(pvo.getPname());
//		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
//		infoTab.add(menuImg);
//		menuInfoL.setText(pvo.getInfo());
//		hotRadioBt.setEnabled(false);
//		iceRadioBt.setSelected(true);
//		spinner.setValue(0);
//		tabbedPane_1.setSelectedIndex(0);
//	}
//
//	// Hot, Ice ��� �����ϴ� ����
//	public void getProductInfoT(String pname) {
//		pdao = new ProductDAO();
//		ProductVO pvo = pdao.selectProduct(pname);
//
//		menuNameL.setText(pvo.getPname());
//		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
//		infoTab.add(menuImg);
//		menuInfoL.setText(pvo.getInfo());
//		hotRadioBt.setEnabled(true);
//		iceRadioBt.setEnabled(true);
//		spinner.setValue(0);
//		tabbedPane_1.setSelectedIndex(0);
//	}

	// �ʱⵥ���� ��û
	public void initTableRequest() {
		cartSelectAll();
	}

	// �ʱⵥ���� ����
	public void initTableResponse(Message msg) {
		if (msg instanceof CartMessage) {
			CartMessage camsg = (CartMessage) msg;
			shopDTM.setNumRows(0);
			addRowCart(camsg.getCalist());
			totalPriceL.setText("�� �ݾ� : " + camsg.getResult() + "��");
		}

	}

	// ��ٱ��� ��ȸ
	public void cartSelectAll() {
		CartMessage camsg = new CartMessage();
		CartVO cavo = new CartVO();
		cavo.setCustID(id);
		camsg.setState(1);
		camsg.setCavo(cavo);
		try {
			ClientHandler.oos.writeObject(camsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		}
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

	// ��ٱ��� ���
	public void insertCart(CartMessage caMsg) {
		if (caMsg.getResult() == 1) {
			JOptionPane.showMessageDialog(null, "��ٱ��Ϸ� �̵��Ǿ����ϴ�.");

		} else {
			JOptionPane.showConfirmDialog(null, "��ٱ��� ��⿡ �����߽��ϴ�. Ȯ���ϼ���.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}

	// ��ٱ��� ����
	public void deleteCart(CartMessage caMsg) {
		if (caMsg.getResult() > 0) {
			JOptionPane.showMessageDialog(null, "�ֹ��� ����մϴ�.");
		} else {
			JOptionPane.showConfirmDialog(null, "�ֹ� ��Ҹ� ���� ���߽��ϴ�. Ȯ���ϼ���.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}

	// ��ٱ��� �ʱ�ȭ
	public void initCart(CartMessage caMsg) {
		if (caMsg.getResult() > 0) {

		} else {
			JOptionPane.showConfirmDialog(null, "��ٱ��� �ʱ�ȭ�� �����߽��ϴ�. Ȯ���ϼ���.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}

	// �ֹ��ϱ�
	public void insertOrders(Message msg) {
		if (msg instanceof OrdersMessage) {
			OrdersMessage omsg = (OrdersMessage) msg;
			if (omsg.getState() == 2) {
				orderID = omsg.getResult();

				DetailOrdersMessage domsg = new DetailOrdersMessage();
				DetailOrdersVO dovo = new DetailOrdersVO();
				dovo.setOrderID(orderID);
				dovo.setCalist(calist);
				dovo.setCustID(id);
				domsg.setState(2);
				domsg.setDovo(dovo);

				try {
					ClientHandler.oos.writeObject(domsg);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showConfirmDialog(null, "�ֹ� �ϷḦ �� �� �����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (msg instanceof DetailOrdersMessage) {
			DetailOrdersMessage domsg = (DetailOrdersMessage) msg;
			if (domsg.getResult() == calist.size()) {
				CartMessage camsg = new CartMessage();
				CartVO cavo = new CartVO();
				cavo.setCustID(id);
				camsg.setState(5);
				camsg.setCavo(cavo);

				try {
					ClientHandler.oos.writeObject(camsg);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cartSelectAll();

				JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ�Ǿ����ϴ�.");
			} else {
				JOptionPane.showConfirmDialog(null, "�ֹ� �ϷḦ �� �� �����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (msg instanceof CartMessage) {
			CartMessage cmsg = (CartMessage) msg;
			calist = cmsg.getCalist();
			if (calist.size() < 1) {
				JOptionPane.showConfirmDialog(null, "��ٱ��ϸ� ä���ּ���.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	// �̺�Ʈ
	public void eventList() {
		backSpace.addActionListener(this);
		addBt.addActionListener(this);
		tabbedPane_1.addMouseListener(this);

	}

	// �˸� �޼���
	public void alarmMessage(String Message) {
		JOptionPane.showConfirmDialog(null, Message, "Ȯ��", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (tabbedPane_1 == e.getSource()) {
			shopDTM.setNumRows(0);
			cartSelectAll();
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
		// �ڷΰ���
		if (backSpace == e.getSource()) {

			panel.removeAll();
			main.initTableRequest();
			main.setVisible(true);
			dispose();

		}

		// ��� ��ư
		else if (addBt == e.getSource()) {
			String menu = menuNameL.getText();
			int count = Integer.parseInt(spinner.getValue().toString());

			if (!menu.equals("menu") && count != 0 && (hotRadioBt.isSelected() || iceRadioBt.isSelected())) {
				ProductMessage pmsg = new ProductMessage();
				ProductVO pvo = new ProductVO();
				pvo.setPname(menu);
				pmsg.setState(3);
				pmsg.setCount(count);
				pmsg.setPvo(pvo);
				try {
					ClientHandler.oos.writeObject(pmsg);
					ClientHandler.oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
//				e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			}

			// �޴� ������ ������ ���
			if (menuNameL.getText().equals("menu")) {
				JOptionPane.showConfirmDialog(null, "�޴��� �������ּ���.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);

				// �ɼ� ������ ������ ���
			} else if (!hotRadioBt.isSelected() && !iceRadioBt.isSelected()) {
				JOptionPane.showConfirmDialog(null, "HOT/ICE�� �������ּ���.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
				// ������ 0�� ���Ϸ� ���� ���
			} else if (count <= 0) {
				JOptionPane.showConfirmDialog(null, "1�� �̻� �������ּ���.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			} else if (!(count + "").matches("[+-]?\\d*(\\.\\d+)?")) {
				JOptionPane.showConfirmDialog(null, "�ùٸ� ���ڰ� �ƴմϴ�.", "���", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	// ��ǰ ���� ��ȸ ����
	public void productResponse(ProductMessage pmsg) {
		ProductVO pvo = pmsg.getPvo();
		if (pvo.getPoption().equals("T")) {
			getProductInfoT(pvo);
		} else if (pvo.getPoption().equals("O")) {
			getProductInfoO(pvo);
		}
	}

	// ��ǰ ���� ��ȸ
	public void productSelect(String pname) {
		ProductMessage pmsg = new ProductMessage();
		ProductVO pvo = new ProductVO();
		pvo.setPname(pname);
		pmsg.setState(2);
		pmsg.setPvo(pvo);
		try {
			ClientHandler.oos.writeObject(pmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//				e1.printStackTrace();
			JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Ice�� �ִ� ����
	public void getProductInfoO(ProductVO pvo) {
//			pdao = new ProductDAO();
//			ProductVO pvo = pdao.selectProduct(pname);

		menuNameL.setText(pvo.getPname());
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
		infoTab.add(menuImg);
		menuInfoL.setText(pvo.getInfo());
		hotRadioBt.setEnabled(false);
		iceRadioBt.setSelected(true);
		spinner.setValue(0);
		tabbedPane_1.setSelectedIndex(0);

		repaint();
	}

	// Hot, Ice ��� �����ϴ� ����
	public void getProductInfoT(ProductVO pvo) {
//			pdao = new ProductDAO();
//			ProductVO pvo = pdao.selectProduct(pname);

		menuNameL.setText(pvo.getPname());
		menuImg.setIcon(new ImageIcon(OrderFrame.class.getResource(pvo.getImgPath())));
		infoTab.add(menuImg);
		menuInfoL.setText(pvo.getInfo());
		hotRadioBt.setEnabled(true);
		iceRadioBt.setEnabled(true);
		spinner.setValue(0);
		tabbedPane_1.setSelectedIndex(0);

		repaint();
	}
	
	public void addToMenu(ProductMessage pmsg) {
		if (pmsg.getState()==4) {
			getProductInfoO(pmsg.getPvo());
		}
		else if (pmsg.getState()==5) {
			getProductInfoT(pmsg.getPvo());
		}
	}

	public void addCart(ProductMessage pmsg) {
		ProductVO pvo = pmsg.getPvo();
		int count = pmsg.getCount();
		String option = "";
		if (hotRadioBt.isSelected()) {
			option = "HOT";
		} else {
			option = "ICE";
		}
		
		
		CartMessage camsg = new CartMessage();
		CartVO cavo = new CartVO();
		cavo.setCustID(id);
		cavo.setProdID(pvo.getProdID());
		cavo.setCoption(option);
		cavo.setCount(count);
		camsg.setState(2);
		camsg.setCavo(cavo);

		try {
			ClientHandler.oos.writeObject(camsg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
//			JOptionPane.showConfirmDialog(null, "�����Ǿ����ϴ�.", "���", JOptionPane.DEFAULT_OPTION,
//					JOptionPane.WARNING_MESSAGE);
		}

	}
}