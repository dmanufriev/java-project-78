package hexlet.code.schemas;

public final class StringSchema implements BaseSchema<String> {
    private boolean needRequired;
    private Integer needMinLength;
    private String needSubstring;

    public StringSchema required() {
        needRequired = true;
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        needMinLength = minLength;
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

        if ((null != needMinLength) && (string.length() < needMinLength)) {
            return false;
        }

        if ((null != needSubstring) && !string.contains(needSubstring)) {
            return false;
        }

        return true;
    }
}
