package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.*;
import java.util.*;

import java.util.List;



public class MyThread extends Thread {

    @Override
    public void run() {

        //SimpleServer.session.getTransaction().begin();
        while (true) {
            //Date date=new Date();
            //date.getTime();

            //SimpleServer.GetAllAppointments()
            SimpleServer.Appointments = (ArrayList<AppointmentEntity>)SimpleServer.GetAllAppointments();
            System.out.println("in the thread num: " + this.getId());
            System.out.println(SimpleServer.Appointments.size());

            //Appointment
            for (AppointmentEntity app : SimpleServer.Appointments) {
                if ((app.isReserved() == true) && (app.getPatient() == null)) {
                    app.setReserved(false);
                    SimpleServer.session.getTransaction().begin();
                    SimpleServer.session.saveOrUpdate(app);
                    SimpleServer.session.flush();
                    SimpleServer.session.getTransaction().commit();

                }
                //send mail before 24h
                //Date date_now=new Date();
                Calendar app_cal=Calendar.getInstance();
                app_cal.set(app.getDate().getYear(),app.getDate().getMonthValue(),app.getDate().getDayOfMonth(),app.getDate().getHour(),app.getDate().getMinute(),app.getDate().getSecond());
                //now.setTime(date_now);
                //now.getTime().
                //LocalDateTime now=LocalDateTime.now();
                long day_1 = (60 * 60 * 24)+ (3000000L *1000)-(500000*1000)+(5500600);
                //long day_1 = 1000L * 1000 * 60 * 60 * 24;
                Date now_date_mills = new Date(System.currentTimeMillis());


                long app_time_in_mills=app_cal.getTimeInMillis();

                if((app.isReserved())&&(app.getPatient()!=null)&&(((now_date_mills.getTime()+day_1+30000)>app_time_in_mills))&&(now_date_mills.getTime()+day_1-30000)<(app_time_in_mills))
                {
                    //System.out.println("\n"+"yes!! in appointment in thread send mail");
                    EmailUtil.sendEmailAppointment(app);

                }
            }


            if(LocalDateTime.now().getHour()==23 && LocalDateTime.now().getMinute()==55)
            {
                LocalDate localDate=LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
                int day_of_week= getDayNumberNew(localDate);
                if(day_of_week==6)
                {
                }
            }
            try {
                //sleep(300000);
               sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return (day.getValue());
    }
}
