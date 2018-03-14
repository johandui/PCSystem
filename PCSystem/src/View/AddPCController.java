package View;

import application.Main;
import database.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PC;
import model.Type;


public class AddPCController {
	private Main mainApp;
	@FXML
	private TextField number;
	@FXML
	private Button btn;
	@FXML
	private ChoiceBox cbox;
	@FXML
	private boolean okClicked = false;
	private Stage dialog;
	private PC pc;
	private DatabaseHelper mydb;
	@FXML
	public void initialize(){

		cbox.setItems(FXCollections.observableArrayList("VIP", "ЭНГИЙН"));
		
	}
	public void setDialogStage(Stage dialog) {
		this.dialog = dialog;
	}
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
	}
	@FXML
	public void handleAddPC() {
		if(isVaild()) {
			pc.setNumber(Integer.parseInt(number.getText()));
			pc.setType(cbox.getValue().toString());
			pc.setState(false);
			okClicked = true;
			dialog.close();

		}
	}
	@FXML
	public void handleCancel() {
		dialog.close();
	}
	@FXML
	public void handleAddType() {
		if(dialog != null) {
			Type temp = new Type();
			boolean okClicked = mainApp.showAddTypeDialog(temp, dialog);
			if(okClicked) {
				mainApp.getTypeData().add(temp);
			}
		}
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	private boolean isVaild() {
		String message = "";
		if(number.getText() == null  || number.getText().length() == 0){
			message = "дугаар сонгоогүй байна\n";
		}
		if(cbox.getValue() == null || cbox.getValue().toString().length() == 0) {
			message += "Төрөл сонгоогүй байна\n";
		}
		if(message.length() == 0) {
			return true;
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(dialog);
		alert.setTitle("Буруу утга");
		alert.setContentText(message);
		alert.showAndWait();
		return false;
	}
	public void setPC(PC pc) {
		// TODO Auto-generated method stub
		this.pc = pc;
	}
	
}

