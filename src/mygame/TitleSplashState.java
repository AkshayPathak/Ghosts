package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class TitleSplashState extends AbstractAppState implements ScreenController{
    
    private SimpleApplication app;
    
    private Nifty nifty;
    private Screen screen;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private AudioNode Music;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app; 
        
        // Initialize the main components of the program
        this.stateManager = this.app.getStateManager();
        this.assetManager = this.app.getAssetManager();
        
        playMusic();
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
    
    // Method to go the next screen. Its called by the XML file
    public void nextScreen() {
        
        // Sending Nifty to the next screen based on the XML screen id
        nifty.gotoScreen("mainMenuXML");
        
        // Getting its controller
        MasterClass.mainMenu = (MainMenuState) nifty.getScreen("mainMenuXML").getScreenController();
        
        // Detaching this state and attaching the next state
        stateManager.detach(this);
        stateManager.attach(MasterClass.mainMenu);
    }

    private void playMusic() {
        Music = new AudioNode(assetManager, "Sounds/TitleMusic.ogg", true);
        Music.setPositional(false);
        Music.setVolume(5f);
        Music.setLooping(false);
        Music.play();
    }
}
