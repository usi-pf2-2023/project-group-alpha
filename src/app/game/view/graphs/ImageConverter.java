package src.app.game.view.graphs;

import jtamaro.en.Graphic;
import jtamaro.en.graphic.Rectangle;
import src.app.game.Settings;
import src.jtamaroPlugins.myImage;

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
    //    It's a test for making graphic
//    public static void main(String[] args) {
//        show(toGraphic("babaEast.png"));
//    }
    public static Graphic toGraphic(String fileName) {
        try {
            String path = "src/app/game/view/graphs/images/";
            File file = new File(path + fileName);
            return new myImage(ImageIO.read(file));
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static Graphic toHints(String fileName, double width, double height) {
        try {
            String path = "src/app/game/view/graphs/images/";
            File file = new File(path + fileName);
            return new myImage(ImageIO.read(file), (int) width, (int) height);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
