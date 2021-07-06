package frame;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class OrderFrame extends JFrame{
	
	
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
	   
	   // 책 정보 패널
	   JPanel menuDetailPanel;
	   // 주문 정보 패널
	   JPanel ordersDetailPanel;
	   // tab 패널
	   JTabbedPane tab;

	
	   // 제목, 아이디, 로그아웃 버튼
	   JLabel titleL;
	   JLabel custIdL;

	   // 책 정보 패널 내용
	   JLabel imgL;
	   JLabel imgVal;
//	   JLabel bookIdVal;
	   JLabel menuNameL;
	   JLabel menuNameVal;
	   JLabel priceL;
	   JLabel priceVal;
	   JLabel infoL;
	   JLabel infoVal;
	   
	   // 주문 정보 패널 내용
	   JLabel orderIdL;
	   JLabel ordermenuNameL;
	   JLabel orderSalePriceL;
	   JLabel orderCntL;
	   JLabel orderDateL;

	   JLabel orderIdVal;
	   JLabel orderBookNameVal;
	   JLabel orderSalePriceVal;
	   JLabel orderCntVal;
	   JLabel orderDateVal;
	   
	   JButton orderCancelBt;
	   JSpinner cnt;
	   JButton orderBt;
	   
	   JScrollPane menuScroll;
	   JScrollPane orderScroll;
	   
	   public OrderFrame() {

		      this.setTitle("주문하기");
		      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		      this.setBounds(200, 200, 800, 550);
		      this.setLayout(new FlowLayout());
		      // 화면구성
		      setComponent();

		      // 초기 데이터 설정
		      initTableRequest();

		      // 이벤트 모음
		      eventList();

		      this.setVisible(true);
		   }
	   
	   public void setComponent() {
		   
		   totalPanel.setLayout(null);

		      menuDetailPanel = new JPanel();
		      menuDetailPanel.setLayout(null);
		      menuDetailPanel.setBorder(new EtchedBorder());

		      ordersDetailPanel = new JPanel();
		      ordersDetailPanel.setLayout(null);
		      ordersDetailPanel.setBorder(new EtchedBorder());

		      tab = new JTabbedPane();

		      titleL = new JLabel();
		      titleL.setText("마 당 서 점");
		      titleL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		      titleL.setHorizontalAlignment(JLabel.CENTER);
		      custIdL = new JLabel();
		      custIdL.setText("id" + "님 환영합니다.");



		      // 메뉴정보, 메뉴담기
//		      imgL = new JLabel();
//		      imgVal = new JLabel();
//		      imgL.setBorder(new EtchedBorder());
//		      bookIdVal = new JLabel();
		      menuNameL = new JLabel();
		      menuNameL.setText("메뉴이름 :");
		      menuNameVal = new JLabel();
		      
		      infoL = new JLabel();
		      infoL.setText("메뉴정보 : ");
		      infoVal = new JLabel();
		      
		      priceL = new JLabel();
		      priceL.setText("가 격 : ");
		      priceVal = new JLabel();
		      
		      cnt = new JSpinner();
		      orderBt = new JButton();
		      orderBt.setText("담기");

		      // 장바구니
		      orderIdL = new JLabel();
		      orderIdL.setText("주문번호 : ");
		      ordermenuNameL = new JLabel();
		      ordermenuNameL.setText("책이름 : ");
		      orderSalePriceL = new JLabel();
		      orderSalePriceL.setText("주문가격 : ");
		      orderCntL = new JLabel();
		      orderCntL.setText("주문수량 : ");
		      orderDateL = new JLabel();
		      orderDateL.setText("주문일자");

		      orderIdVal = new JLabel();
		      orderBookNameVal = new JLabel();
		      orderSalePriceVal = new JLabel();
		      orderCntVal = new JLabel();
		      orderDateVal = new JLabel();
		      orderCancelBt = new JButton();
		      orderCancelBt.setText("주문취소");

		      totalPanel.setBounds(12, 10, 771, 494);
		      menuDetailPanel.setBounds(462, 89, 297, 395);
		      ordersDetailPanel.setBounds(462, 89, 297, 395);
		      titleL.setBounds(121, 14, 215, 42);
		      custIdL.setBounds(635, 15, 100, 20);
//		      logOutBt.setBounds(645, 35, 90, 25);

		      tab.setBounds(30, 66, 420, 418);

		      // 책 내역 위치
		      imgL.setBounds(67, 10, 171, 165);
		      menuNameL.setBounds(22, 199, 75, 31);
		      priceL.setBounds(22, 240, 75, 31);
		      infoL.setBounds(22, 281, 75, 31);
		      menuNameVal.setBounds(109, 199, 165, 31);
		      priceVal.setBounds(109, 240, 165, 31);
		      infoVal.setBounds(109, 281, 165, 31);
		      cnt.setBounds(22, 341, 75, 31);
		      orderBt.setBounds(109, 338, 108, 36);

		      // 주문내역 위치
		      orderIdL.setBounds(22, 31, 91, 36);
		      ordermenuNameL.setBounds(22, 87, 91, 36);
		      orderSalePriceL.setBounds(22, 143, 91, 36);
		      orderCntL.setBounds(22, 199, 91, 36);
		      orderDateL.setBounds(22, 255, 91, 36);
		      orderIdVal.setBounds(125, 31, 160, 36);
		      orderBookNameVal.setBounds(125, 87, 160, 36);
		      orderSalePriceVal.setBounds(125, 143, 160, 36);
		      orderCntVal.setBounds(125, 199, 160, 36);
		      orderDateVal.setBounds(125, 255, 160, 36);
		      orderCancelBt.setBounds(100, 327, 108, 36);

		      // tab 패널
		      tab.addTab("", menuScroll);
		      tab.addTab("주문 내역", orderScroll);

		      // 책 정보 패널
		      bookDetailPanel.add(imgL);
		      bookDetailPanel.add(bookNameL);
		      bookDetailPanel.add(publisherL);
		      bookDetailPanel.add(priceL);
		      bookDetailPanel.add(bookNameVal);
		      bookDetailPanel.add(publisherVal);
		      bookDetailPanel.add(priceVal);
		      bookDetailPanel.add(cnt);
		      bookDetailPanel.add(orderBt);

		      // 주문 정보 패널
		      ordersDetailPanel.add(orderIdL);
		      ordersDetailPanel.add(orderBookNameL);
		      ordersDetailPanel.add(orderSalePriceL);
		      ordersDetailPanel.add(orderCntL);
		      ordersDetailPanel.add(orderDateL);
		      ordersDetailPanel.add(orderIdVal);
		      ordersDetailPanel.add(orderBookNameVal);
		      ordersDetailPanel.add(orderSalePriceVal);
		      ordersDetailPanel.add(orderCntVal);
		      ordersDetailPanel.add(orderDateVal);
		      ordersDetailPanel.add(orderCancelBt);

		      // 전체 패널
		      totalPanel.add(titleL);
		      totalPanel.add(custIdL);
		      totalPanel.add(logOutBt);
		      totalPanel.add(tab);
		      totalPanel.add(bookDetailPanel);

		      this.setContentPane(totalPanel);
		   
		   
		   
		   
		   
	   }
	   
	   
	   
	   
	   
	   
	   
	   public static void main(String[] args) {
		   new OrderFrame();
		
	}
}
