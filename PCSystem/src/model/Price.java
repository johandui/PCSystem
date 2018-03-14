package model;

public class Price {
	private double price;
	private String name;
	public Price(String name, double price) {
		this.price = price;
		this.name = name;
	}
	public Price(Price price) {
		this.price = price.price;
		this.name = price.name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
