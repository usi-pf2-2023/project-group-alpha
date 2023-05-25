package src.app.game.state;

public enum GameStage implements Stage {
    LEVEL_1,
    LEVEL_2,
    LEVEL_3;

    /**
     * MenuStage.isLevelOne evaluates whether the current stage is the first level
     * @param stage, the current game stage
     * @return true if it's the case, false otherwise
     */
    public boolean isLevelOne(Stage stage) {
        return stage.equals(LEVEL_1);
    }
    /**
     * MenuStage.isLevelTwo evaluates whether the current stage is the second level
     * @param stage, the current game stage
     * @return true if it's the case, false otherwise
     */
    public boolean isLevelTwo(Stage stage) {
        return stage.equals(LEVEL_2);
    }
    /**
     * MenuStage.isLevelThree evaluates whether the current stage is the three level
     * @param stage, the current game stage
     * @return true if it's the case, false otherwise
     */
    public boolean isLevelThree(Stage stage) {
        return stage.equals(LEVEL_3);
    }
}