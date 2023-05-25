package src.app.game.view;
import jtamaro.en.Graphic;
import static jtamaro.en.Graphics.*;
import static jtamaro.en.Color.*;
import jtamaro.en.Colors;
import static jtamaro.en.Colors.*;

public record Background (Graphic background) {

    public static final Background MAIN_MENU_BACKGROUND =  new Background( rectangle(200,200, BLACK));
    public static final Background IN_GAME_MENU_BACKGROUND =  new Background( rectangle(200,200, RED));
    public static final Background WON_MENU_BACKGROUND =  new Background( rectangle(200,200, YELLOW));
    public static final Background LOST_MENU_BACKGROUND =  new Background( rectangle(200,200, YELLOW));



}
