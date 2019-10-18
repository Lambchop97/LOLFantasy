package components;

import gui.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LFTabPanel {

    private JPanel content;
    private String currentTab;
    private JButton currentButton;

    public LFTabPanel(String currentTab){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        content.setMaximumSize(new Dimension(1280, 26));
        content.setBackground(UIUtils.grey);

        this.currentTab = currentTab;

        JButton overview = generateTabButton("OVERVIEW", null);
        content.add(overview);
        JButton trade = generateTabButton("TRADE", null);
        content.add(trade);
        JButton players = generateTabButton("PLAYERS", null);
        content.add(players);
        JButton roster = generateTabButton("ROSTER", null);
        content.add(roster);
        JButton matchUp = generateTabButton("MATCH UP", null);
        content.add(matchUp);
        JButton stats = generateTabButton("STATS", null);
        stats.setBorder(BorderFactory.createMatteBorder(0,0,1,0,UIUtils.greyDark));
        content.add(stats);

        currentButton.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight)));
    }

    private JButton generateTabButton(String text, ActionListener actionListener){
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createMatteBorder(0,0,1,1,UIUtils.greyDark));
        if(currentTab.matches(text.toLowerCase())) currentButton = button;
        if(button.equals(currentButton)){
            button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyDark)));
        } else {
            button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight.darker())));
        }
        button.setForeground(Color.white);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        if(actionListener != null) button.addActionListener(actionListener);
        button.addActionListener(e -> {
            currentButton.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight.darker())));
            button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight)));
            currentButton = button;
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.equals(currentButton)){
                    button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight)));
                } else {
                    button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyDark)));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.equals(currentButton)){
                    button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight)));
                } else {
                    button.setIcon(new ImageIcon(UIUtils.createImage(1280/6, 25, UIUtils.greyLight.darker())));
                }
            }
        });
        return button;
    }

    public JPanel getContent(){
        return content;
    }
}