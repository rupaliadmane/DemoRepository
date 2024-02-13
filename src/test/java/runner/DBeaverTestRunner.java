package runner;

import com.hp.lft.sdk.*;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pageobjects.DBeaver;

import java.net.URI;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = {"src\\test\\resources\\features"},
        glue={".stepdefinitions"},
        monochrome = true,
        tags = {},
        dryRun = false,
        strict = true
)
public class DBeaverTestRunner {
    private static DBeaver dBeaver = new DBeaver();
    @BeforeClass
    public static void setUp() throws Exception {
//        ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
//        config.setServerAddress(new URI("ws://localhost:5095"));
//        SDK.init(config);

        dBeaver.launch();
    }
    @AfterClass
    public static void tearDown() throws Exception {
        dBeaver.close();
    }

}


