package message;

import java.io.Serializable;
import java.util.ArrayList;

import salgrade.SalgradeVO;

public class SalgradeMessage implements Serializable, Message {
	// state - insert, update, delete, select
	private int state;
	
	// 결과값 - insert,update,delete : int
	//       select : int
	private int result;
	
	// 등급 객체
	private SalgradeVO svo;
	
	// 등급 리스트
	private ArrayList<SalgradeVO> slist;

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

	public SalgradeVO getSvo() {
		return svo;
	}

	public void setSvo(SalgradeVO svo) {
		this.svo = svo;
	}

	public ArrayList<SalgradeVO> getSlist() {
		return slist;
	}

	public void setSlist(ArrayList<SalgradeVO> slist) {
		this.slist = slist;
	}

	
}
