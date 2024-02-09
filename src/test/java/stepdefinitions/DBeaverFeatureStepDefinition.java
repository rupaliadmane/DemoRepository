package stepdefinitions;

import com.hp.lft.sdk.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pageobjects.DBeaver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class DBeaverFeatureStepDefinition {
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
    public void user_selects_database(String databaseType) throws GeneralLeanFtException, CloneNotSupportedException, IOException {
        dBeaver.selectDatabaseType(databaseType);
        dBeaver.clickButtonWithText("&Next >", dBeaver.connectToADatabaseWindow());
    }

    @When("User enters database details and clicks Enter")
    public void user_enters_database_details_and_clicks_Enter(List<List<String>> dataTable) throws GeneralLeanFtException {
        for (List<String> entry: dataTable){
         dBeaver.enterConnectionDetails(entry.get(0),entry.get(1));
        }
        dBeaver.clickButtonWithText("&Finish",dBeaver.connectToADatabaseWindow());
    }

    @Then("Verify the database connection {string} is created on Database Navigator")
    public void verify_the_database_connection_is_created_on_Database_Navigator(String dbName) throws GeneralLeanFtException {

      Assert.assertTrue(dBeaver.verifyDBConnectionExistsOnDatabaseNavigator(dbName));

    }

    @Then("Close Dbeaver application")
    public void close_dbeaver_application() throws GeneralLeanFtException {
        dBeaver.close();
    }
}
