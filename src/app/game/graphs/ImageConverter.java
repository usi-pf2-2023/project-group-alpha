package src.app.game.graphs;

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
            File file = new File("src/app/game/graphs/images/" + fileName);
            return new myImage(ImageIO.read(file));
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
