package gui;

import logic.Game;

import javax.swing.*;
import java.awt.*;


public class ViewManager extends JFrame {

    private WelcomeView welcomeView;
    private GameView gameView;
    private Game controller;



    public ViewManager(int width, int height, Game gameController) {
        welcomeView = new WelcomeView();
        gameView = new GameView(this, gameController);
        controller = gameController;
        initUI(width, height);
    }

    private void initUI(int width, int height) {

        setPreferredSize(new Dimension(width, height));
        setTitle("Toguz Korgool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        getJMenuBar().setVisible(false);


        Container content = getContentPane();



        content.add(welcomeView);

        pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);

        setVisible(true);


        welcomeView.welcomeButtons.get(0).addActionListener(e -> {controller.updateView();changePanel(gameView);});
        welcomeView.welcomeButtons.get(4).addActionListener(e -> {setVisible(false);System.exit(0);});


    }

    public GameView getGameView() {
        return gameView;
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        invalidate();
        validate();
        repaint();
        getJMenuBar().setVisible(panel == gameView);
    }





}
