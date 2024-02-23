package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.css.Styleable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.dom4j.rule.Stylesheet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.css.Stylesheet.*;


import javax.swing.plaf.metal.MetalTheme;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * JavaFX App
 * this is the second main class after main
 * here we connect to the server and launch our application that includes sign in to users account
 * start : here we launch out application
 * set root : here we change the window to the desired one
 * loadfxml : here we open the new window
 * stop : unregestirng the client
 * onWarningEvent : warning label to the user
 * main : launching
 * getDoctorApptNum : return the Doctors Appointment's number
 * setDoctorApptNum : setting the Doctors Appointment's number
 * getNurseApptNum :  return the nurse Appointment's number
 * setNurseApptNum : setting the nurse Appointment's number
 *
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private Object Styleable;
    public static int DoctorApptNum = 1;
    public static int nurseApptNum = 0;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        int counter = 0;
       // System.out.println("Getting Clinic List0");
        EventBus.getDefault().register(this);
        //System.out.println("Getting Clinic List4");
    	client = SimpleClient.getClient();
        //sleep(2000);
        System.out.println("Getting Clinic List5");
        //sleep(10000);
        /*if(client == null)
        {
            System.out.println(" Getting Conncec!");

            while (client == null) {
                counter = counter + 1;
                System.out.println(" Getting Conncection!");
            }
            if(client != null)
                client.openConnection();
        }
        else
            System.out.println(" Getting ");*/
        client.openConnection();
        System.out.println("Getting Clinic List1");
        SimpleClient.getClient().sendToServer("#GetAllClinics");
        //sleep(1200);
        //System.out.println("Getting Clinic List2");
        /*while (SimpleClient.getClinicList() == null)
        {
            if(counter == 0) {
                System.out.println("Getting Clinic List");
            }
            counter = counter + 1;
        }*/

        stage.setTitle("Health Center");
        scene = new Scene(loadFXML("primary"), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    }

	public static void main(String[] args) {
        launch();
    }

    public static int getDoctorApptNum() {
        return DoctorApptNum;
    }

    public static void setDoctorApptNum() {
        DoctorApptNum++;
    }

    public static int getNurseApptNum() {
        return nurseApptNum;
    }

    public static void setNurseApptNum() {
        nurseApptNum++;
    }

}