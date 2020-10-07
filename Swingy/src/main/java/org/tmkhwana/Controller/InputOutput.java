package org.tmkhwana.Controller;

import org.tmkhwana.Model.Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {
    FileWriter fileWriter;
    FileReader fileReader;

    public InputOutput(){

    }

    public int getInt(){
        Scanner scanner = new Scanner(System.in);
        int i;
        try{
            i = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Please provide valid input ...");
            return getInt();
        }
        return i;
    }

    public String getString(){
        Scanner scanner = new Scanner(System.in);
        String str = null;
        try {
            if (scanner.hasNext()) {
                str = scanner.nextLine();
            }
        } catch (Exception e){
            e.printStackTrace();
            return getString();
        }
        return str;
    }

    public void writeHero(Player p){
        ArrayList<String> heroes = readHeroes();
        try {
            fileWriter = new FileWriter("players.csv");
            String[] line;
            boolean updated = false;
            if(heroes.size() > 0){
                for (String hero: heroes){
                    line = hero.split(",");
                    if (p.getName().equals(line[0])) {
                        fileWriter.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", p.getName(), p.getPlayerClass(), p.getLevel(), p.getEx(), p.getAttack(), p.getDefense(), p.getHP()));
                        updated = true;
                    } else {
                        fileWriter.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", line[0], line[1], line[2], line[3], line[4], line[5], line[6]));
                    }
                }
                if(!updated)
                    fileWriter.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", p.getName(), p.getPlayerClass(), p.getLevel(), p.getEx(), p.getAttack(), p.getDefense(), p.getHP()));
            } else {
                fileWriter.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", p.getName(), p.getPlayerClass(), p.getLevel(), p.getEx(), p.getAttack(), p.getDefense(), p.getHP()));
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readHeroes(){
        Scanner scanner;
        ArrayList<String> heroes = new ArrayList<>();
        String line;
        try {
            fileReader = new FileReader("players.csv");
            scanner = new Scanner(fileReader);
            while(scanner.hasNext()){
                line = scanner.nextLine();
                heroes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}
