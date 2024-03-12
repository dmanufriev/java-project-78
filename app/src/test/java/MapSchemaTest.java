import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {
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
}
