package client;


import client.view.Consumer;

import java.util.HashMap;
import java.util.Map;
public class Connector {


    private Map<String , Connect> connections = new HashMap<>();

    public void registerConnection(String name , Connect connect){
        if(connections.containsKey(name)){
            for (Consumer c : connections.get(name).getConsumers())
                connect.addConsumer(c);
        }
        connections.put(name , connect);
    }

    public Connect connectTo(String connectName , Consumer consumer){
        Connect connect = connections.get(connectName);
        connect.addConsumer(consumer);
        return connect;
    }

    public Connect getConnect(String name){
        return connections.get(name);
    }

    public void updateConnections(){
        for(String name : connections.keySet()){
            Connect cn = connections.get(name);
            cn.updateConsumers();
        }
    }


}
