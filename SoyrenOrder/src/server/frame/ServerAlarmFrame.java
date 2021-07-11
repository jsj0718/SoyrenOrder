package server.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


import cart.CartVO;
import client.program.ClientHandler;
import customer.CustomerVO;
import frame.ServerMainRR;
import message.CustomerMessage;
import message.OrdersMessage;
import orders.OrdersVO;
import server.program.ServerHandler;
import java.awt.BorderLayout;

public class ServerAlarmFrame extends JFrame implements ActionListener{

   JPanel alarmPanel;
   JPanel confirmPanel;
   JLabel custIdL;
   
   JButton yesBt;
   JButton noBt;
   
   JLabel custIdVal;
   
   DefaultTableModel confirmDTM;
   JTable confirmTable;
   public JTable confirmTable_1;
   JScrollPane confirmScroll;
   ServerMainRR serverMain;
   

   
   
public ServerAlarmFrame() {
      
      this.setTitle("주문이 들어왔습니다.");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setBounds(500, 300, 400, 250);
      getContentPane().setLayout(null); //absoulute
      setComponent();
      this.setVisible(true);
      
      
   }
   
   public void setComponent() {
      alarmPanel = new JPanel();
      alarmPanel.setBounds(0, 0, 500, 500);
      alarmPanel.setLayout(null);
      
      
      custIdL = new JLabel("\uACE0\uAC1D\uBA85 : ");
      custIdL.setBounds(118, 35, 57, 15);
      alarmPanel.add(custIdL);
      
      custIdVal = new JLabel();
      custIdVal.setBounds(187, 35, 57, 15);
      alarmPanel.add(custIdVal);

      confirmPanel = new JPanel();
      confirmPanel.setBounds(42, 60, 311, 63);
//      confirmPanel.setLayout(null);
      alarmPanel.add(confirmPanel);
      
      confirmTable = new JTable();
      confirmTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
      confirmTable.setBounds(79, 58, 55, 42);
//      confirmPanel.add(confirmTable);
      
      String[] confirmCol = {"음료", "수량"};
      confirmDTM = new DefaultTableModel(confirmCol,40);
      confirmPanel.setLayout(new BorderLayout(0, 0));
      confirmTable_1 = new JTable(confirmDTM);
      confirmTable_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.PINK, null, null, null));
      confirmScroll = new JScrollPane(confirmTable_1);
      confirmPanel.add(confirmScroll);
      
      yesBt = new JButton("\uC2B9\uC778");
      yesBt.setBounds(66, 156, 97, 23);
      alarmPanel.add(yesBt);
      
       noBt = new JButton("\uAC70\uC808");
       noBt.setBounds(233, 156, 97, 23);
      alarmPanel.add(noBt);
      
      alarmPanel.add(custIdL);
      
      alarmPanel.add(yesBt);
      alarmPanel.add(noBt);
      alarmPanel.add(custIdVal);
      

   
      
      
   
      
      

      
      
      this.setContentPane(alarmPanel);
      eventList();
   }
   
   
   
   
   
   
   
   
   
   public void eventList() {
      yesBt.addActionListener(this);
      noBt.addActionListener(this);
   }

   
   //맞는거니..?
   @Override
   public void actionPerformed(ActionEvent e) {
      if( yesBt == e.getSource()) {
         OrdersMessage omsg = new OrdersMessage();
         OrdersVO ovo = new OrdersVO();
         ovo.setOrderFlag("T");
         omsg.setState(2);
         omsg.setOvo(ovo);
         
         JOptionPane.showConfirmDialog(null, " 승인! 고객님께 전송됩니다","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
         serverMain = new ServerMainRR();
         dispose();
         
         try {
            ServerHandler.oos.writeObject(omsg);
            ServerHandler.oos.flush();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         
         
      }
      if(noBt ==e.getSource()) {
         OrdersMessage omsg = new OrdersMessage();
         OrdersVO ovo = new OrdersVO();
         ovo.setOrderFlag("F");
         omsg.setState(2);
         omsg.setOvo(ovo);
         
         
         JOptionPane.showConfirmDialog(null, " 거절! 고객님께 전송됩니다","확인",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
         dispose();
      }
   }
   
   
   public static void main(String[] args) {
      new ServerAlarmFrame();
   }
}