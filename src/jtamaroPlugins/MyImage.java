package src.jtamaroPlugins;

import jtamaro.en.graphic.AbstractGraphic;

public final class MyImage extends AbstractGraphic {

    public MyImage(java.awt.Image img) {
        super(new MyImageImpl(img));
    }

    public MyImage(java.awt.Image img, int width, int height) {
        super(new MyImageImpl(img, width, height));
    }
}
