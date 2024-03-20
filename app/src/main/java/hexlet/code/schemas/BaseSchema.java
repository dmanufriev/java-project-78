package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    private boolean required = false;

    protected final void setRequired(boolean required) {
        this.required = required;
    }

    protected final void addCheck(String checkName, Predicate<T> check) {
        checks.put(checkName, check);
    }

    public final boolean isValid(T value) {
        if (null == value) {
            return !required;
        }
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
