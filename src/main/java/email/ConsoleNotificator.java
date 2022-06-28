package email;
/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class ConsoleNotificator implements Notificator {

    public void notify(final String address, final String msg) {
        System.out.println("Notifying " + address + ": " + msg);
    }
}
