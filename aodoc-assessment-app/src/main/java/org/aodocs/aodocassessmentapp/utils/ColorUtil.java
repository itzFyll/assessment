package org.aodocs.aodocassessmentapp.utils;

import com.google.api.services.sheets.v4.model.Color;

public class ColorUtil {

    static public Color colorFromRGBA(float r, float g, float b, float a) {
        return new Color().setRed(r).setGreen(g).setBlue(b).setAlpha(a);
    }

    static public Color colorFromHex(int hexColor) {
        float r = (hexColor >> 16 & 0xFF) / 255f;
        float g = (hexColor >> 8 & 0xFF) / 255f;
        float b = (hexColor >> 0 & 0xFF) / 255f;
        return new Color().setRed(r).setGreen(g).setBlue(b);
    }
}
