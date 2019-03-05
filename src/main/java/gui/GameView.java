package gui;

import logic.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameView extends JPanel {

    private ArrayList<JButton> welcomeButtons = new ArrayList<>();
    private ArrayList<JButton> buttonList = new ArrayList<>(18);
    private ArrayList<JLabel> labelList = new ArrayList<>(18);


    private JLabel computerKazanLabel, playerKazanLabel, timerLabel, upLabel, downLabel,
            gameDetailsTitle;
    private Game controller;


    public GameView(ViewManager windowFrame, Game gameController) {
        controller = gameController;

        windowFrame.setJMenuBar(createMenuBar());
        initUI();
        renameButtons(18);
        renameLabels();
    }


    private void initUI() {

        //Content Panel - master layout
        setLayout(new BorderLayout());


        /* GameView Main Containers Setup*/

        //north
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(100, 250));
        northPanel.setBackground(new Color(100, 149, 237));
        northPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        //center
        JPanel centerPanel = new JPanel();

        //south
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(100, 250));
        southPanel.setBackground(new Color(244, 164, 96));
        southPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        //east
        JPanel eastPanel = new JPanel();

        //Nesting all main containers in the ContentPanel.
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        //Layouts
        northPanel.setLayout(new GridLayout(2, 1));
        southPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setLayout(new GridLayout(1, 3));
        eastPanel.setLayout(new GridLayout(4, 1));



        /* Computer Panel & Content*/

        //Container for holes
        JPanel computerHolesPanel = new JPanel();
        computerHolesPanel.setLayout(new GridLayout());
        computerHolesPanel.setBackground(new Color(100, 149, 237));
        //Container for labels
        JPanel computerLabelsPanel = new JPanel();
        computerLabelsPanel.setLayout(new GridLayout());
        computerLabelsPanel.setBackground(new Color(100, 149, 237));
        //add containers to north panel
        northPanel.add(computerLabelsPanel);
        northPanel.add(computerHolesPanel);
        //TEMPORARY: Coloring of panels



        /* Player Panel & Content*/

        //Container for holes
        JPanel playerHolesPanel = new JPanel();
        playerHolesPanel.setLayout(new GridLayout());
        playerHolesPanel.setBackground(new Color(244, 164, 96));
        //Container for labels
        JPanel playerLabelsPanel = new JPanel();
        playerLabelsPanel.setLayout(new GridLayout());
        playerLabelsPanel.setBackground(new Color(244, 164, 96));
        //add containers to south panel
        southPanel.add(playerHolesPanel);
        southPanel.add(playerLabelsPanel);


        /* Computer Kazan & Content*/

        JPanel kazanComputer = new JPanel();
        kazanComputer.setLayout(new GridLayout(2, 1));
        kazanComputer.setBackground(new Color(100, 149, 237));
        //Label
        JLabel computerKazanTitle = new JLabel("Computer's Kazan:", SwingConstants.CENTER);
        computerKazanLabel = new JLabel("", SwingConstants.CENTER);
        computerKazanLabel.setFont(new Font("Roboto", Font.PLAIN, 64));
        //add containers & content to center panel
        centerPanel.add(kazanComputer);
        kazanComputer.add(computerKazanTitle);
        kazanComputer.add(computerKazanLabel);



        /*Timer and Player's Turn Indicator*/

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(3, 1));
        info.setBackground(new Color(207, 203, 237));
        //Border
        Border border = BorderFactory.createLineBorder(Color.black, 5);
        info.setBorder(border);

        //Labels
        timerLabel = new JLabel("14:59", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Roboto", Font.PLAIN, 44));
        upLabel = new JLabel(new ImageIcon("src/main/resources/up.png"));
        downLabel = new JLabel(new ImageIcon("src/main/resources/down.png"));
        //add containers & content to center panel
        centerPanel.add(info);
        info.add(upLabel);
        info.add(timerLabel);
        info.add(downLabel);


        /* Player Kazan & Content*/

        JPanel kazanPlayer = new JPanel();
        kazanPlayer.setLayout(new GridLayout(2, 1));
        kazanPlayer.setBackground(new Color(244, 164, 96));
        //Label
        JLabel playerKazanTitle = new JLabel("Player's Kazan:", SwingConstants.CENTER);
        playerKazanLabel = new JLabel("", SwingConstants.CENTER);
        playerKazanLabel.setFont(new Font("Roboto", Font.PLAIN, 64));
        //add containers & content to center panel
        centerPanel.add(kazanPlayer);
        kazanPlayer.add(playerKazanTitle);
        kazanPlayer.add(playerKazanLabel);


        /*Create Buttons, Labels & initialise contents */

        //makeHolesButtons(0, 8, playerHolesPanel,true);//player side
        //makeHolesButtons(17, 9, computerHolesPanel,false);// computer side
        makeAllHolesButtons(18, playerHolesPanel, computerHolesPanel);

        makeLabels(0, 8, playerLabelsPanel, true);//player side
        makeLabels(17, 9, computerLabelsPanel, false);//computer side

    }

    private void setTurn(Boolean turn) {
        upLabel.setVisible(!turn);
        downLabel.setVisible(turn);
    }

    public void updateView(int[] getHoleSizesArray, int getPlayerTutz, int getPlayerKazanCount,
                           boolean getPlayerSide, int getComputerTutz, int getComputerKazanCount,
                           boolean getComputerSide, boolean getWhiteSideTurn, boolean hasWon,
                           boolean isDraw, boolean hasLost) {
        setTurn(getWhiteSideTurn);
        playerKazanLabel.setText(getPlayerKazanCount + "");
        computerKazanLabel.setText(getComputerKazanCount + "");


        for (int i = 0; i < getHoleSizesArray.length; i++) {

            buttonList.get(i).setText(getHoleSizesArray[i] + "");

            if (i < 9) {
                System.out.println("Player hole " + (i + 1) + " has " + getHoleSizesArray[i] + " Korgools");
            } else {
                System.out.println("Computer hole " + (i - 8) + " has " + getHoleSizesArray[i] + " Korgools");
            }

        }
        System.out.println("====================");
    }

    /**
     * @param max            , the length of the amount of buttons
     * @param whiteSidePanel - pannel at the bottom
     * @param darkSidePanel  - pannel at the top.
     */
    private void makeAllHolesButtons(int max, Container whiteSidePanel, Container darkSidePanel) {
        for (int i = 0; i < max; i++) {
            JButton button = new JButton();
            int j = i;
            if (i < 9) {
                button.setText("Hole " + (i + 1));
                button.addActionListener(e -> {
                    controller.play(j);
                    System.out.println("Button " + (j + 1) + " was clicked");
                });
                buttonList.add(button);
                whiteSidePanel.add(button);
            } else {
                button.setText("Hole " + (i - 8));
                button.addActionListener(e -> {
                    controller.play(j);
                    System.out.println("Button " + (j + 1) + " was clicked");
                });
                buttonList.add(button);
                darkSidePanel.add(button, 0);
            }
        }
    }

    private void makeLabels(int i, int max, Container currentPanel, boolean order) {
        if (order) {
            for (i = i; i <= max; i++) {

                JLabel label = new JLabel((i + 1) + "", SwingConstants.CENTER);
                labelList.add(label);
                currentPanel.add(label);
            }
        } else {
            int temp = i;
            for (i = i; i >= max; i--) {
                JLabel label = new JLabel((i - (temp / 2)) + "", SwingConstants.CENTER);
                labelList.add(label);
                currentPanel.add(label);
            }

        }
    }

    private static JMenuBar createMenuBar() {

        JMenuBar menuBar;
        JMenu fileMenu, editMenu, windowMenu, helpMenu, helpSubMenu;
        JMenuItem fileMenuItem, editMenuItem, windowMenuItem, helpMenuItem;
        JRadioButtonMenuItem rdMenuItem;

        //Create menuBar.
        menuBar = new JMenuBar();

        //Create file menu.
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription("Game options");
        menuBar.add(fileMenu);

        //Items for File Menu

        fileMenuItem = new JMenuItem("New Game", new ImageIcon("src/main/resources/newGame.png"));
        fileMenuItem.setMnemonic(KeyEvent.VK_G);
        fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.SHIFT_MASK));
        fileMenuItem.getAccessibleContext().setAccessibleDescription("New game");
        fileMenu.add(fileMenuItem);

        fileMenuItem = new JMenuItem("Save Progress", new ImageIcon("src/main/resources/save.png"));
        fileMenuItem.setMnemonic(KeyEvent.VK_P);
        fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.SHIFT_MASK));
        fileMenuItem.getAccessibleContext().setAccessibleDescription("Save game progress");
        fileMenu.add(fileMenuItem);

        fileMenu.addSeparator();
        fileMenuItem = new JMenuItem("Exit", new ImageIcon("src/main/resources/exit.png"));
        fileMenuItem.setMnemonic(KeyEvent.VK_E);
        fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
        fileMenuItem.getAccessibleContext().setAccessibleDescription("Exit Game");
        fileMenuItem.addActionListener(e -> {
            System.exit(0);
        });
        fileMenu.add(fileMenuItem);


        //Create Edit menu.
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.getAccessibleContext().setAccessibleDescription("Game logic");
        menuBar.add(editMenu);

        //Create Window Menu.
        windowMenu = new JMenu("Window");
        windowMenu.setMnemonic(KeyEvent.VK_W);
        windowMenu.getAccessibleContext().setAccessibleDescription("Window sizing options");
        menuBar.add(windowMenu);

        //Radio button menu items for window sizing
        ButtonGroup group = new ButtonGroup();

        rdMenuItem = new JRadioButtonMenuItem("Normal");
        rdMenuItem.setSelected(true);
        rdMenuItem.setMnemonic(KeyEvent.VK_N);
        group.add(rdMenuItem);
        windowMenu.add(rdMenuItem);


        windowMenu.addSeparator();
        rdMenuItem = new JRadioButtonMenuItem("Small", new ImageIcon("src/main/resources/minus.png"));
        rdMenuItem.setMnemonic(KeyEvent.VK_S);
        group.add(rdMenuItem);
        windowMenu.add(rdMenuItem);

        rdMenuItem = new JRadioButtonMenuItem("Large", new ImageIcon("src/main/resources/plus.png"));
        rdMenuItem.setMnemonic(KeyEvent.VK_L);
        group.add(rdMenuItem);
        windowMenu.add(rdMenuItem);


        //Create Help menu.
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription("Game options");
        menuBar.add(helpMenu);

        //Items for File Menu

        helpMenuItem = new JMenuItem("Full User Guide", new ImageIcon("src/main/resources/info.png"));
        helpMenuItem.setMnemonic(KeyEvent.VK_H);
        helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.SHIFT_MASK));
        helpMenuItem.getAccessibleContext().setAccessibleDescription("New game");
        helpMenu.add(helpMenuItem);

        //a submenu
        helpMenu.addSeparator();
        helpSubMenu = new JMenu("Quick Access");
        helpSubMenu.setMnemonic(KeyEvent.VK_S);

        helpMenuItem = new JMenuItem("Getting Started");
        helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.SHIFT_MASK));
        helpSubMenu.add(helpMenuItem);

        helpMenuItem = new JMenuItem("Rules");
        helpSubMenu.add(helpMenuItem);
        helpMenu.add(helpSubMenu);

        helpSubMenu.addSeparator();
        helpMenuItem = new JMenuItem("History");
        helpSubMenu.add(helpMenuItem);
        helpMenu.add(helpSubMenu);

        return menuBar;
    }

    private void renameButtons(int max)
    {
        for(int i = 0; i < max; i++){
            int j=i+1;
            buttonList.get(i).setName("hole"+j);
        }
    }

    private void renameLabels()
    {
        computerKazanLabel.setName("computerKazan");
        playerKazanLabel.setName("playerKazan");
    }

}
