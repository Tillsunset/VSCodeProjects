package textrpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    ArrayList<Character> goodGuys;
    ArrayList<Character> badGuys;
    ArrayList<Character> total;
    Character first;
    Character second;
    Character temp;
    String intention;
    boolean tieBreak;
    boolean isUsed;
    int expGain = 0;
    int who;

    public Battle(ArrayList<Character> a, ArrayList<Character> b) {
        System.out.println("Battle has started");

        goodGuys = a;
        badGuys = b;
        
        while ((IsAlive(goodGuys) && IsAlive(badGuys))) {
            int z = 0;
            int w = 0;
            for (Character cha : goodGuys) {
                Turns(cha, z);
                z++;
            }
            for (Character cha : badGuys) {
                EnemyTurns(cha, w);
                w++;
            }
        }
        
        if (!IsAlive(badGuys)) {
            for (Character cha : badGuys) {
                expGain += 2*(cha.level + cha.maxHealth);
            }

            System.out.println("\nBattle Finished");
            for (int i = 0; i < goodGuys.size(); i++) {
                temp = goodGuys.get(i);
                temp.expGain(expGain);
                goodGuys.remove(i);
                goodGuys.add(i, temp);
            }
            first.expGain(expGain);
        } else {
            System.out.println("brah you lost");
            TextRPG.flag = true;
        }
    }
    
    private void Turns (Character first, int z) {
            System.out.println("Would character " + (z+1) + " like to check their \"stats\", the \"enemies\", \"attack\", or \"super attack\"");

            do {
                System.out.println("Enter below");
                intention = scan.next();
            } while (!intention.equals("stats") && !intention.equals("attack") && !intention.equals("enemies") && !intention.equals("super attack"));

            switch (intention) {
                case "super attack":
                    System.out.println("Enter which enemey (1,2,3...)");
                    second = badGuys.get(scan.nextInt());
                    if (second.health < 0) {
                        System.out.println("can not attack that enemy, he's already dead");
                    } else {
                        if (!isUsed) {
                            Attack(first, second, true);
                            isUsed = true;
                        } else {
                            System.out.println("super attack has already been used");
                        }
                    }
                    break;
                case "attack":
                    System.out.println("Enter which enemey (1,2,3...)");
                    second = badGuys.get(scan.nextInt());
                    if (second.health < 0) {
                        System.out.println("can not attack that enemy, he's already dead");
                    } else {
                        Attack(first, second, false);
                    }
                    break;
                case "enemies":
                    int y = 1;
                    for (Character bad : badGuys) {
                        bad.EnemyStats();
                        y++;
                    }
                    break;
                case "stats":
                    int x = 1;
                    for (Character good : goodGuys) {
                        System.out.print("\n Player " + x +" has ");
                        good.Stats();
                        x++;
                    }
            
        }
    }
    
    private void EnemyTurns(Character first, int z) {
        second = goodGuys.get(rand.nextInt(goodGuys.size()));
        GetAttack(first, second);
    }

    private void Attack(Character first, Character second, Boolean bonus) {
        int x = (10 + first.strength - second.defense + rand.nextInt(10));
        if (bonus){
            x = 2*x;
        }
        second.health -= x;
        if (x <= 0) {
            x = 0;
        }
        System.out.println("they have taken " + x + " damage\n"); 
    }

    private void GetAttack(Character first, Character second) {
        int x = (10 + second.strength - first.defense + rand.nextInt(10));
        first.health -= x;
        if (x <= 0) {
            x = 0;
        } else {
            TextRPG.flag = true;
        }
        System.out.println("you have taken " + x + " damage");
    }

    private void TieBreak(Character first, Character second) {
        if (first.speed == second.speed) {
            tieBreak = rand.nextBoolean();

            if (tieBreak) {
                first.speed++;
            } else {
                second.speed++;
            }
        }
    }

    private void FixTieBreak(Character first, Character second) {
        if (tieBreak) {
            first.speed--;
        } else {
            second.speed--;
        }
    }
    
    private boolean IsAlive(ArrayList<Character> a){
        for (Character cha : a) {
            if (cha.health > 0){
                return true;
            }
        }
        return false;
    }
}
