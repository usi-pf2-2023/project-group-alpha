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
    private static final int BACKGROUND_RED_MAX = 22;
    private static final int BACKGROUND_GREEN_MAX = 12;
    private static final int BACKGROUND_BLUE_MAX = 13;
    // It's a test for making graphic
    public static void main(String[] args) throws IOException {
        show(toGraphic("iconBaba.png"));
    }
    public static Graphic toGraphic(String fileName) throws IOException {
        BufferedImage image = loadImage("src/app/game/graphs/images/" + fileName);
        ArrayList<ArrayList<Graphic>> imageMatrix = toArrayList(image);
        Graphic graphic = emptyGraphic();
        for(ArrayList<Graphic> list : imageMatrix) {
            Graphic row = emptyGraphic();
            for(Graphic x : list) {
                row = beside(row, x);
            }
            graphic = above(graphic, row);
        }
        return graphic;
    }
    private static BufferedImage loadImage(String imageName) throws IOException {
        File file = new File(imageName);
        return ImageIO.read(file);
    }
    private static ArrayList<ArrayList<Graphic>> toArrayList(BufferedImage image) {
        ArrayList<ArrayList<Graphic>> imageMatrix = new ArrayList<>();
        int width = image.getWidth();
        int height = image.getHeight();
        for(int i = 0; i < height; i++) {
            ArrayList<Graphic> row = new ArrayList<>();
            for(int j = 0; j < width; j++) {
                int color = image.getRGB(j, i);
                int red = new Color(color).getRed();
                int green = new Color(color).getGreen();
                int blue = new Color(color).getBlue();
                jtamaro.en.Color pixelColor = rgb(red, green, blue);
                if(red == BACKGROUND_RED_MAX && green == BACKGROUND_GREEN_MAX && blue == BACKGROUND_BLUE_MAX) {
                    pixelColor = rgb(0, 0, 0, 0);
                }
                row.add(rectangle(Settings.UNIT_WIDTH / width, Settings.UNIT_HEIGHT / height, pixelColor));
            }
            imageMatrix.add(row);
        }
        return imageMatrix;
    }
}
