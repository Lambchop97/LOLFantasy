package components;

import gui.UIUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class LFTradePanel {

    private JPanel content;

    public LFTradePanel(){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        content.setBackground(UIUtils.greyLight);
        content.setBorder(BorderFactory.createMatteBorder(0,5,5,5,UIUtils.grey));
        content.setMaximumSize(new Dimension(1315, 150));
    }

    public JPanel getContent() {
        return content;
    }
}
