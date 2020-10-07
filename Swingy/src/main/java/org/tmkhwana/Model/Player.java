package org.tmkhwana.Model;

import lombok.NonNull;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Setter
@Getter
public class Player {
    @NotNull(message="Player name cannot be blank")
    @Length(min=3, max=20, message="Player name must be between 3 and 20 in length")
    private String name;
    @NotNull(message= "Player class cannot be blank")
    private String playerClass;
    @Digits(integer=3, fraction=0, message="The value of level cannot be more than 3 digits")
    @Min(value=0, message="Min value of level cannot be less than 0")
    @Max(value=100, message="Max value of level cannot be more than 100")
    private int level;
    @Digits(integer=5, fraction=0, message="Experience cannot be more than 5 digits")
    @Min(value=0, message="Experience cannot be less than 0")
    private int ex;
    @Digits(integer=3, fraction=0, message="Attack cannot be more than 3 digits")
    @Min(value=0, message="Attack cannot be less than 0")
    private int attack;
    @Digits(integer=3, fraction=0, message="Defence cannot be more than 3 digits")
    @Min(value=0, message="Defence cannot be less than 0")
    private int defense;
    @Digits(integer=3, fraction=0, message="HP cannot be more than 3 digits")
    @Min(value=0, message="HP cannot be less than 0")
    private int HP;
    private int y;
    private int x;

    public Player(String name, String playerClass){
        this.name = name;
        this.playerClass = playerClass;
        this.level = 1;
        this.ex = 500;
        this.attack = attack();
        this.defense = defense();
        this.HP = 100;
        this.y = 4;
        this.x = 4;
    }

    public Player(String name, String playerClass, int level, int ex, int attack, int defense, int HP){
        this.name = name;
        this.playerClass = playerClass;
        this.level = level;
        this.ex = ex;
        this.attack = attack;
        this.defense = defense;
        this.HP = HP;
        this.y = heroDefaultPosition(level);
        this.x = heroDefaultPosition(level);
    }

    private int heroDefaultPosition(int level){
        int mapSize = (level-1)*5+10-(level%2);
        return (mapSize-1)/2;
    }

    private int attack(){
        Random rand = new Random();
        int[] attack = new int[]{10, 15, 20, 25};
        return attack[rand.nextInt(4)];
    }

    private int defense(){
        Random rand = new Random();
        int[] attack = new int[]{10, 15, 20, 25};
        return attack[rand.nextInt(4)];
    }
}
