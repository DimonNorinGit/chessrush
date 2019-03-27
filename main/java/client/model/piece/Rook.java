package client.model.piece;

import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.Point;

import java.util.Set;

public  class Rook  extends Piece{


    public Rook(){
        super("Rook");
    }

    @Override
    protected void findAttackedSquares(ChessBoard board, Point pos, Set<BoardSquare> attackedSquares) {
        int x = pos.getX() , y = pos.getY();
        Point[] moveDirs = {new Point(0,1) , new Point(1,0)
                , new Point(0,-1) , new Point(-1,0)};
       checkContinuousPaths(moveDirs , board , x , y);
    }

    /*@Override
    public boolean checkMove(int x, int y, int nx, int ny) {
        return (x == nx) || (y == ny);
    }

    @Override
    public boolean checkSpecialMove(int x, int y, int nx, int ny, boolean hasPiece) {
        return checkMove(x ,y , nx , ny);
    }*/

    @Override
    public String toString() {
        return "Rook";
    }
}
