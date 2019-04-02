package com.github.ryneal.postcodegeolocation.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberUtilTest {

    @Test
    public void shouldParseLongSuccessfully() {
        Long aLong = NumberUtil.parseLong("1");

        assertThat(aLong, is(1L));
    }

    @Test
    public void shouldParseLongAsNullValue() {
    }

    @Test(expected = NumberFormatException.class)
    public void shouldParseLongWithException() {
    }

    @Test
    public void shouldParseDoubleSuccessfully() {
    }

    @Test
    public void shouldParseDoubleAsNullValue() {
    }

    @Test(expected = NumberFormatException.class)
    public void shouldParseDoubleWithException() {
    }
}
