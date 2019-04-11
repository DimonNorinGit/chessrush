package client.controller;

import client.Connect;
import client.Connector;
import client.Point;
import client.model.PlayerInfo;
import client.model.board.BoardSide;
import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.logic.LogicManager;
import client.model.logic.LogicManagerConclusion;
import client.model.logic.StepCorrectState;
import client.model.piece.*;
import client.model.session.Player;
import client.model.session.PlayerStepInfo;
import client.model.session.Session;
import client.view.EventDispatcher;
import client.view.Window;

import java.awt.peer.LightweightPeer;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MVController {


    private Connector connector;
    private SessionController sessionController;
    private Window window;
    private Piece transformingPiece = null;
    private boolean isFirstClick = false;
    private int currentClickX = -1;
    private int currentClickY = -1;
    private int order = 0;

    public MVController(){
        connector = new Connector();
        sessionController = new SessionController();
        PlayerInfo local = new PlayerInfo("Bob" , ChessColor.WHITE , BoardSide.BOTTOM);
        PlayerInfo distant = new PlayerInfo("Bob" , ChessColor.BLACK , BoardSide.TOP);
        sessionController.initialize(local , distant , connector);

        Connect gameOverConnect = new Connect();
        gameOverConnect.setProperty("GAME_OVER" , false);
        gameOverConnect.setProperty("WINNER_COLOR" , null);
        connector.registerConnection("WINDOW" , gameOverConnect);


        Connect chessColorConnect = new Connect();
        chessColorConnect.setProperty("CURRENT_COLOR" , "WHITE");
        connector.registerConnection("GAME_SCENE" , chessColorConnect);

        Connect electorConnect = new Connect();
        electorConnect.setProperty("IS_VISIBLE" , false);
        connector.registerConnection("ELECTOR" , electorConnect);

        Connect flipConnect = new Connect();
        flipConnect.setProperty("IS_FLIPPED" , false);
        connector.registerConnection("FLIP_BOARD" , flipConnect);


        window = new Window(this);
    }

    public Connector getConnector(){
        return  connector;
    }


    /*public static int convertLetter(String l) {
        switch (l) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
        }
        return -1;
    }*/


    public void transformPiece(String pieceName){
        if(transformingPiece == null) return;
        Session session = sessionController.getSession();
        Player player = null;
        if(session.getLocalPlayer().getChessSet().getColor() == transformingPiece.getColor()){
            player = session.getLocalPlayer();
        }else{
            player = session.getDistantPlayer();
        }

        player.getChessSet().removePiece(pieceName ,  transformingPiece);
        Piece newPiece = player.getChessSet().createNewPiece(pieceName);
        Point pos = transformingPiece.getPosition();
        session.getChessBoard().setPiece(pos.getX() , pos.getY() , newPiece);
        transformingPiece = null;
    }


    public void addNewClick(int x , int y){
        //System.out.println("DONE");
        //обработка нажатия
        connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , false);
        connector.getConnect("ELECTOR").setProperty("IS_VISIBLE" , false);

        Session session = sessionController.getSession();
        LogicManager logicManager = session.getLogicManager();

        if(!isFirstClick && !logicManager.isCorrectStartSquare(session , new Point(x , y))){
            System.out.println(x  + "  " + y);
            return;
        }
        if(!isFirstClick) {
            currentClickX = x;
            currentClickY = y;
            isFirstClick = true;
            return;
        }

        Point startPoint = new Point(currentClickX ,currentClickY);
        Point endPoint  = new Point(x , y);


        if(order % 2 == 0){
            session.setLocalPlayerStepInfo(new PlayerStepInfo(startPoint,
                    endPoint));
        }else{
            session.setDistantPlayerStepInfo(new PlayerStepInfo(startPoint , endPoint));
        }
        LogicManagerConclusion conslusion =  session.execNextStep();

        if(conslusion.isSucceed()){
            StepCorrectState state  = conslusion.getCorrectState();
            System.out.println(state);
            if(state == StepCorrectState.CHECK_MATE){
                connector.getConnect("WINDOW").setProperty("GAME_OVER" , true);
                connector.getConnect("WINDOW").setProperty("WINNER_COLOR" , ChessColor.WHITE.toString());
            }else if(state == StepCorrectState.TRANSFORMATION){
                connector.getConnect("ELECTOR").setProperty("IS_VISIBLE" , true);
                transformingPiece = session.getChessBoard().
                        getBoardSquare(endPoint.getX() , endPoint.getY()).getPiece();
            }
            if(logicManager.getCurrentChessColor() == ChessColor.WHITE){
                connector.getConnect("GAME_SCENE").setProperty("CURRENT_COLOR" , ChessColor.WHITE.toString());
            }else{
                connector.getConnect("GAME_SCENE").setProperty("CURRENT_COLOR" , ChessColor.BLACK.toString());
            }
            ++order;
            connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , true);
            isFirstClick = false;
            connector.updateConnections();
        }else{
            System.out.println(conslusion.getWrongState());
            System.out.println("Wrong MOve");
            System.out.print(endPoint);

            if(logicManager.isCorrectStartSquare(session , endPoint)){
                currentClickX = x;
                currentClickY = y;
            }
        }
    }
    public void restart(){
        PlayerInfo local = new PlayerInfo("Bob" , ChessColor.WHITE , BoardSide.BOTTOM);
        PlayerInfo distant = new PlayerInfo("Cat" , ChessColor.BLACK , BoardSide.TOP);
        sessionController.reinitialize(local , distant , connector);
        connector.getConnect("WINDOW").setProperty("GAME_OVER" , false);
        isFirstClick = false;
        order = 0;
        if("WHITE".equals(connector.getConnect("WINDOW").getProperty("WINNER_COLOR"))){
            connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , true);
        }

        connector.updateConnections();
    }

    public void run(){
        window.setVisible(true);
        connector.updateConnections();
    }
}
