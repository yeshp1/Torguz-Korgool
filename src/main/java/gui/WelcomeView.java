package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WelcomeView extends JPanel{

    ArrayList<JButton> welcomeButtons = new ArrayList<JButton>();

    public WelcomeView()
    {

        initView();

        welcomeButtons.get(0).setName("startGame");

    }

    private void initView()
    {

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/main/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image img = myPicture.getScaledInstance(1205, 900, Image.SCALE_SMOOTH);

        JLabel picLabel = new JLabel(new ImageIcon(img));


        picLabel.setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;



        String[] buttonLabels = {"Start Game", "Resume Last Game", "Start Customised Game", "Saved Games", "Tutorial", "Exit"};

        for(int i = 0; i < 6; i++) {
            JButton button = new JButton();
            button.setText(buttonLabels[i]);
            button.setOpaque(false);
            button.setPreferredSize(new Dimension(200, 40));
            welcomeButtons.add(button);
            picLabel.add(button, layoutConstraints);
        }


        add(picLabel);


        //welcomeButtons.get(0).addActionListener(e -> {startFrame.setVisible(false); initUI(height, width);});
        //welcomeButtons.get(1).addActionListener(e -> {startFrame.setVisible(false); initUI(height, width);});
       // welcomeButtons.get(2).addActionListener(e -> { mainContainer.setTitle("Action Accomplished"); });
       // welcomeButtons.get(3).addActionListener(e -> {startFrame.setTitle("Action Accomplished"); });
    }
}
