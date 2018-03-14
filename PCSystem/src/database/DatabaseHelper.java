package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DateReport;
import model.MonthReport;
import model.Order;
import model.PC;
import model.Report;

public class DatabaseHelper {
	private Connection connection;
	private String driverClassName;
	private String dbURL;
	private String user;
	private Statement statement;
	private String password;
	public DatabaseHelper(String driverClassName,String dbURL, String user, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.driverClassName = driverClassName;
		this.dbURL = dbURL;
		this.user = user;
		this.password = password;
		Class.forName(this.driverClassName).newInstance();
		connection = DriverManager.getConnection(dbURL, user, password);
		statement = connection.createStatement();
	}
	public boolean insert(String query){
		try {
			int rs = statement.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean update(String query){
		try {
			int rs = statement.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public int select(String query, String selection){
		try {
			ResultSet rs = statement.executeQuery(query);
			if(rs.first())
			return rs.getInt(selection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}return -1;
		
	}
	public ResultSet querySelect(String query) throws SQLException {
		return statement.executeQuery(query);
	}
	public ObservableList<PC> SelectPC(String query) throws SQLException{
		ResultSet rs = statement.executeQuery(query);
		ObservableList<PC> result = FXCollections.observableArrayList();
		while(rs.next()){
			result.add(new PC(rs.getInt("id"), rs.getInt("number"), rs.getInt("type_id"), rs.getBoolean("state")));
		}
		return result;
	}
	public ObservableList<Order> SelectOrder(String query) throws SQLException{
		ResultSet rs = statement.executeQuery(query);
		ObservableList<Order> result = FXCollections.observableArrayList();
		while(rs.next()) {
			result.add(new Order(rs.getInt("id"), rs.getString("start_date"), rs.getString("end_date"), rs.getInt("count")
					, new PC(rs.getInt("pc_id"))));
		}
		return result;
	}
	public ObservableList<Report> selectReport(String query) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = statement.executeQuery(query);
		ObservableList<Report> result = FXCollections.observableArrayList();
		while(rs.next()) {
			result.add(new Report(rs.getLong("number"), rs.getInt("count"), rs.getDouble("time"), rs.getDouble("profit")));
		}
		return result;
		
	}
	public ObservableList<MonthReport> selectMonthReport(String query) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = statement.executeQuery(query);
		ObservableList<MonthReport> result = FXCollections.observableArrayList();
		while(rs.next()) {
			if(rs.getInt("month") != 0)
			result.add(new MonthReport( rs.getInt("count"), rs.getInt("year"),rs.getInt("month"), rs.getDouble("profit")));
		}
		return result;
		
	}
	public ObservableList<DateReport> selectDateReport(String s) throws SQLException {
		ResultSet rs = statement.executeQuery(s);
		ObservableList<DateReport> result = FXCollections.observableArrayList();
		while(rs.next()) {
			result.add(new DateReport(  rs.getInt("count"), rs.getInt("day"), rs.getDouble("profit")));
			
		}
		System.out.println("bzdrah");
		return result;
		
		
		// TODO Auto-generated method stub
		
	}

}
