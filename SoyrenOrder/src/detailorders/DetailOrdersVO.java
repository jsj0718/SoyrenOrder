package detailorders;

import java.io.Serializable;
import java.util.ArrayList;

import cart.CartVO;

public class DetailOrdersVO implements Serializable {
	private int detailOrderID;
	private int orderID;
	private int prodID;
	private int count;
	private int price;
	private String doption;
	
	private String pname;
	private ArrayList<CartVO> calist;
	private String custID;
	
	public int getDetailOrderID() {
		return detailOrderID;
	}
	public void setDetailOrderID(int detailOrderID) {
		this.detailOrderID = detailOrderID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDoption() {
		return doption;
	}
	public void setDoption(String doption) {
		this.doption = doption;
	}
	public ArrayList<CartVO> getCalist() {
		return calist;
	}
	public void setCalist(ArrayList<CartVO> calist) {
		this.calist = calist;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	
	
	
}
