package screen;

import components.LFServerCard;
import gui.UIUtils;
import start.StartUp;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.io.File;

public class LFSelectServerScreen extends LFScreen {



    public LFSelectServerScreen(){
        super();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.setBackground(UIUtils.grey);

        JPanel scrollingContent = new JPanel();

        JLabel screenTitle = new JLabel("SELECT SERVER");
        screenTitle.setForeground(Color.white);
        screenTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JScrollPane list = new JScrollPane(scrollingContent);
        list.setMaximumSize(new Dimension(350, 500));
        list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        list.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        list.getVerticalScrollBar().setUI(new MetalScrollBarUI(){
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

        list.getVerticalScrollBar().setUnitIncrement(10);
        list.setBorder(null);

        LFServerCard newCard = new LFServerCard();

        scrollingContent.setLayout(new BoxLayout(scrollingContent, BoxLayout.PAGE_AXIS));

        File home = new File(StartUp.HOME_PATH);

        File[] files = home.listFiles();
        int cards = 0;

        if(files != null ) for(File f: files) {
            if (f.isDirectory()) {
                LFServerCard card = new LFServerCard(f.getAbsolutePath());
                scrollingContent.add(card);
                scrollingContent.add(Box.createRigidArea(new Dimension(0,2)));
            }
        }
        scrollingContent.add(newCard);
        scrollingContent.setBackground(UIUtils.grey);

        content.add(screenTitle);
        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.add(list);
        content.add(Box.createRigidArea(new Dimension(0,9)));
    }
}
