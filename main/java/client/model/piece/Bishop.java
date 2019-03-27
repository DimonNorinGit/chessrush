package client.model.piece;

import client.model.Point;
import client.model.board.ChessBoard;
import client.model.board.BoardSquare;



import java.util.Set;

public class Bishop extends Piece {

    public Bishop(){
        super("Bishop");
    }

    @Override
    protected void findAttackedSquares(ChessBoard board , Point pos , Set<BoardSquare> attackedSquares) {
        int x = pos.getX() , y = pos.getY();
        Point[] moveDirs = {new Point(1,1) , new Point(-1,1)
                , new Point(1,-1) , new Point(-1,-1)};
        checkContinuousPaths(moveDirs , board , x , y);
    }

    /*@Override
    public boolean checkMove(int x, int y, int nx, int ny) {
        return Math.abs(x - nx) == Math.abs(y - ny);
    }*/


    /*@Override
    public boolean checkSpecialMove(int x, int y, int nx, int ny, boolean hasPiece) {
        return checkMove(x ,y , nx , ny);
    }*/

    @Override
    public String toString() {
        return "Bishop";
    }
}

