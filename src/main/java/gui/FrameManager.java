package gui;

import javax.swing.*;
import java.awt.*;

public class FrameManager {

    private static JFrame frame;

    private static JPanel currentContent;

    public static void setCurrentContent(JPanel content){
        if(currentContent != null) resetFrame();
        currentContent = content;

        Dimension size = content.getMaximumSize();
        resizeFrame(size.width, size.height);

        frame.add(content);
        frame.revalidate();
        frame.repaint();
    }

    private static void resetFrame(){
        frame.getContentPane().remove(currentContent);
        currentContent = null;
    }

    public static void registerFrame(JFrame rFrame){
        frame = rFrame;
    }

    public static void resizeFrame(int width, int height){
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
