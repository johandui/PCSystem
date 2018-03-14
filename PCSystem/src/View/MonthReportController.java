package View;

import java.sql.SQLException;

import application.Main;
import database.DatabaseHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MonthReport;
import model.Report;

public class MonthReportController {

	@FXML
	private TableColumn<MonthReport, Integer> tableColumnNum;
	@FXML
	private TableColumn<MonthReport, String> tableColumnTime;
	@FXML
	private TableColumn<MonthReport, Integer> tableColumnCount;
	@FXML
	private TableColumn<MonthReport, Double> tableColumnProfit;
	@FXML
	private LineChart<?, ?> lineChart;
	@FXML
	private TableView<MonthReport> tbview;
	@FXML
	private ChoiceBox<String> cbox;
	@FXML
	private CategoryAxis yAxis;
	@FXML
	private NumberAxis xAxis;
	private ObservableList<MonthReport> mylist;
	private Main mainApp;
	private XYChart.Series series = new XYChart.Series<>();
	private XYChart.Series series1 = new XYChart.Series<>();
	
	private DatabaseHelper mydb;
	@FXML
	public void initialize() throws SQLException {
		ObservableList<String> s = FXCollections.observableArrayList();
		s.add("САР");
		s.add("ЖИЛ");
		cbox.setItems(s);
		cbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
			
				if((int)newValue == 0) {	
					lineChart.getData().set(0, series);
				}
				else lineChart.getData().set(0, series1);
				System.out.println(newValue);
			}
			
			
		});
		tableColumnNum.setCellValueFactory(celData -> celData.getValue().yearProperty().asObject());
		tableColumnProfit.setCellValueFactory(celData -> celData.getValue().profitProperty().asObject());
		tableColumnTime.setCellValueFactory(celData -> celData.getValue().monthProperty().asString());
		tableColumnCount.setCellValueFactory(celData -> celData.getValue().countProperty().asObject());

	}
	public Main getMainApp() {
		return mainApp;
	}
	public void setMainApp(Main mainApp) throws SQLException {
		this.mainApp = mainApp;
		this.mydb = mainApp.getMydb();
		tbview.setEditable(true);
		String query = "Select sum(t.price) as profit, MONTH(t.start_date) as month, count(t.archive) as count, "
				+ "YEAR(t.start_date) as year from `t_order` as `t` left join"
				+ "`pc` on `pc`.`id`=`t`.`pc_id` group by MONTH(`t`.`start_date`), YEAR(`t`.`start_date`) order by `t`.`start_date`";
		mylist = mydb.selectMonthReport(query);
		mainApp.setMonthReportData(mylist);
		tbview.setItems(mainApp.getMonthReportData());
		
		for(MonthReport m: mylist) {
	
			series.getData().add(new XYChart.Data<>(m.getmonth()+ "", m.getprofit() ));
			series1.getData().add(new XYChart.Data<>(m.getyear()+ "", m.getprofit() ));
		
		}
		lineChart.getData().add(series);
	}
	
}
