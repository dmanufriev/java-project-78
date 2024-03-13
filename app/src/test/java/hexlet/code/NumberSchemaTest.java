package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    void requiredTest() {
        assertThat(schema.isValid(5)).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(10)).isEqualTo(true);
    }

    @Test
    void positiveTest() {
        assertThat(schema.isValid(-10)).isEqualTo(true);
        schema.positive();
        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(false);
        assertThat(schema.isValid(0)).isEqualTo(false);
    }

    @Test
    void rangeTest() {
        schema.range(5, 10);
        assertThat(schema.isValid(5)).isEqualTo(true);
        assertThat(schema.isValid(7)).isEqualTo(true);
        assertThat(schema.isValid(10)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(false);
        assertThat(schema.isValid(11)).isEqualTo(false);
        assertThat(schema.isValid(null)).isEqualTo(true);
    }
}
