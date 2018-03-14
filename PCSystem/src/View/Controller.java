package View;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import com.mysql.jdbc.ResultSet;

import application.Main;
import database.DatabaseHelper;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import model.Order;
import model.PC;
import model.Price;
import model.Type;

public class Controller {
	private Main mainApp;
	@FXML
	private FlowPane flowPane;
	@FXML
	private FlowPane flowPaneVIP;
	@FXML
	private MenuItem addPCbtn;
	@FXML
	private MenuItem reportbtn;
	@FXML 
	private ImageView imgView;
	@FXML
	private Text pcName;
	@FXML
	private Text pcNumber;
	@FXML
	private Text	 pcTime;
	@FXML
	private Text pcCount; 
	@FXML
	private Button btn_time;
	@FXML
	private Button btn_charge;
	@FXML
	private Button btn_change;
	@FXML
	private Button btn_close;
	private ImageView tempImage;
	private DatabaseHelper mydb;
	private int tempID = -1;
	private Timer timer = new Timer("Timer", true);

	@FXML
	public void initialize(){
		timer.schedule(task, 1000, 6000);
		
	}
	@FXML
	public void closePC(ActionEvent e) {
		PC pc;
		if(e == null) {
			pc = getCurrentPC(tempID);
		}
		else {
		int num = Integer.parseInt(pcNumber.getText());
		 pc = getCurrentPC(num);}
		pc.setState(false);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
		Date date = new Date();

		String query1 = "UPDATE `t_order` SET `end_date`='" +dateFormat.format(date)+ "', `archive`=1 WHERE "
				+ "`pc_id`='" +pc.getId()+"' AND `archive`=0" ;
		mydb.update(query1);
		Image img2;
		if(pc.getType().equals("VIP")) {
			img2 = new Image("/frame/computervipOff.png");
		
		}
		else {
			img2 = new Image("/frame/computerOff.png");
		}
		ImageView v = mainApp.getImageView("#id" + pc.getId());
		v.setImage(img2);
		if(e != null) {
		imgView.setImage(img2);
		pcCount.setText("0");
		pcTime.setText("00:00");
		}
	}
	private void getOrder(PC pc) {
		for(Order order: mainApp.getOrderData()) {
			if(pc.getId() == order.getPc().getId()) {
				mainApp.getOrderData().remove(order);
			}
		}
		
	}
	@FXML
	public void showReport() throws SQLException {
		mainApp.showReport();
	}
	
	@FXML
	public void changePrice() {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Үнэ өөрчлөх");
		dialog.setHeaderText("Look, a Custom Login Dialog");
		

		ButtonType loginButtonType = new ButtonType("Өөрчлөх", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ChoiceBox cbox = new ChoiceBox();

		cbox.setItems(FXCollections.observableArrayList("VIP", "ЭНГИЙН"));
		TextField password = new TextField();
	

		grid.add(cbox, 1, 0);
		grid.add(password, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
	
		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(cbox.getValue().toString(), password.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(val -> {
		    System.out.println("Username=" + val.getKey() + ", Password=" + val.getValue());
		    for(Type x: mainApp.getTypeData()) {
		    	if(x.getName().equals(val.getKey()))
		    		mainApp.getTypeData().add(new Type(val.getKey(), new Price("ЦАГ", Integer.parseInt(val.getValue()))));
		    }
		});
	}
		@FXML
	public void showDateReport() throws SQLException {
		mainApp.showDateReport();
	}
	@FXML
	public void showMonthReport() throws SQLException {
		mainApp.showMonthReport();
	}
	@FXML
	public void addPC() {
		PC tempPC = new PC();
		boolean okClicked = mainApp.showAddPCDialog(tempPC);
		if(okClicked) {
			int type = 0;
			mainApp.getPcData().add(tempPC);
			if(tempPC.getType().equals("VIP")){
				flowPaneVIP.getChildren().add(getPCPane(tempPC.getId(), tempPC.getNumber(), tempPC.getType(), tempPC.isState()));
				type = 1;
			}
			else
				flowPane.getChildren().add(getPCPane(tempPC.getId(), tempPC.getNumber(), tempPC.getType(), tempPC.isState()));

			String query = "INSERT INTO `PC`(`type_id`, `number`) VALUES('"+type +"','"+tempPC.getNumber() +"')";
			mydb.insert(query);
		}
	}
	@FXML 
	public void chargeTime() {
		int num = Integer.parseInt(pcNumber.getText());
		PC pc = getCurrentPC(num);
		Order order = new Order();
		order.setPc(pc);
		boolean okClicked = mainApp.showChargeTimeDialog(order);
		if(okClicked) {
			Image img;
			double price = 800;
			if(pc.getType().equals("VIP")) {
				img = new Image("/frame/computervip.png");
				price = 1000;
			}
			else 
				img = new Image("/frame/computer.png");
			tempImage.setImage(img);
			imgView.setImage(img);
			pcTime.setText(order.getStart_date());
			pcCount.setText(order.getCount() + "");
			String query1 = "UPDATE `t_order` SET `end_date`='" +order.getStart_date()+ "', `archive`=1 WHERE "
					+ "`pc_id`='" +pc.getId()+"' AND `archive`=0" ;

			mydb.update(query1);
			String query = "INSERT INTO `t_order`(`start_date`, `pc_id`, `count`, `price`) VALUES('" + 
					order.getStart_date() +"','"+ order.getPc().getId()+"','" +order.getCount()+  "','"+(order.getCount() / 60  * price) +"')";
			mydb.insert(query);

			mainApp.getOrderData().add(order);
		}
	}
	@FXML 
	public void addTime() {
		int num = Integer.parseInt(pcNumber.getText());
		PC pc = getCurrentPC(num);
		Order order = new Order();
		order.setPc(pc);
		boolean okClicked = mainApp.showAddTimeDialog(order);
		if(okClicked) {
			Image img;
			double price = 800;
			if(pc.getType().equals("VIP")) {
				img = new Image("/frame/computervip.png");
				price = 1000;
			}
			else 
				img = new Image("/frame/computer.png");
			tempImage.setImage(img);
			imgView.setImage(img);
			pcTime.setText(order.getStart_date());
			pcCount.setText(order.getCount() + "");
			String query = "INSERT INTO `t_order`(`start_date`, `pc_id`, `count`, `price`) VALUES('" + 
					order.getStart_date() +"','"+ order.getPc().getId()+"','" +order.getCount()+  "','"+(order.getCount() / 60  * price) +"')";
			mydb.insert(query);

			mainApp.getOrderData().add(order);
		}
	}

	@FXML 
	public void changePC() throws SQLException {
		int num = Integer.parseInt(pcNumber.getText());
		PC pc = getCurrentPC(num);
		Order order = new Order();
		boolean okClicked = mainApp.showChangePCDialog(order);
		if(okClicked) {
			pc.setState(false);
			System.out.println(pc);
			Image img, img2;
			double price = 800;
			if(order.getPc().getType().equals("VIP")) {
				img = new Image("/frame/computervip.png");
				price = 1000;
			}
			else {
				img = new Image("/frame/computer.png");
			}
			if(pc.getType().equals("VIP")) {
				img2 = new Image("/frame/computervipOff.png");
				price = 1000;
			}
			else {
				img2 = new Image("/frame/computerOff.png");
			}
			getCurrentPC(order.getPc().getNumber()).setState(true);
			pcNumber.setText(order.getPc().getNumber() + "");
			tempImage.setImage(img2);
			ImageView v = mainApp.getImageView("#id" + order.getPc().getId());
			v.setImage(img);
			pcTime.setText(order.getStart_date());
			pcCount.setText(order.getCount() + "");
			imgView.setImage(img);
			String query = "INSERT INTO `t_order`(`start_date`, `pc_id`, `count`, `price`) VALUES('" + 
					order.getStart_date() +"','"+ order.getPc().getId()+"','" +order.getCount()+  "','"+(order.getCount() / 60  * price) +"')";
			mydb.insert(query);
			String query1 = "UPDATE `t_order` SET `end_date`='" +order.getStart_date()+ "', `archive`=1 WHERE "
					+ "`pc_id`='" +pc.getId()+"' AND `archive`=0" ;

			mydb.update(query1);
			mainApp.getOrderData().add(order);
		}
	}
	private PC getCurrentPC(int num) {
		for(PC pc: mainApp.getPcData()) {
			if(pc.getNumber() == num) {
				return pc;
			}
		}
		return null;	
	}
	private void getData() throws SQLException {
		String query2 = "select * from t_order where archive=0";
		mainApp.setOrderData(mydb.SelectOrder(query2));
		String query = "select * from pc";
		ObservableList<PC> list = mydb.SelectPC(query);
		for(PC pc: list) {
			if(pc.getType().equals("VIP")){
				flowPaneVIP.getChildren().add(getPCPane(pc.getId(),pc.getNumber(), pc.getType(), pc.isState()));
			}
			else
				flowPane.getChildren().add(getPCPane(pc.getId(), pc.getNumber(), pc.getType(), pc.isState()));
			mainApp.getPcData().add(pc);

		}
	}
	private VBox getPCPane(int id, int num, String t, boolean state) {
		VBox root = new VBox();
		Image img;
		if(t.equals("VIP") && !state) 
			img = new Image("/frame/computervipOff.png");	
		else if(t.equals("VIP") && state)
			img = new Image("/frame/computervip.png");
		else if(!t.equals("VIP") && state) {
			img = new Image("/frame/computer.png");
		}
		else 
			img = new Image("/frame/computerOff.png");	
		ImageView imageview = new ImageView();
		imageview.setId("id" +id);
		imageview.setFitWidth(60);
		imageview.setFitHeight(60);
		imageview.setImage(img);
		root.getChildren().add(imageview);
		root.getChildren().add(new Text("PC" + num));
		root.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				pcCount.setText("0");
				pcTime.setText("00:00");
			
				String text = ((Text)((VBox)event.getSource()).getChildren().get(1)).getText();
				Image img = ((ImageView)((VBox)event.getSource()).getChildren().get(0)).getImage();
				pcNumber.setText(text.substring(2, text.length()));
				PC pc = getCurrentPC(Integer.parseInt(text.substring(2, text.length())));
				pcName.setText(t);
				imgView.setImage(img);
				tempImage = ((ImageView)((VBox)event.getSource()).getChildren().get(0));
			
				for(Order o: mainApp.getOrderData()) {
					if(o.getPc().getId() == pc.getId()) {
						pcCount.setText(o.getCount() + "");
						pcTime.setText(o.getStart_date()+ "");
						break;
					}
				}
				
				if(pc.isState()) {
					btn_time.setDisable(true);
					btn_change.setDisable(false);
					btn_charge.setDisable(false);
					btn_close.setDisable(false);
				}
				else { 
					btn_time.setDisable(false);
					btn_change.setDisable(true);
					btn_charge.setDisable(true);
					btn_close.setDisable(true);
				}
			}
			
		});
		return root;
	}
	TimerTask task = new TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int x = Integer.parseInt(pcCount.getText());
			if(x > 0) {
				x -= 1;
				pcCount.setText(x + "");
				
			}
			for(Order pc: mainApp.getOrderData()) {
				if(pc.getCount() > 0)	 pc.setCount(pc.getCount() - 1);
				if(pc.getCount() <= 1) {
					tempID = pc.getPc().getId();
					closePC(null);
				}
			
			}
		}
			
	};
	
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		this.mydb = mainApp.getMydb();
		try {
			getData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

	

