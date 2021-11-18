public class StandardOutputEmailService implements EmailService {

    @Override
    public void writeEmail(Email email) {
        System.out.println("Notifying " + email.getAddress() + ": " + email.getMessage());
    }
    
}
