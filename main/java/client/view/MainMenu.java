package client.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu extends ClientPanel {

    private JButton playButton;
    private JButton exitButton;
    private JButton configButton;


    public MainMenu(Window root){
        playButton = new JButton(StringConstants.PLAY_TEXT);
        exitButton = new JButton(StringConstants.EXIT_TEXT);
        configButton = new JButton(StringConstants.CONFIG_TEXT);

        Dimension d = new Dimension(SizeConfig.MENU_BUTTONS_X , SizeConfig.MENU_BUTTONS_Y);

        //Color buttonColor = new Color(250, 250, 210);
        //Color textColor =  new Color(105, 105, 105);
        for(JButton b : new JButton[]{playButton , exitButton , configButton}){
            b.setMinimumSize(d);
            b.setPreferredSize(d);
            b.setMaximumSize(d);
            b.setBackground(ColorsSet.MENU_BUTTONS);
            b.setForeground(ColorsSet.MENU_TEXT_COLOR);
        }


        //Container contentPain = getRootPane();
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        add(playButton);
        add(configButton);
        add(exitButton);
        setBackground(Color.black);
        playButton.addActionListener(e -> root.changePanel("GAME_SCENE"));
        exitButton.addActionListener(e ->root.exit());
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        ImageIcon icon =  (ImageIcon)IconSet.getIconByName("mainMenuBackground");
        g.drawImage(icon.getImage(), 0, 0, this);
    }

    @Override
    public void updateSize(){
        setPreferredSize(new Dimension(SizeConfig.MENU_X , SizeConfig.MENU_Y));
    }

}
