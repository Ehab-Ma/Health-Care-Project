package il.cshaifasweng.OCSFMediatorExample.client;

import com.mysql.cj.xdevapi.Client;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *SimpleClient :
 *
 *handling messages an EventBuses sent from server and we have getters and setters
 *
 *
 */
public class SimpleClient extends AbstractClient {

    private static SimpleClient client = null;
    public static List<ClinicEntity> ClinicList;
    public static List<PatientEntity> Patients ;
    public static List<ManagerEntity> Managers;
    public static PatientClient patientClient = null;
    public static DoctorClient doctorClient = null;
    public static NurseClient nurseClient = null;
    public static ManagerClient managerClient = null;
    public static ArrayList<Integer> nurse_patients=new ArrayList<Integer>(); // for magnetic card use
    public static int logInFlag = -1;
    public static int availableUsers = 0;
    public static int currentUser = -1;
    public static int cliniclistflag = 0;
    public static int patientlistflag = 0;
    public static long next_nurse_appointment=0;
    public static long next_doc_appointment=0;
    public static ArrayList<PatientEntity> doc_patients = new ArrayList<PatientEntity>(); //for doctor controller use


    private SimpleClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println(msg.getClass().toString());
        if (msg.getClass().equals(Warning.class)) {
            EventBus.getDefault().post(new WarningEvent((Warning) msg));
        } else if (msg.getClass().equals(ArrayList.class) && cliniclistflag == 0) { // receive the clinic list from the server
                ClinicList = ((List<ClinicEntity>) msg);
                cliniclistflag = 1;
            try {
                client.sendToServer("#getAllPatients");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(msg.getClass().equals(ArrayList.class) && patientlistflag == 0)  // receive the patients list from the server
        {
            Patients = ((List<PatientEntity>) msg);
            patientlistflag=1;
            cliniclistflag =2;
            try {
                client.sendToServer("#getAllManagers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(msg.getClass().equals(ArrayList.class) && currentUser!=0) // receive the manager  list from the server
        {
            Managers=((List<ManagerEntity>)msg) ;
            patientlistflag=2;
        }
        else if(currentUser == 0){
            patientClient.handleMessageFromServer(msg);
        } else if(currentUser == 1){
            nurseClient.handleMessageFromServer(msg);
        } else if(currentUser == 2){
            doctorClient.handleMessageFromServer(msg);
        } else if(currentUser == 3){
            managerClient.handleMessageFromServer(msg);
        } else if (msg.getClass().equals(PatientEntity.class)) {
            patientClient = new PatientClient(this.getHost(), this.getPort(), (PatientEntity) msg);
            availableUsers++;
        } else if (msg.getClass().equals(DoctorEntity.class)) {
            doctorClient = new DoctorClient(this.getHost(), this.getPort(), (DoctorEntity) msg);
            availableUsers++;
        } else if (msg.getClass().equals(NurseEntity.class)) {
            nurseClient = new NurseClient(this.getHost(), this.getPort(), (NurseEntity) msg);
            availableUsers++;
        } else if (msg.getClass().equals(ManagerEntity.class)) {
            managerClient = new ManagerClient(this.getHost(), this.getPort(), (ManagerEntity) msg);
            availableUsers++;
        } else if (msg.getClass().equals(String.class)) {
            if (((String) msg).equals("#Login Success")) {
                logInFlag = 1;
            }else if (((String) msg).equals("#Login Active")) {
                logInFlag = 3;
            }else if ((((String) msg).equals("#Login Failure")) && logInFlag != 3) {
                logInFlag = 2;
            }
        }
    }

    public static void resetClient() // when the client logs off from his username we reset it to enable the process to be done again
    {
        availableUsers = 0;
        logInFlag = -1;
        currentUser = -1;
         patientClient = null;
         doctorClient = null;
         nurseClient = null;
         managerClient = null;
    }

    public static SimpleClient getClient() {
        if (client == null) {
            client = new SimpleClient("localhost", 3000);
            client.connectionEstablished();
        }
        return client;
    }
    public static PatientClient getPatientClient() {

        return patientClient;
    }
    public static ManagerClient getManagerClient() {

        return managerClient;
    }

    public static List<ClinicEntity> getClinicList() {
        return ClinicList;
    }

    public static DoctorClient getDoctorClient() {
        return doctorClient;
    }

    public static NurseClient getNurseClient() {
        return nurseClient;
    }

    public int getLogInFlag() {
        return logInFlag;
    }

    public int getAvailableUsers(){
        return availableUsers;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public void LogIn(int id, String password){
        UserEntity thisUser = new UserEntity();
        thisUser.setTmpPassword(password);
        thisUser.setId(id);
        try {
            SimpleClient.getClient().sendToServer(thisUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
