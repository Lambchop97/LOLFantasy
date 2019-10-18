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
        content.setMaximumSize(new Dimension(1280, 694));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel innerContent = new JPanel();

        innerContent.setLayout(new BoxLayout(innerContent, BoxLayout.LINE_AXIS));
        innerContent.setBackground(UIUtils.greyLight);
        innerContent.setMaximumSize(new Dimension(1280, 534));
        innerContent.setBorder(BorderFactory.createMatteBorder(5,5,5,5,UIUtils.grey));

        LFTradePanel tradePanel = new LFTradePanel();

        content.add(innerContent);
        content.add(tradePanel.getContent());
    }
    public JPanel getContent() {
        return content;
    }
}
