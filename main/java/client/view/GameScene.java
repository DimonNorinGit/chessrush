package client.view;

import client.Connect;

import javax.swing.*;
import java.awt.*;

public class GameScene extends ClientPanel {
    //сделать отдельными классами
    private JPanel verticalPanel = new JPanel();
    private JPanel horizontalPanel = new JPanel();
    private PieceElector elector;

    public GameScene(Window root){
        setBackground(Color.BLUE);

        elector = new PieceElector(root.getController() , root);

        addPanelWithName("LEFT_BAR" , new LeftBar());
        addPanelWithName("RIGHT_BAR" , new RightBar());
        addPanelWithName("TOP_BAR" , new TopBar());
        addPanelWithName("SIDE_BAR" , new SideBar());
        addPanelWithName("BOARD" , new Board(root));


        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(getPanelByName("LEFT_BAR"));
        horizontalPanel.add(getPanelByName("BOARD"));
        horizontalPanel.add(getPanelByName("RIGHT_BAR"));


        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        add(getPanelByName("TOP_BAR"));
        add(horizontalPanel);
        add(getPanelByName("SIDE_BAR"));
        root.getConnector().connectTo("GAME_SCENE", new ColorConsumer());
    }

    private class ColorConsumer implements Consumer{

        @Override
        public void update(Connect connect) {
            String  clr = (String)connect.getProperty("CURRENT_COLOR");
            JPanel p = getPanelByName("TOP_BAR");
            if("WHITE".equals(clr)){
                p.setBackground(ColorsSet.TOP_BAR_WHITE);
            }else p.setBackground(ColorsSet.TOP_BAR_BLACk);
        }
    }


    @Override
    public void updateSize(){
        setPreferredSize(new Dimension(SizeConfig.WINDOW_X , SizeConfig.WINDOW_Y));
        for(String name : getChildrenNames()){
            getPanelByName(name).updateSize();
        }
    }

}
