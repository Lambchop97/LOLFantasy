package components;

import start.StartUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class ServerCard extends JPanel {

    private String name;
    private String description;

    private JLabel image;

    private static BufferedImage defaultIcon;

    static {
        try {
            defaultIcon = ImageIO.read(ServerCard.class.getClassLoader().getResource("plus.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerCard(String path){
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


        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setMinimumSize(new Dimension(0, 64));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        this.setBackground(StartUp.greyDark);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));

        BufferedImage img = null;

        try {
            if(new File(path + "/Icon.png").exists())
                img = ImageIO.read(new File(path + "/Icon.png"));
            else
                img = defaultIcon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(img == null) {
            System.out.println("img is null");
        }
        else{
            image = new JLabel(new ImageIcon(img));
            image.setPreferredSize(new Dimension(32, 32));
            image.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            image.setVisible(true);

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println(name + " has been clicky clicked");
                }
            });

            this.add(Box.createRigidArea(new Dimension(16, 0)));
            this.add(image);
            this.add(Box.createRigidArea(new Dimension(16, 0 )));

            JPanel text = new JPanel();

            text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));

            JLabel nameLabel = new JLabel(name);
            JLabel discLabel = new JLabel(description);

            text.setBackground(StartUp.greyDark);
            nameLabel.setForeground(Color.white);
            discLabel.setForeground(Color.white);

            text.add(Box.createRigidArea(new Dimension(0, 12)));
            text.add(nameLabel);
            text.add(Box.createRigidArea(new Dimension(0, 4)));
            text.add(discLabel);
            text.add(Box.createRigidArea(new Dimension(0, 12)));

            this.add(text);
            this.add(Box.createHorizontalGlue());

            JPanel deletePanel = new JPanel();

            deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));

            JLabel delete = new JLabel("X");
            deletePanel.setBackground(StartUp.greyDark);

            delete.setForeground(StartUp.brickRed);
            delete.setAlignmentY(TOP_ALIGNMENT);
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("delete");
                }
            });

            deletePanel.add(delete);
            deletePanel.setBorder(BorderFactory.createMatteBorder(0,0,0,5,new Color(48,48,48)));

            this.add(deletePanel);
        }
    }


    public void switchToNewServerCard(){
        this.removeAll();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        name = "new server";
        description = "template";



        if(defaultIcon == null)
            System.out.println("img is null");
        else{
            image = new JLabel(new ImageIcon(defaultIcon));
            image.setPreferredSize(new Dimension(32, 32));
            image.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            image.setVisible(true);

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    StartUp.setViewAsAddServer();
                }
            });

            this.add(Box.createRigidArea(new Dimension(0,16)));
            this.add(image);
            this.add(Box.createRigidArea(new Dimension(0,12)));
            this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
            this.setBackground(new Color(48, 48, 48));
        }
    }

}
