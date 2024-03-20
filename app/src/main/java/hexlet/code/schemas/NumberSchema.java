package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positiveCheck = number -> number > 0;
        addCheck("positive", positiveCheck);
        return this;
    }

    public NumberSchema range(Integer rangeMin, Integer rangeMax) {
        Predicate<Integer> rangeCheck = number -> number.compareTo(rangeMin) >= 0
                                                    && number.compareTo(rangeMax) <= 0;
        addCheck("range", rangeCheck);
        return this;
    }
}
