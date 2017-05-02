package bibtexpro;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
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
    
    private void getReferenceView(String id){
        driver.get(baseUrl + "/list/"+id);
    }

    private void getBibTexFile() throws Throwable {
        getFrontPage();
        selectElementById("bibtexLink").click();
    }

    private void selectReferenceType(String type) throws Throwable {
        By typeOption = By.id(type + "Option");
        driver.findElement(typeOption).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(type+"Form")));
    }

    private WebElement selectElementById(String id) throws Throwable {
        return driver.findElement(By.id(id));
    }
    
    private WebElement selectNestedElementByIds(String out, String in) throws Throwable {
        return driver.findElement(By.id(out)).findElement(By.id(in));
    }

    private boolean contains(String content) {
        return driver.getPageSource().contains(content);
    }

    private boolean containsCaseInsensitive(String content) {
        return org.apache.commons.lang3.StringUtils.containsIgnoreCase(driver.getPageSource(), content);
    }
    
    private String capitalize(String s){
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
    
    private String findFromAttributes(String attribute, List<String> attributes){
        for(String att : attributes){
            String[] split = att.split(": ");
            String key = split[0];
            String value = split[1];
            if(key.equals(attribute)){
                return value;
            }
        }
        return null;
    }
    
    @Given("^I have a form for adding an? (.+) available$")
    public void i_have_a_form_for_adding_a_reference_available(String type) throws Throwable{
        getFrontPage();
        selectReferenceType(type);       
    }
    
    @Given("^an? (.+) reference with the following attributes has been added: (.*)$")
    public void a_reference_has_been_added(String type, List<String> attributes) throws Throwable{
        i_have_a_form_for_adding_a_reference_available(type);
        i_add_a_reference_with_the_following_attributes(type, attributes);
    }
    
    @Given("^the index page is selected$")
    public void the_index_page_is_selected() throws Throwable{
        getFrontPage();
    }
    
    @Given("^an? (.+) reference exists in the database with the following attributes: (.*)")
    public void a_reference_exists_in_the_database_with_the_following_attributes(String type, List<String> attributes) throws Throwable{
        i_have_a_form_for_adding_a_reference_available(type);
        i_add_a_reference_with_the_following_attributes(type, attributes);
    }
    
    @When("^I remove an? .+ reference with the following attributes: (.*)$")
    public void i_remove_a_reference_with_the_following_attributes(List<String> attributes) throws Throwable{
        getReferenceListing();
        String id = findFromAttributes("refId", attributes);
        selectElementById("remove-"+id).click();
    }
    
    @When("^I view an? .+ reference with the following attributes: (.*)$")
    public void i_view_a_reference_with_the_following_attributes(List<String> attributes) throws Throwable{
        String id = findFromAttributes("refId", attributes);
        getReferenceView(id);
    }
    
    @When("an? (.+) reference is selected$")
    public void a_reference_is_selected(String type) throws Throwable{
        selectReferenceType(type);
    }
    
    @When("^I add an? (.+) reference with the following attributes: (.*)$")
    public void i_add_a_reference_with_the_following_attributes(String type, List<String> attributes) throws Throwable{
        for(String attribute : attributes){
            String[] split = attribute.split(": ");
            String key = split[0];
            String value = split[1];
            selectNestedElementByIds(type.toLowerCase()+"Form", key).sendKeys(value);
        }
        selectNestedElementByIds(type.toLowerCase()+"Form", "submitButton").click();
    }

    @When("^I export the BibTeX file$")
    public void i_export_the_bibtex_file() throws Throwable {
        getBibTexFile();
    }
    
    @Then("^an? (.+) with the following attributes should be visible: (.*)$")
    public void a_reference_with_the_following_attributes_should_be_visible(String type, List<String> attributes){
        assertTrue(containsCaseInsensitive(type));
        for(String att : attributes){
            String field = att.split(": ")[1];
            assertTrue(contains(field));
        }
    }
    
    @Then("^the following input fields should be visible: (.*)$")
    public void the_following_input_fields_should_be_visible(List<String> fields) throws Throwable{
        for(String field : fields){
            assertTrue(contains(field));
        }
    }
    
    @Then("^an? (.+) with the following attributes should have been added: (.*)$")
    public void a_reference_with_the_following_attributes_should_have_been_added(String type, List<String> attributes) throws Throwable{
        getBibTexFile();
        assertTrue(containsCaseInsensitive(type));
        for(String att : attributes){
            String field = att.split(": ")[1];
            assertTrue(contains(field));
        }
    }
    
    @Then("^an? (.+) with the following attributes should not have been added: (.*)$")
    public void a_reference_with_the_following_attributes_should_not_have_been_added(String type, List<String> attributes) throws Throwable{
        getBibTexFile();
        assertFalse(containsCaseInsensitive(type));
        for(String att : attributes){
            String field = att.split(": ")[1];  
            assertFalse(contains(field));
        }
    }
    
    @Then("^an? (.+) with the following attributes should not exist: (.*)$")
    public void a_reference_with_the_following_attributes_should_not_exist(String type, List<String> attributes) throws Throwable{
        getBibTexFile();
        assertFalse(containsCaseInsensitive(type));
        for(String att : attributes){
            String field = att.split(": ")[1];  
            assertFalse(contains(field));
        }
    }
    
    private boolean referenceExists(String type, List<String> attributes){
        if(!containsCaseInsensitive(type)){
            return false;
        }
        for(String att : attributes){
            String field = att.split(": ")[1];  
            if(!contains(field)){
                return false;
            }
        }
        return true;
    }
    
    @Then("^a BibTeX file containing an? (.+) with the following attributes should have been created: (.*)$")
    public void a_bibtex_file_with_the_following_fields_should_have_been_created(String type, List<String> attributes) throws Throwable{
        assertTrue(containsCaseInsensitive(type));
        for(String att : attributes){
            String field = att.split(": ")[1];
            assertTrue(contains(field));
        }
    }

    @After
    public void tearDown() {
        driver.get(baseUrl+"/reset");
        driver.get(baseUrl + "/reset");
        driver.close();
        driver.quit();
    }
    
}
