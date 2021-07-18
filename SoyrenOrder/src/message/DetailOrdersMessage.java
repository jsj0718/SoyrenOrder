package message;

import java.io.Serializable;
import java.util.ArrayList;

import detailorders.DetailOrdersVO;

public class DetailOrdersMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// ����� - insert,update,delete : int
	//         select : int
	private int result;
	
	// �� �ֹ� ��ü
	private DetailOrdersVO dovo;
	
	// �� �ֹ� ����Ʈ
	private ArrayList<DetailOrdersVO> dolist;

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

	public DetailOrdersVO getDovo() {
		return dovo;
	}

	public void setDovo(DetailOrdersVO dovo) {
		this.dovo = dovo;
	}

	public ArrayList<DetailOrdersVO> getDolist() {
		return dolist;
	}

	public void setDolist(ArrayList<DetailOrdersVO> dolist) {
		this.dolist = dolist;
	}
	
	
}
