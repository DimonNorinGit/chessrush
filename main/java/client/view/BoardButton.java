package client.view;

import client.Connect;
import client.Point;

import javax.swing.*;

public class BoardButton extends JButton implements Consumer {

    private Point pos;


    public BoardButton(Point pos){
        this.pos = pos;
    }

    public Point getPosition(){
        return pos;
    }


    @Override
    public void update(Connect connect) {
        if(connect.getProperty("PIECE")==null){
            setIcon(null);
            return;
        }
        String pType = (String)connect.getProperty("PIECE");
        String clr = (String)connect.getProperty("PIECE_COLOR");
        setIcon(IconSet.getChessIcon(pType , clr));
    }
}
