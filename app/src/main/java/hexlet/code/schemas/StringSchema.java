package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        setRequired(true);
        Predicate<String> requiredCheck = string -> !string.isEmpty();
        addCheck("required", requiredCheck);
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        Predicate<String> lengthCheck = string -> string.length() >= minLength;
        addCheck("minLength", lengthCheck);
        return this;
    }

    public StringSchema contains(String substr) {
        Predicate<String> containsCheck = string -> string.contains(substr);
        addCheck("contains", containsCheck);
        return this;
    }
}
