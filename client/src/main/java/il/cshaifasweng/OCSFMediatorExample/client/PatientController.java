/**
 * Sample Skeleton for 'patient.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;


/**
 *PatientController :
 *
 *initialize: init the fxml page
 *here we have two buttons in case of Reservation Doctor appointment
 * and one for Viewing appointment
 * and canceling appointment button
 *
 *
 */

public class PatientController {


    public List<AppointmentEntity> appointments = SimpleClient.patientClient.getAppointments();
    private AppointmentEntity chosen_appointment;

    private DoctorEntity chosen_doctor;
    private int view_app_counter=0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;



    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="DocBtn"
    private ToggleButton DocBtn; // Value injected by FXMLLoader

    @FXML // fx:id="viewAppsBtn"
    private ToggleButton viewAppsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="welcome_text"
    private Text welcome_text; // Value injected by FXMLLoader

    @FXML
    private ListView<Button> vBox;

    @FXML // fx:id="cancel_app_btn"
    private Button cancel_app_btn; // Value injected by FXMLLoader



    @FXML
    void DocAction(ActionEvent event) throws InterruptedException
    {
        vBox.getItems().clear();
        String role ;
        role = "Doctor";
        for (DoctorClinicEntity doc_patient : SimpleClient.patientClient.getClinic().getDoctorClinicEntities()) { // we search for the doctor in the clinic that the patient belongs to
            if (doc_patient.getDoctor().getRole().equals(role)) {
                chosen_doctor = doc_patient.getDoctor();
                for (AppointmentEntity app : doc_patient.getDoctor().getAppointments()) {
                    if (!(app.getDate().isAfter(LocalDateTime.now().plusWeeks(4)))) // adding the appointments fot the next four weeks
                        vBox.getItems().add(new Button(app.getDate().toString()));
                    vBox.getItems().sort(Comparator.comparing(o -> o.getText()));
                }
            }
        }
            for (Button button : vBox.getItems()) {
                button.setOnAction(ActionEvent ->
                {
                    for (AppointmentEntity app1 : chosen_doctor.getAppointments()) {
                        if (app1.getDate().toString().equals(button.getText())) {
                            try {
                                SimpleClient.getClient().sendToServer(app1); // sending to server so that it updates the Reserved field to true
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                        String.format("Please confirm your reservation!")
                                );
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    app1.setPatient(SimpleClient.patientClient.getPatient());
                                    Platform.runLater(() -> {
                                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
                                                String.format("Email me:"), new ButtonType("Press")
                                        );
                                        Optional<ButtonType> method_result = alert1.showAndWait();
                                        try {
                                            app1.setReserved(true);
                                            SimpleClient.getClient().sendToServer(app1);
                                            while(SimpleClient.patientClient.appointment_flag ==-1)
                                            {
                                                ProgressBar pb = new ProgressBar(0.6);
                                                ProgressBar pi = new ProgressBar(0.6);
                                            }
                                            if(SimpleClient.patientClient.appointment_flag ==1)
                                            {
                                                //System.out.println("Ehab ");
                                                //System.out.println(chosen_appointment.getId());
                                                Platform.runLater(() -> {
                                                    Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION,
                                                            String.format("reserved successfully!")

                                                    );
                                                    alert3.show();
                                                });
                                            }
                                            else
                                            {
                                                Platform.runLater(() -> {
                                                    Alert alert4 = new Alert(Alert.AlertType.ERROR,
                                                            String.format("failed to reserve the appointment!")
                                                    );
                                                    alert4.show();
                                                });
                                            }
                                            SimpleClient.patientClient.appointment_flag=-1;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                                else if (result.get() == ButtonType.CANCEL) {
                                    app1.setReserved(false);
                                    try {
                                        SimpleClient.getClient().sendToServer(app1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        //System.out.println("Baseem Id: ");
        //System.out.println(chosen_appointment.getId());
    }






    @FXML
    void backBtnAction(ActionEvent event) throws IOException { // go back to the previous page
        App.setRoot("primary");
        SimpleClient.resetClient();
    }



    @FXML
    void viewAppsAction(ActionEvent event) throws IOException { // view my appointments
        vBox.getItems().clear(); //clear the list view buttons
        SimpleClient.getClient().sendToServer("#getPatientApps:" + SimpleClient.patientClient.getPatient().getPatientId()); // get the updated appointments of the patient
        cancel_app_btn.setDisable(false); //enabling the cancel button
        for(AppointmentEntity app : SimpleClient.patientClient.getAppointments()) //adding the appointments to the list
        {
            vBox.getItems().add(new Button(app.getDate().toString()+"-"+ app.getDoctor().getFirst_name() + " " + app.getDoctor().getFamily_name()));
        }
        for(Button button : vBox.getItems())
        {
            button.setOnAction(ActionEvent -> {
                for(AppointmentEntity app : SimpleClient.patientClient.getAppointments())
                {
                    if((app.getDate().toString()+"-"+ app.getDoctor().getFirst_name() + " " + app.getDoctor().getFamily_name()).equals(button.getText()))
                    {
                        chosen_appointment=app;
                    }
                }
            });
        }
    }

    @FXML
    void cancel_app_action(ActionEvent event) throws IOException {
        for(Button button : vBox.getItems())
        {
            button.setOnAction(ActionEvent -> {
                for(AppointmentEntity app : SimpleClient.patientClient.getAppointments())
                {
                    if((app.getDate().toString()+"-"+ app.getDoctor().getFirst_name() + " " + app.getDoctor().getFamily_name()).equals(button.getText()))
                    {
                        chosen_appointment=app;
                    }
                }
            });
        }

        //System.out.println("Patient Id: " + chosen_appointment.getId());
        if(chosen_appointment!=null) //if the patient has selected an appointment we need to delete it//
        {
                System.out.println("appoinment not null");
                chosen_appointment.setPatient(null);
                SimpleClient.getClient().sendToServer(chosen_appointment); //sending to server an appointment with isReserved=true and patient != null
        }

        if(SimpleClient.patientClient.cancel_app_flag==1)
        {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        String.format("Appointment Canceled!")
                );
                alert.show();
            });
        }
    }


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {

        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'patient.fxml'.";
        assert DocBtn != null : "fx:id=\"DocBtn\" was not injected: check your FXML file 'patient.fxml'.";
        assert viewAppsBtn != null : "fx:id=\"viewAppsBtn\" was not injected: check your FXML file 'patient.fxml'.";
        assert welcome_text != null : "fx:id=\"welcome_text\" was not injected: check your FXML file 'patient.fxml'.";
        assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'patient.fxml'.";
        assert viewAppsBtn != null : "fx:id=\"viewAppsBtn\" was not injected: check your FXML file 'patient.fxml'.";
        SimpleClient.getClient().setCurrentUser(0);
        cancel_app_btn.setDisable(true); // disabling the cancel button
        welcome_text.setText(SimpleClient.patientClient.getName());  // add patient's name here


    }
}
