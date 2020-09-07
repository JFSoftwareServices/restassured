package steps;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    <T> void put(String key, T value) {
        scenarioContext.put(key, value);
    }

    <T> T get(String key, Class<T> type) {
        return type.cast(scenarioContext.get(key));
    }
}