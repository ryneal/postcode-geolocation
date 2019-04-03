package com.github.ryneal.postcodegeolocation.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class NumberUtilTest {

    @Test
    public void shouldParseLongSuccessfully() {
        Long aLong = NumberUtil.parseLong("1");

        assertThat(aLong, is(1L));
    }

    @Test
    public void shouldParseLongAsNullValue() {
        Long nullLong = NumberUtil.parseLong(null);

        assertThat(nullLong, is(nullValue()));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldParseLongWithException() {
        Long aLong = NumberUtil.parseLong("gfh");
    }

    @Test
    public void shouldParseDoubleSuccessfully() {
        Double aDouble = NumberUtil.parseDouble("1.0");

        assertThat(aDouble, is(1.0));
    }

    @Test
    public void shouldParseDoubleAsNullValue() {
        Double nullDouble = NumberUtil.parseDouble(null);

        assertThat(nullDouble, is(nullValue()));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldParseDoubleWithException() {
        Double aDouble = NumberUtil.parseDouble("fge");
    }
}
