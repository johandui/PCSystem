package View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Order;
import model.PC;

public class ChargeTimeController {
	@FXML
	private ChoiceBox cbox;
	@FXML
	private TextField time;
	@FXML
	private TextField price;
	@FXML
	private ChoiceBox cbox1;
	private Main mainApp;
	private Stage dialogStage;
	private Order order;
	private boolean okClicked = false;
	private ObservableList list = FXCollections.observableArrayList("VIP", "ЭНГИЙН");
	@FXML
	public void initialize() {
		cbox.setItems(list);
		time.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int num = Integer.parseInt(time.getText());
				price.setText(num * 10 + "");
			}
			
		});
	}

	@FXML
	public void handleOk() {
		if(isVaild()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
			Date date = new Date();
			order.setStart_date(dateFormat.format(date).toString());
			order.setCount(Integer.parseInt(time.getText()));
			okClicked = true;
			dialogStage.close();
		}
	}

	private boolean isVaild() {
		String message = "";
		if(time.getText() == null  || time.getText().length() == 0){
			message = "цаг сонгоогүй байна\n";
		}
		if(cbox.getValue() == null || cbox.getValue().toString().length() == 0) {
			message += "Төрөл сонгоогүй байна\n";
		}
		
		if(price.getText() == null  || time.getText().length() == 0){
			message += "Үнийн дүн байхгүй байна\n";
		}
		if(message.length() == 0) {
			return true;
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(dialogStage);
		alert.setTitle("Буруу утга");
		alert.setContentText(message);
		alert.showAndWait();
		return false;
	}
	@FXML
	public void handleCancel() {
		dialogStage.close();
	}
	public void setMainApp(Main main) {
		this.mainApp = main;
	}
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	public boolean isOkClicked() {
		// TODO Auto-generated method stub
		return okClicked;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
