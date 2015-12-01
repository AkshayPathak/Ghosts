package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class GameEndState extends AbstractAppState implements ScreenController{
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);  
    }

    @Override
    public void update(float tpf) { 
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    public void bind(Nifty nifty, Screen screen) {
    }

    public void onStartScreen() {
    }

    public void onEndScreen() {
    }

}
