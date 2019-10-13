package start;

import components.ServerCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class StartUp {

    public static final String HOME_PATH = System.getProperty("user.home") + "/LOLFantasy";

    private static JFrame frame;

    private static JButton startAsServer;
    private static JButton startAsClient;

    private static JPanel currentPanel;

    @SuppressWarnings("all")
    public static Color greyLight = new Color(150,150,150);
    @SuppressWarnings("all")
    public static Color grey = new Color(65,65,65);
    public static Color greyDark = new Color(48,48,48);
    public static Color brickRed = new Color(178,34,34);

    private static Timer addServerWarningTimer;

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

    @SuppressWarnings("all")
    public static void setViewAsStartButtons(){
        if(currentPanel != null) resetFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,125)));

        startAsClient = new JButton("CLIENT");
        startAsClient.setMaximumSize(new Dimension(200, 50));
        startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsClient.setIcon(new ImageIcon(createImage(200, 50, greyLight.darker())));
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
                startAsClient.setIcon(new ImageIcon(createImage(200, 50, greyLight.darker())));
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
        startAsServer.setIcon(new ImageIcon(createImage(200, 50, greyLight.darker())));
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
                startAsServer.setIcon(new ImageIcon(createImage(200, 50, greyLight.darker())));
            }
        });

        startAsServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setViewAsAddServer();
            }
        });

        panel.add(startAsServer);

        currentPanel = panel;
        panel.setBackground(grey);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void setViewAsSelectServer(){
        if(currentPanel != null) resetFrame();


        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel scrollingContent = new JPanel();

        JLabel screenTitle = new JLabel("SELECT SERVER");
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
                g.drawImage(createImage(32, 32, greyLight.darker()), r.x , r.y, r.width, r.height, null);
                g.dispose();
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.blue);
                g.drawImage(createImage(32, 32, greyLight), r.x , r.y, r.width, r.height, null);
                g.dispose();
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

        if(files != null ) for(File f: files){
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

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createMatteBorder(0,0,10,0, grey));

        JPanel nameComps = new JPanel();
        JPanel ipComps = new JPanel();
        JPanel descComps = new JPanel();

        nameComps.setBorder(BorderFactory.createMatteBorder(0,10,0,10,grey));
        ipComps.setBorder(BorderFactory.createMatteBorder(0,10,0,10,grey));
        descComps.setBorder(BorderFactory.createMatteBorder(0,10,0,10,grey));

        panel.setBackground(grey);
        nameComps.setBackground(grey);
        ipComps.setBackground(grey);
        descComps.setBackground(grey);

        nameComps.setLayout(new BoxLayout(nameComps, BoxLayout.LINE_AXIS));
        ipComps.setLayout(new BoxLayout(ipComps, BoxLayout.LINE_AXIS));
        descComps.setLayout(new BoxLayout(descComps, BoxLayout.LINE_AXIS));

        nameComps.setAlignmentX(Component.CENTER_ALIGNMENT);
        ipComps.setAlignmentX(Component.CENTER_ALIGNMENT);
        descComps.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel name = new JLabel("NAME:");
        JLabel ip = new JLabel("IP:");
        JLabel desc = new JLabel("DESCRIPTION:");
        JTextField nameField = new JTextField();
        JTextField ipField = new JTextField();
        JTextField descField = new JTextField();


        name.setAlignmentX(Component.RIGHT_ALIGNMENT);
        name.setForeground(Color.white);
        name.setBorder(BorderFactory.createMatteBorder(0,0,0,5, grey));

        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        nameField.setBackground(greyLight);
        nameField.setForeground(Color.white);
        nameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,greyLight));
        Font f = nameField.getFont();
        nameField.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        nameComps.add(name);
        nameComps.add(nameField);

        ip.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ip.setForeground(Color.white);
        ip.setBorder(BorderFactory.createMatteBorder(0,0,0,5,grey));

        ipField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        ipField.setBackground(greyLight);
        ipField.setForeground(Color.white);
        ipField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,greyLight));
        f = ipField.getFont();
        ipField.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        ipComps.add(ip);
        ipComps.add(ipField);

        desc.setAlignmentX(Component.RIGHT_ALIGNMENT);
        desc.setForeground(Color.white);
        desc.setBorder(BorderFactory.createMatteBorder(0,0,0,5, grey));

        descField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        descField.setBackground(greyLight);
        descField.setForeground(Color.white);
        descField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,greyLight));
        f = descField.getFont();
        descField.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        descComps.add(desc);
        descComps.add(descField);

        JLabel title = new JLabel("ADD SERVER");
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,105)));
        panel.add(nameComps);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(ipComps);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(descComps);

        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.setBackground(grey);

        JPanel iconComps = new JPanel();

        iconComps.setLayout(new BoxLayout(iconComps, BoxLayout.PAGE_AXIS));

        JPanel iconCompsInner = new JPanel();

        iconCompsInner.setLayout(new BoxLayout(iconCompsInner, BoxLayout.LINE_AXIS));

        JLabel icon = new JLabel(new ImageIcon(ServerCard.defaultIcon));
        JLabel iconInner = new JLabel("ICON:");
        JButton changeIcon = new JButton("CHANGE");

        iconComps.setBackground(grey);
        iconCompsInner.setBackground(grey);
        iconInner.setAlignmentX(Component.RIGHT_ALIGNMENT);
        iconInner.setForeground(Color.white);
        changeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        icon.setBorder(BorderFactory.createMatteBorder(0,10,0,0, grey));

        iconComps.setAlignmentX(Component.CENTER_ALIGNMENT);

        iconCompsInner.add(iconInner);
        iconCompsInner.add(icon);
        iconComps.add(iconCompsInner);
        iconComps.add(changeIcon);

        iconCompsInner.setBorder(BorderFactory.createMatteBorder(0,0,10,0,grey));

        changeIcon.setMaximumSize(new Dimension(75, 25));
        changeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeIcon.setBorder(null);
        changeIcon.setIcon(new ImageIcon(createImage(75, 25, greyLight.darker())));
        changeIcon.setForeground(Color.white);
        changeIcon.setHorizontalTextPosition(SwingConstants.CENTER);
        changeIcon.setVerticalTextPosition(SwingConstants.CENTER);
        changeIcon.setBorderPainted(false);
        changeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeIcon.setIcon(new ImageIcon(createImage(75, 25, greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeIcon.setIcon(new ImageIcon(createImage(75, 25, greyLight.darker())));
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(iconComps);
        panel.add(Box.createVerticalGlue());

        JButton back = new JButton("BACK");
        back.setMaximumSize(new Dimension(50, 25));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setBorder(null);
        back.setIcon(new ImageIcon(createImage(50, 25, greyLight.darker())));
        back.setForeground(Color.white);
        back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.setVerticalTextPosition(SwingConstants.CENTER);
        back.setBorderPainted(false);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(new ImageIcon(createImage(50, 25, greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(new ImageIcon(createImage(50, 25, greyLight.darker())));
            }
        });

        back.addActionListener(e -> setViewAsSelectServer());

        JButton add = new JButton("ADD");
        add.setMaximumSize(new Dimension(50, 25));
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setBorder(null);
        add.setIcon(new ImageIcon(createImage(50, 25, greyLight.darker())));
        add.setForeground(Color.white);
        add.setHorizontalTextPosition(SwingConstants.CENTER);
        add.setVerticalTextPosition(SwingConstants.CENTER);
        add.setBorderPainted(false);
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add.setIcon(new ImageIcon(createImage(50, 25, greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add.setIcon(new ImageIcon(createImage(50, 25, greyLight.darker())));
            }
        });
        add.addActionListener(e -> {
            if(nameField.getText().matches("")){
                if(addServerWarningTimer == null){

                    Component area = Box.createRigidArea(new Dimension(0,40));
                    JLabel warning = new JLabel("PLEASE ENTER A NAME");
                    warning.setAlignmentX(Component.CENTER_ALIGNMENT);
                    warning.setForeground(brickRed.brighter());
                    warning.setFont(warning.getFont().deriveFont(20f));
                    addServerWarningTimer = new Timer(0 , al -> {
                        panel.remove(area);
                            panel.remove(warning);
                            panel.revalidate();
                            panel.repaint();
                            addServerWarningTimer = null;
                    });
                    addServerWarningTimer.setRepeats(false);
                    addServerWarningTimer.setInitialDelay(2000);
                    addServerWarningTimer.start();
                    panel.add(area,panel.getComponents().length - 2);
                    panel.add(warning, panel.getComponents().length - 2);
                    panel.revalidate();
                    panel.repaint();
                }
            } else {
                createServerFiles(nameField.getText(), descField.getText(), ipField.getText(), icon.getIcon());
                setViewAsSelectServer();
            }
        });



        bottomPanel.add(Box.createRigidArea(new Dimension(238,0)));
        bottomPanel.add(back);
        bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
        bottomPanel.add(add);

        panel.add(bottomPanel);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void resetFrame(){
        frame.getContentPane().remove(currentPanel);
        currentPanel = null;

    }

    @SuppressWarnings("all")
    public static void startAsClient(){
        setViewAsSelectServer();
    }

    @SuppressWarnings("all")
    public static void createDir(){
        new File(System.getProperty("user.home") + "/LOLFantasy").mkdir();
    }

    @SuppressWarnings("all")
    static public Image createImage(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }

    @SuppressWarnings("all")
    public static void createServerFiles(String name, String desc, String ip, Icon icon){
        name = name.trim();
        String folder = HOME_PATH + "/" + name;
        File file = new File(folder);
        file.mkdir();

        if(!desc.matches("")) try {
            File descFile = new File(folder + "/desc.txt");
            descFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(folder + "/desc.txt"));
            writer.write(desc);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = image.getGraphics();
        icon.paintIcon(null, g, 0,0);
        g.dispose();

        File iconFile = new File(folder + "/icon.png");
        try {
            ImageIO.write(image, "png", iconFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}