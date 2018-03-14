package model;

public class Type {
	private int id;
	private String name;
	private Price price;
	public Type(String string, Price price) {
		// TODO Auto-generated constructor stub
		this.name = string;
		this.price = new Price(price);
	}
	public Type() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
}
