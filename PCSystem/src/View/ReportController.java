package View;

import java.sql.SQLException;

import application.Main;
import database.DatabaseHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Report;

public class ReportController {
	@FXML
	private ChoiceBox cbox;
	@FXML
	private TableColumn<Report, Long> tableColumnPC;
	@FXML
	private TableColumn<Report, Double> tableColumnTime;
	@FXML
	private TableColumn<Report, Integer> tableColumnCount;
	@FXML
	private TableColumn<Report, Double> tableColumnProfit;
	@FXML
	private BarChart barChart;
	@FXML
	private TableView<Report> tbview;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	private Main mainApp;
	private DatabaseHelper mydb;
	@FXML
	public void initialize() throws SQLException {

		tableColumnPC.setCellValueFactory(celData -> celData.getValue().numberProperty().asObject());
		tableColumnProfit.setCellValueFactory(celData -> celData.getValue().profitProperty().asObject());
		tableColumnTime.setCellValueFactory(celData -> celData.getValue().timeProperty().asObject());
		tableColumnCount.setCellValueFactory(celData -> celData.getValue().countProperty().asObject());
		
		cbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
			}
			
		});
	}
	public Main getMainApp() {
		return mainApp;
	}
	public void setMainApp(Main mainApp) throws SQLException {
		this.mainApp = mainApp;
		this.mydb = mainApp.getMydb();
		tbview.setEditable(true);
		cbox.setItems(mainApp.getPcData());
		String query = "Select pc.number as number, sum(t.count) as time, count(t.pc_id) as count, sum(t.price) as profit from `pc` left join"
				+ "`t_order` as `t` on `pc`.`id`=`t`.`pc_id` group by `pc`.`id`";
	
		ObservableList<Report> mylist = mydb.selectReport(query);
		mainApp.setReport(mylist);
		

		tbview.setItems(mainApp.getReport());
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		for(Report r:mylist) {
			series1.setName("Үнийн дүн");
			series1.getData().add(new XYChart.Data<>(r.getnumber() + "", r.getprofit()));
			series2.setName("Хэдэн удаа нээсэн");
			series2.getData().add(new XYChart.Data<>(r.getnumber() + "", r.getcount()));
			series3.setName("Нийт хугацаа");
			series3.getData().add(new XYChart.Data<>(r.getnumber() + "", r.gettime()));
		
		}
		barChart.getData().addAll(series1, series2, series3);
		
	}
	
}
