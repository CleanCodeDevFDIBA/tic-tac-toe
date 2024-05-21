package com.fdibaProject.tictactoe.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

/**
 * Utility class for unit tests.
 */
public class TestUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private TestUtil() {
        // Utility class
    }

    /**
     * Convert an object to JSON byte array.
     *
     * @param object the object to convert.
     * @return the JSON byte array.
     * @throws IOException if the object cannot be serialized.
     */
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    /**
     * Matcher that tests whether a given string represents the same instant as a reference ZonedDateTime.
     */
    public static class ZonedDateTimeMatcher extends TypeSafeMatcher<String> {

        private final ZonedDateTime date;

        public ZonedDateTimeMatcher(ZonedDateTime date) {
            this.date = date;
        }

        @Override
        protected boolean matchesSafely(String item) {
            try {
                ZonedDateTime parsedDate = ZonedDateTime.parse(item);
                return date.toInstant().equals(parsedDate.toInstant());
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a String representing the same Instant as ").appendValue(date);
        }
    }

    /**
     * Creates a matcher that matches when the examined string represents the same instant as the reference datetime.
     *
     * @param date the reference datetime against which the examined string is checked.
     * @return the ZonedDateTimeMatcher.
     */
    public static ZonedDateTimeMatcher sameInstant(ZonedDateTime date) {
        return new ZonedDateTimeMatcher(date);
    }

    /**
     * Matcher that tests whether a given number represents the same value as a reference BigDecimal.
     */
    public static class NumberMatcher extends TypeSafeMatcher<Number> {

        private final BigDecimal value;

        public NumberMatcher(BigDecimal value) {
            this.value = value;
        }

        @Override
        protected boolean matchesSafely(Number item) {
            BigDecimal bigDecimal = asDecimal(item);
            return bigDecimal != null && value.compareTo(bigDecimal) == 0;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a numeric value equal to ").appendValue(value);
        }

        private BigDecimal asDecimal(Number item) {
            if (item == null) {
                return null;
            }
            if (item instanceof BigDecimal) {
                return (BigDecimal) item;
            } else if (item instanceof Long) {
                return BigDecimal.valueOf((Long) item);
            } else if (item instanceof Integer) {
                return BigDecimal.valueOf((Integer) item);
            } else if (item instanceof Double) {
                return BigDecimal.valueOf((Double) item);
            } else if (item instanceof Float) {
                return BigDecimal.valueOf((Float) item);
            } else {
                return BigDecimal.valueOf(item.doubleValue());
            }
        }
    }

    /**
     * Creates a matcher that matches when the examined number represents the same value as the reference BigDecimal.
     *
     * @param number the reference BigDecimal against which the examined number is checked.
     * @return the NumberMatcher.
     */
    public static NumberMatcher sameNumber(BigDecimal number) {
        return new NumberMatcher(number);
    }

    /**
     * Verifies the equals/hashcode contract on the domain object.
     *
     * @param clazz the domain object's class.
     * @param <T> the type of the domain object.
     * @throws Exception if there is an error during reflection.
     */
    public static <T> void equalsVerifier(Class<T> clazz) throws Exception {
        T domainObject1 = clazz.getConstructor().newInstance();
        Assertions.assertThat(domainObject1.toString()).isNotNull();
        Assertions.assertThat(domainObject1).isEqualTo(domainObject1);
        Assertions.assertThat(domainObject1.hashCode()).isEqualTo(domainObject1.hashCode());

        // Test with an instance of another class
        Object testOtherObject = new Object();
        Assertions.assertThat(domainObject1).isNotEqualTo(testOtherObject);
        Assertions.assertThat(domainObject1).isNotEqualTo(null);

        // Test with another instance of the same class
        T domainObject2 = clazz.getConstructor().newInstance();
        Assertions.assertThat(domainObject1).isNotEqualTo(domainObject2);

        // Hash codes are equal because the objects are not persisted yet
        Assertions.assertThat(domainObject1.hashCode()).isEqualTo(domainObject2.hashCode());
    }

    /**
     * Create a {@link FormattingConversionService} which uses ISO date format, instead of the localized one.
     *
     * @return the {@link FormattingConversionService}.
     */
    public static FormattingConversionService createFormattingConversionService() {
        DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService();
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(dfcs);
        return dfcs;
    }
}
