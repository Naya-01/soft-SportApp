package features;

public interface Feature {
    String getName();
    boolean isActive();
    void setActive(boolean active);
    ConstraintType getConstraintType();
    String getGroupName();
    boolean isMandatory();

    public String getParentName();
}
