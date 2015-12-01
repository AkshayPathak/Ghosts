package mygame;

// The master class provides access to all the other classes

public class MasterClass {
    
    public static MainMenuState mainMenu = new MainMenuState();
    public static SettingsState settingsState = new SettingsState();
    public static GameState gameState = new GameState();
    public static TitleSplashState titleSplash = new TitleSplashState();
    public static LogoSplashState logoSplash = new LogoSplashState();
    public static LoadingState loadingState = new LoadingState();
    
}
