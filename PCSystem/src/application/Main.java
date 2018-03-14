package application;
	
import java.io.IOException;
import java.sql.SQLException;

import View.AddPCController;
import View.AddTimeController;
import View.ChangePCController;
import View.ChargeTimeController;
import View.Controller;
import View.DateReportController;
import View.MonthReportController;
import View.ReportController;
import database.DatabaseHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Order;
import model.PC;
import model.Price;
import model.Report;
import model.DateReport;
import model.MonthReport;
import model.Type;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private BorderPane root;
	private FXMLLoader loader;
	private Controller controller;
	private Stage primaryStage;
	private ObservableList<PC> pcData = FXCollections.observableArrayList();
	private ObservableList<Type> typeData = FXCollections.observableArrayList();
	private ObservableList<Order> orderData = FXCollections.observableArrayList();
	private ObservableList<Report> reportData = FXCollections.observableArrayList();
	private ObservableList<MonthReport> monthReportData = FXCollections.observableArrayList();
	private ObservableList<DateReport> dateReportData = FXCollections.observableArrayList();
	private DatabaseHelper mydb;
	private Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		try {
			mydb = new DatabaseHelper("com.mysql.jdbc.Driver","jdbc:mysql://localhost/PCSystem", "root", "");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initWindow();
		setController();
		typeData.add(new Type("VIP", new Price("ЦАГ", 1000)));
		primaryStage.setFullScreen(true);
		primaryStage.setMaximized(true);
		primaryStage.setMinWidth(714);
		primaryStage.setMinHeight(420);
		primaryStage.show();
	}

	private void setController() {
		controller = loader.getController();
		controller.setMainApp(this);
	}
	private void initWindow() {
		try {
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/Scene.fxml"));
			root = (BorderPane)loader.load();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean showAddPCDialog(PC pc) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/AddPCScene.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("PC НЭМЭХ");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			AddPCController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPC(pc);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	public boolean showAddTypeDialog(Type temp, Stage dialog) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/AddTypeScene.fxml"));
			GridPane root = (GridPane)loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("PC НЭМЭХ");
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			AddPCController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();;
			return controller.isOkClicked();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean showAddTimeDialog(Order order) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/AddTimeScene.fxml"));
			GridPane root = (GridPane)loader.load();
			Stage dialogStage = new Stage() ;
			dialogStage.setTitle("ЦАГ НЭЭХ");
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			AddTimeController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrder(order);
			dialogStage.showAndWait();
			return controller.isOkClicked();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean showChangePCDialog(Order order) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/ChangePCScene.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Stage dialogStage = new Stage() ;
			dialogStage.setTitle("ШИЛЖҮҮЛЭХ");
			
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			ChangePCController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrder(order);
			controller.setMainApp(this);
			dialogStage.showAndWait();
			return controller.isOkClicked();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean showChargeTimeDialog(Order order) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/ChargeTime.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Stage dialogStage = new Stage() ;
			dialogStage.setTitle("ШИЛЖҮҮЛЭХ");
			
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			ChargeTimeController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrder(order);
			controller.setMainApp(this);
			dialogStage.showAndWait();
			return controller.isOkClicked();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public void showReport() throws SQLException {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/Report.fxml"));
			Pane root = (Pane)loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Тайлан");
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			ReportController controller = loader.getController();
			controller.setMainApp(this);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showDateReport() throws SQLException {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/DateReport.fxml"));
			Pane root = (Pane)loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Тайлан");
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			DateReportController controller = loader.getController();
			controller.setMainApp(this);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showMonthReport() throws SQLException {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/MonthReport.fxml"));
			Pane root = (Pane)loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Тайлан");
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			MonthReportController controller = loader.getController();
			controller.setMainApp(this);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ImageView getImageView(String id) {
		return (ImageView) scene.lookup(id);
	}
	public static void main(String[] args) {
		launch(args);
	}
	public ObservableList<PC> getPcData() {
		return pcData;
	}
	public void setPcData(ObservableList<PC> pcData) {
		this.pcData = pcData;
	}
	public ObservableList<Type> getTypeData() {
		return typeData;
	}
	public void setTypeData(ObservableList<Type> typeData) {
		this.typeData = typeData;
	}
	public DatabaseHelper getMydb() {
		return mydb;
	}
	public void setMydb(DatabaseHelper mydb) {
		this.mydb = mydb;
	}
	public ObservableList<Order> getOrderData() {
		return orderData;
	}
	public void setOrderData(ObservableList<Order> orderData) {
		this.orderData = orderData;
	}
	public void setReport(ObservableList<Report> mylist) {
		// TODO Auto-generated method stub
		this.reportData = mylist;
	}
	public ObservableList<Report> getReport() {
		return reportData;
	}
	public BorderPane getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	public ObservableList<MonthReport> getMonthReportData() {
		return monthReportData;
	}

	public void setMonthReportData(ObservableList<MonthReport> monthReportData) {
		this.monthReportData = monthReportData;
	}

	public ObservableList<DateReport> getDateReportData() {
		return dateReportData;
	}

	public void setDateReportData(ObservableList<DateReport> dateReportData) {
		this.dateReportData = dateReportData;
	}
	
	
	
}
