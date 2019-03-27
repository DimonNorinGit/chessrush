package client.model.board;


import client.model.Point;
import client.model.piece.Piece;



public class ChessBoard {

    public static final int X_SIZE = 8;
    public static final int Y_SIZE = 8;

    private  BoardSquare[][] board;


    public ChessBoard(){
        board = new BoardSquare[Y_SIZE][X_SIZE];
        init();
    }


    public boolean inBoard(int x , int y){
        return x >= 0 && y >=0 && x < X_SIZE && y < Y_SIZE;
    }

    public BoardSquare getBoardSquare(int x , int y){
        return board[y][x];
    }

   /* public BoardSquare getBoardSquare(Point coordinates){
        return board[coordinates.getX()][coordinates.getY()];
    }*/


    public ChessBoard movePiece(int x , int y , int nx , int ny){
        Piece p = board[y][x].removePiece();
        p.setPosition(nx , ny);
        //выполнение происходит без проверки на присутсвие фигуры в клетке nx , ny
        //и отсутсвие в x , y
        // т к это происходит на уровень выше(LogicManager)
        board[ny][nx].setPiece(p);
        return this;
    }

    public ChessBoard setPiece(int x , int y , Piece piece ){
        board[y][x].setPiece(piece);
        piece.setPosition(x , y);
        return this;
    }

    public Piece removePiece(int x , int y){
        return  board[y][x].removePiece();
    }

    //void modifyBoardSquare(int x , int y , )




    public void show(){
        System.out.print("  ");
        for(int i = 0; i < X_SIZE; ++i) System.out.print(i + " ");
        System.out.print("\n");
        for(int i = 0; i < X_SIZE; ++i){

            System.out.print(i + " ");
            for(int j = 0; j < Y_SIZE; ++j){
                if(board[i][j].hasPiece()){
                    Piece p  = board[i][j].getPiece();
                    char sim = 0;
                    switch (p.getType()){
                        case "Bishop": sim = '!'; break;
                        case "King" : sim = '*'; break;
                        case "Pawn" : sim = '^'; break;
                        case "Queen" : sim = '$'; break;
                        case "Rook": sim = '#';break;
                        case "Knight": sim = '%';break;
                    }
                    System.out.print(sim + " ");

                    //System.out.printf(board[i][j].getPiece().getType() + " ");
                }else{
                    SquareColor color = board[i][j].getColor();

                    if(color == SquareColor.WHITE){
                        System.out.print('+' + " ");
                    }else{
                        System.out.print('-' + " ");
                    }
                }

            }
            System.out.print("\n");
        }
    }

    private void init(){
        for(int i = 0; i < X_SIZE; ++i){
            for(int j = 0; j < Y_SIZE; ++j){
                if((i + j)% 2 == 0){
                    board[i][j] = new BoardSquare(SquareColor.WHITE , new Point(j , i));
                }else{
                    board[i][j] = new BoardSquare(SquareColor.BLACK , new Point(j , i));
                }
            }
        }
    }
}



