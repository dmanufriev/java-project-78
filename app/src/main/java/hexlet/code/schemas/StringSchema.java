package hexlet.code.schemas;

public class StringSchema implements BaseSchema<String> {
    private boolean required;
    private int minLength;
    private String substring;

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substr) {
        this.substring = substr;
        return this;
    }

    @Override
    public boolean isValid(String string) {

        if ((null == string) || (string.isEmpty())) {
            return !required;
        }

        if (string.length() < minLength) {
            return false;
        }

        if ((null != substring) && !string.contains(substring)) {
            return false;
        }

        return true;
    }
}
