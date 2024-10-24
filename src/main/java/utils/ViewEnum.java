package utils;

public enum ViewEnum {
    EXERCICE("exercice"),
    PAYMENT_METHODS("payment_methods"),
    COMMUNITY("community");

    private final String viewName;

    ViewEnum(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
