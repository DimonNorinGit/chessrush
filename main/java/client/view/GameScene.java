package client.view;

import javax.swing.*;
import java.awt.*;

public class GameScene extends ClientPanel {
    //сделать отдельными классами
    private JPanel verticalPanel = new JPanel();
    private JPanel gorizontalPanel = new JPanel();


    public GameScene(Window root){
        setBackground(Color.BLUE);

        addPanelWithName("LEFT_BAR" , new LeftBar());
        addPanelWithName("RIGHT_BAR" , new RightBar());
        addPanelWithName("TOP_BAR" , new TopBar());
        addPanelWithName("SIDE_BAR" , new SideBar());
        addPanelWithName("BOARD" , new Board(root));


        gorizontalPanel.setLayout(new BoxLayout(gorizontalPanel , BoxLayout.X_AXIS));
        gorizontalPanel.add(getPanelByName("LEFT_BAR"));
        gorizontalPanel.add(getPanelByName("BOARD"));
        gorizontalPanel.add(getPanelByName("RIGHT_BAR"));


        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        add(getPanelByName("TOP_BAR"));
        add(gorizontalPanel);
        add(getPanelByName("SIDE_BAR"));
    }


    @Override
    public void updateSize(){
        setPreferredSize(new Dimension(SizeConfig.WINDOW_X , SizeConfig.WINDOW_Y));
        for(String name : getChildrenNames()){
            getPanelByName(name).updateSize();
        }
    }

}
