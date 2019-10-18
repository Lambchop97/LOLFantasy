package components;

import gui.UIColors;
import start.StartUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ServerCard extends JLayeredPane {

    private String name;
    private String description;

    private JLabel image;

    public static BufferedImage defaultIcon;

    private JPanel content;

    private Timer deleteAnimationTimer;
    private Timer resetDeleteTimer;

    int r = UIColors.brickRed.darker().getRed();
    int g = UIColors.brickRed.darker().getGreen();
    int b = UIColors.brickRed.darker().getBlue();

    private boolean deleteMode = false;
    private boolean canDelete = false;

    static {
        try {
            URL url = ServerCard.class.getClassLoader().getResource("icon.png");
            if(url != null) defaultIcon = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerCard(){
        this.setPreferredSize(new Dimension(333, 64));
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setMinimumSize(new Dimension(0, 64));
        content.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        content.setBackground(UIColors.greyDark);
        name = "new server";
        description = "template";

        image = null;

        try {
            URL url = ServerCard.class.getClassLoader().getResource("plus.png");
            image = new JLabel(new ImageIcon(ImageIO.read(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(image == null)
            System.out.println("img is null");
        else{
            image.setPreferredSize(new Dimension(32, 32));
            image.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            content.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    StartUp.setViewAsAddServer();
                }
            });

            content.add(Box.createRigidArea(new Dimension(0, 16)));
            content.add(image);
            content.setBounds(0,0,333,64);
            this.add(content, Integer.valueOf(1));
        }
    }

    public ServerCard(String path){
        this.setPreferredSize(new Dimension(333, 64));
        name = path.split("\\\\")[path.split("\\\\").length - 1];
        description = "This is a default description";

        if(path.matches("")) return;


        if(new File(path + "/desc.txt").exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path+"/desc.txt"));
                description = reader.readLine();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        image = null;
        readIcon(path + "/icon.png");

        if(image == null) {
            System.out.println("img is null");
        } else{
            content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
            content.setBackground(UIColors.greyDark);
            content.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
            content.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if(!deleteMode)
                    System.out.println(name + " has been clicky clicked");
                }
            });

            content.add(Box.createRigidArea(new Dimension(16, 0)));
            content.add(image);
            content.add(Box.createRigidArea(new Dimension(16, 0 )));

            JPanel text = new JPanel();

            text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));

            JLabel nameLabel = new JLabel(name);
            JLabel discLabel = new JLabel(description);

            text.setBackground(UIColors.greyDark);
            nameLabel.setForeground(Color.white);
            discLabel.setForeground(Color.white);

            text.add(Box.createRigidArea(new Dimension(0, 12)));
            text.add(nameLabel);
            text.add(Box.createRigidArea(new Dimension(0, 4)));
            text.add(discLabel);
            text.add(Box.createRigidArea(new Dimension(0, 12)));
            text.setBorder(BorderFactory.createMatteBorder(0,0,10,0,UIColors.greyDark));

            content.add(text);
            content.add(Box.createHorizontalGlue());

            JPanel deletePanel = new JPanel();

            deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));

            JLabel delete = new JLabel("X");
            deletePanel.setBackground(UIColors.greyDark);


            JPanel test = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(getBackground());
                    Rectangle r = g.getClipBounds();
                    g.fillRect(r.x,r.y,r.width,r.height);
                    super.paintComponent(g);
                }
            };
            test.setBounds(333,0,0,64);
            test.setBackground(new Color(178, 34 ,34, 255));
            test.setOpaque(false);

            JLabel testDelete = new JLabel("DELETE");
            testDelete.setFont(testDelete.getFont().deriveFont(22f));
            testDelete.setBounds(166,0,60,40);
            testDelete.setForeground(UIColors.brickRed.darker());

            testDelete.setAlignmentX(CENTER_ALIGNMENT);

            test.add(testDelete);

            resetDeleteTimer = new Timer(2, e -> {
               int alpha = test.getBackground().getAlpha();
               alpha -= 2;
               if(alpha <= 0){
                   alpha = 0;
                   resetDeleteTimer.stop();
                   test.setBounds(333, 0, 0, 64);
                   deleteMode = false;
               }
               test.setBackground(new Color(178,34,34, alpha));
               testDelete.setForeground(new Color(r,g,b,alpha));
               canDelete = false;

            });
            resetDeleteTimer.setInitialDelay(2000);



            deleteAnimationTimer = new Timer(2, e -> {
                int width = test.getWidth();
                width += 2;
                if(width >= 333){
                    width = 333;
                    deleteAnimationTimer.stop();
                    resetDeleteTimer.start();
                    canDelete = true;
                }
                test.setBounds(333 - width, 0, width, 64);
                testDelete.setBounds(125,10,160,40);
            });
            deleteAnimationTimer.setInitialDelay(100);

            test.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                     if(canDelete){
                         delete();
                         StartUp.setViewAsSelectServer();
                         deleteAnimationTimer.stop();
                         resetDeleteTimer.stop();
                     }
                }
            });


            delete.setForeground(UIColors.brickRed);
            delete.setAlignmentY(TOP_ALIGNMENT);
            delete.setFont(delete.getFont().deriveFont(18f));
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if(!deleteMode){
                        System.out.println("delete");

                        test.setBounds(333, 0, 0, 64);
                        test.setBackground(new Color(178,34,34,255));
                        testDelete.setForeground(UIColors.brickRed.darker());
                        deleteAnimationTimer.restart();
                        deleteMode = true;
                    }
                }
            });

            deletePanel.add(delete);
            deletePanel.setBorder(BorderFactory.createMatteBorder(0,0,0,5,new Color(48,48,48)));

            content.add(deletePanel);

            content.setBounds(0,0, 333, 64);
            this.add(content, Integer.valueOf(1));


            this.add(test, Integer.valueOf(2));
            this.revalidate();
            this.repaint();
        }
    }

    private void readIcon(String path){
        BufferedImage img = null;


        try {
            File file = new File(path);
            if(file.exists())
                img = ImageIO.read(file);
            else
                img = defaultIcon;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(img == null) return;
        image = new JLabel(new ImageIcon(img));
        image.setPreferredSize(new Dimension(32, 32));
        image.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    private void delete(){
        File file = new File(StartUp.HOME_PATH + "/" + name);

        deleteFile(file);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void deleteFile(File file){
        File[] files = file.listFiles();
        if(files != null) for(File f: files){
            deleteFile(f);
        }
        file.delete();
    }

}
