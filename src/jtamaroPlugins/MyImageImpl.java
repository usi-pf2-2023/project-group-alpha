package src.jtamaroPlugins;

import jtamaro.internal.gui.RenderOptions;
import jtamaro.internal.representation.GraphicImpl;
import src.app.game.Settings;

import java.awt.*;
import java.awt.geom.Path2D;

public class MyImageImpl extends GraphicImpl {

    private Image img;

    public MyImageImpl(Image img) {
        this(img, (int) Settings.UNIT_WIDTH, (int) Settings.UNIT_HEIGHT);
    }

    public MyImageImpl(Image img, int w, int h) {
        img = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        this.img = img;
        double width = img.getWidth(null);
        double height = img.getHeight(null);
        Path2D.Double path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.closePath();
        this.setPath(path);
    }

    @Override
    public void render(Graphics2D graphics2D, RenderOptions renderOptions) {
        graphics2D.drawImage(img, 0, 0, null);
    }
}
