package View;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import application.Main;
import database.DatabaseHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.DateReport;
import model.MonthReport;
import model.Report;

public class DateReportController {
	@FXML
	private DatePicker date1;
	@FXML
	private DatePicker date2;
	@FXML
	private TableView tbview;
	@FXML
	private TableColumn<DateReport, Long> pcNum;
	@FXML
	private TableColumn<DateReport, Integer> pcCount;
	@FXML
	private TableColumn<DateReport, Double> pcProfit;
	@FXML
	private TableColumn<DateReport, Integer> pcTime;
	@FXML
	private LineChart chart;
	private XYChart.Series series = new XYChart.Series<>();
	private XYChart.Series series1 = new XYChart.Series<>();
	private Main mainApp;
	private DatabaseHelper mydb;
	private ObservableList<DateReport> mylist;
	@FXML
	public void initialize() {
		pcProfit.setCellValueFactory(celData -> celData.getValue().profitProperty().asObject());
		pcTime.setCellValueFactory(celData -> celData.getValue().dayProperty().asObject());
		pcCount.setCellValueFactory(celData -> celData.getValue().countProperty().asObject());
	}
	@FXML
	public void handle_search() throws SQLException {
		Date dt = Date.from(date1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dt1 = Date.from(date2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		String query = "Select count(t.archive) as count, sum(t.count) as profit, DAYOFMONTH(t.start_date) as day from `t_order` as `t` left join"
				+ "`pc` on `pc`.`id` = `t`.`pc_id` where t.start_date< '" +date2.getValue()+ "' AND t.start_date>'" +date1.getValue()+ "' group by DAYOFMONTH(`t`.`start_date`)";

		mylist = mydb.selectDateReport(query); 
		mainApp.setDateReportData(mylist);
		tbview.setItems(mylist);
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		for(DateReport m: mylist) {
	
			series.getData().add(new XYChart.Data<>(m.getday()+  "", m.getprofit() ));
		
		}
		
		chart.getData().add(series);
	}
	public void setMainApp(Main mainApp) throws SQLException {
		this.mainApp = mainApp;
		this.mydb = mainApp.getMydb();
		tbview.setEditable(true);

		
	}
	
}
