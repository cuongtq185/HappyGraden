package vn.com.unit.pageable;

public class pageItem {
	private boolean isCurrent;
	private int number;
	public pageItem( int number,boolean isCurrent) {
		this.isCurrent = isCurrent;
		this.number = number;
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
