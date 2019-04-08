package client.view;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class ClientPanel extends JPanel{

    private Map<String , ClientPanel> components;

    public ClientPanel(){
        components = new HashMap<>();
    }

    public void addPanelWithName(String name , ClientPanel component){
        components.put(name , component);
    }

    public ClientPanel removePanelByName(String name){
        return components.remove(name);
    }

    public ClientPanel getPanelByName(String name){
        return components.get(name);
    }

    public void updateSize(){

    }

    public Set<String> getChildrenNames(){
        return components.keySet();
    }
}
