package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.ManagerEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.greenrobot.eventbus.EventBus;

/**
 *ManagerClient :
 *
 *
 *
 *Handling messages in case of managerClient
 *
 *
 */
public class ManagerClient  extends AbstractClient {
    private static ManagerClient client = null;
    public static ManagerEntity Manager = null;

    public ManagerClient(String host, int port, ManagerEntity Manager) {
        super(host, port);
        this.Manager = Manager;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        if (msg.getClass().equals(Warning.class)) {
            EventBus.getDefault().post(new WarningEvent((Warning) msg));
        } else if (msg.getClass().equals(ManagerEntity.class)) {
            Manager = (ManagerEntity) msg;
        }else if(msg.getClass().equals(String.class))
        {
            System.out.println("ManagerClient string");
        }
    }

    public static ManagerClient getClient() {
        if (client == null) {
            client = new ManagerClient("localhost", 3000, Manager);
        }
        return client;
    }

    public String getName()
    {
        return (this.Manager.getFirst_name() + " " + this.Manager.getFamily_name());
    }

    public ClinicEntity getClinic()
    {
        return this.Manager.getClinic();
    }

}