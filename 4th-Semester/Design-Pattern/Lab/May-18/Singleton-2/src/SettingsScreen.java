public class SettingsScreen {
    public void changeVolume(int newVolume) {
        System.out.println("\n[SettingsScreen] Changing volume to " + newVolume + "...");
        GameSettingsManager.getInstance().setVolume(newVolume);
    }
    public void updateDifficulty(String difficulty) {
        System.out.println("[SettingsScreen] Updating difficulty to " + difficulty + "...");
        GameSettingsManager.getInstance().setDifficulty(difficulty);
    }
    public void changeLanguage(String language) {
        System.out.println("[SettingsScreen] Changing language to " + language + "...");
        GameSettingsManager.getInstance().changeLanguage(language);
    }
    public void applySettings() {
        System.out.println("[SettingsScreen] Applying all settings...");
        GameSettingsManager.getInstance().saveSettings();
    }
}
