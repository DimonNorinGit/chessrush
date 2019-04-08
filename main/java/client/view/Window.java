package client.view;

import client.Connect;
import client.Connector;
import client.controller.MVController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {

    private JPanel rootPanel;
    private CardLayout cardLayout;
    private Map<String , ClientPanel> screens;
    private String currentScreenName = null;
    private Connector connector;
    private EventDispatcher eventDispatcher;

    public Window(MVController controller){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IconSet.loadIcons();

        eventDispatcher = new EventDispatcher(controller);
        screens = new HashMap<>();
        this.connector = controller.getConnector();
        screens.put("MAIN_MENU" , new MainMenu(this));
        screens.put("GAME_SCENE" , new GameScene(this));

        rootPanel = new JPanel();
        cardLayout = new CardLayout();
        rootPanel.setLayout(cardLayout);

        rootPanel.add(screens.get("MAIN_MENU") , "MAIN_MENU");
        rootPanel.add(screens.get("GAME_SCENE") , "GAME_SCENE");

        currentScreenName = "MAIN_MENU";
        cardLayout.show(rootPanel , "MAIN_MENU");
        add(rootPanel);
        updateSize();
    }

    public ClientPanel getPanelByName(String name){
        return screens.get(name);
    }

    public void changePanel(String name){
        currentScreenName = name;
        updateSize();
        cardLayout.show(rootPanel , name);
    }


    public EventDispatcher getEventDispatcher(){
        return eventDispatcher;
    }

    public void updateSize(){
        setSize(SizeConfig.WINDOW_X , SizeConfig.WINDOW_Y);
        getContentPane().setSize(SizeConfig.WINDOW_X , SizeConfig.WINDOW_Y);
        setResizable(false);
        screens.get(currentScreenName).updateSize();
    }

    public Connector getConnector(){
        return connector;
    }

    public void exit(){
        setVisible(false);
        dispose();
    }
}
