/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Util {

    public static void writeEmail(final Email email) {
        System.out.println("Notifying " + email.address + ": " + email.message);
    }
    
    public static void sendToChatBot(final Email email) {
        System.out.println("Notifying BOT " + email.address + ": " + email.message);
    }

    
    public static Email createEmailObject(final String message, final String address) {
        Email email = new Email();
        email.address = address;
        email.message = message;        		
        return email;
    }
}
