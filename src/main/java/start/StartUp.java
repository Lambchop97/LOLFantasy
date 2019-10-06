package start;

import components.ServerCard;
import io.CSVFile;
import listeners.JButtonActionListeners;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class StartUp {

    public static final String HOME_PATH = System.getProperty("user.home") + "/LOLFantasy";

    private static JFrame frame;

    private static JButton startAsServer;
    private static JButton startAsClient;

    private static JPanel currentPanel;

    public static void main(String[] args) {

        frame = new JFrame("LOLFantasy");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(384, 448));

        frame.setResizable(false);

        setViewAsStartButtons();

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


        createDir();
    }

    private static void setViewAsStartButtons(){

        if(currentPanel != null){
            resetFrame();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,125)));

        startAsClient = new JButton("Client");
        startAsClient.setMaximumSize(new Dimension(200, 50));
        startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsClient.setIcon(new ImageIcon(createImage(200, 50, new Color(65,65,65))));
        startAsClient.setForeground(Color.white);
        startAsClient.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsClient.setVerticalTextPosition(SwingConstants.CENTER);
        startAsClient.setBorderPainted(false);
        startAsClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(createImage(200, 50, new Color(48,48,48))));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(createImage(200, 50, new Color(65,65,65))));
            }
        });

        startAsClient.addActionListener(JButtonActionListeners.getClientStartActionListener());

        panel.add(startAsClient);
        panel.add(Box.createRigidArea(new Dimension(0,75)));

        startAsServer = new JButton("Server");
        startAsServer.setMaximumSize(new Dimension(200, 50));
        startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsServer.setIcon(new ImageIcon(createImage(200, 50, new Color(65,65,65))));
        startAsServer.setForeground(Color.white);
        startAsServer.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsServer.setVerticalTextPosition(SwingConstants.CENTER);
        startAsServer.setBorderPainted(false);
        startAsServer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(createImage(200, 50, new Color(48,48,48))));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(createImage(200, 50, new Color(65,65,65))));
            }
        });

        startAsServer.addActionListener(JButtonActionListeners.getServerStartActionListener());

        panel.add(startAsServer);

        currentPanel = panel;
        panel.setBackground(new Color(150,150,150));
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void setViewAsSelectServer(){
        if(currentPanel != null){
            resetFrame();
        }

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel scrollingContent = new JPanel();

        JLabel screenTitle = new JLabel("Select Server");
        screenTitle.setForeground(Color.white);
        screenTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(screenTitle);
        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JScrollPane list = new JScrollPane(scrollingContent);
        list.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));
        list.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
                ((Graphics2D) g).drawImage(createImage(32, 32, new Color(48,48,48)), r.x , r.y, r.width, r.height, null);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.blue);
                ((Graphics2D) g).drawImage(createImage(32, 32, new Color(150,150,150)), r.x , r.y, r.width, r.height, null);
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

        ServerCard newCard = new ServerCard("");
        newCard.switchToNewServerCard();

        scrollingContent.setLayout(new BoxLayout(scrollingContent, BoxLayout.PAGE_AXIS));

        File home = new File(HOME_PATH);

        File[] files = home.listFiles();

        for(File f: files){
            if(f.isDirectory()){
                ServerCard card = new ServerCard(f.getAbsolutePath());
                scrollingContent.add(card);
            }
        }
        scrollingContent.add(newCard);

        scrollingContent.revalidate();
        scrollingContent.repaint();

        panel.add(list);
        panel.add(Box.createRigidArea(new Dimension(0,9)));


        panel.setBackground(new Color(65,65,65));

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void resetFrame(){
        frame.getContentPane().remove(currentPanel);
        currentPanel = null;

    }

    public static void startAsClient(){
        setViewAsSelectServer();
    }

    @SuppressWarnings("all")
    public static void createDir(){
        new File(System.getProperty("user.home") + "/LOLFantasy").mkdir();
    }

    static private Image createImage(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }
}
