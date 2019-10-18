package components;

import gui.FrameManager;
import gui.UIUtils;
import start.StartUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LFAddServerScreen extends LFScreen{



    private static Timer addServerWarningTimer;

    public LFAddServerScreen(){
        super();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBorder(BorderFactory.createMatteBorder(0,0,10,0, UIUtils.grey));
        content.setBackground(UIUtils.grey);

        JLabel title = new JLabel("ADD SERVER");
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nameComps = generateFieldPanel("NAME:");
        JPanel descComps = generateFieldPanel("DESCRIPTION:");
        JPanel ipComps = generateFieldPanel("IP:");

        JPanel iconComps = new JPanel();

        iconComps.setLayout(new BoxLayout(iconComps, BoxLayout.PAGE_AXIS));

        JPanel iconCompsInner = new JPanel();

        iconCompsInner.setLayout(new BoxLayout(iconCompsInner, BoxLayout.LINE_AXIS));

        JLabel icon = new JLabel(new ImageIcon(ServerCard.defaultIcon));
        JLabel iconInner = new JLabel("ICON:");
        JButton changeIcon = new JButton("CHANGE");

        iconComps.setBackground(UIUtils.grey);
        iconCompsInner.setBackground(UIUtils.grey);
        iconInner.setAlignmentX(Component.RIGHT_ALIGNMENT);
        iconInner.setForeground(Color.white);
        changeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        icon.setBorder(BorderFactory.createMatteBorder(0,10,0,0, UIUtils.grey));

        iconComps.setAlignmentX(Component.CENTER_ALIGNMENT);

        iconCompsInner.add(iconInner);
        iconCompsInner.add(icon);
        iconComps.add(iconCompsInner);
        iconComps.add(changeIcon);

        iconCompsInner.setBorder(BorderFactory.createMatteBorder(0,0,10,0, UIUtils.grey));

        changeIcon.setMaximumSize(new Dimension(75, 25));
        changeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeIcon.setBorder(null);
        changeIcon.setIcon(new ImageIcon(UIUtils.createImage(75, 25, UIUtils.greyLight.darker())));
        changeIcon.setForeground(Color.white);
        changeIcon.setHorizontalTextPosition(SwingConstants.CENTER);
        changeIcon.setVerticalTextPosition(SwingConstants.CENTER);
        changeIcon.setBorderPainted(false);
        changeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeIcon.setIcon(new ImageIcon(UIUtils.createImage(75, 25, UIUtils.greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeIcon.setIcon(new ImageIcon(UIUtils.createImage(75, 25, UIUtils.greyLight.darker())));
            }
        });
        changeIcon.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(){
                @Override
                public boolean accept(File f) {
                    if(f.isDirectory()) return true;

                    String ext = null;
                    String name = f.getName();
                    int i = name.lastIndexOf(".");

                    if(i > 0 && i < name.length() - 1) {
                        ext = name.substring(i+1).toLowerCase();
                    }

                    return (ext != null && ext.equals("png"));
                }
            };
            chooser.updateUI();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(chooser);

            try {
                UIManager.setLookAndFeel(MetalLookAndFeel.class.getName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            chooser.showOpenDialog(null);
        });

        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.setBackground(UIUtils.grey);

        JButton back = new JButton("BACK");
        back.setMaximumSize(new Dimension(50, 25));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setBorder(null);
        back.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyLight.darker())));
        back.setForeground(Color.white);
        back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.setVerticalTextPosition(SwingConstants.CENTER);
        back.setBorderPainted(false);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyLight.darker())));
            }
        });

        back.addActionListener(e -> FrameManager.setCurrentContent(new LFSelectServerScreen().getContent()));

        JButton add = new JButton("ADD");
        add.setMaximumSize(new Dimension(50, 25));
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setBorder(null);
        add.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyLight.darker())));
        add.setForeground(Color.white);
        add.setHorizontalTextPosition(SwingConstants.CENTER);
        add.setVerticalTextPosition(SwingConstants.CENTER);
        add.setBorderPainted(false);
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyDark)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add.setIcon(new ImageIcon(UIUtils.createImage(50, 25, UIUtils.greyLight.darker())));
            }
        });
        add.addActionListener(e -> {
            if(((JTextField)nameComps.getComponent(1)).getText().matches("")){
                if(addServerWarningTimer == null){

                    Component area = Box.createRigidArea(new Dimension(0,40));
                    JLabel warning = new JLabel("PLEASE ENTER A NAME");
                    warning.setAlignmentX(Component.CENTER_ALIGNMENT);
                    warning.setForeground(UIUtils.brickRed.brighter());
                    warning.setFont(warning.getFont().deriveFont(20f));
                    addServerWarningTimer = new Timer(0 , al -> {
                        content.remove(area);
                        content.remove(warning);
                        content.revalidate();
                        content.repaint();
                        addServerWarningTimer = null;
                    });
                    addServerWarningTimer.setRepeats(false);
                    addServerWarningTimer.setInitialDelay(2000);
                    addServerWarningTimer.start();
                    content.add(area,content.getComponents().length - 2);
                    content.add(warning, content.getComponents().length - 2);
                    content.revalidate();
                    content.repaint();
                }
            } else {
                createServerFiles(((JTextField)nameComps.getComponent(1)).getText(), ((JTextField)descComps.getComponent(1)).getText(), ((JTextField)ipComps.getComponent(1)).getText(), icon.getIcon());
                FrameManager.setCurrentContent(new LFSelectServerScreen().getContent());
            }
        });

        bottomPanel.add(Box.createRigidArea(new Dimension(238,0)));
        bottomPanel.add(back);
        bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
        bottomPanel.add(add);


        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0,105)));
        content.add(nameComps);
        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.add(ipComps);
        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.add(descComps);

        content.add(Box.createRigidArea(new Dimension(0,10)));
        content.add(iconComps);
        content.add(Box.createVerticalGlue());
        content.add(bottomPanel);
    }

    private JPanel generateFieldPanel(String fieldName){
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBorder(BorderFactory.createMatteBorder(0,10,0,10, UIUtils.grey));
        fieldPanel.setBackground(UIUtils.grey);
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.LINE_AXIS));
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fieldLabel = new JLabel(fieldName);
        JTextField fieldText = new JTextField();

        fieldLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldLabel.setForeground(Color.white);
        fieldLabel.setBorder(BorderFactory.createMatteBorder(0,0,0,5, UIUtils.grey));

        fieldText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        fieldText.setBackground(UIUtils.greyLight);
        fieldText.setForeground(Color.white);
        fieldText.setBorder(BorderFactory.createMatteBorder(1,1,1,1, UIUtils.greyLight));
        fieldText.requestFocus();
        Font f = fieldText.getFont();
        fieldText.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        fieldPanel.add(fieldLabel);
        fieldPanel.add(fieldText);

        return fieldPanel;
    }

    @SuppressWarnings("all")
    public static void createServerFiles(String name, String desc, String ip, Icon icon){
        name = name.trim();
        String folder = StartUp.HOME_PATH + "/" + name;
        File file = new File(folder);
        if(file.exists()) return;
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
