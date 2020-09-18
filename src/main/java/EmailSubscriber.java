public class EmailSubscriber implements Subscriber{
    private String name;
    public EmailSubscriber(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
    
        return name;
    }

    @Override
    public void onNotify(String formatedName, String message) {
        
        Util.writeEmail(formatedName, message);
    }
    
}