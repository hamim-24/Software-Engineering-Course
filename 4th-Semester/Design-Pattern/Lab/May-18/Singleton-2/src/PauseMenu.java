public class PauseMenu {
    public void pauseGame() {
        GameSettingsManager settings = GameSettingsManager.getInstance();
        System.out.println("\n[PauseMenu] Game paused.");
        System.out.println("[PauseMenu] Current volume: " + settings.getVolume());
    }
    public void resumeGame() {
        System.out.println("[PauseMenu] Resuming game...");
    }
}
