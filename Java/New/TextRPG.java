package textrpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TextRPG {
    static boolean flag = false;

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        ArrayList<Character> goodGuys = new ArrayList<>();
        ArrayList<Character> badGuys = new ArrayList<>();
        
        String intention;
        
        System.out.println("enter party size");
        int size = scan.nextInt();
        for (int i = 0; i < size; i++){
            Character good = new Character(rand.nextInt(50) + 50, rand.nextInt(5), rand.nextInt(5), rand.nextInt(5), 1);
            goodGuys.add(good);
        }
        
        do {
            System.out.println("Enter \"battle\", \"skip\", or \"end\"");
            intention = scan.next();
            for (int i = 0; i < size; i++) {
                Character bad = new Character(rand.nextInt(50) + 50, rand.nextInt(5), rand.nextInt(5), rand.nextInt(5), goodGuys.get(0).level);
                badGuys.add(bad);
            }
            switch (intention) {
                case "battle":
                    Battle yes = new Battle(goodGuys, badGuys);
                    break;
                case "end":
                    flag = true;
                    break;
                case "skip":
                    break;
                default:
                    System.out.println("retype your request");
                    break;
            }
        }
        while (!flag);
        System.out.println("end of game");
        scan.close();
    }
}
