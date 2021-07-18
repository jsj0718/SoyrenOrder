package message;

import java.io.Serializable;
import java.util.ArrayList;

import cart.CartVO;

public class CartMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// °á°ú°ª - insert,update,delete : int
	//         select : int
	private int result;
	
	private CartVO cavo;
	
	private ArrayList<CartVO> calist;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public CartVO getCavo() {
		return cavo;
	}

	public void setCavo(CartVO cavo) {
		this.cavo = cavo;
	}

	public ArrayList<CartVO> getCalist() {
		return calist;
	}

	public void setCalist(ArrayList<CartVO> calist) {
		this.calist = calist;
	}
	
	
}
