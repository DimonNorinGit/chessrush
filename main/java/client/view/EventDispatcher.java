package client.view;

import client.Connector;
import client.Point;
import client.controller.MVController;

public class EventDispatcher{

    private MVController controller;

    public EventDispatcher(MVController controller){
        this.controller = controller;
    }

    public void processClick(int x , int y){
        controller.addNewClick(x , y);
        controller.getConnector().updateConnections();
    }

   /*public boolean isReady(){
        return isReady;
    }*/
}
