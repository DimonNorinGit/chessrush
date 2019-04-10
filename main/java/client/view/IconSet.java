package client.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IconSet {
    static private Map<String , Icon> whiteIcons = new HashMap<>();
    static private Map<String , Icon> blackIcons = new HashMap<>();
    static private Map<String , Icon> icons = new HashMap<>();

    public static void loadIcons(){
        whiteIcons.put("King" , new ImageIcon(IconsPaths.WHITE_KING));
        whiteIcons.put("Queen" , new ImageIcon(IconsPaths.WHITE_QUEEN));
        whiteIcons.put("Rook" , new ImageIcon(IconsPaths.WHITE_ROOK));
        whiteIcons.put("Pawn" , new ImageIcon(IconsPaths.WHITE_PAWN));
        whiteIcons.put("Bishop" , new ImageIcon(IconsPaths.WHITE_BISHOP));
        whiteIcons.put("Knight" , new ImageIcon(IconsPaths.WHITE_KNIGHT));

        blackIcons.put("King" , new ImageIcon(IconsPaths.BLACK_KING));
        blackIcons.put("Queen" , new ImageIcon(IconsPaths.BLACK_QUEEN));
        blackIcons.put("Rook" , new ImageIcon(IconsPaths.BLACK_ROOK));
        blackIcons.put("Pawn" , new ImageIcon(IconsPaths.BLACK_PAWN));
        blackIcons.put("Bishop" , new ImageIcon(IconsPaths.BLACK_BISHOP));
        blackIcons.put("Knight" , new ImageIcon(IconsPaths.BLACK_KNIGHT));
        icons.put("mainMenuBackground" , new ImageIcon(IconsPaths.MENU_BACKGROUND));
    }



    static Set<String> getPieceIconNames(){
        return blackIcons.keySet();
    }

    static Icon getIconByName(String name){
        return icons.get(name);
    }

    static Icon getChessIcon(String name , String color){
        if(color.compareTo("WHITE") == 0){
            return whiteIcons.get(name);
        }
        return blackIcons.get(name);
    }
}
