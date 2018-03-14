package View;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PC;
import model.Price;
import model.Type;


public class AddTypeController {
	private Main mainApp;
	@FXML
	private TextField price;
	@FXML
	private Button btn;
	@FXML
	private TextField name;
	@FXML
	private boolean okClicked = false;
	private Stage dialog;
	private Type type;
	public void initialize(){
		
	}
	public void setDialogStage(Stage dialog) {
		this.dialog = dialog;
	}
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
	}
	@FXML
	public void handleAddType() {
		if(isVaild()) {
			this.type = new Type();
			type.setName(name.getText());
			type.setPrice(new Price("ЦАГ", Double.parseDouble(price.getText())));
			okClicked = true;
			dialog.close();
		}
	}
	@FXML
	public void handleCancel() {
		dialog.close();
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	private boolean isVaild() {
		String message = "";
		if(price.getText() == null  || price.getText().length() == 0){
			message = "Үнэ оруулаагүй байна\n";
		}
		if(name.getText() == null  || name.getText().length() == 0){
			message = "Үнийн дүн оруулаагүй байна\n";
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
	public void setType(Type type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	
}

