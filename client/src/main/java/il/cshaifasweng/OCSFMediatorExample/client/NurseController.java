/**
 * Sample Skeleton for 'doctor.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.PatientEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.*;
import javafx.scene.control.ListView;


/**
 *NurseController :
 *
 *initialize: init the fxml page
 *here we have two screens to view Patients and View Appointments
 *
 *
 */

public class NurseController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button call_next_patient_btn;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="view_apps"
    private Button view_apps; // Value injected by FXMLLoader


    @FXML // fx:id="welcome_text"
    private Text welcome_text; // Value injected by FXMLLoader


    @FXML
    private ListView<Text> patients_area;


    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void callingNextPatient(ActionEvent event) {
        patients_area.getItems().remove(0);
        SimpleClient.nurse_patients.remove(0);
        App.setNurseApptNum();
    }

    @FXML
    void view_apps_handler(ActionEvent event) { // add doctor's appointments dynamically
        //apps_area.setText("appointment information (date , clinic, patient's name");
        int i = 1;
        for(int patientNum : SimpleClient.nurse_patients)
        {
            for(PatientEntity patient :  SimpleClient.Patients)
            {
                if(patient.getId() == patientNum) {
                    patients_area.getItems().add(new Text(patient.getFirst_name() + " " + patient.getFamily_name() + " - App number:" + i));
                    i++;
                }
            }
        }
    }

    @FXML
    void view_patients_handler(ActionEvent event) { // add doctor's patients dynamically
        //patients_area.setText("patient's name");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'doctor.fxml'.";
        assert view_apps != null : "fx:id=\"view_apps\" was not injected: check your FXML file 'doctor.fxml'.";
        assert welcome_text != null : "fx:id=\"welcome_text\" was not injected: check your FXML file 'doctor.fxml'.";
        assert patients_area != null : "fx:id=\"patients_area\" was not injected: check your FXML file 'doctor.fxml'.";
        assert call_next_patient_btn != null : "fx:id=\"call_next_patient_btn\" was not injected: check your FXML file 'nurse.fxml'.";

        welcome_text.setText(SimpleClient.nurseClient.getName());   // nurse's name here
    }

}
