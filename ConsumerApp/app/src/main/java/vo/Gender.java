package vo;

/**
 * Created by matthew on 08-06-17.
 */

public class Gender {
//    FEMALE("FEMALE"),
//    MALE("MALE");

    private String label;

    private Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
