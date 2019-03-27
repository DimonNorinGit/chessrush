package client.model.piece;

import client.model.board.BoardSide;
import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.Point;

import java.util.Set;

public class Pawn extends Piece{


    public Pawn(){
        super("Pawn");
    }

    @Override
    protected void findAttackedSquares(ChessBoard board, Point pos, Set<BoardSquare> attackedSquares) {
        int x = pos.getX() , y = pos.getY();
        int dirY = -1;
        if(getBoardSide() == BoardSide.TOP){
            dirY = 1;
        }

        Point[] attackedMoves = {new Point(-1,dirY) , new Point(1 , dirY)};
        for (Point m : attackedMoves){
            int moveX = x + m.getX() , moveY = y + m.getY();
            addAttackedSquare(board , moveX , moveY);
        }

    }

    public BoardSquare getFrontMoveSquare(ChessBoard board){
        int dirY = -1;
        if(getBoardSide() == BoardSide.TOP){
            dirY = 1;
        }
        Point pos = getPosition();
        int x = pos.getX() , y = pos.getY();
        if(!board.inBoard(x , y + dirY)) return null;
        BoardSquare square = board.getBoardSquare(x , y + dirY);
        if(square.hasPiece())return null;
        return square;
    }

    /*@Override
    public boolean checkMove(int x, int y, int nx, int ny) {
        //подразумевается, что противник находиться в верхней части поля
        BoardSide side = getBoardSide();
        int abs = Math.abs(nx - x);
        boolean value = abs == 1 || abs == 0;
        if(side == BoardSide.BOTTOM){
            return y - ny == 1 && value;
        }else{
            return ny - y == 1 && value;
        }
    }*/

    public boolean checkMove(BoardSquare endSquare, ChessBoard board) {
        if(endSquare.hasPiece())return getAttackedSquares(board).contains(endSquare);
        BoardSide side = getBoardSide();
        Point currentPos = getPosition();
        Point endPos = endSquare.getCoordinates();
        boolean value = (currentPos.getX() - endPos.getX()) == 0;
        int diff = currentPos.getY() - endPos.getY();
        int possibleDiff = 1;
        if(!isMoved()) possibleDiff = 2;
        if(side == BoardSide.TOP) diff = -diff;
        return (diff == 1 || diff == possibleDiff)  && value;
    }

    /*@Override
    public boolean checkSpecialMove(int x, int y, int nx, int ny, boolean hasPiece) {
        BoardSide side = getBoardSide();
        boolean correctY = false;
        if(side == BoardSide.BOTTOM){
            correctY = (y - ny == 1);
        }else{
            correctY = (ny - y == 1);
        }

        int xDiff = Math.abs(nx - x);

        if(hasPiece)
            return correctY && xDiff == 1;


        return correctY && xDiff == 0;
    }*/

    @Override
    public String toString() {
        return "Pawn";
    }
}
