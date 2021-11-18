public interface INotificationSubscriber {
    void notify(Message message);

    // boolean shouldNotify(Message message);
}