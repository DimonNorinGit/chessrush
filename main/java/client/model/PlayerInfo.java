package client.model;

import client.model.board.BoardSide;
import client.model.piece.ChessColor;
import client.model.session.Player;

public class PlayerInfo {
    private String name;
    private ChessColor color;
    private BoardSide side;
    public PlayerInfo(String name , ChessColor color , BoardSide side){
        this.name = name;
        this.color = color;
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public ChessColor getColor() {
        return color;
    }

    public BoardSide getSide() {
        return side;
    }
}
