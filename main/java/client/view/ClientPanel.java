package client.view;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ClientPanel extends JPanel {

    private Map<String , Component> components;

    public ClientPanel(){
        components = new HashMap<>();
    }

    public void addComponentWithName(String name , Component component){
        components.put(name , component);
    }

    public Component removeComponentByName(String name){
        return components.remove(name);
    }

    public Component getComponentByName(String name){
        return components.get(name);
    }
}
