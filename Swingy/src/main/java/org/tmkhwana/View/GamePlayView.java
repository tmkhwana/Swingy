package org.tmkhwana.View;

public class GamePlayView {
    public void showMap(String[][] map){
        for (String[] str : map){
            for (String v : str){
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public void showMoves(){
        System.out.println( "\n* * * * * * * * * * * * * * * *\n" +
                "* Please enter move direction *\n" +
                "*                             *\n" +
                "* 1. N for North              *\n" +
                "* 2. E for East               *\n" +
                "* 3. W for West               *\n" +
                "* 4. S for South              *\n" +
                "* * * * * * * * * * * * * * * *");
    }

    public void showWin(){
        System.out.println( "\n* * * * * * * * * * * * * * * * * * *\n" +
                "*                                   *\n" +
                "* Game Over, you have won           *\n" +
                "*                                   *\n" +
                "* Play again to increase you level  *\n" +
                "*                                   *\n" +
                "* * * * * * * * * * * * * * * * * * *");
    }

    public void fightOrRun() {
        System.out.println( "\n* * * * * * * * * * * * * * *\n" +
                            "*                           *\n" +
                            "* You met with the Villain  *\n" +
                            "*                           *\n" +
                            "* 1. Run                    *\n" +
                            "* 2. Fight                  *\n" +
                            "*                           *\n" +
                            "* * * * * * * * * * * * * * *");
    }

    public void showLost() {
        System.out.println( "\n* * * * * * * * * * * * * * * * * * *\n" +
                "*                                   *\n" +
                "* Game Over, you have lost          *\n" +
                "*                                   *\n" +
                "* Try again to increase you level   *\n" +
                "*                                   *\n" +
                "* * * * * * * * * * * * * * * * * * *");
    }
}
