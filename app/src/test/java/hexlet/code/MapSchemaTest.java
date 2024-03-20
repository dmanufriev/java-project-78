package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {
    private Validator v;
    private MapSchema schema;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void requiredTest() {
        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(new HashMap<>())).isEqualTo(true);
    }

    @Test
    void sizeofTest() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isEqualTo(true);
        schema.sizeof(2);
        assertThat(schema.isValid(data)).isEqualTo(false);
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isEqualTo(true);
    }

    @Test
    void shapesTest() {

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        // Полное соответствие схеме
        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        human1.put("age", 20);
        assertThat(schema.isValid(human1)).isEqualTo(true);

        // Тест на отсутствие поля в проверяемых схемах
        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "Anna");
        human2.put("status", "married");
        human2.put("age", 70);
        assertThat(schema.isValid(human2)).isEqualTo(true);

        // Ошибка валидации классом StringSchema
        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "John");
        human3.put("lastName", null);
        assertThat(schema.isValid(human3)).isEqualTo(false);

        // Ошибка валидации классом StringSchema
        Map<String, Object> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "B");
        assertThat(schema.isValid(human4)).isEqualTo(false);
    }
}
