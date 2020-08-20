package components;

import gui.UIUtils;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;

public class LFStatusChangePanel {

    private JScrollPane contentScroll;

    public LFStatusChangePanel(){
        JPanel content = new JPanel();

        contentScroll = new JScrollPane(content);
        contentScroll.setMaximumSize(new Dimension(1315, 185));
        contentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentScroll.setBorder(BorderFactory.createMatteBorder(0,5,5,5,UIUtils.grey));
        contentScroll.getHorizontalScrollBar().setUI(new MetalScrollBarUI(){
            JButton b = new JButton(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(0,0);
                }
            };

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.blue);
                g.drawImage(UIUtils.createImage(32, 32, UIUtils.greyLight.darker()), r.x , r.y, r.width, r.height, null);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.blue);
                g.drawImage(UIUtils.createImage(32, 32, UIUtils.greyLight), r.x , r.y, r.width, r.height, null);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return b;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return b;
            }
        });
        contentScroll.getHorizontalScrollBar().setUnitIncrement(10);


        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        content.setBackground(UIUtils.grey);
        content.setBorder(BorderFactory.createMatteBorder(0,0,5,0,UIUtils.grey));

        for(int i = 0; i < 3; i++){
            content.add(new LFStatusChangeCard().getContent());
            if(i < 2) content.add(Box.createRigidArea(new Dimension(5, 0)));
        }
    }

    public JScrollPane getContent() {
        return contentScroll;
    }
}
