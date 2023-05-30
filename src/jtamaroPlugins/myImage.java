package src.jtamaroPlugins;

import jtamaro.en.graphic.AbstractGraphic;

public final class myImage extends AbstractGraphic {

    public myImage(java.awt.Image img) {
        super(new myImageImpl(img));
    }

    public myImage(java.awt.Image img, int width, int height) {
        super(new myImageImpl(img, width, height));
    }
}
