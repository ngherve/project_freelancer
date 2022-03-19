package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryTest {

    @Test
    public void queryTest() {
        Query query = new Query("query", "true");

        assertEquals("query", query.key);
        assertEquals("true", query.value);

    }
}