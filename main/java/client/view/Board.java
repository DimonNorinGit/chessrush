package client.view;

import client.Connect;
import client.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board extends ClientPanel {

    public static final int xSize = 8;
    public static final int ySize = 8;
    private Window root;

    //private JButton[][] squares = new BoardButton[xSize][ySize];
    private List<BoardButton> squares = new LinkedList<>();

    public Board(Window root){
        this.root = root;
        GridLayout gl = new GridLayout(8 , 8);
        setSize(SizeConfig.BOARD_X , SizeConfig.BOARD_Y);
        gl.preferredLayoutSize(this);
        setLayout(gl);
        initBoard();
        updateSize();
    }

    public void updateSize(){
        setSize(SizeConfig.BOARD_X , SizeConfig.BOARD_Y);
        for (int i = 0; i < xSize; ++i){
            for(int j = 0; j < ySize; ++j){
                squares.get(i * ySize + j).setSize(SizeConfig.SQUARE_X , SizeConfig.SQUARE_Y);
            }
        }
    }

    private void initBoard(){
        SquareHandler handler = new SquareHandler();
        root.getConnector().connectTo("FLIP_BOARD" , new FlipConsumer());
        for (int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){
                squares.add(new BoardButton(new Point(j , i)));
                Color clr = null;
                if((i + j) % 2 == 0){
                    clr = ColorsSet.SQUARE_WHITE;
                }else{
                    clr = ColorsSet.SQUARE_BLACK;
                }
                JButton b = squares.get(i * xSize + j);
                b.setSize(SizeConfig.SQUARE_X , SizeConfig.SQUARE_Y);
                b.setBackground(clr);
                //squares[i][j].setIcon(new ImageIcon(IconsPaths.KNIGHT));
                b.addActionListener(handler);
                root.getConnector().connectTo("SQUARE" + i + j , (Consumer)b);
                add(b);
            }
        }
    }

    private class FlipConsumer implements Consumer{
        @Override
        public void update(Connect connect) {
            if(connect.getProperty("IS_FLIPPED") == null){
                return;
            }
            boolean is_flipped = (Boolean) connect.getProperty("IS_FLIPPED");
            if(is_flipped){
                flip();
            }

            /*
            String side = (String)connect.getProperty("CURRENT_SIDE");
            if(side.compareTo("NORMAL") == 0){

            }else if(side.compareTo("FLIPPED") == 0){

            }*/
        }

        private void flip(){
            removeAll();
            Collections.reverse(squares);
            for (int i = 0; i < ySize; ++i){
                for(int j = 0; j < xSize; ++j){
                    add(squares.get(i * xSize + j));
                }
            }
        }
    }



     private class SquareHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            EventDispatcher dispatcher =  root.getEventDispatcher();
            for(BoardButton button : squares ) {
                if (button == source) {

                    Point pos = button.getPosition();
                    dispatcher.processClick(pos.getX(), pos.getY());

                    //System.out.println(i + " " + j);
                    //отдать обработчику нажатий
                }
            }
        }
    }
}



