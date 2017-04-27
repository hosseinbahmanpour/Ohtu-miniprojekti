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
    
    @Test
    public void toBibTexWorksWithoutScandicsAndWithoutAbbreviations() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
        
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meikalainen, Matti},\n"));
        assertTrue(output.contains("title = {testiKirja},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
    @Test
    public void toBibTexWorksWithLowerCaseScandicsAndWithoutAbbreviations() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikälöinen, Måtti");
        map.put("year", "2017");
        this.ref = new Reference(map);
        
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meik\\\"{a}l\\\"{o}inen, M\\\'{a}tti},\n"));
        assertTrue(output.contains("title = {testiKirja},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
    @Test
    public void toBibTexWorksWithUpperCaseScandicsAndWithoutAbbreviations() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "MeikÄlöinen, Ålgren");
        map.put("year", "2017");
        this.ref = new Reference(map);
        
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meik\\\"{A}l\\\"{o}inen, \\\'{A}lgren},\n"));
        assertTrue(output.contains("title = {testiKirja},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
    @Test
    public void toBibTexWorksWithoutScandicsAndWithtAbbreviations() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "CS 1");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
        
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meikalainen, Matti},\n"));
        assertTrue(output.contains("title = {{C}{S} 1},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
    @Test
    public void toBibTexWorksWithScandicsAndWithAbbreviations() {
        Map<String,String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("refId", "testiId");
        map.put("title", "ÄÖ-kirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        this.ref = new Reference(map);
        
        String output = BibTexParser.toBibTex(ref);
        assertTrue(output.startsWith("@Book{testiId,\n"));
        assertTrue(output.contains("year = {2017},\n"));
        assertTrue(output.contains("author = {Meikalainen, Matti},\n"));
        assertTrue(output.contains("title = {{\\\"{A}}{\\\"{O}}-kirja},\n"));
        assertTrue(output.endsWith("}\n\n"));
    }
    
}
