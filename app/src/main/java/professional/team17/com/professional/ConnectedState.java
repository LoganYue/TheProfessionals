package professional.team17.com.professional;

/**
 * Created by ag on 2018-03-25.
 */

public class ConnectedState {
    public static status state = null;
    private static ConnectedState connectedState = null;

    /**
     *
     * @return the instance of the singleton
     */
    public static ConnectedState getInstance() {
        if (connectedState==null) {
            connectedState = new ConnectedState();
        }
        return connectedState;
    }

    /**
     * Will change to online
     */
    public void setOnline(){
        if (this.state ==null || this.state==status.OFFLINE) {
            this.state = status.ONLINE;
        }

    }

    /**
     * will change state to offline
     */
    public void setOffline(){
        if (this.state ==null || this.state==status.ONLINE) {
            this.state = status.OFFLINE;
        }
    }

    /**
     *
     * @return false if state is online, true if offline
     */
    public boolean isOffline(){
        return (this.state == status.OFFLINE);
    }

    public enum status {
        ONLINE, OFFLINE

    }


}