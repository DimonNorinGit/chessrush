package client.controller;

import client.Point;
import client.model.piece.ChessColor;

public class ChessInfoKeaper {
    ChessColor color;
    Point position;
    ChessInfoKeaper(ChessColor color , Point position){
        this.color = color;
        this.position = position;
    }
    public Point getPosition() {
        return position;
    }

    public ChessColor getColor() {
        return color;
    }
}
