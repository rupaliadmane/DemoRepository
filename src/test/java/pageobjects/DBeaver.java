package pageobjects;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.stdwin.*;

import java.net.URI;
import java.net.URISyntaxException;

public class DBeaver {

    Aut dbeaver;
    public Window dbeaverWindow() throws GeneralLeanFtException {
        return Desktop.describe(Window.class,
                            new WindowDescription.Builder().windowTitleRegExp("DBeaver 23.3.4").build());
    }
    public void closeTipOfTheDayWindow() throws GeneralLeanFtException {
        Window tipOfTheDayWindow= dbeaverWindow().describe(Window.class, new WindowDescription.Builder()
                .windowTitleRegExp("Tip of the day").build());
        Button closeButton = tipOfTheDayWindow.describe(Button.class, new ButtonDescription.Builder().text("&Close").build());
        closeButton.click();
    }

    public ToolBar toolBar() throws GeneralLeanFtException {
       return dbeaverWindow().describe(ToolBar.class, new ToolBarDescription.Builder()
                .nativeClass("ToolbarWindow32")
                .index(0).build());
    }

    public ToolBarButton newDatabaseConnectionButton() throws GeneralLeanFtException {
        return toolBar().getButton("1");
    }

    public void selectDatabaseType(String databaseType) throws GeneralLeanFtException, CloneNotSupportedException {
        Window connectToADatabaseWindow = dbeaverWindow().describe(Window.class, new WindowDescription.Builder()
                .windowTitleRegExp("Connect to a database").build());
        connectToADatabaseWindow.findChildren(EditField.class,new EditFieldDescription())[0].sendKeys(databaseType);
       //In progress
    }

    public void launch() throws Exception {

        ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();

        config.setServerAddress(new URI("ws://localhost:5095"));
        SDK.init(config);
        //File Name would be customizable later
        dbeaver=Desktop.launchAut("C:\\Users\\rupali.admane\\AppData\\Local\\DBeaver\\DBeaver.exe");
        dbeaverWindow().activate();

        closeTipOfTheDayWindow();
    }
}
