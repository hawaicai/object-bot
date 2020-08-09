package com.github.dreamhead.bot.junit5;

import com.github.dreamhead.bot.AnyField;
import com.github.dreamhead.bot.Bot;
import com.github.dreamhead.bot.BotInitializer;
import com.github.dreamhead.bot.BotWith;
import com.github.dreamhead.bot.DoubleField;
import com.github.dreamhead.bot.FieldFactory;
import com.github.dreamhead.bot.FloatField;
import com.github.dreamhead.bot.IntField;
import com.github.dreamhead.bot.LongField;
import com.github.dreamhead.bot.ObjectBot;
import com.github.dreamhead.bot.StringField;
import com.github.dreamhead.bot.junit5.junit5.BotExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(BotExtension.class)
@BotWith(RepeatableFieldTest.class)
public class RepeatableFieldTest implements BotInitializer {
    @Override
    public void initializer(final ObjectBot bot) {
        bot.define("longFields", new LongFieldsData(1, 2));
        bot.define("intFields", new IntFieldsData(1, 2));
        bot.define("stringFields", new StringFieldsData("foo1", "foo2"));
        bot.define("anyFields", new AnyFieldsData("hello", 1L));
        bot.define("doubleFields", new DoubleFieldsData(-1.1, -1.2));
        bot.define("floatFields", new FloatFieldsData(-1.1f, -1.2f));
    }

    @Bot("longFields")
    @LongField(name = "field1", value = 2)
    @LongField(name = "field2", value = 3)
    private LongFieldsData longFieldsData;

    @Test
    void should_have_long_fields() {
        assertThat(longFieldsData.field1).isEqualTo(2);
        assertThat(longFieldsData.field2).isEqualTo(3);
    }

    @Bot("intFields")
    @IntField(name = "field1", value = 2)
    @IntField(name = "field2", value = 3)
    private IntFieldsData intFieldsData;

    @Test
    void should_have_int_fields() {
        assertThat(intFieldsData.field1).isEqualTo(2);
        assertThat(intFieldsData.field2).isEqualTo(3);
    }

    @Bot("stringFields")
    @StringField(name = "field1", value = "bar1")
    @StringField(name = "field2", value = "bar2")
    private StringFieldsData stringFieldsData;

    @Test
    void should_have_string_fields() {
        assertThat(stringFieldsData.field1).isEqualTo("bar1");
        assertThat(stringFieldsData.field2).isEqualTo("bar2");
    }

    @Bot("anyFields")
    @AnyField(name = "field1", factory = AnyField1Factory.class)
    @AnyField(name = "field2", factory = AnyField2Factory.class)
    private AnyFieldsData anyFieldsData;

    @Test
    void should_have_any_fields() {
        assertThat(anyFieldsData.field1).isEqualTo("world");
        assertThat(anyFieldsData.field2).isEqualTo(2L);
    }

    @Bot("doubleFields")
    @DoubleField(name = "field1", value = 1.0)
    @DoubleField(name = "field2", value = 2.0)
    private DoubleFieldsData doubleFieldData;

    @Test
    void should_have_double_fields() {
        assertThat(doubleFieldData.field1).isEqualTo(1.0);
        assertThat(doubleFieldData.field2).isEqualTo(2.0);
    }

    @Bot("floatFields")
    @FloatField(name = "field1", value = 1.0f)
    @FloatField(name = "field2", value = 2.0f)
    private FloatFieldsData floatFieldData;

    @Test
    void should_have_float_fields() {
        assertThat(floatFieldData.field1).isEqualTo(1.0f);
        assertThat(floatFieldData.field2).isEqualTo(2.0f);
    }

    private static class LongFieldsData {
        private long field1;
        private long field2;

        public LongFieldsData(final long field1, final long field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    private static class IntFieldsData {
        private int field1;
        private int field2;

        public IntFieldsData(final int field1, final int field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    private static class StringFieldsData {
        private String field1;
        private String field2;

        public StringFieldsData(final String field1, final String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    private class AnyFieldsData {
        private Object field1;
        private Object field2;

        public AnyFieldsData(final Object field1, final Object field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    private static class AnyField1Factory implements FieldFactory<String> {
        @Override
        public String getValue() {
            return "world";
        }
    }

    private static class AnyField2Factory implements FieldFactory<Long> {
        @Override
        public Long getValue() {
            return 2L;
        }
    }

    private class DoubleFieldsData {
        private double field1;
        private double field2;

        public DoubleFieldsData(final double field1, final double field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    private class FloatFieldsData {
        private float field1;
        private float field2;

        public FloatFieldsData(float field1, float field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }
}