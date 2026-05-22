public class GameplayScreen {
    public void applyGraphicsSettings() {
        GameSettingsManager settings = GameSettingsManager.getInstance();
        System.out.println("\n[GameplayScreen] Applying graphics settings.");
        System.out.println("[GameplayScreen] Resolution : " + settings.getResolution());
        System.out.println("[GameplayScreen] Brightness : " + settings.getBrightness());
    }
    public void displayCurrentSettings() {
        System.out.println("[GameplayScreen] Fetching latest settings...");
        GameSettingsManager.getInstance().displayCurrentSettings();
    }
    public void startGame() {
        GameSettingsManager settings = GameSettingsManager.getInstance();
        System.out.println("\n[GameplayScreen] Starting game.");
        System.out.println("[GameplayScreen] Difficulty : " + settings.getDifficulty());
        System.out.println("[GameplayScreen] Volume     : " + settings.getVolume());
    }
}
