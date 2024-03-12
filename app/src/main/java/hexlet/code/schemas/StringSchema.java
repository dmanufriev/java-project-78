package hexlet.code.schemas;

public class StringSchema implements BaseSchema<String> {
    private boolean needRequired;
    private int minLength;
    private String needSubstring;

    public StringSchema required() {
        needRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substr) {
        this.needSubstring = substr;
        return this;
    }

    @Override
    public boolean isValid(String string) {

        if ((null == string) || (string.isEmpty())) {
            return !needRequired;
        }

        if (string.length() < minLength) {
            return false;
        }

        if ((null != needSubstring) && !string.contains(needSubstring)) {
            return false;
        }

        return true;
    }
}
