package client.model.board;

import client.model.Point;
import client.model.piece.Piece;


import java.util.HashSet;
import java.util.Set;

public class BoardSquare{

    private SquareColor color;
    private Point coordinates;
    private int hashCode = 0;
    private Piece piece;
    private Set<Piece> underAttack;


    BoardSquare(SquareColor color , Point coordinates){
        this.color = color;
        this.coordinates = coordinates;
        hashCode = this.coordinates.getX() * 10 + this.coordinates.getY();
        piece = null;
        underAttack = new HashSet<>();
    }

    @Override
    public int hashCode(){
        return hashCode;
    }


    //!!!!!!!!!!!!!!!!
    @Override
    public boolean equals(Object square){
        if(!(square instanceof BoardSquare))return false;
        return hashCode == ((BoardSquare) square).hashCode;
    }


    public Point getCoordinates(){
        return coordinates;
    }


    public void addAttackPiece(Piece piece){
        underAttack.add(piece);
    }

    public void removeAttackPiece(Piece piece){
        underAttack.remove(piece);
    }

    public boolean isAttacked(Piece piece){
       return underAttack.contains(piece);
    }

    public void clearAttackPieces(){
        underAttack.clear();
    }


    public boolean hasPiece(){
        return ! (null == piece);
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){return piece;}

    public Piece removePiece(){
        Piece p = piece;
        piece = null;
        return p;
    }

    public void setColor(SquareColor color){
        this.color = color;
    }

    public SquareColor getColor(){
        return color;
    }
}
