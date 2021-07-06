package frame;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OrderFramd_D {
	
	   // 전체 패널
	   // 배경 이미지 삽입
	   static JPanel totalPanel=new JPanel() {
	      //이미지 읽어오기
	      Image backGroundImg=new ImageIcon("./img/backgound.png").getImage();
	      //창 크기에 맞게 변경
	      Image scaledBackGroundImg = backGroundImg.getScaledInstance(800, 550, Image.SCALE_DEFAULT);
	      Image backGroundFinalImg = new ImageIcon(scaledBackGroundImg).getImage();
	      
	      public void paintComponent(Graphics g) {//그리는 함수
	         g.drawImage(backGroundFinalImg, 0, 0, null);//backgroundImg를 그려줌   
	         setOpaque(false); //투명하게
	            super.paintComponent(g);

	      }
	   };   
	   

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFramd_D window = new OrderFramd_D();
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
	public OrderFramd_D() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uC8FC\uBB38");
		frame.setBounds(100, 100, 813, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane menuTabPanel = new JTabbedPane(JTabbedPane.TOP);
		menuTabPanel.setBounds(12, 70, 433, 426);
		frame.getContentPane().add(menuTabPanel);
		
		JScrollPane coffeeScroll = new JScrollPane();
		menuTabPanel.addTab("커피", null, coffeeScroll, null);
		
		
		JScrollPane noncoffeeScroll = new JScrollPane();
		menuTabPanel.addTab("음료", null, noncoffeeScroll, null);
		
		JTabbedPane orderTabPanel = new JTabbedPane(JTabbedPane.TOP);
		orderTabPanel.setBounds(468, 70, 308, 426);
		frame.getContentPane().add(orderTabPanel);
		
		JScrollPane infoScroll = new JScrollPane();
		orderTabPanel.addTab("정보", null, infoScroll, null);
		
		JScrollPane shopScroll = new JScrollPane();
		orderTabPanel.addTab("장바구니", null, shopScroll, null);
		
		JLabel orderL = new JLabel("\uC8FC\uBB38\uD558\uAE30");
		orderL.setBounds(178, 25, 122, 45);
		orderL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		frame.getContentPane().add(orderL);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 787, 506);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		

	}
}
