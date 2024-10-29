package features.managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class StateManager {

    protected Map<String, Boolean> states;
    protected Logger logger;

    public StateManager() {
        states = new HashMap<>();
        logger =  Logger.getLogger("Log");
        logger.info("Init StateManager");
        logger.info(this.getClass().getName() + " initialized");
    }

    public abstract boolean activate(String featureName);

    public abstract boolean deactivate(String featureName);

    public abstract boolean isActive(String featureName);

    public Map<String, Boolean> getStates() {
        return new HashMap<>(states);
    }

    protected void loadStates(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return;
            }

            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                this.states.put(key, Boolean.parseBoolean(properties.getProperty(key)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
