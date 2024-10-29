package features;

public enum ConstraintType {
    MANDATORY("MANDATORY"),
    OPTIONAL("OPTIONAL"),
    OR("OR"),
    ALTERNATIVE("ALTERNATIVE");

    private final String name;


    ConstraintType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
