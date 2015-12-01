package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class GameState extends AbstractAppState implements ScreenController{
    
    private SimpleApplication app;
    
    private Nifty nifty;
    private Screen screen;
    private Node rootNode;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private FlyByCamera flyCam;
    private ViewPort viewPort;
       
    private FilterPostProcessor fpp;
    private Spatial sceneModel;
    private Vector3f lightDir = new Vector3f(-4f,-1f,5f);
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app; 
        
        this.assetManager = this.app.getAssetManager();
        this.rootNode = this.app.getRootNode();
        this.stateManager = this.app.getStateManager();
        this.flyCam = this.app.getFlyByCamera();
        this.viewPort = this.app.getViewPort();
        
        // Disable the cursor 
        flyCam.setDragToRotate(false);
        
        // Changing the speed of the camera
        flyCam.setMoveSpeed(100f);
        
        initScene();
        initLight();
        initBloom();
        //initDepthOfField();
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

    private void initScene() {
        sceneModel = assetManager.loadModel("Scenes/Enviroment.j3o");  
        
        rootNode.attachChild(sceneModel);
    }
    
    // Light
    private void initLight() {
        // Sun
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(lightDir);
        sun.setColor(ColorRGBA.Gray.clone().multLocal(1.7f));
        rootNode.addLight(sun);
        
        // Another Sun
        DirectionalLight l = new DirectionalLight();
        l.setDirection(Vector3f.UNIT_Y.mult(-1));
        l.setColor(ColorRGBA.Gray.clone().multLocal(0.3f));
        rootNode.addLight(l);
    }

    private void initDepthOfField() {
        DepthOfFieldFilter dofFilter = new DepthOfFieldFilter();
        
        dofFilter.setFocusDistance(0);
        dofFilter.setFocusRange(50);
        dofFilter.setBlurScale(1.4f);
        fpp.addFilter(dofFilter);
        
        viewPort.addProcessor(fpp);
    }

    private void initBloom() {
        fpp = new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter();
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
}
