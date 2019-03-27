package client.model.piece;

import client.model.board.BoardSide;
import client.model.board.ChessBoard;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


abstract public class ChessSet {

    private static final String[] CHESS_NAMES = {"Pawn" , "King" , "Knight" , "Queen" , "Rook" , "Bishop"};
    private Map<Class , Integer> piecesCounts;
    private Map<String , List<Piece>> pieces;
    private String owner;
    private ChessColor color;
    private BoardSide boardSide;

    public ChessSet(String owner , ChessColor color , BoardSide boardSide){
        this.owner = owner;
        this.color = color;
        this.boardSide = boardSide;
        piecesCounts = new HashMap<>();
        pieces = new HashMap<>();
        definePiecesCounts();
        setPieces();
    }



    protected Map<Class , Integer> getPiecesCounts(){return piecesCounts;}
    protected Map<String , List<Piece>> getPieces(){return pieces;}
    public String getOwner(){ return  owner; }
    public ChessColor getColor(){return color;}
    public List<Piece> getChessListByName(String name){
        return pieces.get(name);
    }

    public BoardSide getBoardSide() {
        return boardSide;
    }

    abstract protected   void definePiecesCounts();

    //should clear
    abstract protected void setPieces();

    abstract public void arrangePieces(ChessBoard board);

    public String[] getChessNames(){
        return CHESS_NAMES;
    }
}
