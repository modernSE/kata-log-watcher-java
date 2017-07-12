
public class EmailNameMapper implements NameMapper {

	@Override
	public String map(String name) {
		name = name.toLowerCase();
		name = name.replace("ü", "ue");
		name = name.replace("ä", "ae");
		name = name.replace("ö", "oe");
		name = name.replace(" ", ".");
        name = name + "@cas.de";
		return name;
	}

}
