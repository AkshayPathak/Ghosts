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

public class LoadingState extends AbstractAppState implements ScreenController{
    
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
        
        //Disable the mouse for the loading screen 
        flyCam.setDragToRotate(false);
        
        nextScreen();
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
    
    // Switching to the game state
    public void nextScreen() {
        // Sending Nifty to the next screen based on the XML screen id
        nifty.gotoScreen("GameXML");
        
        // Getting its controller
        MasterClass.gameState = (GameState) nifty.getScreen("GameXML").getScreenController();
        
        // Detaching this state and attaching the next state
        stateManager.detach(this);
        stateManager.attach(MasterClass.gameState);
    } 
}
