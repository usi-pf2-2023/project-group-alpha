package src.app.game.graphs;

import jtamaro.en.Graphic;
import jtamaro.en.graphic.Rectangle;
import src.app.game.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.Colors.*;
import static jtamaro.en.IO.*;

public class ImageConverter {
    public static final int BACKGROUND_RED = 13;
    public static final int BACKGROUND_GREEN = 153;
    public static final int BACKGROUND_BLUE = 57;

    //    It's a test for making graphic
//    public static void main(String[] args) {
//        show(toGraphic("babaEast.png"));
//    }
    public static Graphic toGraphic(String fileName) {
        BufferedImage image = loadImage("src/app/game/graphs/images/" + fileName);
        int width = image.getWidth();
        int height = image.getHeight();
        Graphic graphic = emptyGraphic();
        for (int i = 0; i < height; i++) {
            Graphic row = emptyGraphic();
            for (int j = 0; j < width; j++) {
                int color = image.getRGB(j, i);
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;
                jtamaro.en.Color pixelColor = rgb(red, green, blue);
                if (red == BACKGROUND_RED && green == BACKGROUND_GREEN && blue == BACKGROUND_BLUE) {
                    pixelColor = rgb(0, 0, 0, 0);
                }
                row = beside(row, rectangle(Settings.UNIT_WIDTH / width, Settings.UNIT_HEIGHT / height, pixelColor));
            }
            graphic = above(graphic, row);
        }
        return graphic;
    }

    // Read the image file
    private static BufferedImage loadImage(String imageName) {
        File file = new File(imageName);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("File reading error: " + imageName);
        }
        return null;
    }
}
