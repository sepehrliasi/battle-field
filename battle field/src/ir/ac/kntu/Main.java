package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("How many soldiers for each side?");
        int num = input.nextInt();
        Controller controller = new Controller(num);
        System.out.println(controller.game());
    }
}
