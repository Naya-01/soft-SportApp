package features;

public abstract class AbstractFeature implements Feature {
    protected String name;
    protected boolean active;
    protected ConstraintType constraintType;
    protected String groupName;
    protected boolean isMandatory;
    private String parentName;

    public AbstractFeature(String name, boolean active, boolean isMandatory, ConstraintType constraintType, String groupName, String parentName) {
        this.name = name;
        this.active = active;
        this.constraintType = constraintType;
        this.groupName = groupName;
        this.isMandatory = isMandatory;
        this.parentName = parentName;
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

    @Override
    public boolean isMandatory() {
        return isMandatory;
    }

    public String getParentName() {
        return parentName;
    }
}
