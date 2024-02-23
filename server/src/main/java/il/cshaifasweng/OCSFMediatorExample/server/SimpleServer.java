package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import net.bytebuddy.asm.Advice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;



/**
 * SimpleServer
 *
 * handling messages that sent from client and making connection to the database
 *
 */

public class SimpleServer extends AbstractServer {
    public static Session session;
    private static List<ClinicEntity> Clinics;
    private static List<PatientEntity> Patients ;
    private static List<ManagerEntity> managers;
    public static ArrayList<AppointmentEntity> Appointments=new ArrayList<AppointmentEntity>();
    private static ArrayList<AppointmentEntity> doc_old_apps = new ArrayList<AppointmentEntity>();



    public void initSesssion() {
        session = getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            String[] service = new String[]{"aaa", "bbb", "ccc"};

            /*
            *
            * Clinics init
            *
            *
            * */

            ClinicEntity clinic1 = new ClinicEntity("Mashhad clinic", "18:00", "23:00", service, new ArrayList<PatientEntity>());
            session.save(clinic1);
            service = new String[]{"bbb", "ddd", "ccc"};
            ClinicEntity clinic2 = new ClinicEntity("Deir-Hanna clinic", "08:00", "09:00", service, new ArrayList<PatientEntity>());
            session.save(clinic2);
            service = new String[]{"aaa", "eee", "ddd"};
            ClinicEntity clinic3 = new ClinicEntity("Tel-Aviv clinic", "08:00", "09:00", service, new ArrayList<PatientEntity>());
            session.save(clinic3);
            service = new String[]{"ww", "zz"};
            ClinicEntity clinic4 = new ClinicEntity("Haifa clinic", "08:00", "09:00", service, new ArrayList<PatientEntity>());
            session.save(clinic4);


            /* Patients init */
            PatientEntity pat1 = new PatientEntity(1,"Ehab","Mansour","ehab.m.97@hotmail.com","1",25,clinic1);
            session.save(pat1);
            PatientEntity pat2 = new PatientEntity(2,"Baseem","Salem","baseem.salem@gmail.com","1",23,clinic2);
            session.save(pat2);
            PatientEntity pat3 = new PatientEntity(3,"Shady","Salem","pat3@gmail.com","1",21,clinic3);
            session.save(pat3);
            PatientEntity pat4 = new PatientEntity(4,"Basel","Salem","pat4@gmail.com","1",26,clinic4);
            session.save(pat4);

            /*
            *
            *
            * Nurses init
            *
            *
            * */
            NurseEntity nurse1 = new NurseEntity(5,"Nurse.","1","nurse1@gmail.com","1",clinic1);
            session.save(nurse1);
            NurseEntity nurse2 = new NurseEntity(6,"Nurse.","2","nurse2@gmail.com","1",clinic2);
            session.save(nurse2);
            NurseEntity nurse3 = new NurseEntity(7,"Nurse.","3","nurse3@gmail.com","1",clinic3);
            session.save(nurse3);
            NurseEntity nurse4 = new NurseEntity(8,"Nurse.","4","nurse4@gmail.com","1",clinic4);
            session.save(nurse4);

            /*
            *
            *
            * Doctor init
            *
            *
            *  */
            DoctorEntity doc1= new DoctorEntity(9,"dr.","1","dr1@gmail.com","1","Doctor");
            session.save(doc1);
            DoctorEntity doc2= new DoctorEntity(10,"dr.","2","dr2@gmail.com","1","Doctor");
            session.save(doc2);
            DoctorEntity doc3= new DoctorEntity(11,"dr.","3","dr3@gmail.com","1","Doctor");
            session.save(doc3);
            DoctorEntity doc4= new DoctorEntity(12,"dr.","4","dr4@gmail.com","1","Doctor");
            session.save(doc4);

            /*
            *
            * Times init
            *
            *
            * */
            ArrayList<String> times=new ArrayList<String>();
            times.add("18:00-23:00");
            times.add("08:00-09:00");
            times.add("08:00-09:00");
            times.add("08:00-09:00");
            times.add("08:00-09:00");
            times.add("08:00-09:00");
            times.add("00:00-00:00");

            /*
            *
            * DoctorClinic Init
            *
            * */
            DoctorClinicEntity doctorClinic1= new DoctorClinicEntity(doc1,clinic1,times);
            session.save(doctorClinic1);
            DoctorClinicEntity doctorClinic2= new DoctorClinicEntity(doc2,clinic2,times);
            session.save(doctorClinic2);
            DoctorClinicEntity doctorClinic3= new DoctorClinicEntity(doc3,clinic3,times);
            session.save(doctorClinic3);
            DoctorClinicEntity doctorClinic4= new DoctorClinicEntity(doc4,clinic4,times);
            session.save(doctorClinic4);

            /*
            *
            * Manager init
            * */
            ManagerEntity man1 = new ManagerEntity(doc1.getId(), "man", "1",
                    doc1.getMail(),"111",clinic1);
            session.save(man1);
            ManagerEntity man2 = new ManagerEntity(doc2.getId(), "man", "2",
                     doc2.getMail(),"111",clinic2);
              session.save(man2);
               ManagerEntity man3 = new ManagerEntity(doc3.getId(), "man", "3",
                       doc3.getMail(),"111",clinic3);
            session.save(man3);
             ManagerEntity man4 = new ManagerEntity(doc4.getId(), "man", "4",
                      doc4.getMail(),"111",clinic4);
             session.save(man4);

             /*
             *
             *
             *Doctor Patient init
             *
             * */
              DoctorPatientEntity docpat1 = new DoctorPatientEntity(doc1,pat1);
              session.save(docpat1);
              DoctorPatientEntity docpat2 = new DoctorPatientEntity(doc2,pat2);
              session.save(docpat2);
              DoctorPatientEntity docpat3 = new DoctorPatientEntity(doc3,pat3);
              session.save(docpat3);
              DoctorPatientEntity docpat4 = new DoctorPatientEntity(doc4,pat4);
              session.save(docpat4);
              session.flush();
              session.getTransaction().commit();
              UpdateAppointments();
              Appointments = (ArrayList<AppointmentEntity>) GetAllAppointments();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        }

        //Appointments = (ArrayList<AppointmentEntity>) GetAllAppointments();
    }

    public SimpleServer(int port) {
        super(port);

        initSesssion();
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public void stopSession() {
        if (session != null) {
            session.close();
        }
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ClinicEntity.class);
        configuration.addAnnotatedClass(PatientEntity.class);
        configuration.addAnnotatedClass(NurseEntity.class);
        configuration.addAnnotatedClass(DoctorEntity.class);
        configuration.addAnnotatedClass(DoctorClinicEntity.class);
        configuration.addAnnotatedClass(ManagerEntity.class);
        configuration.addAnnotatedClass(DoctorPatientEntity.class);
        configuration.addAnnotatedClass(AppointmentEntity.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String msgString = msg.toString();
        if (msgString.startsWith("#warning")) {
            Warning warning = new Warning("Warning from server!");
            try {
                client.sendToClient(warning);
                System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#CloseSession")) {
            stopSession();
        }
        else if(msgString.equals("#getAllPatients"))
        {
            List<PatientEntity> patients = getALLPatients();
            Patients = patients;
            try {
                client.sendToClient(Patients);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(msgString.startsWith("#getPatientApps:"))
        {
            msgString=msgString.substring(16);
            try {
                ArrayList<AppointmentEntity> patient_apps=get_apps_with_PatientId((Integer.parseInt(msgString)));
                client.sendToClient(patient_apps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(msgString.equals("#getAllManagers"))
        {
            managers = getALLMangers();
            try {
                client.sendToClient(managers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (msgString.equals("#GetAllClinics")) {
            try {
                //UpdateAppointments();


                List<ClinicEntity> clinics = getALLClinics();
                Clinics = clinics;
                client.sendToClient(clinics);
                System.out.format("Sent all clinics to client %s\n", client.getInetAddress().getHostAddress());
            } catch (Exception e) {
                if (session != null) {
                    session.getTransaction().rollback();
                }
            }
        }
        else if(msgString.startsWith("#updateAppsForDoc:"))
        {
            msgString = msgString.substring(18);
            int i =Integer.parseInt(msgString);
            AppointmentEntity current_app = Appointments.get(i);
            while(Appointments.get(i).getDoctor().getDoctor_id()==current_app.getDoctor().getDoctor_id() && Appointments.get(i).getClinic().getId()==current_app.getClinic().getId()
            && ((Appointments.get(i).getDate().equals(Appointments.get(i-1).getDate().plusMinutes(20))) || (Appointments.get(i).getDate().equals(Appointments.get(i-1).getDate().plusMinutes(15)))) )
            {
                AppointmentEntity tmp = Appointments.get(i);
                LocalDateTime time = tmp.getDate();
                if(tmp.getActual_date() != null)
                {
                    time = tmp.getActual_date();
                }
                if(Appointments.get(i).getDoctor().getRole().equals("Doctor"))
                {
                    tmp.setActual_date(time.plusMinutes(15));
                }
                else {
                    tmp.setActual_date(time.plusMinutes(20));
                }
                if(Appointments.get(i).getPatient().equals(null)) //if we found an appointment with a null patient this means that we have already updated all the reserved appointments
                {
                    break;
                }

            }
        }
        else if(msgString.startsWith("#increase nurse app:"))
        {
            LocalDate localDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
            int day_of_week= getDayNumberNew(localDate);
            if(day_of_week==7)
                day_of_week=0;
            msgString = msgString.substring(20);
            for(ClinicEntity clinic : Clinics)
            {
                if(clinic.getId() == Integer.parseInt(msgString))
                {
                    break;
                }
            }
        }
        else if (msg.getClass().equals(ClinicEntity.class)) {
            for (int i = 0; i < Clinics.size(); i++) {
                if (Clinics.get(i).getId() == ((ClinicEntity) msg).getId()) {
                    session.beginTransaction();
                    Clinics.get(i).setOpen(((ClinicEntity) msg).getOpen());
                    Clinics.get(i).setClose(((ClinicEntity) msg).getClose());
                    session.save(Clinics.get(i));
                    session.flush();
                    session.getTransaction().commit();
                    System.out.format("Updating all clinics on client %s\n", client.getInetAddress().getHostAddress());
                }
            }
        }
        else if (msg.getClass().equals(AppointmentEntity.class))
        {
            AppointmentEntity app=get_app_with_id(((AppointmentEntity) msg).getId());
            System.out.println(app.getDate());
            if(!((AppointmentEntity) msg).isReserved()) // the client has pressed on app but not confirmed the reservation yet
            {
                app.setReserved(true);
            }
            else if((app.getPatient()==null) ) { // the client has confirmed the reservation
                app.setPatient(((AppointmentEntity) msg).getPatient());
                app.setReserved(true);
                EmailUtil.sendEmail((((AppointmentEntity) msg).getPatient()).getMail(),"appointment confirmation","you have appointment in :"+app.getClinic().getName().toString()+"\n"+"with doctor: "+app.getDoctor().getFamily_name().toString()+"\n"+"at : "+app.getDate());
                try {
                    client.sendToClient("reservation done!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((AppointmentEntity) msg).getPatient().getAppointments().add((AppointmentEntity) msg);
            }
            else if(((AppointmentEntity) msg).isReserved() == true)   // isReserved=true and patient != null so we need to cancel the appointment
            {
                System.out.println("Shaky");
                app.setReserved(false);
                app.setPatient(null);
                try {
                    client.sendToClient("Appointment Cancelled!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*if((app.getPatient()==null) ) { // the client has confirmed the reservation
                app.setPatient(((AppointmentEntity) msg).getPatient());
                EmailUtil.sendEmail((((AppointmentEntity) msg).getPatient()).getMail(),"appointment confirmation","you have appointment in :"+app.getClinic().getName().toString()+"\n"+"with doctor: "+app.getDoctor().getFamily_name().toString()+"\n"+"at : "+app.getDate());
                try {
                    client.sendToClient("reservation done!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((AppointmentEntity) msg).getPatient().getAppointments().add((AppointmentEntity) msg);
            }*/
            /*else  // isReserved=true and patient != null so we need to cancel the appointment
            {
                app.setReserved(false);
                app.setPatient(null);
                try {
                    client.sendToClient("Appointment Cancelled!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            session.beginTransaction();
            session.saveOrUpdate(app);
           // session.saveOrUpdate(((AppointmentEntity) msg).getPatient());
            session.flush();
            session.getTransaction().commit();
        }
        else if (msg.getClass().equals(UserEntity.class)){
            System.out.println(msg.toString());
            System.out.println(msg.getClass().toString());
            List<ManagerEntity> Mangers = getALLMangers();
           boolean flag_manager = checkPassword(Mangers,((UserEntity) msg),client);
            List<DoctorEntity> Doctors = getALLDoctors();
            boolean flag_doctor = checkPassword(Doctors,((UserEntity) msg),client);
            List<NurseEntity> Nurses = getALLNurses();
            boolean flag_nurse = checkPassword(Nurses,((UserEntity) msg),client);
            List<PatientEntity> Patients = getALLPatients();
            boolean flag_patient = checkPassword(Patients,((UserEntity) msg),client);
            String stringResult="";
            if(flag_manager||flag_doctor||flag_patient||flag_nurse){
                System.out.println("flags ok");
                stringResult="#Login Success";
            }else{
                stringResult="#Login Failure";
            }
            try {

                client.sendToClient(stringResult);

            }catch (Exception e) {
                if (session != null) {
                    session.getTransaction().rollback();
                }
            }

        }

    }
    <T extends UserEntity> boolean checkPassword(List<T> Users,UserEntity user,ConnectionToClient client){  // check if the entered username and password exists and correct
        for (int i = 0 ; i < Users.size(); i++){
            if(user.getId() == Users.get(i).getId()){
                if(Users.get(i).comparePassword(user.getPassword())) {
                    try {
                        client.sendToClient(Users.get(i));
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (session != null) {
                            session.getTransaction().rollback();
                        }
                    }
                }else{

                    return false;
                }
            }
        }
        return  false;
    }


    private static void UpdateAppointments(){  // update the appointments for the doctors by their working hours
        //session.getTransaction().begin();
        System.out.println("Update App");
        LocalDateTime now=LocalDateTime.now();
        now.getDayOfWeek();
        List<DoctorEntity> all_docs=getALLDoctors();
        for (DoctorEntity doc:all_docs) {
            List<DoctorClinicEntity> doc_clinics = doc.getDoctorClinicEntities();
            List<AppointmentEntity> doc_appointments = doc.getAppointments();
            for (AppointmentEntity app:doc_appointments) {

                if(app.getDate().isBefore(now)) {
                    if(app.isReserved()) {
                        doc_old_apps.add(app);
                    }
                    /*
                    *
                    *adding the appointment to the old apps array
                    *
                    * */
                    Appointments.remove(app); //removing the appointment from the array of the current apps
                }
            }
            LocalDateTime latest_appointment;
            if(doc_appointments.size()>0)
            {
                doc_appointments.sort(Comparator.comparing(o -> o.getDate()));
                latest_appointment=doc_appointments.get(doc_appointments.size()-1).getDate();
                System.out.println(latest_appointment.toString());

            }else{
                latest_appointment = now;
            }
            for (int i = latest_appointment.getMonthValue() ; i <= (now.getMonthValue()+3); i++)
            {
                int year= now.getYear();
                if(i>12){
                    year++;
                }
               /* Calendar cal = Calendar.getInstance();
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);*/
                YearMonth yearMonthObject = YearMonth.of(year, i);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                for (int j=1;j<=daysInMonth;j++){
                    for(DoctorClinicEntity doc_clinic : doc_clinics){
                        List<LocalTime> work_hours = doc_clinic.GetWorkingDateTime();
                        LocalDate localDate=LocalDate.of(year,i,j);
                        int day_of_week= getDayNumberNew(localDate);
                        if(day_of_week==7){
                            day_of_week=1;
                        }else{
                            day_of_week++;
                        }
                        LocalTime opening = work_hours.get(2*(day_of_week-1));
                        LocalTime closing =work_hours.get(2*(day_of_week-1)+1);
                        int duration=15;
                        if(!opening.toString().equals("00:00") && !closing.toString().equals("00:00")){
                            for(int hour=opening.getHour()*60;hour<=closing.getHour()*60;hour+=duration) {
                                LocalDateTime appointment_time = LocalDateTime.of(year, i % 12, j, hour / 60, hour % 60);
                                if (!appointment_time.isBefore(now)) {

                                    AppointmentEntity app = new AppointmentEntity(appointment_time, doc_clinic, duration);
                                   // doc.getAppointments().add(app);

                                    session.getTransaction().begin();
                                    session.saveOrUpdate(app);
                                    session.flush();
                                    session.getTransaction().commit();

                                }
                            }
                        }
                    }
                    //session.flush();

                }
            }
        }
        //session.getTransaction().commit();
    }
    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return (day.getValue());
    }


    private static List<ClinicEntity> getALLClinics() { // get all the clinics from the database3
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClinicEntity> query = builder.createQuery(ClinicEntity.class);
        query.from(ClinicEntity.class);
        List<ClinicEntity> result = session.createQuery(query).getResultList();
        return result;
    }

    private static List<PatientEntity> getALLPatients()  // get all the patients from the database
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> query = builder.createQuery(PatientEntity.class);
        query.from(PatientEntity.class);
        List<PatientEntity> result = session.createQuery(query).getResultList();
        return result;
    }
    private static List<NurseEntity> getALLNurses() {  // get all the nurses from the database
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NurseEntity> query = builder.createQuery(NurseEntity.class);
        query.from(NurseEntity.class);
        List<NurseEntity> result = session.createQuery(query).getResultList();
        return result;
    }
    private static List<DoctorEntity> getALLDoctors() {  // get all the doctors from the database
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DoctorEntity> query = builder.createQuery(DoctorEntity.class);
        query.from(DoctorEntity.class);
        List<DoctorEntity> result = session.createQuery(query).getResultList();
        return result;
    }
    private static List<ManagerEntity> getALLMangers() { // get all the managers from the database
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ManagerEntity> query = builder.createQuery(ManagerEntity.class);
        query.from(ManagerEntity.class);
        List<ManagerEntity> result = session.createQuery(query).getResultList();
        return result;
    }
    private static List<DoctorClinicEntity> getALLDoctorClinics() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DoctorClinicEntity> query = builder.createQuery(DoctorClinicEntity.class);
        query.from(DoctorClinicEntity.class);
        List<DoctorClinicEntity> result = session.createQuery(query).getResultList();
        return result;
    }

    public static List<AppointmentEntity> GetAllAppointments() {  // get all the appointments from the database
       // UpdateAppointments();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AppointmentEntity> query = builder.createQuery(AppointmentEntity.class);
        query.from(AppointmentEntity.class);
        List<AppointmentEntity> result = session.createQuery(query).getResultList();
        return result;
    }


    static <T> Predicate equal(CriteriaBuilder cb, Expression<T> left, T right) {
        return cb.equal(left, right);
    }

    private static AppointmentEntity get_app_with_id(int id)  // get the appointment with the same id from the database
    {
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AppointmentEntity> query = builder.createQuery(AppointmentEntity.class);
            Root<AppointmentEntity> tmp = query.from(AppointmentEntity.class);
            query.select(tmp);
            query.where(builder.equal(tmp.get("id"),id));
            TypedQuery<AppointmentEntity> q = session.createQuery(query);
            AppointmentEntity app = q.getSingleResult(); //getSingleResult();
            return app;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    private static ArrayList<AppointmentEntity> get_apps_with_PatientId(int patient_id)  // get all the appointments of the given patient
    {
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AppointmentEntity> query = builder.createQuery(AppointmentEntity.class);
            Root<AppointmentEntity> tmp = query.from(AppointmentEntity.class);
            query.select(tmp);
            query.where(builder.equal(tmp.get("patient"),patient_id));
            TypedQuery<AppointmentEntity> q = session.createQuery(query);
            ArrayList<AppointmentEntity> apps = (ArrayList<AppointmentEntity>) q.getResultList(); //getSingleResult();
            return apps;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    private static ManagerEntity get_manager_with_id(int id)  // get the manager with the given id from the database
    {
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ManagerEntity> query = builder.createQuery(ManagerEntity.class);
            Root<ManagerEntity> tmp = query.from(ManagerEntity.class);
            query.select(tmp);
            query.where(builder.equal(tmp.get("manager_id"),id));
            TypedQuery<ManagerEntity> q = session.createQuery(query);
            ManagerEntity manager = q.getSingleResult(); //getSingleResult();
            return manager;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
