package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuState extends AbstractAppState implements ScreenController {
    
    private SimpleApplication app;
    
    private Nifty nifty;
    private Screen screen;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private FlyByCamera flyCam;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app; 
        
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.flyCam = this.app.getFlyByCamera();
        
        //Enable the mouse for the menu screens
        flyCam.setDragToRotate(true);
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
    
    // Method that are called from the XML file for the start button
    public void startButton() {
        // Sending Nifty to the next screen based on the XML screen id
        nifty.gotoScreen("LoadingXML");
        
        // Getting its controller
        MasterClass.loadingState = (LoadingState) nifty.getScreen("LoadingXML").getScreenController();
        
        // Detaching this state and attaching the next state
        stateManager.detach(this);
        stateManager.attach(MasterClass.loadingState);
    }
    
    // Method that are called from the XML file for the load button
    public void loadButton() {
        System.out.println("Load button pressed!");
    }
    
    // Method that are called from the XML file for the settings button
    public void settingsButton() {
        // Sending Nifty to the next screen based on the XML screen id
        nifty.gotoScreen("SettingsXML");
        
        // Getting its controller
        MasterClass.settingsState = (SettingsState) nifty.getScreen("SettingsXML").getScreenController();
        
        // Detaching this state and attaching the next state
        stateManager.detach(this);
        stateManager.attach(MasterClass.settingsState);
    }
    
    // Method that are called from the XML file for the quit button
    public void quitButton() {
        app.stop();
    }
}