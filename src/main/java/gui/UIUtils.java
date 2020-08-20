package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIUtils {

    public static Color greyLight = new Color(150,150,150);
    public static Color grey = new Color(65,65,65);
    public static Color greyDark = new Color(48,48,48);
    public static Color brickRed = new Color(178,34,34);
    public static Color transparent = new Color(0,0,0,0);

    static public Image createImage(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }

    static public Color lighter(Color color){
        return new Color((int) (color.getRed() * 1.2f),(int) (color.getGreen() * 1.2f),(int) (color.getBlue() * 1.2f));
    }

    static public Color darker(Color color){
        return new Color((int) (color.getRed() * .8f),(int) (color.getGreen() * .8f),(int) (color.getBlue() * .8f));
    }
}
