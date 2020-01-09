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
        content.setBorder(BorderFactory.createMatteBorder(0,0,0,5,UIUtils.greyLight));
        content.setMaximumSize(new Dimension(820, 514));
        content.setAlignmentY(Component.TOP_ALIGNMENT);

        content.add(new LFMatchupCard().getContent());
        content.add(new LFMatchupCard().getContent());
        content.add(new LFMatchupCard().getContent());
    }

    public JPanel getContent() {
        return content;
    }
}
