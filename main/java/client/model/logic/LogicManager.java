package client.model.logic;


import client.Point;
import client.model.board.BoardSquare;
import client.model.board.ChessBoard;
import client.model.piece.*;
import client.model.session.Player;
import client.model.session.PlayerStepInfo;
import client.model.session.Session;


import java.util.ArrayList;
import java.util.Set;
import java.util.List;


//сделать для каждого поля хешкод
//для быстрой проверки атакованных фигур

public class LogicManager {

    private ChessColor currentChessColor = null;

    public LogicManager(ChessColor startColor){
        currentChessColor = startColor;
    }

    public ChessColor getCurrentChessColor(){
        return currentChessColor;
    }

    private void changeWalkingColor(){
        if(currentChessColor == ChessColor.WHITE) currentChessColor = ChessColor.BLACK;
        else currentChessColor = ChessColor.WHITE;
    }

    //return true if next step is valid
    //если ход неверный , а пришел он от удаленного игрока ->
    //читерит или ошибка сервера и т д


    public boolean isCorrectStartSquare(Session session , Point chosenPoint){
        int x = chosenPoint.getX() , y = chosenPoint.getY();
        BoardSquare square = session.getChessBoard().getBoardSquare(x , y);
        return square.hasPiece() && square.getPiece().getColor() == currentChessColor;
    }

    public LogicManagerConclusion run(Session session){
        Player local = session.getLocalPlayer();
        Player distant = session.getDistantPlayer();
        ChessBoard board = session.getChessBoard();


        Player currentPlayer = local;
        Player opponentPlayer = distant;
        if(currentChessColor == distant.getChessSet().getColor()){
            currentPlayer = distant;
            opponentPlayer = local;
        }



        PlayerStepInfo stepInfo = currentPlayer.getStepInfo();

        Point startPoint = stepInfo.getStartPoint();
        Point endPoint = stepInfo.getEndPoint();
        int x = startPoint.getX();
        int y = startPoint.getY();
        int nx = endPoint.getX();
        int ny = endPoint.getY();

        //нет проверки inBoard
        BoardSquare startSquare = board.getBoardSquare(x , y);
        BoardSquare endSquare = board.getBoardSquare(nx , ny);


        //эти проверки можно сделать как отдельные методы
        //с возвратом boolean
        /*
        //если startSquare это не наша фигура или клетка пуста=)
        if(!startSquare.hasPiece()){
            return false;
        }else if( startSquare.getPiece().getColor() != currentChessColor){

            return false;
        }*/


        LogicManagerConclusion conclusion = new LogicManagerConclusion();


        Piece movingPiece = startSquare.getPiece();
        //нет фигуры
        if(movingPiece == null || movingPiece.getColor() != currentChessColor){
            conclusion.setWrongState(StepWrongState.INCORRECT_MOVE);
            return conclusion;
        }

        /*if(!movingPiece.getAttackedSquares(board).contains(endSquare)){

        }*/



        if(endSquare.hasPiece() && endSquare.getPiece().getColor() == currentChessColor){
            Piece endPiece = endSquare.getPiece();
            //проверка на лакировку
            if(!checkCastling(board , opponentPlayer.getChessSet() , movingPiece , endPiece)){
                conclusion.setWrongState(StepWrongState.IMPOSSIBLE_CASTLING);
                return conclusion;
            }
            doCastling(movingPiece , endPiece , board);

            /*board.removePiece(nx , ny);

            board.movePiece(x , y , nx , ny);

            board.setPiece(x , y , endPiece);*/
            //странная рокировка

            conclusion.setCorrectState(StepCorrectState.CASTLING);
            changeWalkingColor();
            movingPiece.setIsMoved(true);
            return conclusion;
            //проверка на одну и ту же фигуру
        }


        //переписать проверку возможности хода
        boolean correctMove =  movingPiece.checkMove(endSquare, board);
        if(!correctMove){
            conclusion.setWrongState(StepWrongState.INCORRECT_MOVE);
            return conclusion;
        }




        Piece currentKing = currentPlayer.getChessSet().getChessListByName("King").get(0);
        Piece opponentKing  = opponentPlayer.getChessSet().getChessListByName("King").get(0);

        //тут точно есть срубленная фигура
        Piece capture = execStep(board , startPoint , endPoint);
        if(capture != null){
            capture.kill();
            opponentPlayer.getChessSet().removePiece(capture.getType() , capture);
        }
        //remove from ChessSet?

        //проверка на шах самому себе
        if(verifyCheck(board , opponentPlayer.getChessSet() , currentKing)){
            //возвращяем предыдущее состояние доски
            execStep(board , endPoint , startPoint);
            if(capture != null)
                board.setPiece(nx , ny , capture);

            conclusion.setWrongState(StepWrongState.UNDER_CHECK);
            return conclusion;
        }

        if(capture != null){
            conclusion.setCorrectState(StepCorrectState.CAPTURE);
        }else{
            conclusion.setCorrectState(StepCorrectState.MOVE);
        }

        //проверка шаха и мата противнику
        if(verifyCheck(board , currentPlayer.getChessSet() , opponentKing)){
            if(verifyCheckMate(board , currentPlayer.getChessSet() ,
                    opponentPlayer.getChessSet() , opponentKing))
            {
                conclusion.setCorrectState(StepCorrectState.CHECK_MATE);
            }else{
                conclusion.setCorrectState(StepCorrectState.CHECK);
            }
        }
        if(movingPiece instanceof Pawn){
            if(ny == ChessBoard.Y_SIZE - 1 || ny == 0){
                conclusion.setCorrectState(StepCorrectState.TRANSFORMATION);
            }
        }

        movingPiece.setIsMoved(true);
        changeWalkingColor();
        return conclusion;
    }


    //делает ход , если фигура срубает другую, то срубленная будет возвращена
    private Piece execStep(ChessBoard board , Point startPoint , Point endPoint){
        int x = startPoint.getX() , y = startPoint.getY();
        int nx = endPoint.getX() , ny = endPoint.getY();
        BoardSquare endSquare = board.getBoardSquare(nx , ny);
        if(endSquare.hasPiece()){
            Piece capture = endSquare.removePiece();
            board.movePiece(x , y , nx , ny);
            return capture;
        }
        board.movePiece(x , y , nx , ny);
        return null;
    }


    private void doCastling(Piece a , Piece b , ChessBoard board){
        Piece rook = a , king = b;
        if(a instanceof King){
            rook = b;
            king = a;
        }
        Point rookPos = rook.getPosition();
        Point kingPos = king.getPosition();
        int rookNewX = 3 , kingNewX = 2;
        if(rook.getPosition().getX() == ChessBoard.X_SIZE - 1){
            rookNewX = ChessBoard.X_SIZE - 3;
            kingNewX = ChessBoard.X_SIZE - 2;
        }

        board.movePiece(rookPos.getX() , rookPos.getY() ,rookNewX ,  rookPos.getY());
        board.movePiece(kingPos.getX() , kingPos.getY() , kingNewX , kingPos.getY());
    }


    //хардкод с проверкой точного местоположения фигур
    private boolean checkCastling(ChessBoard board , ChessSet opponentSet , Piece initialPiece , Piece finalPiece){
        boolean ok = (initialPiece instanceof King && finalPiece instanceof Rook) ||
                (initialPiece instanceof Rook && finalPiece instanceof King);
        if(!ok) return false;

        if(initialPiece.isMoved() || finalPiece.isMoved())return false;

        Point startPoint = initialPiece.getPosition();
        Point endPoint = finalPiece.getPosition();
        int x = startPoint.getX() , y = startPoint.getY();
        int nx = endPoint.getX() , ny = endPoint.getY();
        int movingDirX = 0 , movingDirY = 0;
        if(x == nx){
           movingDirY = (ny - y) / Math.abs(ny - nx);
        }else if(y == ny){
            movingDirX = (nx - x) / Math.abs(nx - x);
        }else{
           return false;
        }

        List<BoardSquare>  sandwichedSquares = new ArrayList<>();

        while(!(x == (nx + movingDirX) && y == (ny + movingDirY))){
            sandwichedSquares.add(board.getBoardSquare(x , y));
            x+=movingDirX;
            y+=movingDirY;
        }
        if(sandwichedSquares.size() < 4 ) return false;

        for(int i = 1; i < sandwichedSquares.size() - 1; ++i){
            if(sandwichedSquares.get(i).hasPiece())return false;
        }

        for (String name : opponentSet.getChessNames()){
            List<Piece> pieces = opponentSet.getChessListByName(name);
            for (Piece p : pieces){
                Set<BoardSquare> set = p.getAttackedSquares(board);
                for (BoardSquare boardSquare : sandwichedSquares){
                    if(set.contains(boardSquare)) return false;
                }
            }
        }
        return true;
    }


    //определяет начальное распределение атакованных полей
    private void defineAttackedFields(ChessBoard board , ChessSet chessSet){
        for(String  name: chessSet.getChessNames()){
            List<Piece> pieces = chessSet.getChessListByName(name);
            for(Piece p : pieces){
                if(!p.isAlive())continue;
                Set<BoardSquare> squares = p.getAttackedSquares(board);
                for(BoardSquare sq : squares){
                    sq.clearAttackPieces();
                    sq.addAttackPiece(p);
                }
            }
        }
    }



    private boolean verifyCheckMate(ChessBoard board , ChessSet currentSet , ChessSet opponentSet , Piece king){

        boolean solvable = false;
        for(String name : opponentSet.getChessNames()){
            for(Piece movingPiece : opponentSet.getChessListByName(name)){
                if(!movingPiece.isAlive())continue;

                Set<BoardSquare> attackedSquares = movingPiece.getAttackedSquares(board);
                if(movingPiece instanceof Pawn){
                    //test!?
                    BoardSquare bs = ((Pawn) movingPiece).getFrontMoveSquare(board);
                    if(bs != null) attackedSquares.add(bs);
                }
                if(attackedSquares.isEmpty())continue;
                for(BoardSquare square : attackedSquares){
                    try {
                        Point squareCoordinates  = square.getCoordinates();
                        Point keepPos = movingPiece.getPosition();
                        Piece capture = execStep(board, movingPiece.getPosition(), squareCoordinates);
                        //movingPiece меняется положенение
                        if (capture != null) capture.kill();
                        //пересчет атакованных
                        defineAttackedFields(board, currentSet);

                        if (!verifyCheck(board, currentSet, king)) {
                            solvable = true;
                        }

                        execStep(board, movingPiece.getPosition(), keepPos);
                        Point pos = square.getCoordinates();
                        if (capture != null) {
                            capture.ret();
                            board.setPiece(squareCoordinates.getX() , squareCoordinates.getY(), capture);
                        }
                        if (solvable) {
                            defineAttackedFields(board, currentSet);
                            return false;
                        }
                    }catch (Exception exc){
                        System.out.println(exc.getMessage());
                    }
                }
            }
        }
        return true;
    }



    //проверка шаха
    private boolean verifyCheck(ChessBoard board , ChessSet set , Piece king){
        defineAttackedFields(board , set);
        Point pos = king.getPosition();
        BoardSquare kingSquare = board.getBoardSquare(pos.getX() , pos.getY());
        for (String name : set.getChessNames()){
            List<Piece> pieces = set.getChessListByName(name);
            for (Piece p : pieces){
                if(p.getAttackedSquares(board).contains(kingSquare))return true;
                /*for(BoardSquare square : p.getAttackedSquares(board)){
                    if(king.getPosition().compare(square.getCoordinates())){
                        return true;
                    }
                }*/
            }
        }
        return false;
    }


    public void reset(){
        currentChessColor = ChessColor.WHITE;
    }

}
