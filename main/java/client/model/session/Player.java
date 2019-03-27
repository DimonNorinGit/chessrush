package client.model.session;

import client.model.board.ChessBoard;
import client.model.piece.*;


public class Player {
    private String name;
    private ChessColor chessColor;
    private ChessSet chessSet;
    private PlayerStepInfo stepInfo;

    public Player(String name , ChessColor color,  ChessSet chessSet){
       this.chessSet = chessSet;
       this.name = name;
       this.chessColor = color;
    }

    void setStepInfo(PlayerStepInfo stepInfo) {
        this.stepInfo = stepInfo;
    }

    public PlayerStepInfo getStepInfo() {
        return stepInfo;
    }

    public ChessSet getChessSet() {
        return chessSet;
    }

    void arrangePiecesOnBoard(ChessBoard board){
        chessSet.arrangePieces(board);
    }

}
