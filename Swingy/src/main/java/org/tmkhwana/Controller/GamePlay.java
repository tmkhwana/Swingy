package org.tmkhwana.Controller;

import lombok.Getter;
import lombok.Setter;
import org.tmkhwana.Model.Player;
import org.tmkhwana.View.GamePlayView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

@Setter
@Getter
public class GamePlay {
    Player hero;
    ArrayList<Player> enemies = new ArrayList<>();
    String[][] gameMap;
    String [] enemyNames = new String[]{"PinkiePinkie", "Skeleton", "Zombie", "Vampire", "Ghost"};
    String [] classes = new String[]{"Level1", "Level2", "Level3", "Level4", "Level5", "Level6", "Level7"};
    InputOutput input = new InputOutput();
    GamePlayView view = new GamePlayView();
    boolean win = false;

    public GamePlay(Player player){
        this.hero = player;
    }

    public void play(){
        boolean play = true;
        String direction;
        createEnemies(hero.getLevel() * 5, hero.getLevel());
        createMap(hero.getLevel(), "*");
        mapPlayers();
        view.showMap(this.gameMap);
        while(play){
            view.showMoves();
            direction = input.getString();
            play = movePlayer(hero.getY(), hero.getX(), direction, "*");
            if (meetEnemy()){
                view.fightOrRun();
                int run = input.getInt();
                if (run == 1){
                    Random rand = new Random();
                    if (rand.nextInt(2) == 2)
                        if (fight())
                            System.out.println("You beat the Villain");
                } else
                    if (fight())
                        System.out.println("You beat the Villain");
                    else {
                        view.showLost();
                        input.writeHero(this.hero);
                        return;
                    }
            }
            view.showMap(this.gameMap);
        }
        if (win){
            input.writeHero(this.hero);
            view.showWin();
        }
    }

   public void createEnemies(int number, int level){
        Random random = new Random();
        Player enemy;
        boolean exist = false;
        int mapSize = (level-1)*5+10-(level%2);
        int x, y;
        int enemyCount = number;
        while(enemyCount > 0){
            x = random.nextInt(mapSize);
            y = random.nextInt(mapSize);
            for(Player p: enemies){
                if (p.getX() == x && y == p.getY()){
                    exist = true;
                    break;
                }
            }
            if (!exist){
                String playerClass = classes[random.nextInt(level)];
                int[] stats = getStats(playerClass);
                enemy = new Player(enemyNames[random.nextInt(5)], playerClass, stats[0], stats[1], stats[2], stats[3], stats[4]);
                enemy.setX(x);
                enemy.setY(y);
                enemies.add(enemy);
                enemyCount--;
            }
            exist = false;
        }
   }

    private int[] getStats(String playerClass) {
        switch (playerClass){
            case "Level1":
                return new int[]{1, 1000, 20, 15, 50};
            case "Level2":
                return new int[]{2, 2000, 25, 30, 100};
            case "Level3":
                return new int[]{3, 3000, 35, 30, 150};
            case "Level4":
                return new int[]{4, 4000, 40, 45, 200};
            case "Level5":
                return new int[]{5, 5000, 50, 45, 250};
            case "Level6":
                return new int[]{6, 6000, 55, 60, 300};
            default:
                return new int[]{7, 7000, 65, 60, 350};
        }
    }

    public void createMap(int level, String val){
        int mapSize = (level-1)*5+10-(level%2);
        this.gameMap = new String[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++ ){
            for(int j = 0; j < mapSize; j++){
                gameMap[i][j] = val;
            }
        }
    }

    public void mapPlayers(){
        placePlayer(hero.getY(), hero.getX(), 1);

        for(Player p: enemies){
            placePlayer(p.getY(), p.getX(), 2);
        }
    }

    public void placePlayer(int y, int x, int player){
        if (player == 1){
            gameMap[y][x] = "#";
        } else if(player == 2) {
            gameMap[y][x] = "@";
        }
    }

    public boolean movePlayer(int y, int x, String d, String val){
        int mapSize = gameMap.length - 1;
        if ((d.equalsIgnoreCase("N") && y - 1 < 0) ||
                (d.equalsIgnoreCase("E") && x + 1 > mapSize) ||
                (d.equalsIgnoreCase("W") && x - 1 < 0) ||
                (d.equalsIgnoreCase("S") && y + 1 > mapSize)){
            win = true;
            return false;
        }

        switch(d.toUpperCase()){
            case "N":
                gameMap[y][x] = val;
                gameMap[y-1][x] = "#";
                hero.setY(y-1);
                break;
            case "E":
                gameMap[y][x] = val;
                gameMap[y][x+1] = "#";
                hero.setX(x+1);
                break;
            case "W":
                gameMap[y][x] = val;
                gameMap[y][x-1] = "#";
                hero.setX(x-1);
                break;
            case "S":
                gameMap[y][x] = val;
                gameMap[y+1][x] = "#";
                hero.setY(y+1);
                break;
        }
        return true;
    }

    public boolean fight() {
        for(int i = 0; i < enemies.size(); i++){
            Player e = enemies.get(i);
            if (hero.getX() == e.getX() && hero.getY() == e.getY()){
                int levelup = e.getLevel()*1000+(int)(Math.pow(Double.parseDouble(String.valueOf(e.getLevel()-1)), 2))*450;
                if (hero.getAttack() > e.getAttack() && hero.getDefense() > e.getDefense()){
                    hero.setAttack(hero.getAttack() + 5);
                    hero.setDefense(hero.getDefense() + 5);
                    hero.setEx(hero.getEx() + (levelup/2));
                    hero.setHP(hero.getHP() + 20);
                    hero.setLevel(setHeroLevel(hero.getEx()));
                    enemies.remove(enemies.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    public int setHeroLevel(int exp){
        if (exp < 2450)
            return 1;
        else if (exp < 4800)
            return 2;
        else if (exp < 8050)
            return 3;
        else if (exp < 12200)
            return 4;
        else if (exp < 17250)
            return 5;
        else if (exp < 23200)
            return 6;
        else
            return 7;
    }

    public boolean meetEnemy(){
        for(Player e: enemies){
            if (hero.getX() == e.getX() && hero.getY() == e.getY()){
                return true;
            }
        }
        return false;
    }

    public void generateMap(){
        createEnemies(hero.getLevel() * 5, hero.getLevel());
        createMap(hero.getLevel(), "");
        mapPlayers();
    }
}
