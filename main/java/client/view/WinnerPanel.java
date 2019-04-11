package client.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnerPanel extends ClientPanel {
    private Window root;
    private JButton backButton = new JButton();
    private JButton restartButton = new JButton();

    public WinnerPanel(Window root){
        this.root = root;
        backButton.setText("BACK");
        backButton.addActionListener(e -> root.changePanel("MAIN_MENU"));
        restartButton.setText("RESTART");
        restartButton.addActionListener(e -> root.changePanel("GAME_SCENE"));
        add(backButton);
        add(restartButton);

        //root.getConnector().connectTo("")
    }




}
