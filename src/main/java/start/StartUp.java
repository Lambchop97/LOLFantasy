package start;

import components.ServerCard;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.plaf.metal.MetalTextFieldUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static Color greyLight = new Color(150,150,150);
    public static Color grey = new Color(65,65,65);
    public static Color greyDark = new Color(48,48,48);
    public static Color brickRed = new Color(178,34,34);

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
        if(currentPanel != null) resetFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,125)));

        startAsClient = new JButton("Client");
        startAsClient.setMaximumSize(new Dimension(200, 50));
        startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsClient.setIcon(new ImageIcon(createImage(200, 50, grey)));
        startAsClient.setForeground(Color.white);
        startAsClient.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsClient.setVerticalTextPosition(SwingConstants.CENTER);
        startAsClient.setBorderPainted(false);
        startAsClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(createImage(200, 50, greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsClient.setIcon(new ImageIcon(createImage(200, 50, grey)));
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

        startAsServer = new JButton("Server");
        startAsServer.setMaximumSize(new Dimension(200, 50));
        startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsServer.setIcon(new ImageIcon(createImage(200, 50, grey)));
        startAsServer.setForeground(Color.white);
        startAsServer.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsServer.setVerticalTextPosition(SwingConstants.CENTER);
        startAsServer.setBorderPainted(false);
        startAsServer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(createImage(200, 50, greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startAsServer.setIcon(new ImageIcon(createImage(200, 50, grey)));
            }
        });

        startAsServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("It worked");
            }
        });

        panel.add(startAsServer);

        currentPanel = panel;
        panel.setBackground(greyLight);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void setViewAsSelectServer(){
        if(currentPanel != null) resetFrame();


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
                ((Graphics2D) g).drawImage(createImage(32, 32, greyDark), r.x , r.y, r.width, r.height, null);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.blue);
                ((Graphics2D) g).drawImage(createImage(32, 32, greyLight), r.x , r.y, r.width, r.height, null);
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


        panel.setBackground(grey);

        currentPanel = panel;

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void setViewAsAddServer(){
        if(currentPanel != null) resetFrame();

        JPanel panel = new JPanel();

        currentPanel = panel;

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        JPanel labels = new JPanel();
        JPanel textFields = new JPanel();

        labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.PAGE_AXIS));

        JLabel name = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ip = new JLabel("IP:");
        JTextField ipField = new JTextField();

        name.setAlignmentX(Component.RIGHT_ALIGNMENT);
        name.setForeground(Color.white);
        ip.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ip.setForeground(Color.white);

        labels.add(name);
        labels.add(Box.createRigidArea(new Dimension(0,15)));
        labels.add(ip);

        labels.setBorder(BorderFactory.createMatteBorder(0,5,0,6,grey));
        textFields.setBorder(BorderFactory.createMatteBorder(0,0,0,5,grey));
        panel.setBackground(grey);
        labels.setBackground(grey);
        textFields.setBackground(grey);


        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        nameField.setBackground(greyLight);
        nameField.setForeground(Color.white);
        nameField.setBorder(BorderFactory.createMatteBorder(3,2,1,2,greyLight));
        ipField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        ipField.setBackground(greyLight);
        ipField.setForeground(Color.white);
        ipField.setBorder(BorderFactory.createMatteBorder(3,2,1,2,greyLight));

        textFields.add(nameField);
        textFields.add(Box.createRigidArea(new Dimension(0, 10)));
        textFields.add(ipField);

        panel.add(labels);
        panel.add(textFields);

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

    static public Image createImage(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }
}