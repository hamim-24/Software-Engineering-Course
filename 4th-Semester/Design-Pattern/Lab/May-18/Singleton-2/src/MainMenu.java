public class MainMenu {
    public void displayMenu() {
        GameSettingsManager settings = GameSettingsManager.getInstance();
        System.out.println("\n[MainMenu] Displaying menu.");
        System.out.println("[MainMenu] Language: " + settings.getLanguage());
        System.out.println("[MainMenu] Volume  : " + settings.getVolume());
    }
    public void loadSettings() {
        GameSettingsManager.getInstance().loadSettings();
        System.out.println("[MainMenu] Settings loaded.");
    }
}
