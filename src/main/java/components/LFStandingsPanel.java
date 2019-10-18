package components;

import gui.UIUtils;

import javax.swing.*;
import java.awt.*;

public class LFStandingsPanel {
    private JPanel content;

    public LFStandingsPanel(){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(UIUtils.greyLight.brighter());
        content.setBorder(BorderFactory.createMatteBorder(5,0,5,5,UIUtils.greyLight));
        content.setMaximumSize(new Dimension(1255/3 + 1, 524));
    }

    public JPanel getContent() {
        return content;
    }
}
