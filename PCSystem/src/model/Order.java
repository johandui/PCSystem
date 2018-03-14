package model;

public class Order {
	private int id;
	private String start_date;
	private String end_date;
	private PC pc;
	private int count;
	public Order(int id, String start_date, String end_date, int count, PC pc){
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.pc = pc;
		this.count = count;
	}
	public Order(int id, String start_date, String end_date){
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
	
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public PC getPc() {
		return pc;
	}
	public void setPc(PC pc) {
		this.pc = pc;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
