package client.controller;

import client.model.PlayerInfo;
import client.model.board.ChessBoard;
import client.model.piece.ChessColor;
import client.model.piece.ChessSet;
import client.model.piece.ClassicChessSet;
import client.model.session.Player;
import client.model.session.Session;

public class SessionController {

    private boolean initialized = false;
    private Session session = null;

    public void initialize(PlayerInfo localInfo  , PlayerInfo distantInfo){
        if(initialized)return;

        ChessSet localChessSet = new ClassicChessSet(localInfo.getName() ,
                localInfo.getColor() ,
                localInfo.getSide());

        Player local = new Player(localInfo.getName() , localInfo.getColor() , localChessSet);


        ChessSet distantChessSet = new ClassicChessSet(distantInfo.getName() ,
                distantInfo.getColor() ,
                distantInfo.getSide());
        Player distant = new Player(distantInfo.getName() , distantChessSet.getColor() , distantChessSet);

        ChessBoard board = new ChessBoard();

        session = new Session(local , distant , board);

        initialized = true;
    }


    public void reinitialize(PlayerInfo localInfo  ,PlayerInfo distantInfo){
        initialized = false;
        initialize(localInfo , distantInfo);
    }


}
