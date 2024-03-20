package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        setRequired(true);
        return this;
    }

    public MapSchema sizeof(Integer limit) {
        Predicate<Map> sizeofCheck = map -> limit.equals(map.size());
        addCheck("sizeof", sizeofCheck);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Map> shapeCheck = map -> {
            for (var key : map.keySet()) {
                BaseSchema keySchema = schemas.get(key);
                if (null != keySchema) {
                    if (!keySchema.isValid(map.get(key))) {
                        return false;
                    }
                }
            }
            return true;
        };
        addCheck("shape", shapeCheck);
        return this;
    }
}
