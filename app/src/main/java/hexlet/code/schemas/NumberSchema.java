package hexlet.code.schemas;

public final class NumberSchema implements BaseSchema<Integer> {
    private boolean needRequired;
    private boolean needPositive;
    private Integer minValue;
    private Integer maxValue;

    public NumberSchema required() {
        needRequired = true;
        return this;
    }

    public NumberSchema positive() {
        needPositive = true;
        return this;
    }

    public NumberSchema range(Integer rangeMin, Integer rangeMax) {
        minValue = rangeMin;
        maxValue = rangeMax;
        return this;
    }

    @Override
    public boolean isValid(Integer number) {

        if (null == number) {
            return !needRequired;
        }

        if (needPositive && (number.compareTo(0) <= 0)) {
            return false;
        }

        if ((null != minValue) && (null != maxValue)) {
            if (number.compareTo(minValue) < 0 || number.compareTo(maxValue) > 0) {
                return false;
            }
        }

        return true;
    }
}
