package client.controller;

import client.Connect;
import client.Connector;
import client.Point;
import client.model.PlayerInfo;
import client.model.board.BoardSide;
import client.model.logic.LogicManager;
import client.model.logic.LogicManagerConclusion;
import client.model.piece.ChessColor;
import client.model.session.PlayerStepInfo;
import client.model.session.Session;
import client.view.EventDispatcher;
import client.view.Window;

import java.awt.peer.LightweightPeer;
import java.util.Scanner;

public class MVController {


    private Connector connector;
    private EventDispatcher dispatcher;
    private SessionController sessionController;
    private Window window;
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

        Connect connect = new Connect();
        connect.setProperty("IS_FLIPPED" , false);
        connector.registerConnection("FLIP_BOARD" , connect);
        window = new Window(this);
        dispatcher = window.getEventDispatcher();
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



    public void addNewClick(int x , int y){
        connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , false);
        Session session = sessionController.getSession();
        LogicManager logicManager = session.getLogicManager();

        if(!isFirstClick && !logicManager.isCorrectStartSquare(session , new Point(x , y))){
            return;
        }

        if(!isFirstClick) {
            currentClickX = x;
            currentClickY = y;
            isFirstClick = true;
            return;
        }

        System.out.println("WHAT");

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
            System.out.println(conslusion.getCorrectState());
            ++order;
            connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , true);
            isFirstClick = false;
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

    public void run(){
        window.setVisible(true);
        connector.updateConnections();


        /*int order = 0;
        Session session = sessionController.getSession();
        LogicManager logicManager = session.getLogicManager();

        while(true){
            Point startPoint = null , endPoint = null;
            while (true) {
                synchronized (dispatcher) {
                    if (dispatcher.isReady()) {
                        startPoint = dispatcher.getStartPoint();
                        endPoint = dispatcher.getEndPoint();
                        if(!logicManager.isCorrectStartSquare(session , startPoint)){
                            dispatcher.restart();
                            System.out.println("Not my piece");
                            continue;
                        }
                        if(startPoint.compare(endPoint)){
                            dispatcher.backupEndPoint();
                            continue;
                        }

                        System.out.println(startPoint);
                        System.out.println(endPoint);
                        break;
                    }
                }
            }

            if(order % 2 == 0){
                session.setLocalPlayerStepInfo(new PlayerStepInfo(startPoint,
                        endPoint));
                //dispatcher.restart();
            }else{
                session.setDistantPlayerStepInfo(new PlayerStepInfo(startPoint , endPoint));
            }
            LogicManagerConclusion conslusion =  session.execNextStep();

            if(conslusion.isSucceed()){
                System.out.println(conslusion.getCorrectState());
                ++order;
                connector.getConnect("FLIP_BOARD").setProperty("IS_FLIPPED" , true);
                connector.updateConnections();
            }else{
                System.out.println(conslusion.getWrongState());
                System.out.println("Wrong MOve");
            }
            dispatcher.restart();
        }*/


    }






}
