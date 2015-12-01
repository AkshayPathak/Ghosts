package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class SettingsState extends AbstractAppState implements ScreenController{
    
    private SimpleApplication app;
    
    private Nifty nifty;
    private Screen screen;
    private AppStateManager stateManager;
    private AssetManager assetManager;
       
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication) app; 
        
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
    }

    @Override
    public void update(float tpf) { 
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    public void onStartScreen() {
    }

    public void onEndScreen() {
    }
    
    // Method that are called from the XML file for the back button
    public void back() {        
        // Sending Nifty to the next screen based on the XML screen id
        nifty.gotoScreen("start");
        
        // Getting its controller
        MasterClass.mainMenu = (MainMenuState) nifty.getScreen("start").getScreenController();
        
        // Detaching this state and attaching the next state
        stateManager.detach(this);
        stateManager.attach(MasterClass.mainMenu);
    }
}
