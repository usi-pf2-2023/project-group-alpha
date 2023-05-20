package src.jtamaroPlugins;

import jtamaro.en.graphic.AbstractGraphic;

public final class myImage extends AbstractGraphic {

    public myImage(java.awt.Image img) {
        super(new myImageImpl(img));
    }
}
