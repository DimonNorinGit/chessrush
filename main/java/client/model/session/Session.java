package client.model.session;

import client.model.board.ChessBoard;
import client.model.logic.LogicManager;
import client.model.logic.LogicManagerConclusion;
import client.model.piece.ChessColor;

public class Session {
    private Player localPlayer;
    private Player distantPlayer;
    private ChessBoard chessBoard;
    private LogicManager logicManager;

    public Session(Player localPlayer , Player distantPlayer , ChessBoard chessBoard){
        this.localPlayer = localPlayer;
        this.distantPlayer = distantPlayer;
        this.chessBoard = chessBoard;
        logicManager = new LogicManager(ChessColor.WHITE);
        localPlayer.arrangePiecesOnBoard(chessBoard);
        distantPlayer.arrangePiecesOnBoard(chessBoard);
    }

    public Player getDistantPlayer() {
        return distantPlayer;
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }



    public void loadSession(SessionRepresentation representation){
        //load existing session
    }

    public void setLocalPlayerStepInfo(PlayerStepInfo stepInfo){
        localPlayer.setStepInfo(stepInfo);
    }

    public void setDistantPlayerStepInfo(PlayerStepInfo stepInfo){
        distantPlayer.setStepInfo(stepInfo);
    }




    //if step unsuitable error info will set into LogicManagerConclusion
    //ret true if step is successful otherwise ret false
    public LogicManagerConclusion execNextStep(){

        return logicManager.run(this);
    }

    public SessionRepresentation getSessionRepresentation(){
        return null;
    }


}
