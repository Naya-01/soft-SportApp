package features;

public abstract class AbstractFeature implements Feature {
    protected String name;
    protected boolean active;
    protected ConstraintType constraintType;
    protected String groupName;
    private String parentName;

    private String depend_on;

    public AbstractFeature(String name, boolean active, ConstraintType constraintType, String depend_on) {
        this.name = name;
        this.active = active;
        this.constraintType = constraintType;
        this.depend_on = depend_on;
        this.groupName = deriveName();
        this.parentName = deriveName();
    }

    private String deriveName() {
        String packageName = this.getClass().getPackage().getName();
        String[] parts = packageName.split("\\.");
        String currentPackage = parts[parts.length - 1];

        if (name.equalsIgnoreCase(currentPackage)) {
            if (parts.length > 2 && !parts[parts.length - 2].equals("commands")) {
                return parts[parts.length - 2];
            }
            return null;
        }
        return currentPackage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public ConstraintType getConstraintType() {
        return constraintType;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    public String getParentName() {
        return parentName;
    }

    public String getDependOn() {
        return depend_on;
    }
}
