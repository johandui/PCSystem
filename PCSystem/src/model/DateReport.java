package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateReport {
	private LongProperty number = null;
	private IntegerProperty count = null;
	private DoubleProperty profit = null;
	private IntegerProperty day = null;
	private StringProperty hour = null;
	public DateReport(long number, int count, double time){
		this.count = new SimpleIntegerProperty(count);
		this.number = new SimpleLongProperty(number);
	}
	public DateReport(int count, int year, double profit){
		this.count = new SimpleIntegerProperty(count);
		this.day = new SimpleIntegerProperty(year);
		this.profit = new SimpleDoubleProperty(profit);
		this.day = new SimpleIntegerProperty(year);
		this.profit = new SimpleDoubleProperty(profit);
	
	}
	public DateReport(DateReport tablePart) {
		this.count = tablePart.count;
		this.number = tablePart.number;
		this.day = tablePart.day;
		this.profit = tablePart.profit;
		this.hour = tablePart.hour;
	
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
	public int getday() {
		return day.get();
	}
	public IntegerProperty dayProperty() {
		return day;
	}
	public void setday(SimpleIntegerProperty time) {
		this.day = time;
	}
	public String gethour() {
		return hour.get();
	}
	public StringProperty monthProperty() {
		return hour;
	}
	public void sethour(SimpleStringProperty time) {
		this.hour = time;
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
