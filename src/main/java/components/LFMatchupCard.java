package components;

import javax.swing.*;
import java.awt.*;

public class LFMatchupCard {
    private LFRoundedPanel content;

    public LFMatchupCard(){
        content = new LFRoundedPanel();

        content.setPreferredSize(new Dimension(820, 162));
        content.setMaximumSize(new Dimension(820, 162));
        content.setBackground(Color.BLUE);
    }

    public JPanel getContent(){
        return content;
    }
}
