package client.model.session;

import client.Point;

public class PlayerStepInfo {

    private Point startPoint;
    private Point endPoint;

    //than there will be more info about step

    public PlayerStepInfo(Point startPoint , Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }
}
