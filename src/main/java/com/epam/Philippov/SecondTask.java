package com.epam.Philippov;

public class SecondTask {
    public static void main(String[] args) {
        try {
            int time = Integer.valueOf(args[0]);
            int lastPart = time % 10;

            switch (lastPart) {
                case 0:
                case 1:
                case 2:
                case 3:
                    System.out.println("green");
                    break;
                case 4:
                case 5:
                    System.out.println("yellow");
                    break;
                default:
                    System.out.println("red");

            }

        }catch (NumberFormatException e){
            System.out.println(e + " - not a number");
        }

    }
}
