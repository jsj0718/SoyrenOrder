package message;

import java.io.Serializable;
import java.util.ArrayList;

import customer.CustomerVO;

public class CustomerMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// °á°ú°ª - insert,update,delete : int
	//         select : int
	private int result;
	
	// °í°´ °´Ã¼
	private CustomerVO cvo;
	
	// °í°´ ¸®½ºÆ®
	private ArrayList<CustomerVO> clist;

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

	public CustomerVO getCvo() {
		return cvo;
	}

	public void setCvo(CustomerVO cvo) {
		this.cvo = cvo;
	}

	public ArrayList<CustomerVO> getClist() {
		return clist;
	}

	public void setClist(ArrayList<CustomerVO> clist) {
		this.clist = clist;
	}
	
	
	
}
