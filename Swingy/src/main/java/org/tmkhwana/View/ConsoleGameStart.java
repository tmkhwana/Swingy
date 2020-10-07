package org.tmkhwana.View;

import org.tmkhwana.Controller.GamePlay;
import org.tmkhwana.Controller.InputOutput;
import org.tmkhwana.Controller.PlayerValidator;
import org.tmkhwana.Model.Player;

import java.util.ArrayList;

public class ConsoleGameStart implements GameStart {
    InputOutput input = new InputOutput();
    PlayerValidator validator = new PlayerValidator();
    public ConsoleGameStart(){}
    public void start(){
        GamePlay game;
        Player hero = null;
        boolean errors = true;
        System.out.println( "* * * * * * * * * * *\n" +
                            "* Welcome to Swingy *\n" +
                            "*                   *\n" +
                            "* 1. Create Player  *\n" +
                            "* 2. Select Player  *\n" +
                            "* * * * * * * * * * *\n" +
                            "Choose option: ");
        int option = input.getInt();
        if (option == 1) {
            while (errors){
                hero = createHero();
                errors = validator.validate(hero);
            }
            game = new GamePlay(hero);
            game.play();
        } else if (option == 2){
            while (errors){
                hero = selectHero();
                errors = validator.validate(hero);
            }
            hero.setX(heroDefaultPosition(hero.getLevel()));
            hero.setY(heroDefaultPosition(hero.getLevel()));
            game = new GamePlay(hero);
            game.play();
        }else {
            System.out.println("Invalid choice");
            start();
        }
    }

    public Player createHero(){
        String name, playerClass;
        System.out.println("Please enter Hero name: ");
        name = input.getString();
        playerClass = showClasses();
        return new Player(name, playerClass);
    }

    public Player selectHero() {
        String[] line;
        ArrayList<String> heroes = input.readHeroes();
        if (heroes.size() <= 0 ) {
            System.out.println("No heroes saved, create a new one");
            return createHero();
        } else {
            System.out.println("Select a Hero index:\n");
            for (int i = 0; i < heroes.size(); i++) {
                System.out.println(i+1 + ". " + heroes.get(i).split(",")[0]);
            }
            line = heroes.get(input.getInt()-1).split(",");
        }
        return (new Player(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6])));
    }

    public int heroDefaultPosition(int level){
        int mapSize = (level-1)*5+10-(level%2);
        return (mapSize-1)/2;
    }

    public String showClasses() {
        System.out.println( "* * * * * * * * * * *\n" +
                            "* Choose class      *\n" +
                            "* 1. Spiderman      *\n" +
                            "* 2. Spongebob      *\n" +
                            "* 3  WonderWoman    *\n" +
                            "* * * * * * * * * * *");
        switch (input.getInt()){
            case 1:
                return "Spiderman";
            case 2:
                return "Spongebob";
            case 3:
                return "WonderWoman";
        }
        return "Stupid";
    }
}