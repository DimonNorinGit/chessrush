package client.model.piece;

import java.util.Set;
import java.util.HashSet;

import client.model.board.BoardSide;
import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.Point;



//hashcode equales

public abstract class Piece {
    private Point position;
    private boolean isMoved = false;
    private boolean isAlive = true;
    private ChessColor color = null;
    private String owner = null;


    private BoardSide boardSide;
    private String type;

    private Set<BoardSquare> attackedSquares;


    //private List<BoardSquare> attackedSquares;

    public Piece(String type){
        this.type = type;
        attackedSquares = new HashSet<>();
    }


    BoardSide getBoardSide() {
        return boardSide;
    }

    public void setBoardSide(BoardSide boardSide) {
        this.boardSide = boardSide;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public String getType(){
        return type;
    }

    public void setIsMoved(boolean isMoved){
        this.isMoved = isMoved;
    }

    public ChessColor getColor() {
        return color;
    }

    public void setPosition(Point position) {
        //attackedSquares.clear();//!!!!!!!!!!
        this.position = position;
    }

    public void setPosition(int x , int y){
        setPosition(new Point(x , y));
    }

    public void kill() {
        isAlive = false;
    }

    public void ret(){
        isAlive = true;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public Point getPosition() {
        return position;
    }

    public void setColor(ChessColor color) {
        this.color = color;
    }

    //return true if move is suitable
    public Set<BoardSquare> getAttackedSquares(ChessBoard board){
        if(!isAlive){
            attackedSquares.clear();
            return attackedSquares;
        }
        //if(!attackedSquares.isEmpty()) return attackedSquares;
        attackedSquares.clear();
        findAttackedSquares(board , position , attackedSquares);
        return attackedSquares;
    }

    /*public void updateAttackedSquares(ChessBoard board){
        attackedSquares.clear();
        findAttackedSquares(board , position , attackedSquares);
    }*/


    boolean addAttackedSquare(ChessBoard board , int x , int y) {
        if (!board.inBoard(x, y)) return false;
        BoardSquare boardSquare = board.getBoardSquare(x, y);
        if (!boardSquare.hasPiece()){
            attackedSquares.add(boardSquare);
            return  true;
        }
        if(boardSquare.getPiece().getColor() != getColor())attackedSquares.add(boardSquare);
        return false;
    }



    void checkContinuousPaths(Point[] moveDirs , ChessBoard board , int x , int y){
        for (Point dir : moveDirs){
            int moveX = x , moveY = y;
            do {
                moveX += dir.getX();
                moveY += dir.getY();
            } while (addAttackedSquare(board, moveX, moveY));
        }
    }

    public boolean checkMove(BoardSquare endSquare, ChessBoard board) {
        return getAttackedSquares(board).contains(endSquare);
    }

    abstract protected void findAttackedSquares(ChessBoard board , Point pos , Set<BoardSquare> attackedSquares);
    //abstract public boolean checkMove(int x , int y , int nx , int ny);
    //abstract public boolean checkSpecialMove(int x , int y , int nx , int ny , boolean hasPiece);
    abstract public String toString();
}
