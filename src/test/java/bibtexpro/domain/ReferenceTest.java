package bibtexpro.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTest {

    Reference ref;
    Map<String, String> map;

    public ReferenceTest() {
    }

    @Before
    public void setUp() {
        this.map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
    }

    @Test
    public void testConstructorAddsType() {
        assertEquals("Book", ref.getType());
    }

    @Test
    public void testConstructorAddsId() {
        assertEquals("testiId", ref.getRefId());
    }

    @Test
    public void testConstructorRemovesTypeAndIdsFromMap() {
        assertNull(ref.getAttributes().get("type"));
        assertNull(ref.getAttributes().get("id"));
    }

    @Test
    public void testConstructorDontRemoveAttributes() {
        for (String key : map.keySet()) {
            if (key == "type" || key == "id") {
                continue;
            }
            assertNotNull(ref.getAttributes().get(key));
        }
    }
}
