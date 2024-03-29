package il.cshaifasweng.OCSFMediatorExample.client;

import com.sun.prism.MediaFrame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *PrimaryController :
 *
 *initialize: init the fxml page
 *here the ussers login to the system and here we have button to change the station to the magnetic one
 *
 *
 */
public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button LoginBtn;

	@FXML
	private TextField ID;

	@FXML
	private AnchorPane anchor_pane;

	@FXML
	private Text welcome_text;

	@FXML
	private VBox vBox;


	@FXML
	private PasswordField Password;

  
	@FXML
	private Button NurseBtn;

	@FXML // fx:id="control_station_btn"
	private static Button control_station_btn; // Value injected by FXMLLoader

	public static void DisableControl_station_btn()
	{
		control_station_btn.setDisable(true);
	}
	public static void EnableControl_station_btn()
	{
		control_station_btn.setDisable(false);
	}


	@FXML
	void initialize() {
		assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'primary.fxml'.";
		assert ID != null : "fx:id=\"ID\" was not injected: check your FXML file 'primary.fxml'.";
		assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'primary.fxml'.";
		assert anchor_pane != null : "fx:id=\"anchor_pane\" was not injected: check your FXML file 'primary.fxml'.";
		assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'primary.fxml'.";
		assert control_station_btn != null : "fx:id=\"control_station_btn\" was not injected: check your FXML file 'primary.fxml'.";

	}

	@FXML
	public void Login(javafx.event.ActionEvent actionEvent) throws IOException {
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ID.getText());
		if (m.matches()) {
			SimpleClient.getClient().LogIn(Integer.parseInt(ID.getText()), Password.getText());
			while (SimpleClient.getClient().logInFlag == -1) {
				ProgressBar pb = new ProgressBar(0.6);
				ProgressBar pi = new ProgressBar(0.6);
			}
			if (SimpleClient.getClient().logInFlag == 1 && ID.getText().length() < 10) { // TODO change to == 9
				 if(SimpleClient.getClient().getAvailableUsers() < 1){ // error in available users
				 	SimpleClient.getClient().logInFlag = -1;
				 }else if(SimpleClient.getClient().getAvailableUsers() == 1){
				 	App.setRoot("patient");
					 SimpleClient.getClient().setCurrentUser(0);
				 }else{
				 	App.setRoot("login");
				 }
			} else if(SimpleClient.getClient().logInFlag == 3){
				SimpleClient.getClient().logInFlag = -1;
				ID.setText("");
				Password.setText("");
				Platform.runLater(() -> {
					Alert alert = new Alert(Alert.AlertType.ERROR,
							String.format("User already active")
					);
					alert.show();
				});
			} else {
				Password.setText("");
				SimpleClient.getClient().logInFlag = -1;
				Platform.runLater(() -> {
					Alert alert = new Alert(Alert.AlertType.ERROR,
							String.format("Incorrect Id or Password, try again")
					);
					alert.show();
				});
			}
		} else {
			ID.setText("");
			Password.setText("");
			Platform.runLater(() -> {
				Alert alert = new Alert(Alert.AlertType.ERROR,
						String.format("ID should contain only numbers, try again")
				);
				alert.show();
			});
		}
	}

	@FXML
	public void control_station_action(javafx.event.ActionEvent actionEvent) throws IOException{
		App.setRoot("magnetic_card_Login");
	}
}
