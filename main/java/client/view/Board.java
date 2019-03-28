package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends ClientPanel {

    public static final int xSize = 8;
    public static final int ySize = 8;

    private JButton[][] squares = new JButton[xSize][ySize];

    public Board(){
        setLayout(new GridLayout(8 , 8));
        initBoard();
    }



    private void initBoard(){





        for (int i = 0; i < xSize; ++i){
            for(int j = 0; j < ySize; ++j){
                squares[i][j] = new JButton();
                add(squares[i][j]);
            }
        }
    }


    class SquareHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            for (int i = 0; i < xSize; ++i){
                for(int j = 0; j < ySize; ++j){
                    if(squares[i][j] == source){
                        //отдать обработчику нажатий
                    }
                }
            }

        }
    }
}



