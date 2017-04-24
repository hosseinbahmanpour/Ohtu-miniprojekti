package bibtexpro.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luhtalam
 */
public class BibTexParserTest {
    Reference ref;
    
    public BibTexParserTest() {
    }
    
    @Before
    public void setUp() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
    }
    
    @Test
    public void testToBibTex() {
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meikalainen, Matti},\n"));
        assertTrue(output.contains("title = {testiKirja},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
}
