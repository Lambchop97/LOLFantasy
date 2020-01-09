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
        content.setBorder(BorderFactory.createMatteBorder(0,0,0,0,UIUtils.greyLight));
        content.setMaximumSize(new Dimension(435, 514));
        content.setAlignmentY(Component.TOP_ALIGNMENT);
    }

    public JPanel getContent() {
        return content;
    }
}
