package features;

public interface Feature {
    String getName();
    boolean isActive();
    void setActive(boolean active);
    ConstraintType getConstraintType();
    String getGroupName();

    public String getParentName();

    public String getDependOn();
}
