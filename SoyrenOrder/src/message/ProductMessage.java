package message;

import java.io.Serializable;
import java.util.ArrayList;

import product.ProductVO;

public class ProductMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// 搬苞蔼 - insert,update,delete : int
	//       select : int
	private int result;
	
	// 力前 按眉
	private ProductVO pvo;
	
	// 力前 府胶飘
	private ArrayList<ProductVO> plist;

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

	public ProductVO getPvo() {
		return pvo;
	}

	public void setPvo(ProductVO pvo) {
		this.pvo = pvo;
	}

	public ArrayList<ProductVO> getPlist() {
		return plist;
	}

	public void setPlist(ArrayList<ProductVO> plist) {
		this.plist = plist;
	}
	
	
}
