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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class Stepdefs {

    @Test
    public void setupTest() {
    }

    WebDriver driver = new HtmlUnitDriver(true);
    String baseUrl = "http://localhost:8080";
    WebDriverWait wait = new WebDriverWait(driver, 1);

    @Before
    public void setUp() throws Exception {
        if (driver == null) {
            driver = new HtmlUnitDriver();
            driver.navigate().to(baseUrl);
            driver.manage().deleteAllCookies();
        }
        if (wait == null) {
            wait = new WebDriverWait(driver, 1);
        }
    }

    private void getFrontPage() {
        driver.get(baseUrl + "/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("referenceType")));
    }

    private void getReferenceListing() {
        driver.get(baseUrl + "/list");
    }

    private void getBibTexFile() throws Throwable {
        getFrontPage();
        selectElementById("bibtexLink").click();
    }

    private void selectReferenceType(String type) throws Throwable {
        By typeOption = By.id(type + "Option");
        driver.findElement(typeOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("refId")));
    }

    private WebElement selectElementById(String id) throws Throwable {
        return driver.findElement(By.id(id));
    }

    private boolean contains(String content) {
        return driver.getPageSource().contains(content);
    }

    @Given("^index page is selected$")
    public void index_page_is_selected() throws Throwable {
        getFrontPage();
    }

    @When("^\"([^\"]*)\" reference is selected$")
    public void reference_is_selected(String ref) throws Throwable {
        selectReferenceType(ref.toLowerCase());
    }

    // TODO: skip?
    @Then("^the input fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" exist$")
    public void the_input_fields_exist(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {

    }

    // TODO: skip?
    @Then("^the input fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" exist$")
    public void the_input_fields_exist(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {

    }

    @Given("^I have the required fields available to insert the data into$")
    public void i_have_the_required_fields_available_to_insert_the_data_into() throws Throwable {
        getFrontPage();
    }

    @When("^I add an article reference with the Id \"([^\"]*)\", Author \"([^\"]*)\", Title \"([^\"]*)\", Journal \"([^\"]*)\", year \"([^\"]*)\", Volume \"([^\"]*)\"$")
    public void add_article_with_Id_Author_Title_Journal_year_Volume(String id, String author, String title, String journal, String year, String volume) throws Throwable {
        selectReferenceType("article");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);

        selectElementById("author").sendKeys(author);
        selectElementById("title").sendKeys(title);
        selectElementById("journal").sendKeys(journal);
        selectElementById("year").sendKeys(year);
        selectElementById("volume").sendKeys(volume);
        form.submit();
    }

    @Then("^the article \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void article_should_have_been_added(String id, String author, String title, String journal, String year, String volume) throws Throwable {
        getBibTexFile();
        assertTrue(contains(id));
        assertTrue(contains(author));
        assertTrue(contains(title));
        assertTrue(contains(journal));
        assertTrue(contains(year));
        assertTrue(contains(volume));
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
    public void add_book_with_Id_Title_Author_year(String id, String title, String author, String year) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("title").sendKeys(title);
        selectElementById("author").sendKeys(author);
        selectElementById("year").sendKeys(year);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Volume/Number \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Volume_Number(String id, String authorEditor, String title, String publisher, String year, String volumeNumber) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("volume").sendKeys(volumeNumber);
        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Address \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Address(String id, String authorEditor, String title, String publisher, String year, String address) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("address").sendKeys(address);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year(String id, String authorEditor, String title, String publisher, String year) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Key \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Key(String id, String authorEditor, String title, String publisher, String year, String key) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("key").sendKeys(key);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Edition \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Edition(String id, String authorEditor, String title, String publisher, String year, String edition) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("edition").sendKeys(edition);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Series \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Series(String id, String authorEditor, String title, String publisher, String year, String series) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("series").sendKeys(series);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Month \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Month(String id, String authorEditor, String title, String publisher, String year, String month) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("month").sendKeys(month);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\"$")
    public void add_book_with_Id_Title_Publisher_year(String id, String title, String publisher, String year) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);

        form.submit();
    }

    @When("^I add a book reference with the Id \"([^\"]*)\", Author/Editor \"([^\"]*)\", Title \"([^\"]*)\", Publisher \"([^\"]*)\", year \"([^\"]*)\", Note \"([^\"]*)\"$")
    public void add_book_with_Id_Author_Editor_Title_Publisher_year_Note(String id, String authorEditor, String title, String publisher, String year, String note) throws Throwable {
        selectReferenceType("book");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(authorEditor);
        selectElementById("title").sendKeys(title);
        selectElementById("publisher").sendKeys(publisher);
        selectElementById("year").sendKeys(year);
        selectElementById("note").sendKeys(note);

        form.submit();
    }

    @Then("^the book \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void book_should_have_been_added(String id, String title, String author, String year) throws Throwable {
        getBibTexFile();
        assertTrue(contains(id));
        assertTrue(contains(title));
        assertTrue(contains(author));
        assertTrue(contains(year));
    }

    @Then("^the book \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void book_should_have_been_added(String id, String title, String author, String year, String arg5, String arg6) throws Throwable {
        getBibTexFile();
        book_should_have_been_added(id, title, author, year);
        assertTrue(contains(arg5));
        assertTrue(contains(arg6));
    }

    @Then("^the reference \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void the_reference_should_have_been_added(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
        getBibTexFile();
        assertTrue(contains(arg1));
        assertTrue(contains(arg2));
        assertTrue(contains(arg3));
        assertTrue(contains(arg4));
        assertTrue(contains(arg5));
        assertTrue(contains(arg6));
    }

    @Then("^the book \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void the_book_should_have_been_added(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
        the_reference_should_have_been_added(arg1, arg2, arg3, arg4, arg5, arg5);
    }

    @Then("^the book \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should not have been added$")
    public void the_book_should_not_have_been_added(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        getBibTexFile();
        System.out.println(driver.getPageSource());
        assertFalse(contains(arg1));
        assertFalse(contains(arg2));
        assertFalse(contains(arg3));
        assertFalse(contains(arg4));
    }

    @When("^I add an Inproceeding reference with the Id \"([^\"]*)\", Author \"([^\"]*)\", Title \"([^\"]*)\", Booktitle \"([^\"]*)\", year \"([^\"]*)\"$")
    public void add_inproceeding_with_the_Id_Author_Title_Booktitle_year(String id, String author, String title, String booktitle, String year) throws Throwable {
        selectReferenceType("inproceeding");
        WebElement form = driver.findElement(By.id("refId"));
        form.sendKeys(id);
        selectElementById("author").sendKeys(author);
        selectElementById("title").sendKeys(title);
        selectElementById("booktitle").sendKeys(booktitle);
        selectElementById("year").sendKeys(year);
        form.submit();
    }

    @Then("^the inproceeding \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" should have been added$")
    public void inproceeding_should_have_been_added(String id, String author, String title, String booktitle, String year) throws Throwable {
        getBibTexFile();
        assertTrue(contains(id));
        assertTrue(contains(author));
        assertTrue(contains(title));
        assertTrue(contains(booktitle));
        assertTrue(contains(year));
    }

    @When("^I export the book reference$")
    public void export_book_reference() throws Throwable {
        getBibTexFile();
    }

    @Then("^a BibTeX file with the contents of Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\" should have been created$")
    public void bibtex_file_should_have_been_created(String id, String title, String author, String year) throws Throwable {
        assertTrue(contains(id));
        assertTrue(contains(title));
        assertTrue(contains(author));
        assertTrue(contains(year));
    }

    @Given("^there is a book in the reference library with the Id \"([^\"]*)\", Title \"([^\"]*)\", Author \"([^\"]*)\", year \"([^\"]*)\"$")
    public void there_is_a_book_in_the_reference_library_with_the_Id_Title_Author_year(String id, String title, String author, String year) throws Throwable {
        getFrontPage();
        add_book_with_Id_Title_Author_year(id, title, author, year);
    }

    @After
    public void tearDown() {
        driver.get(baseUrl+"/reset");
        driver.close();
        driver.quit();
    }

}
