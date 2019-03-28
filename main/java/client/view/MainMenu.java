package client.view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends ClientPanel {

    private JButton playButton;
    private JButton exitButton;
    private JButton configButton;


    public MainMenu(Window root){
        playButton = new JButton(StringConstants.PLAY_TEXT);
        exitButton = new JButton(StringConstants.EXIT_TEXT);
        configButton = new JButton(StringConstants.CONFIG_TEXT);
        //Container contentPain = getRootPane();
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        add(playButton);
        add(configButton);
        add(exitButton);
        setBackground(Color.cyan);


        playButton.addActionListener(e -> root.changePanel("GAME_SCENE"));
        exitButton.addActionListener(e ->root.exit());



    }

}
