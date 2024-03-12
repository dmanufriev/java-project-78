package hexlet.code.schemas;

import java.util.Map;

public class MapSchema implements BaseSchema<Map> {
    private boolean needRequired;
    private Integer sizeLimit;

    public MapSchema required() {
        needRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer limit) {
        sizeLimit = limit;
        return this;
    }

    @Override
    public boolean isValid(Map map) {

        if (null == map) {
            return !needRequired;
        }

        if (null != sizeLimit) {
            if (!sizeLimit.equals(map.size())) {
                return false;
            }
        }

        return true;
    }
}
