package components;

import javax.swing.*;
import java.awt.*;

public class LFRoundedPanel extends JPanel {

    private int strokeSize = 1;
    private Color shadowColor = Color.black;
    private boolean hasShadow = true;

    private Dimension arc = new Dimension(20,20);
    private int shadowGap = 4;
    private int shadowOffset = 3;
    private int shadowAlpha = 150;


    LFRoundedPanel(){
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(hasShadow){
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,
                    shadowOffset,
                    width - strokeSize - shadowOffset,
                    height - strokeSize - shadowOffset,
                    arc.width,
                    arc.height);
        } else {
            shadowGap = 1;
        }

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0,0,width - shadowGap, height - shadowGap, arc.width, arc.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0,0,width - shadowGap, height - shadowGap, arc.width, arc.height);

        graphics.setStroke(new BasicStroke());
    }

    public void setHasShadow(boolean hasShadow){
        this.hasShadow = hasShadow;
    }
}
