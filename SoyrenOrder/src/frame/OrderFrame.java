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
	
	
	   // ��ü �г�
	   // ��� �̹��� ����
	   static JPanel totalPanel=new JPanel() {
	      //�̹��� �о����
	      Image backGroundImg=new ImageIcon("./img/backgound.png").getImage();
	      //â ũ�⿡ �°� ����
	      Image scaledBackGroundImg = backGroundImg.getScaledInstance(800, 550, Image.SCALE_DEFAULT);
	      Image backGroundFinalImg = new ImageIcon(scaledBackGroundImg).getImage();
	      
	      public void paintComponent(Graphics g) {//�׸��� �Լ�
	         g.drawImage(backGroundFinalImg, 0, 0, null);//backgroundImg�� �׷���   
	         setOpaque(false); //�����ϰ�
	            super.paintComponent(g);

	      }
	   };  
	   
	   // å ���� �г�
	   JPanel menuDetailPanel;
	   // �ֹ� ���� �г�
	   JPanel ordersDetailPanel;
	   // tab �г�
	   JTabbedPane tab;

	
	   // ����, ���̵�, �α׾ƿ� ��ư
	   JLabel titleL;
	   JLabel custIdL;

	   // å ���� �г� ����
	   JLabel imgL;
	   JLabel imgVal;
//	   JLabel bookIdVal;
	   JLabel menuNameL;
	   JLabel menuNameVal;
	   JLabel priceL;
	   JLabel priceVal;
	   JLabel infoL;
	   JLabel infoVal;
	   
	   // �ֹ� ���� �г� ����
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

		      this.setTitle("�ֹ��ϱ�");
		      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		      this.setBounds(200, 200, 800, 550);
		      this.setLayout(new FlowLayout());
		      // ȭ�鱸��
		      setComponent();

		      // �ʱ� ������ ����
		      initTableRequest();

		      // �̺�Ʈ ����
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
		      titleL.setText("�� �� �� ��");
		      titleL.setFont(new Font("���� ���", Font.BOLD, 30));
		      titleL.setHorizontalAlignment(JLabel.CENTER);
		      custIdL = new JLabel();
		      custIdL.setText("id" + "�� ȯ���մϴ�.");



		      // �޴�����, �޴����
//		      imgL = new JLabel();
//		      imgVal = new JLabel();
//		      imgL.setBorder(new EtchedBorder());
//		      bookIdVal = new JLabel();
		      menuNameL = new JLabel();
		      menuNameL.setText("�޴��̸� :");
		      menuNameVal = new JLabel();
		      
		      infoL = new JLabel();
		      infoL.setText("�޴����� : ");
		      infoVal = new JLabel();
		      
		      priceL = new JLabel();
		      priceL.setText("�� �� : ");
		      priceVal = new JLabel();
		      
		      cnt = new JSpinner();
		      orderBt = new JButton();
		      orderBt.setText("���");

		      // ��ٱ���
		      orderIdL = new JLabel();
		      orderIdL.setText("�ֹ���ȣ : ");
		      ordermenuNameL = new JLabel();
		      ordermenuNameL.setText("å�̸� : ");
		      orderSalePriceL = new JLabel();
		      orderSalePriceL.setText("�ֹ����� : ");
		      orderCntL = new JLabel();
		      orderCntL.setText("�ֹ����� : ");
		      orderDateL = new JLabel();
		      orderDateL.setText("�ֹ�����");

		      orderIdVal = new JLabel();
		      orderBookNameVal = new JLabel();
		      orderSalePriceVal = new JLabel();
		      orderCntVal = new JLabel();
		      orderDateVal = new JLabel();
		      orderCancelBt = new JButton();
		      orderCancelBt.setText("�ֹ����");

		      totalPanel.setBounds(12, 10, 771, 494);
		      menuDetailPanel.setBounds(462, 89, 297, 395);
		      ordersDetailPanel.setBounds(462, 89, 297, 395);
		      titleL.setBounds(121, 14, 215, 42);
		      custIdL.setBounds(635, 15, 100, 20);
//		      logOutBt.setBounds(645, 35, 90, 25);

		      tab.setBounds(30, 66, 420, 418);

		      // å ���� ��ġ
		      imgL.setBounds(67, 10, 171, 165);
		      menuNameL.setBounds(22, 199, 75, 31);
		      priceL.setBounds(22, 240, 75, 31);
		      infoL.setBounds(22, 281, 75, 31);
		      menuNameVal.setBounds(109, 199, 165, 31);
		      priceVal.setBounds(109, 240, 165, 31);
		      infoVal.setBounds(109, 281, 165, 31);
		      cnt.setBounds(22, 341, 75, 31);
		      orderBt.setBounds(109, 338, 108, 36);

		      // �ֹ����� ��ġ
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

		      // tab �г�
		      tab.addTab("", menuScroll);
		      tab.addTab("�ֹ� ����", orderScroll);

		      // å ���� �г�
		      bookDetailPanel.add(imgL);
		      bookDetailPanel.add(bookNameL);
		      bookDetailPanel.add(publisherL);
		      bookDetailPanel.add(priceL);
		      bookDetailPanel.add(bookNameVal);
		      bookDetailPanel.add(publisherVal);
		      bookDetailPanel.add(priceVal);
		      bookDetailPanel.add(cnt);
		      bookDetailPanel.add(orderBt);

		      // �ֹ� ���� �г�
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

		      // ��ü �г�
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
