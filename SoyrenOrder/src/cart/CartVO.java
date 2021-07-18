package cart;

import java.io.Serializable;

import message.Message;

public class CartVO implements Serializable {
	private int cartID;
	private int prodID;
	private String custID;
	private String coption;
	private int count;
	
	private String pname;
	private int cprice;
	
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCoption() {
		return coption;
	}
	public void setCoption(String coption) {
		this.coption = coption;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	
	
}
