package client.view;

import client.Connect;
import client.controller.MVController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PieceElector extends JFrame {

    private JPanel panel  = new JPanel();
    private List<Cell> options = new ArrayList<>();
    private MVController controller;
    private Window root;

    public PieceElector(MVController controller , Window root){
        this.root = root;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;
        controller.getConnector().connectTo("ELECTOR" , new Visability());
        panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));
        init();
        updateSize();
        add(panel);
    }

    private void init(){
        int i = 0;
        for (String pieceName : new String[]{"Rook" , "Queen" , "Knight" , "Bishop"}){
            Cell b = new Cell(pieceName);
            options.add(b);
            b.setIcon(IconSet.getChessIcon(pieceName , "White" ));
            Dimension d = new Dimension(SizeConfig.SQUARE_X , SizeConfig.SQUARE_Y);
            b.setPreferredSize(d);
            b.addActionListener(e -> {
                for (Cell c : options){
                    if(c == e.getSource()){
                        actionPerformer(c.getName());
                        break;
                    }
                }
            });
            panel.add(b);
        }
    }


    private void actionPerformer(String chosenPiece){
        System.out.println("DONE");
        controller.transformPiece(chosenPiece);
        controller.getConnector().updateConnections();
        setVisible(false);
    }

    public void updateSize(){
        setSize(SizeConfig.PIECE_PICKER_X , SizeConfig.PIECE_PICKER_Y);
        //getContentPane().setSize(SizeConfig.WINDOW_X , SizeConfig.WINDOW_Y);
        setResizable(false);
    }

    private class Visability implements Consumer{
        @Override
        public void update(Connect connect) {
            boolean isVisible = (Boolean) connect.getProperty("IS_VISIBLE");
            if(isVisible){
                setVisible(true);
                setLocation(root.getLocation());
            }
        }
    }

    private class Cell extends JButton{
        private String name;

        public Cell(String pieceName){
            name = pieceName;
        }

        public String getName(){
            return name;
        }

    }

}
