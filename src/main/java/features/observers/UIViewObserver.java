package features.observers;

public interface UIViewObserver {
    void onUIViewStateChanged(boolean enabled);
    void onFeatureStateChanged(String featureName, boolean isActive);
}
