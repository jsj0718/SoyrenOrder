package message;

import java.io.Serializable;
import java.util.ArrayList;

import product.ProductVO;

public class ProductMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// 결과값 - insert,update,delete : int
	//       select : int
	private int result;
	
	// 제품 객체
	private ProductVO pvo;
	
	// 제품 리스트
	private ArrayList<ProductVO> plist;
	
	// 수량
	private int count;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
