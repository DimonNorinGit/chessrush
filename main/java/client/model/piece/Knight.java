package client.model.piece;

import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.Point;

import java.util.Set;

public class Knight extends Piece {


    public Knight(){
        super("Knight");
    }


    @Override
    protected void findAttackedSquares(ChessBoard board, Point pos, Set<BoardSquare> attackedSquares) {
        int x = pos.getX() , y = pos.getY();
        Point[] moveDirs = {new Point(2,1) , new Point(2,-1)
                , new Point(-2,1) , new Point(-2,-1), new Point(1 , 2)
                , new Point(1 , -2) , new Point(-1 , 2) , new Point(-1 , -2)};

        for (Point dir : moveDirs){
            int moveX = x , moveY = y;
            moveX += dir.getX();
            moveY += dir.getY();
            addAttackedSquare(board , moveX , moveY);
        }
    }

    /*@Override
    public boolean checkMove(int x, int y, int nx, int ny) {
        int mx = Math.abs(x - nx);
        int my = Math.abs(y - ny);
        return (mx == 2 && my == 1) || (mx == 1 && my == 2);
    }

    @Override
    public boolean checkSpecialMove(int x, int y, int nx, int ny, boolean hasPiece) {
        return checkMove(x ,y , nx , ny);
    }*/

    @Override
    public String toString() {
        return "Knight";
    }
}
