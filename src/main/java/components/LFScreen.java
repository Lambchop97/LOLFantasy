package components;

import javax.swing.*;
import java.awt.*;

public class LFScreen {

    JPanel content;

    LFScreen(){
        content = new JPanel();

        content.setMaximumSize(new Dimension(384,448));
    }

    public JPanel getContent() {
        return content;
    }
}
