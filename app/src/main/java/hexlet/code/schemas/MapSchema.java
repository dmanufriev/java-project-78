package hexlet.code.schemas;

import java.util.Map;

public class MapSchema implements BaseSchema<Map<String, String>> {
    private boolean needRequired;
    private Integer sizeLimit;
    private Map<String, BaseSchema<String>> fieldsSchema;

    public MapSchema required() {
        needRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer limit) {
        sizeLimit = limit;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> map) {

        if (null == map) {
            return !needRequired;
        }

        if (null != sizeLimit) {
            if (!sizeLimit.equals(map.size())) {
                return false;
            }
        }

        if (null != fieldsSchema) {
            for (var key : map.keySet()) {
                BaseSchema<String> keySchema = fieldsSchema.get(key);
                if (null != keySchema) {
                    if (!keySchema.isValid(map.get(key))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        this.fieldsSchema = schemas;
    }
}
