package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Report {
	private LongProperty number = null;
	private IntegerProperty count = null;
	private DoubleProperty time = null;
	private DoubleProperty profit = null;;
	public Report(long number, int count, double time){
		this.count = new SimpleIntegerProperty(count);
		this.number = new SimpleLongProperty(number);
		this.time = new SimpleDoubleProperty(time);
	}
	public Report(long number, int count, double time, double profit){
		this.count = new SimpleIntegerProperty(count);
		this.number = new SimpleLongProperty(number);
		this.time = new SimpleDoubleProperty(time);
		this.profit = new SimpleDoubleProperty(profit);
	
	}
	public Report(Report tablePart) {
		this.count = tablePart.count;
		this.number = tablePart.number;
		this.time = tablePart.time;
		this.profit = tablePart.profit;
	
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
	public double gettime() {
		return time.get();
	}
	public DoubleProperty timeProperty() {
		return time;
	}
	public void settime(SimpleDoubleProperty time) {
		this.time = time;
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
