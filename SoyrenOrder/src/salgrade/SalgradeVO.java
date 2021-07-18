package salgrade;

import java.io.Serializable;

public class SalgradeVO implements Serializable {
	private String grade;
	private int losal;
	private int hisal;
	
	private String custID;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getLosal() {
		return losal;
	}
	public void setLosal(int losal) {
		this.losal = losal;
	}
	public int getHisal() {
		return hisal;
	}
	public void setHisal(int hisal) {
		this.hisal = hisal;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	
	
	
}
