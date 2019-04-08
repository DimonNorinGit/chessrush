package client;

public class Point {
    private int x;
    private int y;

    public Point(int x , int y){
        this.x = x;
        this.y = y;
    }

    public boolean compare(Point p){
        return x == p.getX() && y == p.getY();
    }

    @Override
    public String toString(){
        return x + " " + y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
