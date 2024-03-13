package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private Validator v;
    private StringSchema schema;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    void requiredTest() {
        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
    }

    @Test
    void minLengthTest() {
        String testStr = "hexlet";
        assertThat(schema.isValid(testStr)).isEqualTo(true);
        assertThat(schema.minLength(15).isValid(testStr)).isEqualTo(false);
    }

    @Test
    void containsTest() {
        String testStr = "what does the fox say";
        assertThat(schema.contains("wh").isValid(testStr)).isEqualTo(true);
        assertThat(schema.contains("what").isValid(testStr)).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid(testStr)).isEqualTo(false);
        assertThat(schema.isValid(testStr)).isEqualTo(false);
        assertThat(schema.contains("what").isValid(testStr)).isEqualTo(true);
    }
}
