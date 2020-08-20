package screen;

import javax.swing.*;
import java.awt.*;

public class LFScreen {
    JPanel content;

    Dimension dimension;

    LFScreen(){
        content = new JPanel();

        dimension = new Dimension(384,448);

        content.setMaximumSize(dimension);
    }

    public JPanel getContent() {
        return content;
    }
    public Dimension getDimension(){
        return dimension;
    }
}
