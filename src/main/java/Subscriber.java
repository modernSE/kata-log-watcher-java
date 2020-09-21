public interface Subscriber {

    public String getName();

    public void onNotify(String formatedName, String message);
}