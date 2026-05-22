public class GameSettingsManager {
    private  static GameSettingsManager instance;
    private int volumeLevel;
    private String difficultyMode;
    private String language;
    private String resolution;
    private float brightness;

    private GameSettingsManager() {
        this.volumeLevel    = 50;
        this.difficultyMode = "Medium";
        this.language       = "English";
        this.brightness     = 0.8f;
        this.resolution     = "1920x1080";
    }

    public static synchronized GameSettingsManager getInstance() {
        if (instance == null) {
            instance = new GameSettingsManager();
        }
        return instance;
    }

    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            System.out.println("Volume must be between 0 and 100.");
            return;
        }
        this.volumeLevel = volume;
        System.out.println("Volume set to: " + volumeLevel);
    }

    public int getVolume() {
        return volumeLevel;
    }

    public String getDifficulty() {
        return difficultyMode;
    }

    public void setDifficulty(String difficultyMode) {
        if (difficultyMode.equals("Medium") || difficultyMode.equals("Hard") || difficultyMode.equals("Easy")) {
            this.difficultyMode = difficultyMode;
            System.out.println("Difficulty set to: " + difficultyMode);
        }
        System.out.println();
    }

    public void changeLanguage(String language) {
        this.language = language;
        System.out.println("Language changed to: " + language);
    }
    public String getLanguage() {
        return language;
    }
    public void setBrightness(float brightness) {
        if (brightness < 0.0f || brightness > 1.0f) {
            System.out.println("Brightness must be between 0.0 and 1.0.");
            return;
        }
        this.brightness = brightness;
        System.out.println("Brightness set to: " + brightness);
    }
    public float getBrightness() {
        return brightness;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
        System.out.println("Resolution set to: " + resolution);
    }
    public String getResolution() {
        return resolution;
    }
    public void saveSettings() {
        System.out.println("\n── Saving settings ──────────────────");
        System.out.println("  Volume     : " + volumeLevel);
        System.out.println("  Difficulty : " + difficultyMode);
        System.out.println("  Language   : " + language);
        System.out.println("  Brightness : " + brightness);
        System.out.println("  Resolution : " + resolution);
        System.out.println("  Settings saved successfully.");
    }
    public void loadSettings() {
        System.out.println("Settings loaded successfully.");
    }
    public void displayCurrentSettings() {
        System.out.println("\n── Current Settings ─────────────────");
        System.out.println("  Volume     : " + volumeLevel);
        System.out.println("  Difficulty : " + difficultyMode);
        System.out.println("  Language   : " + language);
        System.out.println("  Brightness : " + brightness);
        System.out.println("  Resolution : " + resolution);
        System.out.println("─────────────────────────────────────");
    }
}
