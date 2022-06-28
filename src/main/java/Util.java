/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Util {

    public static String writeEmail(final String address, final String msg) {
        String email = String.format("Notifying %s: %s", address, msg);
        if (Log.ERROR.equals(msg) 
        || (Log.EXCEPTION.equals(msg) && address.contains("Robert")))
        System.out.println(email);
        return email;
    }

    public static String[] getSubscriber(String[] subscribers, String msg) {
            switch
    }
}
