package org.tmkhwana;

import org.tmkhwana.View.ConsoleGameStart;
import org.tmkhwana.View.GameStart;
import org.tmkhwana.View.GuiGameStart;

import java.io.File;

public class Swingy {
    public static void main(String[] args) {
        GameStart game;
        if (args.length > 0){
            if (args[0].equalsIgnoreCase("GUI")){
                game = new GuiGameStart();
                game.start();
            } else if (args[0].equalsIgnoreCase("Console")){
                game = new ConsoleGameStart();
                game.start();
            } else {
                System.out.println("To play Swingy type: java -jar Swingy-1.0.jar [Console or GUI]");
            }
        } else {
            System.out.println("To play Swingy type: java -jar Swingy-1.0.jar [Console or GUI]");
        }
    }
}
