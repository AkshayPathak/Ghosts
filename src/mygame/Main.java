package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        
        // All the settings. Can be changed when the game starts
        AppSettings cfg = new AppSettings(true);
        cfg.setFrameRate(60); // Frame Rate
        cfg.setVSync(true);   // Prevents page tearing
        cfg.setFrequency(cfg.getFrequency()); // Set to screen refresh rate
        cfg.setResolution(1920, 1080);   
        cfg.setFullscreen(true); 
        cfg.setSamples(2);    // Anti-aliasing
        cfg.setTitle("Ghosts®"); // Window name
        
//        try {
//            cfg.setIcons(new BufferedImage[]{ImageIO.read(new File("assets/Interface/icon.gif"))}); // Window Icon
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Icon missing.", ex);
//        }
        
        cfg.setSettingsDialogImage("Interface/Images/SplashScreens/SettingsSplash.png"); // Splash screen
        
        // Hiding the stats 
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        
        app.setSettings(cfg); // Add settings to the program
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initNifty();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    // Initialize Nifty
    public void initNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);        
        
        // Add all XML files to Nifty
        nifty.addXml("Interface/XML/LogoSplash.xml");
        nifty.addXml("Interface/XML/TitleSplash.xml");
        nifty.addXml("Interface/XML/MainMenu.xml");
        nifty.addXml("Interface/XML/GameState.xml");
        nifty.addXml("Interface/XML/SettingsMenu.xml");
        nifty.addXml("Interface/XML/Loading.xml");
        
        // Going to the first Screen
        nifty.gotoScreen("start");
        
        // Setting the controller and attaching the state to the stateManager
        //logoSplash = (LogoSplashState) nifty.getScreen("start").getScreenController();
        MasterClass.mainMenu = (MainMenuState) nifty.getScreen("start").getScreenController();
        stateManager.attach(MasterClass.mainMenu); 
    }
}
