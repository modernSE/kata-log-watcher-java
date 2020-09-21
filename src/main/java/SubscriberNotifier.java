public class SubscriberNotifier {

    public static boolean notifySubscribers(String logMessage, String[] subscribers) {
        for (int i = 0; i < subscribers.length; i++) {
            String name = subscribers[i];
            name = name.toLowerCase();
            name.replace("ü", "ue");
            name.replace("ä", "ae");
            name.replace("ö", "oe");
            name.replace(" ", ".");
            name = name + "@cas.de";

            if(!Util.writeEmail(name, logMessage)){
                return false;
            }
        }
        return true;
    }
    
}