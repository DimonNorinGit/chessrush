package client.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {

    private JPanel rootPanel;
    CardLayout cardLayout;
    Map<String , Component> screens;

    public Window(int width , int height){
        setSize(width , height);
        screens = new HashMap<>();
        screens.put("MAIN_MENU" , new MainMenu(this));
        screens.put("GAME_SCENE" , new GameScene(this));


        rootPanel = new JPanel();
        cardLayout = new CardLayout();
        rootPanel.setLayout(cardLayout);

        rootPanel.add(screens.get("MAIN_MENU") , "MAIN_MENU");
        rootPanel.add(screens.get("GAME_SCENE") , "GAME_SCENE");
        cardLayout.show(rootPanel , "MAIN_MENU");
        add(rootPanel);
    }

    public Component getPanelByName(String name){
        return screens.get(name);
    }

    public void changePanel(String name){
        cardLayout.show(rootPanel , name);
    }

    public void exit(){
        setVisible(false);
        dispose();
    }
}
