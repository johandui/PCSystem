package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;

public class MonthReport {
	private LongProperty number = null;
	private IntegerProperty count = null;
	private DoubleProperty profit = null;
	private IntegerProperty year = null;
	private IntegerProperty month = null;
	public MonthReport(long number, int count, double time){
		this.count = new SimpleIntegerProperty(count);
		this.number = new SimpleLongProperty(number);
	}
	public MonthReport(int count, int year, int month, double profit){
		this.count = new SimpleIntegerProperty(count);
		//this.number = new SimpleLongProperty(number);
		this.year = new SimpleIntegerProperty(year);
		this.month = new SimpleIntegerProperty(month);
		this.profit = new SimpleDoubleProperty(profit);
	
	}
	public MonthReport(MonthReport tablePart) {
		this.count = tablePart.count;
		this.number = tablePart.number;
		this.year = tablePart.year;
		this.profit = tablePart.profit;
		this.month = tablePart.month;
	
	}
	public long getnumber() {
		return number.get();
	}
	public LongProperty numberProperty() {
		return number;
	}
	public void setnumber(SimpleLongProperty number) {
		this.number = number;
	}
	public int getcount() {
		return count.get();
	}
	public IntegerProperty countProperty() {
		return count;
	}
	public void setcount(SimpleIntegerProperty count) {
		this.count = count;
	}
	public double getyear() {
		return year.get();
	}
	public IntegerProperty yearProperty() {
		return year;
	}
	public void setyear(SimpleIntegerProperty time) {
		this.year = time;
	}
	public double getmonth() {
		return month.get();
	}
	public IntegerProperty monthProperty() {
		return month;
	}
	public void setmonth(SimpleIntegerProperty time) {
		this.month = time;
	}
	public double getprofit() {
		return profit.get();
	}
	public void setprofit(SimpleDoubleProperty profit) {
		this.profit = profit;
	}
	public DoubleProperty profitProperty() {
		return profit;
	}
	@Override
	public String toString() {
		return "gg";
	}
}
