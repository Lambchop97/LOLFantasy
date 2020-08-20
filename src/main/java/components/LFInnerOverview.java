package components;

import gui.UIUtils;

import javax.swing.*;
import java.awt.*;

public class LFInnerOverview {

    private JPanel content;

    public LFInnerOverview(){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(UIUtils.grey);
        content.setMaximumSize(new Dimension(1280, 657));

        content.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel innerContent = new JPanel();

        innerContent.setLayout(new BoxLayout(innerContent, BoxLayout.LINE_AXIS));
        innerContent.setBackground(UIUtils.greyLight);
        innerContent.setMaximumSize(new Dimension(1315, 534));
        innerContent.setBorder(BorderFactory.createMatteBorder(5,5,5,5,UIUtils.grey));

        LFStandingsPanel standingsPanel = new LFStandingsPanel();

        LFMatchesPanel matchesPanel = new LFMatchesPanel();

        innerContent.add(matchesPanel.getContent());
        innerContent.add(standingsPanel.getContent());

        LFStatusChangePanel tradePanel = new LFStatusChangePanel();

        content.add(innerContent);
        content.add(tradePanel.getContent());
    }
    public JPanel getContent() {
        return content;
    }
}
