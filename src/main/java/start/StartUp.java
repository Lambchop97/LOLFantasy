package start;

import screen.LFAddServerScreen;
import screen.LFSelectServerScreen;
import gui.FrameManager;
import gui.UIUtils;
import web.LFWebRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class StartUp {

    public static final String HOME_PATH = System.getProperty("user.home") + "/LOLFantasy";

    private static JFrame frame;

    private static JButton startAsServer;
    private static JButton startAsClient;


    public static void main(String[] args) {

        frame = new JFrame("LOLFantasy");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(384, 448));

        frame.setResizable(false);

        FrameManager.registerFrame(frame);
        setViewAsStartButtons();

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


        createDir();
        new LFWebRequest("https://lol.gamepedia.com/LCS/2020_Season/Spring_Season");
    }

    @SuppressWarnings("all")
    public static void setViewAsStartButtons(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,125)));

        startAsClient = new JButton("CLIENT");
        startAsClient.setMaximumSize(new Dimension(200, 50));
        startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsClient.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyLight.darker())));
        startAsClient.setForeground(Color.white);
        startAsClient.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsClient.setVerticalTextPosition(SwingConstants.CENTER);
        startAsClient.setBorderPainted(false);
        startAsClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyLight.darker())));
            }
        });

        startAsClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAsClient();
            }
        });

        panel.add(startAsClient);
        panel.add(Box.createRigidArea(new Dimension(0,75)));

        startAsServer = new JButton("SERVER");
        startAsServer.setMaximumSize(new Dimension(200, 50));
        startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsServer.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyLight.darker())));
        startAsServer.setForeground(Color.white);
        startAsServer.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsServer.setVerticalTextPosition(SwingConstants.CENTER);
        startAsServer.setBorderPainted(false);
        startAsServer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(UIUtils.createImage(200, 50, UIUtils.greyLight.darker())));
            }
        });

        startAsServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameManager.setCurrentContent(new LFAddServerScreen().getContent());
            }
        });

        panel.add(startAsServer);

        panel.setBackground(UIUtils.grey);

        panel.setMaximumSize(new Dimension(384, 448));
        FrameManager.setCurrentContent(panel);
    }

    @SuppressWarnings("all")
    public static void startAsClient(){
        FrameManager.setCurrentContent(new LFSelectServerScreen().getContent());
    }

    @SuppressWarnings("all")
    public static void createDir(){
        new File(System.getProperty("user.home") + "/LOLFantasy").mkdir();
    }
}