import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Settings {
    private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());

    private final Map<SettingKey, Boolean> settings;

    public enum SettingKey {
        NOTIFICATIONS_ENABLED,
        PRIVATE_ACCOUNT,
        TWO_FACTOR_AUTH,
        DARK_MODE
    }

    public Settings() {
        settings = new HashMap<>();
        initializeDefaultSettings();
    }

    private void initializeDefaultSettings() {
        settings.put(SettingKey.NOTIFICATIONS_ENABLED, true);
        settings.put(SettingKey.PRIVATE_ACCOUNT, false);
        settings.put(SettingKey.TWO_FACTOR_AUTH, false);
        settings.put(SettingKey.DARK_MODE, false);
    }

    public boolean getSetting(SettingKey key) {
        return settings.getOrDefault(key, false);
    }

    public void setSetting(SettingKey key, boolean value) {
        settings.put(key, value);
        LOGGER.info("Setting updated: " + key + " = " + value);
    }

    public Map<SettingKey, Boolean> getAllSettings() {
        return new HashMap<>(settings);
    }

    @Override
    public String toString() {
        return "Settings" + settings;
    }
}