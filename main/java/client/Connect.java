package client;
import client.view.Consumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Connect {

    Map<String , Object> properties = new HashMap<>();
    Set<Consumer> consumers = new HashSet<>();

    public void setProperty(String name , Object value){
        properties.put(name , value);
    }
    public Object removeProperty(String name){
        return properties.remove(name);
    }
    public Object getProperty(String name){
        return properties.get(name);
    }

    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }

    public Set<Consumer> getConsumers() {
        return consumers;
    }

    public void updateConsumers(){
        for(Consumer c : consumers){
            c.update(this);
        }
    }

}
