package stepdefinitions;

import com.hp.lft.sdk.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.DBeaver;

import java.net.URI;
import java.net.URISyntaxException;

public class DBeaverFeatureStepDefinition {
    Aut dbeaver;
    DBeaver dBeaver = new DBeaver();
    @Given("DBeaver is launched")
    public void dbeaver_is_launched() throws Exception {
        dBeaver.launch();
    }

    @When("User selects create connection on Database Navigator")
    public void user_selects_create_connection_on_Database_Navigator() throws GeneralLeanFtException {
        dBeaver.newDatabaseConnectionButton().press();
    }

    @When("User selects {string} database")
    public void user_selects_database(String databaseType) throws GeneralLeanFtException, CloneNotSupportedException {
        dBeaver.selectDatabaseType(databaseType);
    }

    @When("User enters database details and clicks Enter")
    public void user_enters_database_details_and_clicks_Enter(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

    }

    @Then("Verify the database connection {string} is created on Database Navigator")
    public void verify_the_database_connection_is_created_on_Database_Navigator(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("Close Dbeaver application")
    public void close_dbeaver_application() throws GeneralLeanFtException {
        dbeaver.close();
    }
}
