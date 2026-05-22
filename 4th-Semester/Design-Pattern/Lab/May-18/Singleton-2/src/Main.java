public class Main {

    public static void main(String[] args) {

        System.out.println("════════════════════════════════════════");
        System.out.println("   Game Settings Manager — Singleton    ");
        System.out.println("════════════════════════════════════════");

        GameSettingsManager s1 = GameSettingsManager.getInstance();
        GameSettingsManager s2 = GameSettingsManager.getInstance();
        System.out.println("\nSingleton check — s1 == s2: " + (s1 == s2));

        MainMenu mainMenu = new MainMenu();
        mainMenu.loadSettings();
        mainMenu.displayMenu();

        SettingsScreen settingsScreen = new SettingsScreen();
        settingsScreen.changeVolume(75);
        settingsScreen.updateDifficulty("Hard");
        settingsScreen.changeLanguage("French");
        settingsScreen.applySettings();

        GameplayScreen gameplayScreen = new GameplayScreen();
        gameplayScreen.startGame();
        gameplayScreen.applyGraphicsSettings();
        gameplayScreen.displayCurrentSettings();

        PauseMenu pauseMenu = new PauseMenu();
        pauseMenu.pauseGame();
        pauseMenu.resumeGame();

        System.out.println("\n════════════════════════════════════════");
        System.out.println("   All screens share ONE settings object");
        System.out.println("════════════════════════════════════════");
    }
}