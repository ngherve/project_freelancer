import models.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Query test.
 * @author Seunghyun Hong
 */
public class QueryTest {

    /**
     * test Query constructor
     */
    @Test
    public void queryTest() {
        Query query = new Query("query", "true");

        assertEquals("query", query.key);
        assertEquals("true", query.value);

    }
}