package pageobjects;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.insight.InsightDescription;
import com.hp.lft.sdk.insight.InsightObject;
import com.hp.lft.sdk.stdwin.*;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class DBeaver {


    /*-------------------------------------------
        Page objects for DBeaver application
     ------------------------------------------
     */

    Aut dbeaver;
    private Window dbeaverWindow() throws GeneralLeanFtException {
        return Desktop.describe(Window.class,
                            new WindowDescription.Builder().windowTitleRegExp("DBeaver 23.3.4").build());
    }

    private ToolBar toolBar() throws GeneralLeanFtException {
       return dbeaverWindow().describe(ToolBar.class, new ToolBarDescription.Builder()
                .nativeClass("ToolbarWindow32")
                .index(0).build());
    }

    public ToolBarButton newDatabaseConnectionButton() throws GeneralLeanFtException {
        return toolBar().getButton("1");
    }

    public Window connectToADatabaseWindow() throws GeneralLeanFtException {
       return dbeaverWindow().describe(Window.class, new WindowDescription.Builder()
                .windowTitleRegExp("Connect to a database").build());
    }

    public Window getWindowByTitle() throws GeneralLeanFtException {
        return dbeaverWindow().describe(Window.class, new WindowDescription.Builder()
                .windowTitleRegExp("Connect to a database").build());
    }

    private TreeView databaseNavigator() throws GeneralLeanFtException {
            return dbeaverWindow().describe(TreeView.class, new TreeViewDescription.Builder().index(0).build());
    }

    /*-------------------------------------------
        Common methods for DBeaver application
     ------------------------------------------
     */

    public void closeTipOfTheDayWindow() throws GeneralLeanFtException {
        Window tipOfTheDayWindow= dbeaverWindow().describe(Window.class, new WindowDescription.Builder()
                .windowTitleRegExp("Tip of the day").build());
        Button closeButton = tipOfTheDayWindow.describe(Button.class, new ButtonDescription.Builder().text("&Close").build());
        closeButton.click();
    }
    public void selectDatabaseType(String databaseType) throws GeneralLeanFtException, CloneNotSupportedException, IOException {

        connectToADatabaseWindow().findChildren(EditField.class,new EditFieldDescription())[0].sendKeys(databaseType);

        // Locate the bitmap image for type.
        File imgFile = new File(".\\src\\test\\resources\\testdata\\images\\"+databaseType+".bmp");
        RenderedImage image = ImageIO.read(imgFile);
        InsightObject databaseTypeImage = connectToADatabaseWindow().describe(InsightObject.class, new InsightDescription(image));

        // Click on database type Image
        databaseTypeImage.click();
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

    public void close() throws GeneralLeanFtException {
        dbeaver.close();
    }

    public void enterConnectionDetails(String field, String value) throws GeneralLeanFtException {
        connectToADatabaseWindow().describe(EditField.class, new EditFieldDescription.Builder().
                attachedText(field+":").nativeClass("Edit").build() ).setText(value);

    }

    public void clickButtonWithText(String text,Window parentWindow) throws GeneralLeanFtException {

        parentWindow.describe(Button.class, new ButtonDescription.Builder().text(text).build()).click();

    }

    public boolean verifyDBConnectionExistsOnDatabaseNavigator(String dbName) throws GeneralLeanFtException {
            List<TreeViewNode> nodes = databaseNavigator().getSelectedNodes();
            for (TreeViewNode node : nodes) {
                 if(dbName.equalsIgnoreCase(node.getText()))
                     return true;
            }
        return false;

    }
}
