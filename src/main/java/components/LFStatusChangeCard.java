package components;

import gui.UIUtils;
import start.StartUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LFStatusChangeCard {
    private LFRoundedPanel content;

    private String teamOwner = "Josh";
    private String status = "Dropped";
    private String player = "Xmithie";
    private static Image playerImage = null;

    static {
        try {
            URL url = LFServerCard.class.getClassLoader().getResource("default_player.png");
            if(url != null) playerImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public LFStatusChangeCard(){

        content = new LFRoundedPanel();
        content.setMaximumSize(new Dimension(140,145));
        content.setMinimumSize(new Dimension(140,145));
        content.setPreferredSize(new Dimension(140,145));
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBackground(UIUtils.greyDark);
        content.setForeground(UIUtils.greyLight);
        //content.setHasShadow(false);

        content.add(Box.createRigidArea(new Dimension(0,5)));

        JLabel img = new JLabel(new ImageIcon(playerImage));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(img);
        content.add(Box.createRigidArea(new Dimension(0,5)));
        content.add(generateJLabel(teamOwner));
        content.add(generateJLabel(status));
        content.add(generateJLabel(player));
    }

    public JPanel getContent() {
        return content;
    }

    private JLabel generateJLabel(String text){
        JLabel label = new JLabel(text);

        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }
}
