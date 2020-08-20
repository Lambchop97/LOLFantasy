package screen;

import components.LFInnerOverview;
import components.LFTabPanel;
import components.LFTransparentPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LFOverviewScreen extends LFScreen {

    private static final String TINT = "Tint";
    private Action tint = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ACTION");
            tintPanel.setVisible(!tintPanel.isVisible());
            content.revalidate();
            content.repaint();
        }
    };

    LFTransparentPane tintPanel;

    public LFOverviewScreen(){
        super();
        dimension = new Dimension(1264, 720);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        JLayeredPane wrapperPanel = new JLayeredPane();
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        content.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        wrapperPanel.setMinimumSize(dimension);
        wrapperPanel.setMaximumSize(dimension);
        wrapperPanel.setPreferredSize(dimension);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0,0,1264,720);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        LFTabPanel tabs = new LFTabPanel("overview");

        LFInnerOverview innerContent = new LFInnerOverview();

        contentPanel.add(tabs.getContent());
        contentPanel.add(innerContent.getContent());



        tintPanel = new LFTransparentPane();

        tintPanel.setBounds(0,0,1264, 720);
        tintPanel.setBackground(new Color(20,20,20, 128));
        tintPanel.setOpaque(false);
        tintPanel.setVisible(false);

        content.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), TINT);
        content.getActionMap().put(TINT, tint);

        wrapperPanel.add(contentPanel, Integer.valueOf(1));
        wrapperPanel.add(tintPanel, Integer.valueOf(2));

        content.add(wrapperPanel);
    }

}
