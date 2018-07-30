import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class LogWatcher {

    public void watchAndAlert() {
        Optional<String> logEntry = Log.popNextLine();
        watchAndAlert(logEntry);
    }
    
    public void watchAndAlert(final Optional<String> logEntry) {
        if(logEntry.isPresent()) {
        	Set<Email> emails = this.createNotifications(logEntry.get());
	    	  for(Email email : emails) {
	          	Util.writeEmail(email);        	
	          }
        }
    }

    protected Set<Email> createNotifications(String logMessage) {
    	
    	Set<Email> notifications = new HashSet<>();
    	
    	
    		for (int i = 0; i < User.subscribers.length; i++) {
                User user = User.subscribers[i];
            	for(Subscription subscription : user.subscriptions) {
            		 if(shouldBeSend(logMessage, subscription)) {
            			 String name = user.firstname + " " + user.lastname;
                         String emailAddress = createEmailAddress(name);
                         notifications.add(Util.createEmailObject(emailAddress, logMessage));  
                         
                         if(target = "EMAIL") {	
            				 
            			 }
                         
                         for(Subscription subSubscription : subscription.subSubscriptions) {
                        	 if(subSubscription.name.equals("LAST_STACK_TRACE")) {
                        		 if(Log.lastTraceMessage.isPresent()) {
                        			 notifications.add(Util.createEmailObject(emailAddress, Log.lastTraceMessage.get())); 
                        		 } else {
                        			 notifications.add(Util.createEmailObject(emailAddress, "NO STACK TRACE AVAILABLE"));
                        		 }
                        	 }
                         }
                         
                         break;
    	             }
            	}
            }
        
    	return notifications;
    }
    
    public boolean shouldBeSend(final String message, final Subscription subscription) {
    	if(subscription.name.equals(message)) {
			return true;
		}
    	
    	return false;
    }
    
    public String createEmailAddress(String name) {
    	name = name.toLowerCase();
        name = name.replace("ü", "ue");
        name = name.replace("ä", "ae");
        name = name.replace("ö", "oe");
        name = name.replace(" ", ".");
        name = name + "@cas.de";
        return name;
    }
}
