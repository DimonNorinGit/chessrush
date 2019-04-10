package client.model.piece;

import client.Point;
import client.model.board.BoardSide;
import client.model.board.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClassicChessSet extends ChessSet {



    public ClassicChessSet(String owner , ChessColor color ,  BoardSide side){
        super(owner , color , side);
    }

    public void arrangePieces(ChessBoard board){
        Map<String , List<Piece>> pieces = super.getPieces();
        BoardSide side = getBoardSide();
        //top board side
        int vanguardPosY = 1 , rearWardPosY = 0;

        if(side == BoardSide.BOTTOM){
            vanguardPosY = ChessBoard.Y_SIZE - 2;
            rearWardPosY = ChessBoard.Y_SIZE - 1;
        }

        //set pawns
        for (int i = 0; i < ChessBoard.X_SIZE; ++i){
            board.setPiece(i , vanguardPosY , pieces.get("Pawn").get(i));
        }

        //set king
        board.setPiece(4 , rearWardPosY , pieces.get("King").get(0));

        //set queen
        board.setPiece(3 , rearWardPosY , pieces.get("Queen").get(0));

        //set rook
        board.setPiece(0 , rearWardPosY , pieces.get("Rook").get(0));
        board.setPiece(ChessBoard.X_SIZE - 1 , rearWardPosY , pieces.get("Rook").get(1));

        //set knight
        board.setPiece(1 , rearWardPosY , pieces.get("Knight").get(0));
        board.setPiece(ChessBoard.X_SIZE - 2 , rearWardPosY , pieces.get("Knight").get(1));

        //set bishop
        board.setPiece(2 , rearWardPosY , pieces.get("Bishop").get(0));
        board.setPiece(ChessBoard.X_SIZE - 3 , rearWardPosY , pieces.get("Bishop").get(1));

    }

    protected   void definePiecesCounts(){
        Map<Class , Integer> piecesCounts = super.getPiecesCounts();
        piecesCounts.clear();
        piecesCounts.put(King.class , 1);
        piecesCounts.put(Knight.class , 2);
        piecesCounts.put(Pawn.class , 8);
        piecesCounts.put(Queen.class , 1);
        piecesCounts.put(Bishop.class , 2);
        piecesCounts.put(Rook.class , 2);

        /*for (String name : this.getChessNames()){
            switch (name){
                case "Pawn": piecesCounts.put(name , 8);break;
                case "Knight": piecesCounts.put(name , 2);break;
                case "Bishop":piecesCounts.put(name , 2);break;
                case "Queen":piecesCounts.put(name , 1);break;
                case "Rook": piecesCounts.put(name , 2);break;
                case "King":piecesCounts.put(name , 1);break;
            }
        }*/
    }

    @Override
    public void removePiece(String name, Piece piece) {
        getPieces().get(name).remove(piece);
    }

    @Override
    public Piece createNewPiece(String pieceName) {
        Class pieceClass = null;
        switch (pieceName){
            case "Bishop": pieceClass =  Bishop.class; break;
            case "King" : pieceClass =  King.class; break;
            case "Pawn" : pieceClass =  Pawn.class; break;
            case "Queen" : pieceClass =  Queen.class; break;
            case "Rook": pieceClass =  Rook.class;break;
            case "Knight": pieceClass =  Knight.class;break;
        }
        Piece newPiece = null;
        if(pieceClass == null)return null;
        try {
            newPiece = (Piece) pieceClass.getConstructor().newInstance();
        }catch (Exception exc){
            //df
        }
        newPiece.setColor(getColor());
        newPiece.setOwner(getOwner());
        getPieces().get(pieceName).add(newPiece);
        return newPiece;
    }

    //should clear
    protected void setPieces(){
        Map<Class , Integer> piecesCounts = super.getPiecesCounts();
        Map<String , List<Piece>> pieces = super.getPieces();
        pieces.clear();


        /*int count = piecesCounts.get("King");
        for(int i = 0; i < count; ++i){

        }*/



        BoardSide side = getBoardSide();

        for (Class pieceClass : piecesCounts.keySet()){
            int count = piecesCounts.get(pieceClass);
            String simpleName = pieceClass.getSimpleName();
            pieces.put(simpleName , new ArrayList<>(count));
            for(int i = 0; i < count; ++i){
                Piece p = null;
                try {
                    p = (Piece) pieceClass.getConstructor().newInstance();//(Piece)cl.getConstructor().newInstance();
                }catch (Exception  exc){
                    return;
                }
                p.setBoardSide(side);
                p.setOwner(getOwner());
                p.setColor(getColor());
                pieces.get(simpleName).add(p);
            }
        }
    }

}
