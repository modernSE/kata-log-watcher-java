import java.util.List;

public class Subscriber {
    
    String name;
    List<Integer> codesToNotify;

    public Subscriber(String name, List<Integer> codesToNotify) {
        this.name = name;
        this.codesToNotify = codesToNotify;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getCodesToNotify() {
        return codesToNotify;
    }

}
