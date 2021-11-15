public class Person {
    private String name;
    private String emailAddress;
    private LogLinePredicate predicate;

    public Person(String name, LogLinePredicate predicate) {
        this.name = name;
        this.emailAddress = calculateEmailAddress(name);
        this.predicate = predicate;
    }

    private String calculateEmailAddress(String name) {
        String address = name.toLowerCase();
        address = address.replace("ü", "ue");
        address = address.replace("ä", "ae");
        address = address.replace("ö", "oe");
        address = address.replace(" ", ".");
        address = address + "@cas.de";
        return address;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LogLinePredicate getPredicate() {
        return predicate;
    }
}
