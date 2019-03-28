package client;

import client.model.Point;
import client.model.board.ChessBoard;
import client.model.logic.LogicManagerConclusion;
import client.model.piece.ChessColor;
import client.model.piece.ClassicChessSet;
import client.model.board.BoardSide;
import client.model.session.Player;
import client.model.session.PlayerStepInfo;
import client.model.session.Session;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;


//проблемы с ChessSet , как без дублирования использовать разные классы
//проблем не обнаружено
//проверка на мат не тестировалась(1 тест)
//добавить список срубленных фигур(сейчас происходит потеря ссылки)




public class ClientApp {


    public static int convertLetter(String l){
        switch (l){
            case "a": return 0;
            case "b": return 1;
            case "c": return 2;
            case "d": return 3;
            case "e": return 4;
            case "f": return 5;
            case "g": return 6;
            case "h": return 7;
        }
        return -1;
    }

    public static void main(String[] args) {
        String localName = "Bob";
        String distantName = "Cat";


       // System.out.println(Session.class.getSimpleName());
        ChessBoard board = new ChessBoard();
       Session session = new Session(new Player(localName , ChessColor.WHITE,new ClassicChessSet(localName , ChessColor.WHITE , BoardSide.BOTTOM))
                ,new Player(distantName , ChessColor.BLACK,new ClassicChessSet(distantName , ChessColor.BLACK , BoardSide.TOP))
                , board);


        //session.setDistantPlayerStepInfo(new PlayerStepInfo(new Point(0,1) , new Point(0,2)));
        //session.setLocalPlayerStepInfo(new PlayerStepInfo(new Point(0,6) ,new Point(0 , 5)));
       Scanner scanner = new Scanner(System.in);
        int order = 0;
        while(true){
            board.show();
            //scanner.hasNextInt()
            System.out.print("Start Point:");
            String xs = scanner.next();
            int y = scanner.nextInt();
            System.out.print("\nEnd Point:");
            String  nxs = scanner.next();
            int ny = scanner.nextInt();
            int x = convertLetter(xs), nx = convertLetter(nxs);




            if(order % 2 == 0){
                session.setLocalPlayerStepInfo(new PlayerStepInfo(new Point(x,y) ,new Point(nx , ny)));
            }else{


                session.setDistantPlayerStepInfo(new PlayerStepInfo(new Point(x,y) , new Point(nx,ny)));
            }
            LogicManagerConclusion conslusion =  session.execNextStep();

            if(conslusion.isSucceed()){
                System.out.println(conslusion.getCorrectState());
                ++order;
            }else{
                System.out.println(conslusion.getWrongState());
                System.out.println("Wrong MOve");
            }
        }


    }

}
