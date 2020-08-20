package components;

import gui.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LFTabPanel {

    private JPanel content;
    private String currentTab;
    private JButton currentButton;

    private static Color defaultColor = UIUtils.lighter(UIUtils.grey.brighter());
    private static Color hoverColor = UIUtils.lighter(defaultColor);
    private static Color clickedColor = UIUtils.darker(defaultColor);

    private static Color borderColor = UIUtils.greyDark;

    private static int buttonWidth = 1276/6;

    public LFTabPanel(String currentTab){
        content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        content.setMaximumSize(new Dimension(1280, 26));
        content.setBackground(UIUtils.grey);
        content.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

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
        stats.setBorder(BorderFactory.createMatteBorder(0,0,1,0,borderColor));
        content.add(stats);

        currentButton.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, clickedColor)));
    }

    private JButton generateTabButton(String text, ActionListener actionListener){
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createMatteBorder(0,0,1,1,borderColor));
        if(currentTab.matches(text.toLowerCase())) currentButton = button;
        if(button.equals(currentButton)){
            button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, clickedColor)));
        } else {
            button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, defaultColor)));
        }
        button.setForeground(Color.white);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        if(actionListener != null) button.addActionListener(actionListener);
        button.addActionListener(e -> {
            currentButton.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, defaultColor)));
            button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, clickedColor)));
            currentButton = button;
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.equals(currentButton)){
                    button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, clickedColor)));
                } else {
                    button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, hoverColor)));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.equals(currentButton)){
                    button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, clickedColor)));
                } else {
                    button.setIcon(new ImageIcon(UIUtils.createImage(buttonWidth, 25, defaultColor)));
                }
            }
        });
        return button;
    }

    public JPanel getContent(){
        return content;
    }
}