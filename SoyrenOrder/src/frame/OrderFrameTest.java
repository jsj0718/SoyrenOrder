package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class OrderFrameTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrameTest window = new OrderFrameTest();
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
	public OrderFrameTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 843, 617);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(36, 87, 418, 512);
		panel.add(tabbedPane);
		
		JPanel coffeeTab = new JPanel();
		tabbedPane.addTab("커피", null, coffeeTab, null);
		coffeeTab.setLayout(null);
		
		JLabel americanoL = new JLabel("\uC544\uBA54\uB9AC\uCE74\uB178");
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
		
		JButton americanoImg = new JButton("\uC544\uBA54\uB9AC\uCE74\uB178");
		americanoImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uC0AC\uC9C4\\Saved Pictures\\cloudc+02.png"));
		americanoImg.setBounds(40, 33, 83, 60);
		coffeeTab.add(americanoImg);
		
		JButton latteImg = new JButton("\uCE74\uD398\uB77C\uB5BC");
		latteImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uC0AC\uC9C4\\Saved Pictures\\cloudc+05.png"));
		latteImg.setBounds(40, 116, 83, 60);
		coffeeTab.add(latteImg);
		
		JButton mochaImg = new JButton("\uCE74\uD398\uBAA8\uCE74");
		mochaImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uC0AC\uC9C4\\Saved Pictures\\AAE2txO.jpg"));
		mochaImg.setBounds(40, 202, 83, 60);
		coffeeTab.add(mochaImg);
		
		JButton cappuccinoImg = new JButton("\uCE74\uD478\uCE58\uB178");
		cappuccinoImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uC0AC\uC9C4\\Saved Pictures\\Mood+01.png"));
		cappuccinoImg.setBounds(40, 291, 83, 60);
		coffeeTab.add(cappuccinoImg);
		
		JButton banillaImg = new JButton("\uBC14\uB2D0\uB77C\uB77C\uB5BC");
		banillaImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uBB38\uC11C\\\uCE74\uCE74\uC624\uD1A1 \uBC1B\uC740 \uD30C\uC77C\\KakaoTalk_20201208_184900857.jpg"));
		banillaImg.setBounds(40, 382, 83, 60);
		coffeeTab.add(banillaImg);
		
		JLabel banillaL = new JLabel("\uBC14\uB2D0\uB77C\uB77C\uB5BC");
		banillaL.setBounds(175, 392, 206, 41);
		coffeeTab.add(banillaL);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(472, 87, 335, 512);
		panel.add(tabbedPane_1);
		
		JPanel infoTab = new JPanel();
		tabbedPane_1.addTab("정보", null, infoTab, null);
		infoTab.setLayout(null);
		
		JLabel menuImg = new JLabel("New label");
		menuImg.setIcon(new ImageIcon("C:\\Users\\kjs64\\OneDrive\\\uC0AC\uC9C4\\Saved Pictures\\cloudc+05.png"));
		menuImg.setBounds(96, 27, 127, 121);
		infoTab.add(menuImg);
		
		JLabel menuNameL = new JLabel("\uBA54\uB274\uC774\uB984");
		menuNameL.setHorizontalAlignment(SwingConstants.CENTER);
		menuNameL.setBounds(26, 196, 274, 34);
		infoTab.add(menuNameL);
		
		JLabel menuInfo = new JLabel("\uBA54\uB274\uC124\uBA85");
		menuInfo.setHorizontalAlignment(SwingConstants.CENTER);
		menuInfo.setBounds(26, 240, 274, 60);
		infoTab.add(menuInfo);
		
		JButton addBt = new JButton("\uB2F4\uAE30");
		addBt.setBounds(132, 421, 91, 23);
		infoTab.add(addBt);
		
		JRadioButton hotRadioBt = new JRadioButton("HOT");
		hotRadioBt.setBounds(72, 323, 79, 23);
		infoTab.add(hotRadioBt);
		
		JRadioButton rdbtnIce = new JRadioButton("ICE");
		rdbtnIce.setBounds(202, 323, 79, 23);
		infoTab.add(rdbtnIce);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(72, 422, 30, 22);
		infoTab.add(spinner);
		
		JPanel shopTab = new JPanel();
		tabbedPane_1.addTab("장바구니", null, shopTab, null);
		
		JLabel lblNewLabel = new JLabel("\uC8FC \uBB38 \uD558 \uAE30");
		lblNewLabel.setBounds(332, 37, 148, 40);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		panel.add(lblNewLabel);
	}
}
