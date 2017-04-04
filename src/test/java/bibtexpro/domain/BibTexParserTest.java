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
        map.put("id", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
    }
    
    @Test
    public void testToBibTex() {
        String s = "@Book{testiId,\n"
                + "year = {2017},\n"
                + "author = {Meikalainen, Matti},\n"
                + "title = {testiKirja}\n"
                + "}\n\n";
        assertEquals(s, BibTexParser.toBibTex(ref));
    }
    
}
