package client.view;

import javax.swing.*;
import java.awt.*;

public class GameScene extends ClientPanel {
    //сделать отдельными классами
    private JPanel verticalPanel = new JPanel();
    private JPanel gorizontalPanel = new JPanel();


    public GameScene(Window root){
        setBackground(Color.BLUE);

        addComponentWithName("LEFT_BAR" , new LeftBar());
        addComponentWithName("RIGHT_BAR" , new RightBar());
        addComponentWithName("TOP_BAR" , new TopBar());
        addComponentWithName("SIDE_BAR" , new SideBar());
        addComponentWithName("BOARD" , new Board());


        gorizontalPanel.setLayout(new BoxLayout(gorizontalPanel , BoxLayout.X_AXIS));
        gorizontalPanel.add(getComponentByName("LEFT_BAR"));
        gorizontalPanel.add(getComponentByName("BOARD"));
        gorizontalPanel.add(getComponentByName("RIGHT_BAR"));

        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        add(getComponentByName("TOP_BAR"));
        add(gorizontalPanel);
        add(getComponentByName("SIDE_BAR"));
    }

}
