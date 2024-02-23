/**
 * Sample Skeleton for 'manager.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.DoctorClinicEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.DoctorEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.css.CSSStyleSheet;

import javax.print.Doc;


/**
 *ManagerController :
 *
 *initialize: init the fxml page
 *here we have the status updating Hours method to the Clinic that accessed only by the Clinic manager
 *
 *
 */
public class ManagerController {
    // set manager's name in initialize()
    /*
        add menu items to doctor menu dynamically like this:
        // choose_doctor_menu.getItems().add(new MenuItem("doctor's name"));
    */

    private ClinicEntity choosen_clinic = SimpleClient.getManagerClient().getClinic();
    private DoctorClinicEntity choosen_doctor_clinic;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="clinic_close_txt"
    private TextField clinic_close_txt; // Value injected by FXMLLoader

    @FXML // fx:id="clinic_current_hours"
    private TextField clinic_current_hours; // Value injected by FXMLLoader

    @FXML // fx:id="clinic_open_txt"
    private TextField clinic_open_txt; // Value injected by FXMLLoader

    @FXML // fx:id="update_clinics_btn"
    private Button update_clinics_btn; // Value injected by FXMLLoader

    @FXML // fx:id="welcome_text"
    private Text welcome_text; // Value injected by FXMLLoader

    @FXML // fx:id="clinic_text"
    private Text clinic_text; // Value injected by FXMLLoader

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
        App.setRoot("login");

    }


    @FXML
    void update_clinics_handler(ActionEvent event) throws IOException {
        if(!clinic_open_txt.getText().equals("")) {
            //choosen_clinic.setOpen(clinic_open_txt.getText());
            ;
        }
        if(!clinic_close_txt.getText().equals("")) {
            //choosen_clinic.setClose(clinic_close_txt.getText());
            ;
        }
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    String.format("Currently Disabled, contact clinic IT staff!")
            );
            alert.show();
        });
        /*SimpleClient.getClient().sendToServer(choosen_clinic);
        clinic_current_hours.setText(choosen_clinic.getOpen() + "-" + choosen_clinic.getClose());*/
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert clinic_close_txt != null : "fx:id=\"clinic_close_txt\" was not injected: check your FXML file 'manager.fxml'.";
        assert clinic_current_hours != null : "fx:id=\"clinic_current_hours\" was not injected: check your FXML file 'manager.fxml'.";
        assert clinic_open_txt != null : "fx:id=\"clinic_open_txt\" was not injected: check your FXML file 'manager.fxml'.";
        assert update_clinics_btn != null : "fx:id=\"update_clinics_btn\" was not injected: check your FXML file 'manager.fxml'.";
        assert welcome_text != null : "fx:id=\"welcome_text\" was not injected: check your FXML file 'manager.fxml'.";
        assert clinic_text != null : "fx:id=\"clinic_text\" was not injected: check your FXML file 'manager.fxml'.";
        welcome_text.setText(SimpleClient.managerClient.getName());  // add manager's name here
        clinic_text.setText(SimpleClient.getManagerClient().getClinic().getName());
        clinic_current_hours.setText(choosen_clinic.getOpen() + "-" + choosen_clinic.getClose());
    }

}
