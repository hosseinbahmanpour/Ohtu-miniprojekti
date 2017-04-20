package bibtexpro;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
public class Stepdefs {

    @Test
    public void setupTest() {
    }

    WebDriver driver = new HtmlUnitDriver(true);
    String baseUrl = "http://localhost:8080";

//    @Before
//    public void setUp() throws Exception {
//        if (driver == null) {
//            driver = new HtmlUnitDriver();
//            driver.navigate().to(baseUrl);
//            driver.manage().deleteAllCookies();
//        }
//    }
    private void getFrontPage() {
        driver.get(baseUrl + "/");
    }

    private void getReferenceListing() {
        driver.get(baseUrl + "/list");
    }

    @Given("^index page is selected$")
    public void index_page_is_selected() throws Throwable {
        getFrontPage();
    }

    @When("^the page is loaded$")
    public void wait_for_load() throws Throwable {
        Thread.sleep(1000);
    }

    @When("^\"([^\"]*)\" reference is selected$")
    public void wait_for_load(String type) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(type + "Option")).click();
        Thread.sleep(1000);
    }

    @Then("^input element with id \"([^\"]*)\" and value \"([^\"]*)\" exists$")
    public void element_with_given_id_and_given_value_exists(String id, String value) throws Throwable {
        WebElement element = driver.findElement(By.id(id));
        String s = element.getAttribute("value");
        assertEquals(value, s);
    }

    @Then("^input element contains only id \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void element_does_not_contains_other(String id, String value) throws Throwable {
        Set<String> references = new HashSet(Arrays.asList("Article", "Book", "Proceeding"));
        references.remove(value);
        WebElement element = driver.findElement(By.id(id));
        String s;
        for (String reference : references) {
            s = element.getAttribute("Value");
            assertNotEquals(value,s);
        }
    }

//    @Given("^I have the required fields available to insert the data into$")
//    public void i_have_the_required_fields() throws Throwable {
//        getAddReference();
//    }
//
//    @When("^I add a book reference with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
//    public void i_add_a_book_reference(String id, String title, String author, String year) throws Throwable {
//
//        WebDriverWait some = new WebDriverWait(driver, 5);
//        System.out.println(driver.getPageSource());
//
//        some.until(ExpectedConditions.visibilityOfElementLocated(By.name("id")));
//
//        WebElement element = driver.findElement(By.name("id"));
//        element.sendKeys(id);
//
//        element = driver.findElement(By.name("title"));
//        element.sendKeys(title);
//
//        element = driver.findElement(By.name("author"));
//        element.sendKeys(author);
//        element = driver.findElement(By.name("year"));
//        element.sendKeys(year);
//        element.submit();
//    }
//
//    @Then("^the reference \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
//    public void the_reference_should_have_been_added(String id, String title, String author, String year) throws Throwable {
//        getReferenceListing();
//        assertTrue(driver.getPageSource().contains(id));
//        assertTrue(driver.getPageSource().contains(title));
//        assertTrue(driver.getPageSource().contains(author));
//        assertTrue(driver.getPageSource().contains(year));
//    }
//
//    @Given("^there is a book in the reference library with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
//    public void there_is_a_book_in_the_reference_library(String id, String title, String author, String year) throws Throwable {
//        i_add_a_book_reference(id, title, author, year);
//    }
//
//    @When("^I export the book reference$")
//    public void export_book_reference() throws Throwable {
//        getAddReference();
//        WebElement element = driver.findElements(By.name("form")).get(0);
//        element.submit();
//    }
//
//    @Then("^a BibTeX file with the contents of Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\" should have been created$")
//    public void bibtex_file_should_have_been_created(String id, String title, String author, String year) throws Throwable {
//        getReferenceListing();
//        assertTrue(driver.getPageSource().contains(id));
//        assertTrue(driver.getPageSource().contains(title));
//        assertTrue(driver.getPageSource().contains(author));
//        assertTrue(driver.getPageSource().contains(year));
//    }
    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
