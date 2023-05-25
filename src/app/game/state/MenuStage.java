package src.app.game.state;

public enum MenuStage implements Stage{
    IN_GAME_MENU,
    MAIN_MENU,
    LEVEL_SELECT_MENU,
    WON_MENU,
    LOST_MENU;

    public static boolean isInGameMenu(Stage stage) {
        return stage.equals(IN_GAME_MENU);
    }

    public static boolean isMainMenu(Stage stage) {
        return stage.equals(MAIN_MENU);
    }
    public static boolean isLevelSelectMenu(Stage stage) {
        return stage.equals(LEVEL_SELECT_MENU);
    }

    public static boolean isWonMenu(Stage stage) {
        return stage.equals(WON_MENU);
    }

    public static boolean isLostMenu(Stage stage) {
        return stage.equals(LOST_MENU);
    }
}
