package net.hzbox.vj.journal.utils;

import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

public final class OptionalUtils {

    private OptionalUtils() {}

    public static final String DEFAULT_STRING = "";
    public static final Boolean DEFAULT_BOOLEAN = false;
    public static final Byte DEFAULT_BYTE = 0;
    public static final Short DEFAULT_SHORT = 0;
    public static final Integer DEFAULT_INTEGER = 0;
    public static final Character DEFAULT_CHARACTER = '\u0000';
    public static final Long DEFAULT_LONG = 0L;
    public static final Float DEFAULT_FLOAT = 0.0f;
    public static final Double DEFAULT_DOUBLE = 0.0;

    public static String getDefault(String input) {
        return getOther(input, DEFAULT_STRING);
    }

    public static Long getDefault(Long input) {
        return getOther(input, DEFAULT_LONG);
    }

    public static Integer getDefault(Integer input) {
        return getOther(input, DEFAULT_INTEGER);
    }


    public static Double getDefault(Double input) {
        return getOther(input, DEFAULT_DOUBLE);
    }


    public static Float getDefault(Float input) {
        return getOther(input, DEFAULT_FLOAT);
    }


    public static Short getDefault(Short input) {
        return getOther(input, DEFAULT_SHORT);
    }


    public static Byte getDefault(Byte input) {
        return getOther(input, DEFAULT_BYTE);
    }

    public static Character getDefault(Character input) {
        return getOther(input, DEFAULT_CHARACTER);
    }

    public static Boolean getDefault(Boolean input) {
        return getOther(input, DEFAULT_BOOLEAN);
    }

    public static <T> T getOther(T input, T other) {
        return ofNullable(input).orElse(other);
    }

    public static <T> void ifParent(T input, Consumer<T> consumer) {
        Optional.ofNullable(input).ifPresent(consumer);
    }

}
