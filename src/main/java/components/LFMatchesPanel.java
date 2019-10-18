package components;

import gui.UIUtils;

import javax.swing.*;
import java.awt.*;

public class LFMatchesPanel {
    private JPanel content;

    public LFMatchesPanel(){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(UIUtils.greyLight.brighter());
        content.setBorder(BorderFactory.createMatteBorder(5,5,5,5,UIUtils.greyLight));
        content.setMaximumSize(new Dimension(1255*2/3, 524));
    }

    public JPanel getContent() {
        return content;
    }
}
