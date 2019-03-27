package client.model.piece;

import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.Point;

import java.util.Set;;

public class King  extends Piece{

    public King(){
        super("King");
    }

    @Override
    public void findAttackedSquares(ChessBoard board , Point pos , Set<BoardSquare> attackedSquares) {
        int x = pos.getX() , y = pos.getY();
        for(int r = 0; r < 3; ++r){
            for(int c =0; c < 3; ++c){
                int moveX = x + r - 1 , moveY = y + c - 1;
                addAttackedSquare(board , moveX , moveY);
            }
        }
    }

    /*@Override
    public boolean checkSpecialMove(int x, int y, int nx, int ny, boolean hasPiece) {
        return checkMove(x ,y , nx , ny);
    }

    @Override
    public boolean checkMove(int x, int y, int nx, int ny) {
        return  ((x - 1) <= nx && nx <= (x + 1)) && ((y - 1) <= ny && ny <= (y + 1));
    }*/

    @Override
    public String toString() {
        return "King";
    }
}
