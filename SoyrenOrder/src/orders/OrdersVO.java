package orders;

import java.io.Serializable;

public class OrdersVO implements Serializable {
	private int orderID;
	private String custID;
	private String odate;
	private String orderFlag;
	
	private int oprice;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}
	public int getOprice() {
		return oprice;
	}
	public void setOprice(int oprice) {
		this.oprice = oprice;
	}

	
	
}
