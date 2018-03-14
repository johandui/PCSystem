package model;

public class PC {
	private int id;
	private String type;
	private int number = -1;
	private boolean state = false;
	public PC() {
	}
	public PC(int num) {
		this.id = num;
	}
	public PC(int num, int type) {
		this.number = num;
		if(type == 0)
			this.type = "VIP";
		else 
			this.type = "ЭНГИЙН";
	}
	public PC(int id, int num, int type) {
		this.number = num;
		this.id = id;
		if(type == 0)
			this.type = "VIP";
		else 
			this.type = "ЭНГИЙН";
	}
	public PC(int id, int num, int type, boolean state) {
		// TODO Auto-generated constructor stub
		this.number = num;
		this.id = id;
		if(type == 0)
			this.type = "VIP";
		else 
			this.type = "ЭНГИЙН";
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
