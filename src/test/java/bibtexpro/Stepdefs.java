package bibtexpro;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(
        classes = Application.class,
        loader = SpringBootContextLoader.class)
@WebAppConfiguration
@SpringBootTest
public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:8080";

    @Before
    public void setUp() throws Exception {
        if (driver == null) {
            driver = new HtmlUnitDriver();
            driver.navigate().to(baseUrl);
            driver.manage().deleteAllCookies();
        }
    }

    private void getAddReference() {
        driver.get(baseUrl + "/addreference");
    }

    private void getReferenceListing() {
        driver.get(baseUrl + "/list");
    }

    @Given("^I have the required fields available to insert the data into$")
    public void i_have_the_required_fields() throws Throwable {
        getAddReference();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
    public void i_add_a_book_reference(String id, String title, String author, String year) throws Throwable {

        WebDriverWait some = new WebDriverWait(driver, 5);
        System.out.println(driver.getPageSource());

        some.until(ExpectedConditions.visibilityOfElementLocated(By.name("id")));

        WebElement element = driver.findElement(By.name("id"));
        element.sendKeys(id);

        element = driver.findElement(By.name("title"));
        element.sendKeys(title);

        element = driver.findElement(By.name("author"));
        element.sendKeys(author);
        element = driver.findElement(By.name("year"));
        element.sendKeys(year);
        element.submit();
    }

    @Then("^the reference \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void the_reference_should_have_been_added(String id, String title, String author, String year) throws Throwable {
        getReferenceListing();
        assertTrue(driver.getPageSource().contains(id));
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(author));
        assertTrue(driver.getPageSource().contains(year));
    }

    @Given("^there is a book in the reference library with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
    public void there_is_a_book_in_the_reference_library(String id, String title, String author, String year) throws Throwable {
        i_add_a_book_reference(id, title, author, year);
    }

    @When("^I export the book reference$")
    public void export_book_reference() throws Throwable {
        getAddReference();
        WebElement element = driver.findElements(By.name("form")).get(0);
        element.submit();
    }

    @Then("^a BibTeX file with the contents of Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\" should have been created$")
    public void bibtex_file_should_have_been_created(String id, String title, String author, String year) throws Throwable {
        getReferenceListing();
        assertTrue(driver.getPageSource().contains(id));
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(author));
        assertTrue(driver.getPageSource().contains(year));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
