package laba2os;
public enum Priority {
	LOW(1, "������"),
	MEDIUM(2, "�������"),
	HIGH(3, "�������");
	
	private int num;
	private String name;
	
	private Priority(int num, String name) {
		this.num = num;
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public int getNum() {
		return num;
	}

}