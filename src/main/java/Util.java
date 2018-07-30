/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Util {

    public void writeEmail(Mail mail) {
        System.out.println("Notifying " + mail.getAddress() + ": " + mail.getMsg());
    }
}
