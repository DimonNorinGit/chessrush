package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow {


    public static void main(String[] args) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screanSize = tk.getScreenSize();

        JFrame window = new JFrame();
        window.setSize(screanSize.width / 2 , screanSize.height / 2);

        JPanel cards;
        final String BUTTONPANEL = "Card with JButtons";
        final String TEXTPANEL = "Card with JTextField";

//Where the components controlled by the CardLayout are initialized:
//Create the "cards".
        JPanel card1 = new JPanel();
        card1.add(new JButton(BUTTONPANEL));

        JPanel card2 = new JPanel();
        card2.add(new JButton(TEXTPANEL));

//Create the panel that contains the "cards".
        CardLayout cr = new CardLayout();
        cards = new JPanel();
        cards.setLayout(cr);



        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        cr.show(cards , BUTTONPANEL);


        window.add(cards);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.pack();
        window.setVisible(true);



        //cards.setVisible(true);


        /*JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(screanSize.width / 2, screanSize.height / 2);
        jf.setVisible(true);

       // jf.add(new JButton("The"));


        JFrame jf2 = new JFrame();
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setSize(screanSize.width / 2, screanSize.height / 2);
        //jf2.setVisible(true);
        jf2.add(new JButton("Hello"));

        Container p = jf.getContentPane();
        p.setLayout(new GridLayout(3 , 3));

        JButton b = new JButton("start 1");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 jf.setVisible(false);
                 jf2.setVisible(true);
            }
        });
        p.add(b);
        p.add(new JButton("start 2"));
        p.add(new JButton("start 3"));
        p.add(new JButton("start 4"));
        p.add(new JButton("start 5"));
        p.add(new JButton("start 6"));*/

        //p.add(new JButton("Okay"));
    }

}
