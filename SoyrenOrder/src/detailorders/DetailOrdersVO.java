package detailorders;

public class DetailOrdersVO {
	private int detailOrderID;
	private int orderID;
	private int prodID;
	private int count;
	private int price;
	
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
	
	
}
