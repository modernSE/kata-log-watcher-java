import java.util.function.Consumer;

import email.Notificator;

public class TestNotificator implements Notificator{
    private Consumer<String> lambda;

    public TestNotificator(Consumer<String> lambda) {
        this.lambda = lambda;
    }

    @Override
    public void notify(String address, String msg) {
        lambda.accept(address);
    }
}